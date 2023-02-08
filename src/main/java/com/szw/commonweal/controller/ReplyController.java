package com.szw.commonweal.controller;

import com.szw.commonweal.entity.Reply;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    /**
     * 删除子级评论
     * */
    @CrossOrigin
    @RequestMapping("/delChildrenContent")
    public ResultInfo<String> delChildrenContent(@RequestParam("replyNum")Long replyNum){
        return replyService.delChildrenRemark(replyNum);
    }

    /**
     * 回复评论
     * */
    @CrossOrigin
    @RequestMapping("/replyContent")
    public ResultInfo<String> replyContent(Reply reply){
        return replyService.replyRemark(reply);
    }

    /**
     * 获取特定子级评论
     * */
    @CrossOrigin
    @RequestMapping("/getSpecificContent")
    public List<Reply> getSpecificContent(@RequestParam("remarkNum")String remarkNum){
        return replyService.getSpecificChildrenContent(remarkNum);
    }
}
