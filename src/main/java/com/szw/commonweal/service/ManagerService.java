package com.szw.commonweal.service;

import com.szw.commonweal.entity.Manager;
import com.szw.commonweal.entity.Volunteer;

import java.util.List;

/**
 * 用户身份证检测业务接口
 * @author  史泽文
 * */
public interface ManagerService {
    /**
     * 用户身份证检测重复或是否存在
     * */
    public List<Manager> getManager();

}
