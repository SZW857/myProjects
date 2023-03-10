package com.szw.commonweal.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szw.commonweal.dao.*;

import com.szw.commonweal.entity.*;

import com.szw.commonweal.entity.views.DistinctInformation;
import com.szw.commonweal.entity.views.GetSignIn;
import com.szw.commonweal.entity.views.GetVolunteers;
import com.szw.commonweal.service.ManagerService;
import com.szw.commonweal.utils.Base64;
import com.szw.commonweal.utils.JwtUtils;
import com.szw.commonweal.utils.UploadUtil;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.szw.commonweal.utils.EphemeralCode.EMAILREALCODE;
import static com.szw.commonweal.utils.EphemeralCode.TELEPHONEREALCODE;


@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private MangerMapper mangerMapper;
    @Autowired
    private GetVolunteerMapper getVolunteerMapper;
    @Autowired
    private DistinctMapper distinctMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private GetSignInMapper getSignInMapper;
    @Autowired
    private VolunteerMapper volunteerMapper;
    @Autowired
    private AttendanceMapper attendanceMapper;
    @Autowired
    private HelpMapper helpMapper;


    /**
     * 提供管理员名单(注册使用)
     * */
    @Override
    public List getManagerInfo() {
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.select("admin_name","id_card");
        List<Map<String, Object>> mapList = mangerMapper.selectMaps(wrapper);
        System.out.println(mapList);
        return mapList;
    }


    /**
     * 通过志愿者的审核(DONE)
     * */
    @Override
    public ResultInfo<String> verifyVolunteers(String userId) {
        ResultInfo<String> res = new ResultInfo<>();
        UpdateWrapper<GetVolunteers> wrapper = new UpdateWrapper<>();
        wrapper.set("verify_status",1).eq("user_id",userId);
        int i = getVolunteerMapper.update(null, wrapper);
        if (i==1){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("操作成功,审核通过");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("审核不通过");
        }
        return res;
    }

    /**
     * 自动清除不合法的志愿者
     * */
    @Override
    public ResultInfo<String> cleanVolunteers(String userId) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<GetVolunteers> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        int i = getVolunteerMapper.delete(wrapper);
        if (i==1){
            res.setStatus(ResultInfo.SUCCESS);
        }else{
            res.setStatus(ResultInfo.FAIL);
        }
        return res;
    }

    /**
     * 通过志愿者的求助审核并发布
     * */
    @Override
    @Transactional
    @Synchronized
    public ResultInfo<Object> passedVolunteerHelpInfo(HttpServletRequest request) {
        ResultInfo<Object> res = new ResultInfo<>();
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.eq("help_num",request.getParameter("helpNum"));
        int i = helpMapper.delete(wrapper);
        if (i==1){
            ResultInfo<String> resultInfo = publishProject(request);
            if (resultInfo.getCode()==200){
                res.setStatus(ResultInfo.SUCCESS);
                res.setData("审核通过,求助信息发布成功");
            }else {
                res.setData("求助信息发布失败");
            }
        }

        return res;
    }

    /**
     * 获取志愿者求助信息
     * */
    @Override
    public ResultInfo<Object> getVolunteerHelpInformation() {
        ResultInfo<Object> res = new ResultInfo<>();
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        List<Help> list = helpMapper.selectList(wrapper);
        if (!list.isEmpty()){
            res.setData(list);
            res.setStatus(ResultInfo.SUCCESS);
        }else {
            res.setStatus(ResultInfo.FAIL);
        }
        return res;
    }

    /**
     * 通过志愿者的签到审核
     * */
    @Override
    @Transactional
    @Synchronized
    public ResultInfo<String> passedVolunteerSignIn(String userId, Long serialNum) {
        ResultInfo<String> res = new ResultInfo<>();
        UpdateWrapper<Attendance> wrapper = new UpdateWrapper<>();
        UpdateWrapper<Volunteer> wrapper1 = new UpdateWrapper<>();
        wrapper.set("status",1).eq("user_id",userId).eq("serial_num",serialNum);
        int i = attendanceMapper.update(null, wrapper);
        if (i==1){
            int update = volunteerMapper.passedVolunteer(userId);
            if (update==1){
                res.setStatus(ResultInfo.SUCCESS);
                res.setData("通过了签到,签到数+1,状态改为1");
            }else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("状态修改成功但签到失败数据库崩溃");
            }
            return res;
        }else {
            res.setStatus(ResultInfo.FAIL);

            return null;
        }
    }

    /**
     *分页获取志愿者签到申请
     * */
    @Override
    public ResultInfo<Object> getSignInItems(int currentPage, int pageSize) {
        QueryWrapper<GetSignIn> wrapper = new QueryWrapper<>();
        wrapper.select("*").eq("status",0);
        ResultInfo<Object> res = new ResultInfo<>();
        Page<GetSignIn> page = new Page<>(currentPage,pageSize);
        Page<GetSignIn> mapsPage = getSignInMapper.selectPage(page, wrapper);
        long total = mapsPage.getTotal();
        List<GetSignIn> list = mapsPage.getRecords();
        System.out.println("实体类数据"+list);
        if (!list.isEmpty()){
            res.setData(list);
            res.setTotal(total);
            res.setStatus(ResultInfo.SUCCESS);
            res.setCode(200);
        }else {
            res.setData("获取志愿者签到信息失败");
        }
        return res;
    }

    /**
     * 管理员发布志愿活动
     * */
    @Override
    public ResultInfo<String> publishProject(HttpServletRequest request) {
        Project project = new Project();
        String startDate = request.getParameter("startDate");
        String finishDate = request.getParameter("finishDate");
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = ft.parse(startDate);
            date2 = ft.parse(finishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql_date1 = new java.sql.Date(date1.getTime());
        java.sql.Date sql_date2 = new java.sql.Date(date2.getTime());

        project.setStartDate(sql_date1);
        project.setFinishDate(sql_date2);
        project.setContact(request.getParameter("contact"));
        project.setEmail(request.getParameter("email"));
        project.setAdminId(request.getParameter("adminId"));
        project.setRemaining(Integer.valueOf(request.getParameter("remaining")));
        project.setTitle(request.getParameter("title"));
        project.setImageUrl(request.getParameter("imageUrl"));
        project.setTelephone(request.getParameter("telephone"));
        project.setContent(request.getParameter("content"));
        project.setPeopleNum(Integer.valueOf(request.getParameter("peopleNum")));
        project.setType(request.getParameter("type"));
        ResultInfo<String> res = new ResultInfo<>();
        int i = projectMapper.insert(project);
        System.out.println("插入结果"+i);
        if (i==1){
            res.setCode(200);
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("发布失败");
        }
        return res;
    }

    /**
     * 管理员上传活动图片
     * */
    @Override
    public ResultInfo<String> uploadActivePic(MultipartFile picture) throws Exception{
        return UploadUtil.uploadImage(picture);
    }

    /**
     * 获取注册的志愿者(DONE)
     * */
    @Override
    public List getVolunteers() {
        QueryWrapper<GetVolunteers> wrapper = new QueryWrapper<>();
        wrapper.select("*").eq("verify_status",0);
        List<GetVolunteers> list = getVolunteerMapper.selectList(wrapper);
        return list;
    }

    /**
     * 管理员邮箱检测
     * */
    @Override
    public ResultInfo<String> EmailCheck(String email) {
        ResultInfo<String> res=new ResultInfo<>();
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        List<Manager> list = mangerMapper.selectList(wrapper);
        System.out.println("集合为："+list);
        System.out.println("用户名本次的参数：："+email);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("邮箱已存在");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("邮箱不存在");
        }
        return res;
    }

    /**
     * 管理员登录名检测
     * */
    @Override
    public ResultInfo<String> userNameCheck(String adminName) {
        ResultInfo<String> res=new ResultInfo<>();
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_name",adminName);
        List<Manager> list = mangerMapper.selectList(wrapper);
        System.out.println("集合为："+list);
        System.out.println("用户名本次的参数：："+adminName);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("用户名已存在");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("用户名不存在");
        }
        return res;
    }

    /**
     * 用户身份证号检测(DONE)
     * */
    @Override
    public ResultInfo<String> idCheck(String idCard) {
        ResultInfo<String> res=new ResultInfo<>();
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.select("id_card").eq("id_card",idCard);
        List<Manager> list = mangerMapper.selectList(wrapper);
        System.out.println("集合为："+list);
        System.out.println("本次的参数：："+idCard);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("身份证号存在");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("身份证号不存在");
        }
        return res;
    }

    /**
     * 用户修改密码(DONE)*
     * */
    @Transactional
    @Override
    public ResultInfo<String> changePasswd(String adminId, String passwd) {
        ResultInfo<String> res = new ResultInfo<>();
        String X= Base64.unLock(passwd);
        UpdateWrapper<Manager> wrapper = new UpdateWrapper<>();
        wrapper.eq("id_card",adminId).set("passwd",X);
        int i = mangerMapper.update(null, wrapper);
        if (i==1){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("管理员修改密码成功");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("管理员修改密码失败");
        }
        return res;
    }

    /**
     * 用户修改邮箱*(DONE)
     * */
    @Override
    public ResultInfo<String> changEmail(String adminName, String email) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<DistinctInformation> distinct = new QueryWrapper<>();
        distinct.select("email").eq("email",email);
        List<DistinctInformation> list = distinctMapper.selectList(distinct);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.FAIL);
            res.setData("邮箱已经存在");
            return res;
        }else {
            UpdateWrapper<Manager> wrapper = new UpdateWrapper<>();
            wrapper.set("email",email).eq("admin_name",adminName);
            int update = mangerMapper.update(null, wrapper);
            if (update==1){
                res.setStatus(ResultInfo.SUCCESS);
                res.setData("邮箱修改成功");
            }else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("邮箱修改失败");
            }
            return res;
        }

    }

    /**
     * 用户修改手机号(DONE)*
     * */
    @Override
    @Transactional
    public ResultInfo<String> changTelephone(String adminName, String telephone) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<DistinctInformation> wrapper1 = new QueryWrapper<>();
        wrapper1.select("telephone").eq("telephone",telephone);
        List<DistinctInformation> list = distinctMapper.selectList(wrapper1);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.FAIL);
            res.setData("该手机号已经注册");
        }else {
            UpdateWrapper<Manager> wrapper = new UpdateWrapper<>();
            wrapper.eq("admin_name",adminName).set("telephone",telephone);
            int update = mangerMapper.update(null,wrapper);
            if (update==1){
                res.setStatus(ResultInfo.SUCCESS);
                res.setData("手机号修改成功");
            }else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("手机号修改失败");
            }
        }
        return res;
    }

    /**
     * 用户修改密码(忘记密码)(DONE)
     * */
    @Override
    public ResultInfo<String> changePasswd_F(String adminId,String passwd,String falseCode) {
        ResultInfo<String> res = new ResultInfo<>();
        System.out.println("管理员身份证"+adminId);
        System.out.println("管理员的密码"+passwd);
        System.out.println("加密的密码:"+falseCode);
        String X=Base64.unLock(falseCode);
        System.out.println("解密的密码:"+X);
        //验证
        if (TELEPHONEREALCODE !=""||EMAILREALCODE!=""){
            System.out.println("手机1真实验证码"+TELEPHONEREALCODE);
            System.out.println("邮件1真实验证码"+EMAILREALCODE);
            System.out.println("结果"+EMAILREALCODE.equals(X));
        if (TELEPHONEREALCODE.equals(X) || EMAILREALCODE.equals(X)){
            //清除缓存验证码
            TELEPHONEREALCODE="";
            EMAILREALCODE="";
            UpdateWrapper<Manager> wrapper = new UpdateWrapper<>();
            wrapper.set("passwd",passwd).eq("id_card",adminId);
            int update = mangerMapper.update(null, wrapper);
            if (update==1){
                res.setStatus(ResultInfo.SUCCESS);
                res.setData("密码修改成功");
            }else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("密码修改失败");
            }
        }else {
            System.out.println("手机真实验证码"+TELEPHONEREALCODE);
            System.out.println("邮件真实验证码"+EMAILREALCODE);
            res.setStatus(ResultInfo.FAIL);
            res.setData("修改密码失败,后台解码操作失败");
          }
            //没有发验证码的非法请求
            res.setStatus(ResultInfo.FAIL);
            res.setData("修改密码失败,未通过发送校验码非法路径操作");
        }
           return res;
    }

    /**
     * 管理员个人页面展示(DONE)*
     *
     * @return*/
    @Override
    public List<Map<String, Object>> getOneManagerInfo(String adminName) {
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.select("telephone","email").eq("admin_name",adminName);
        List<Map<String, Object>> maps = mangerMapper.selectMaps(wrapper);
        return maps;
    }

    /**
     * 管理员邮箱登录
     * */
    @Override
    public ResultInfo<String> checkEmailLogin(String email, String passwd) {
        ResultInfo<String> res=new ResultInfo<>();
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("email",email)
                .eq("passwd",passwd);
        List<Manager> list = mangerMapper.selectList(wrapper);
        System.out.println("service层集合为："+list);
        System.out.println("service层的参数：："+email);
        System.out.println("service层的参数：："+passwd);
        HashMap<String, String> map = new HashMap<>();
        if (!list.isEmpty()){
            map.put("id",list.get(0).getAdminName());
            map.put("pwd",passwd);
            String token = JwtUtils.setToken(map);
            res.setStatus(ResultInfo.SUCCESS);
            res.setData(token);
            res.setID(list.get(0).getIdCard());
            res.setAdminName(list.get(0).getAdminName());
            res.setEmail(list.get(0).getEmail());
            res.setTelephone(list.get(0).getTelephone());
            return res;
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("登陆失败");
            return res;
        }
    }

    /**
     * 管理员账号登录
     * */
    @Override
    public ResultInfo<String> checkLogin(String adminName, String passwd) {
        ResultInfo<String> res=new ResultInfo<>();
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("admin_name",adminName)
                .eq("passwd",passwd);
        List<Manager> list = mangerMapper.selectList(wrapper);
        System.out.println("service层集合为："+list);
        System.out.println("service层的参数：："+adminName);
        System.out.println("service层的参数：："+passwd);
        HashMap<String, String> map = new HashMap<>();
        if (!list.isEmpty()){
            map.put("id",adminName);
            map.put("pwd",passwd);
            String token = JwtUtils.setToken(map);
            res.setStatus(ResultInfo.SUCCESS);
            res.setData(token);
            res.setID(list.get(0).getIdCard());
            res.setAdminName(adminName);
            res.setEmail(list.get(0).getEmail());
            res.setTelephone(list.get(0).getTelephone());
            return res;
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("登陆失败");
            return res;
        }
    }



}


