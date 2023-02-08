package com.szw.commonweal.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szw.commonweal.dao.ReplyMapper;
import com.szw.commonweal.entity.Reply;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReplyServiceImpl implements ReplyService {

   @Autowired
   private ReplyMapper replyMapper;



    /**
     * 回复评论
     * */
    @Override
    public ResultInfo<String> replyRemark(Reply reply) {
        ResultInfo<String> res = new ResultInfo<>();
        int i = replyMapper.insert(reply);
        if (i==1){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("回复评论成功");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("回复评论失败");
        }
        return res;
    }


    /**
     * 获得特定的子级评论
     * */
    @Override
    public List<Reply> getSpecificChildrenContent(String remarkNum) {
        QueryWrapper<Reply> wrapper = new QueryWrapper<>();
        wrapper.select("*").eq("remark_num",remarkNum);
        List<Reply> list = replyMapper.selectList(wrapper);
        return list;
    }

    /**
     *删除子级评论)
     * */
    @Override
    public ResultInfo<String> delChildrenRemark(Long replyNum) {
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<Reply> wrapper = new QueryWrapper<>();
        wrapper.select("reply_num").eq("reply_num",replyNum);
        int i = replyMapper.delete(wrapper);
        if (i==1){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("删除子级评论成功");
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("删除子级评论失败");
        }
        return res;
    }


}
