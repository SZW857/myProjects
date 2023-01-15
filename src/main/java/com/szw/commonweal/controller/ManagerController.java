package com.szw.commonweal.controller;


import com.szw.commonweal.entity.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/admin")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/selectAdminInfo")
    public List getAdminInfo(){
        return managerService.getManagerInfo();
    }
}
