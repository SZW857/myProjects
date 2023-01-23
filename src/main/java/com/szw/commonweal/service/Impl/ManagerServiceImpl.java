package com.szw.commonweal.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.szw.commonweal.dao.DistinctMapper;
import com.szw.commonweal.dao.GetVolunteerMapper;
import com.szw.commonweal.dao.MangerMapper;
import com.szw.commonweal.entity.Manager;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.views.EmailAndTelephone;
import com.szw.commonweal.entity.views.GetVolunteers;
import com.szw.commonweal.service.ManagerService;
import com.szw.commonweal.utils.Base64;
import com.szw.commonweal.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        QueryWrapper<EmailAndTelephone> distinct = new QueryWrapper<>();
        distinct.select("email").eq("email",email);
        List<EmailAndTelephone> list = distinctMapper.selectList(distinct);
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
        QueryWrapper<EmailAndTelephone> wrapper1 = new QueryWrapper<>();
        wrapper1.select("telephone").eq("telephone",telephone);
        List<EmailAndTelephone> list = distinctMapper.selectList(wrapper1);
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
//            System.out.println("EMAILREALCODE的typpe"+EMAILREALCODE.getClass().getName());
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
    public List<Map<String, Object>> getOneManagerInfo(String adminId) {
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.select("telephone","email").eq("admin_name",adminId);
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
        wrapper.select("admin_name","id_card","email")
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
            res.setExtra(list.get(0).getAdminName());
            res.setID(list.get(0).getIdCard());
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
        wrapper.select("admin_name","id_card")
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
            res.setExtra(adminName);
            res.setID(list.get(0).getIdCard());
            return res;
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("登陆失败");
            return res;
        }
    }
}


