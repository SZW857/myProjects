package com.szw.commonweal.service;

import com.szw.commonweal.entity.Remark;
import com.szw.commonweal.entity.ResultInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RemarkService {

    /**
     * 发表父级言论的接口
     * */
    public ResultInfo<String> remarking(HttpServletRequest request);

    /**
     * 获取父评论
     * */
    public List<Remark> getParentRemark();

    /**
     * 删除父级评论
     * */
    public ResultInfo<String> delParentRemark(Long remarkNum);
}
