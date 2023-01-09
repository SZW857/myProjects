package com.szw.commonweal.controller;

import com.szw.commonweal.entity.Manager;
import com.szw.commonweal.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @ResponseBody
    @RequestMapping("/adminInfo")
    public List<Manager> getAdminInfo(){
        return managerService.getManager();
    }
}
