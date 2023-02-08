package com.szw.commonweal.controller;

import com.szw.commonweal.entity.Remark;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/remark")
public class RemarkController {
    @Autowired
    private RemarkService remarkService;

    /**
     * 所有人发表言论的接口
     * */
    @CrossOrigin
    @RequestMapping("/allPeople")
    public ResultInfo<String> remark(HttpServletRequest request){
        return remarkService.remarking(request);
    }

    /**
     * 获取父级评论的接口
     * */
    @CrossOrigin
    @RequestMapping("/getParentContent")
    public List<Remark> getParentContent(){
        return remarkService.getParentRemark();
    }

    /**
     * 删除父级评论的接口
     * */
    @CrossOrigin
    @RequestMapping("/delParentComments")
    public ResultInfo<String> delParentComments(@PathParam("remarkNum")Long remarkNum){
        return remarkService.delParentRemark(remarkNum);
    }

}
