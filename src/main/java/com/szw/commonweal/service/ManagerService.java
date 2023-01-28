package com.szw.commonweal.service;

import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.entity.views.AdminPublishActivity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户身份证检测业务接口
 * @author  史泽文
 * */
public interface ManagerService {
    /**
     * 提供管理员名单(用于注册)
     * */
    public List<Object> getManagerInfo();

    /**
     * 管理员名称检测是否存在
     * */
    public ResultInfo<String> userNameCheck(String userName);

    /**
     * 管理员邮箱检测是否存在
     * */
    public ResultInfo<String> EmailCheck(String email);

    /**
     * 管理员账号登录
     * */
    public ResultInfo<String> checkLogin(String adminId, String passwd);

    /**
     * 管理员邮箱登录
     * */
    public ResultInfo<String> checkEmailLogin(String adminId, String passwd);

    /**
     * 管理员个人页面展示(DONE)*
     *
     * @return*/
    public List<Map<String, Object>> getOneManagerInfo(String userId);

    /**
     * 管理员身份证检测重复或是否存在
     * */
    public ResultInfo<String> idCheck(String idCard);

    /**
     * 管理员修改密码(忘记密码)
     * */
    public ResultInfo<String> changePasswd_F(String idCard,String passwd,String fLaseCode);

    /**
     * 管理员修改密码*
     * */
    public ResultInfo<String> changePasswd(String adminId,String passwd);

    /**
     * 管理员修改QQ邮箱*
     * */
    public ResultInfo<String> changEmail(String adminName,String email);

    /**
     * 管理员修改手机号*
     * */
    public ResultInfo<String> changTelephone(String adminName,String telephone);

    /**
     * 管理员审核注册的志愿者*
     * */
    public ResultInfo<String> verifyVolunteers(String userId);

    /**
     * 自动清除不合法的志愿者
     * */
    public ResultInfo<String> cleanVolunteers(String userId);

    /**
     * 获取注册的志愿者
     * */
    public List getVolunteers();

    /**
     * 管理员上传活动图片
     * */
    public ResultInfo<String> uploadActivePic(MultipartFile picture) throws Exception;

    /**
     * 管理员发布志愿活动
     * */
    public ResultInfo<String> publishActivity(AdminPublishActivity activity);

}
