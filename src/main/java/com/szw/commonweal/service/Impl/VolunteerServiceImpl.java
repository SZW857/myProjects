package com.szw.commonweal.service.Impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.szw.commonweal.dao.UserMapper;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.Volunteer;
import com.szw.commonweal.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VolunteerServiceImpl  implements VolunteerService {
    @Autowired
    private UserMapper userMapper;

    ResultInfo<String> res = new ResultInfo<>();
    QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
    /**
     * 用户登录名检测
     * */
    @Override
    public ResultInfo<String> userNameCheck(String userId) {
        QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Volunteer> list = userMapper.selectList(wrapper);
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
     * 用户身份证号检测
     * */
    @Override
    public ResultInfo<String> idCheck(String idCard) {
        QueryWrapper<Volunteer> wrapper = new QueryWrapper<>();
        wrapper.eq("id_card",idCard);
        List<Volunteer> list = userMapper.selectList(wrapper);
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
     * 用户修改密码
     * */
    @Override
    public ResultInfo<String> changePasswd(String passwd,String idCard) {
        ResultInfo<String> res = new ResultInfo<>();
        UpdateWrapper<Volunteer> wrapper = new UpdateWrapper<>();
        wrapper.set("passwd",passwd).eq("id_card",idCard);
        int update = userMapper.update(null, wrapper);
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
     * 用户登录
     * */
    @Override
    public ResultInfo<String> checkLogin(String userId,String passwd) {
            wrapper.select("user_id")
                    .eq("user_id",userId)
                    .eq("passwd",passwd);
            List<Volunteer> list = userMapper.selectList(wrapper);
            System.out.println("service层集合为："+list);
            System.out.println("service层的参数：："+passwd);
            if (!list.isEmpty()){
                res.setStatus("success");
                return res;
            }else {
                res.setStatus("fail");
                return res;
            }
    }

    /**
     * 返回用户登录信息
     * */
    @Override
    public ResultInfo<String> backUserInfo() {

        res.setStatus(ResultInfo.SUCCESS);

        res.setExtra("我是放行后的结果");
        return res;
    }

    /**
     * 用户注册业务
     * */
    @Override
    public ResultInfo<String> userRegister(Volunteer volunteer) {

        int i = userMapper.insert(volunteer);
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

}
