package com.szw.commonweal.controller;
import com.szw.commonweal.entity.ResultInfo;
import org.springframework.web.bind.annotation.*;

@RestController
public class token {

    /**
     * 用于测试的接口 (DONE)
     * */
    @CrossOrigin
    @RequestMapping("/measure")
    public ResultInfo<Object> hello(){
        ResultInfo<Object> res = new ResultInfo<>();
        res.setData("ok");
        return res;
    }
}
