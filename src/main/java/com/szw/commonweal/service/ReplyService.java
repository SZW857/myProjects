package com.szw.commonweal.service;

import com.szw.commonweal.entity.Reply;
import com.szw.commonweal.entity.ResultInfo;

import java.util.List;

public interface ReplyService {



    /**
     * 回复评论
     * */
    public ResultInfo<String> replyRemark(Reply reply);

    /**
     * 获取特定子级评论
     * */
    public List<Reply> getSpecificChildrenContent(String remarkNum);

    /**
     *删除子级评论
     * */
    public ResultInfo<String> delChildrenRemark(Long remarkNum);

}
