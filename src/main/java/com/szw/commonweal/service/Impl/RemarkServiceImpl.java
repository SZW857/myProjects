package com.szw.commonweal.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.szw.commonweal.dao.RemarkMapper;
import com.szw.commonweal.dao.ReplyMapper;
import com.szw.commonweal.entity.Remark;
import com.szw.commonweal.entity.Reply;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RemarkServiceImpl implements RemarkService {

    @Autowired
    private RemarkMapper remarkMapper;

    @Autowired
    private ReplyMapper replyMapper;


    /**
     * 删除父级评论
     * */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public ResultInfo<String> delParentRemark(Long remarkNum) {
        int i1,i = 0;
        ResultInfo<String> res = new ResultInfo<>();
        QueryWrapper<Remark> wrapper = new QueryWrapper<>();
            QueryWrapper<Reply> wrapper1 = new QueryWrapper<>();
            wrapper1.select("remark_num").eq("remark_num",remarkNum);
             i1 = replyMapper.delete(wrapper1);
            if (i1==1||i1==0){
                wrapper.select("remark_num").eq("remark_num",remarkNum);
                 i = remarkMapper.delete(wrapper);
                if (i==1){
                res.setStatus(ResultInfo.SUCCESS);
                res.setData("删除父级评论成功");
                }else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("删除父级评论失败");
                }
        }else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("删除父子级评论失败");
            }
        return res;
    }

    /**
     * 发表父级言论的接口
     * */
    @Override
    public ResultInfo<String> remarking(HttpServletRequest request) {
        Remark remark = new Remark();
        ResultInfo<String> res = new ResultInfo<>();
        String content = request.getParameter("content");
        String userId = request.getParameter("userId");
        String adminName = request.getParameter("adminName");
        if (userId!=null&&!userId.equals("")){
            remark.setContent(content);
            remark.setUserId(userId);
            int i = remarkMapper.insert(remark);
            if (i==1){
                res.setStatus(ResultInfo.SUCCESS);
                res.setData("评论成功");
            }else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("评论失败,500");
            }
            return res;
        }else if (adminName!=null&&!adminName.equals("")){
            remark.setContent(content);
            remark.setAdminName(adminName);
            int i =  remarkMapper.insert(remark);
            if (i==1){
                res.setStatus(ResultInfo.SUCCESS);
                res.setData("评论成功");
            }else {
                res.setStatus(ResultInfo.FAIL);
                res.setData("评论失败,500");
            }
            return res;
        }else {
            res.setStatus(ResultInfo.FAIL);
            res.setData("评论失败,500");
            return res;
        }
    }

    /**
     * 获取父评论
     * */
    @Override
    public List<Remark> getParentRemark() {
        QueryWrapper<Remark> wrapper = new QueryWrapper<>();
        wrapper.select("*");
        List<Remark> list = remarkMapper.selectList(wrapper);
        System.out.println(list);
        return list;
    }

    /**
     * 删除父级评论
     * */


}
