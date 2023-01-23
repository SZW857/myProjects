package com.szw.commonweal.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.szw.commonweal.dao.DistinctMapper;
import com.szw.commonweal.dao.VolunteerMapper;
import com.szw.commonweal.entity.views.EmailAndTelephone;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.service.VolunteerService;
import com.szw.commonweal.utils.Base64;
import com.szw.commonweal.utils.EphemeralCode;
import com.szw.commonweal.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.szw.commonweal.utils.EphemeralCode.EMAILREALCODE;
import static com.szw.commonweal.utils.EphemeralCode.TELEPHONEREALCODE;

@Transactional
@Service
public class VolunteerServiceImpl  implements VolunteerService {
    @Autowired
    private VolunteerMapper volunteerMapper;
    @Autowired
    private DistinctMapper distinctMapper;



    /**
     * 用户Email检测(DONE)
     * */
    @Override
    public ResultInfo<String> emailCheck(String email) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<EmailAndTelephone> wrapper = new QueryWrapper<>();
        wrapper.select("email").eq("email",email);
        List<EmailAndTelephone> list = distinctMapper.selectList(wrapper);
        System.out.println("集合为："+list);
        System.out.println("用户名本次的参数：："+email);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("该Email已存在");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("该Email可用");
        }
        return res;
    }

    /**
     * 用户登录名检测(DONE)
     * */
    @Override
    public ResultInfo<String> userNameCheck(String userId) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
        wrapper.select("user_id").eq("user_id",userId);
        List<Volunteer> list = volunteerMapper.selectList(wrapper);
        System.out.println("集合为："+list);
        System.out.println("用户名本次的参数：："+userId);
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
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<EmailAndTelephone> wrapper = new QueryWrapper<>();
        wrapper.select("id_card").eq("id_card",idCard);
        List<EmailAndTelephone> list = distinctMapper.selectList(wrapper);
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
     * 用户手机号检测(DONE)
     * */
    @Override
    public ResultInfo<String> telephoneCheck(String telephoneNum) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<EmailAndTelephone> wrapper = new QueryWrapper<>();
        wrapper.select("telephone").eq("telephone",telephoneNum);
        List<EmailAndTelephone> list = distinctMapper.selectList(wrapper);
        System.out.println("集合为："+list);
        System.out.println("本次的参数：："+telephoneNum);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("手机号已存在");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("手机号可注册");
        }
        return res;
    }

    /**
     * 用户修改密码(忘记密码)(DONE)
     * */
    @Override
    public ResultInfo<String> changePasswd(String idCard,String passwd,String falseCode) {
        ResultInfo<String> res = new ResultInfo<>();
        String X= Base64.unLock(falseCode);
        if (TELEPHONEREALCODE!=""||EMAILREALCODE!=""){
            if (TELEPHONEREALCODE.equals(X)||EMAILREALCODE.equals(X)){
                EphemeralCode.TELEPHONEREALCODE="";
                EphemeralCode.EMAILREALCODE="";
                UpdateWrapper<Volunteer> wrapper = new UpdateWrapper<>();
                wrapper.set("passwd",passwd).eq("id_card",idCard);
                int update = volunteerMapper.update(null, wrapper);
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
        }else {
            //没有发验证码的非法请求
            res.setStatus(ResultInfo.FAIL);
            res.setData("修改密码失败,未通过发送校验码非法路径操作");
        }
        return res;
    }

    /**
     * 用户修改邮箱*(DONE)
     * */
    @Override
    public ResultInfo<String> changEmail(String userId, String email) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<EmailAndTelephone> distinct = new QueryWrapper<>();
        distinct.select("email").eq("email",email);
        List<EmailAndTelephone> list = distinctMapper.selectList(distinct);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.FAIL);
            res.setData("邮箱已经存在");
            return res;
        }else {
            UpdateWrapper<Volunteer> wrapper = new UpdateWrapper<>();
            wrapper.set("email",email).eq("user_id",userId);
            int update = volunteerMapper.update(null, wrapper);
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
     * 用户保存信息*
     * */
    @Override
    public ResultInfo<String> saveInformation(String userId,String address, String sex, int age) {
        ResultInfo<String> res = new ResultInfo<>();
        UpdateWrapper<Volunteer> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id",userId).set("address",address).set("age",age).set("sex",sex);
        int i = volunteerMapper.update(null, wrapper);
        if (i==1){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("保存成功");
        }else{
            res.setStatus(ResultInfo.FAIL);
            res.setData("保存失败");
        }
        return res;
    }

    /**
     * 用户修改手机号(DONE)*
     * */
    @Override
    @Transactional
    public ResultInfo<String> changTelephone(String userId, String telephone) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<EmailAndTelephone> wrapper1 = new QueryWrapper<>();
        wrapper1.select("telephone").eq("telephone",telephone);
        List<EmailAndTelephone> list = distinctMapper.selectList(wrapper1);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.FAIL);
            res.setData("该手机号已经注册");
        }else {
            UpdateWrapper<Volunteer> wrapper = new UpdateWrapper<>();
            wrapper.eq("user_id",userId).set("telephone",telephone);
            int update = volunteerMapper.update(null,wrapper);
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
     * 用户修改密码*(DONE)
     * */
    @Override
    public ResultInfo<String> changPasswd_P(String userId, String passwd) {
        ResultInfo<String> res = new ResultInfo<>();
        UpdateWrapper<Volunteer> wrapper = new UpdateWrapper<>();
        wrapper.set("passwd",passwd).eq("user_id",userId);
        int update = volunteerMapper.update(null, wrapper);
        if (update==1){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("密码修改成功");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("密码修改失败");
        }
        return res;
    }

    /**
     * 用户登录*(DONE)
     * */
    @Override
    public ResultInfo<String> checkLogin(String userId,String passwd) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
            wrapper.select("*")
                    .eq("passwd",passwd);
            List<Volunteer> list = volunteerMapper.selectList(wrapper);
            System.out.println("service层集合为："+list);
            System.out.println("service层的参数：："+userId);

        HashMap<String, String> map = new HashMap<>();
            if(!list.isEmpty()){
                if (list.get(0).getVerifyStatus()!=0){
                    map.put("id",userId);
                    map.put("pwd",passwd);
                    String token = JwtUtils.setToken(map);
                    res.setStatus(ResultInfo.SUCCESS);
                    res.setData(token);
                    res.setExtra(userId);
                }else {
                    res.setStatus("NoPassed");
        System.out.println("service层的参数1：："+list.get(0).getVerifyStatus());
                    res.setData("暂未通过管理员审核!!!,请等待...");
                }
            }else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("登陆失败");
            }
                return res;
    }

    /**
     * 用户注册业务(DONE)
     * */
    @Override
    public ResultInfo<String> userRegister(Volunteer volunteer) {
        ResultInfo<String> res = new ResultInfo<>();
        int i = volunteerMapper.insert(volunteer);
        System.out.println("本次的参数:"+i);
        if (i==1){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("注册正确");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("注册失败");
        }
        return res;
    }

    /**
     * 个人页面展示*(DONE)
     * */
    @Override
    public List PersonInfo(String userId) {
        QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
        wrapper.select("age","telephone","email","address","sex","sign_num","verify_status").eq("user_id",userId);
        List<Map<String, Object>> list = volunteerMapper.selectMaps(wrapper);
        return list;
    }

}
