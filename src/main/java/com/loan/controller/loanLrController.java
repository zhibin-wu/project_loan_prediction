package com.loan.controller;

import com.github.pagehelper.PageInfo;
import com.loan.bean.LoanLr;
import com.loan.service.LoanLrService;
import com.loan.bean.ResultObject;
import com.loan.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 逻辑回归数据的控制器
 */
@RequestMapping("/weibo_lr")
@RestController
public class loanLrController {

    /**
     * 注入逻辑回归数据接口
     */
    @Autowired
    private LoanLrService loanLrService;

    /**
     * 模糊查询逻辑回归数据
     */
    @RequestMapping("/getAllLoanLr") //标识请求地址
    public ResultObject<List<LoanLr>> getAllLoanLr(LoanLr loanLr, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        System.out.println(loanLr);
        // 1.获取微博互动-训练集数据并分页
        PageInfo<LoanLr> pageInfo = loanLrService.getAllLoanLr(loanLr, limit, page);
        // 2.统一返回
        ResultObject<List<LoanLr>> rs = new ResultObject<>();
        // 3.返回获取到的数据
        List<LoanLr> list = pageInfo.getList();
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("查询成功");
        rs.setData(list);
        rs.setCount(pageInfo.getTotal());
        return rs;
    }
}
