package com.szw.commonweal.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szw.commonweal.dao.HelpMapper;
import com.szw.commonweal.entity.Help;
import com.szw.commonweal.entity.ResultInfo;
import com.szw.commonweal.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HelpServiceImpl implements HelpService {

    @Autowired
    private HelpMapper helpMapper;

    /**
     * 清除不合理的志愿者申请求助信息
     * */
    @Override
    @Transactional
    public ResultInfo<String> delVolunteerHelpInfo(String helpNum) {
        ResultInfo<String> res = new ResultInfo<>();

        System.out.println("**************"+helpNum);
        int i = helpMapper.deleteById(helpNum);
        System.out.println("**************"+i);
        if (i==1){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("已清除该志愿者不合理的发布信息");
        }else {
            res.setStatus(ResultInfo.FAIL);
        }
        return res;
    }

    /**
     * 志愿者求助页详情
     * */
    @Override
    public ResultInfo<Object> getHelpInfoDetail(String helpNum) {
        ResultInfo<Object> res = new ResultInfo<>();
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.select("*").eq("help_num",helpNum);
        List<Help> list = helpMapper.selectList(wrapper);
        if (!list.isEmpty()){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData(list);
        }else {
            res.setStatus(ResultInfo.FAIL);
        }
        return res;
    }

    /**
     * 获取志愿者发布的求助信息
     * */
    @Override
    public ResultInfo<Object> getDetailPage(String helpNum) {
        ResultInfo<Object> res = new ResultInfo<>();
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.select("*").eq("help_num",helpNum);
        List<Help> list = helpMapper.selectList(wrapper);
        if (!list.isEmpty()){
            res.setData(list);
            res.setStatus(ResultInfo.SUCCESS);
        }else {
            res.setStatus(ResultInfo.FAIL);
        }

        return res;
    }

    /**
     * 志愿者提交求助申请
     * */
    @Override
    public ResultInfo<String> postHelpInfo(HttpServletRequest request) {
        Help help = new Help();
        ResultInfo<String> res = new ResultInfo<>();
        help.setPeopleNum(Integer.valueOf(request.getParameter("peopleNum")));
        help.setTitle(request.getParameter("title"));
        help.setContent(request.getParameter("content"));
        help.setType(request.getParameter("type"));
        help.setImageUrl(request.getParameter("imageUrl"));
        help.setTelephone(request.getParameter("telephone"));
        help.setEmail(request.getParameter("email"));
        help.setRemaining(Integer.valueOf(request.getParameter("remaining")));
        help.setUserId(request.getParameter("userId"));
        help.setAdminId(request.getParameter("adminId"));
        help.setFinishDate(request.getParameter("finishDate"));
        help.setStartDate(request.getParameter("startDate"));

        int i = helpMapper.insert(help);
        if (i==1){
            res.setStatus(ResultInfo.SUCCESS);
            res.setData("申请活动发布成功,等待管理员审核...");
        }else {
            res.setData("申请活动发布失败,后台崩溃~");
        }
        return res;
    }
}
