package com.loan.controller;

import com.github.pagehelper.PageInfo;
import com.loan.bean.ResultObject;
import com.loan.bean.LoanDt;
import com.loan.service.LoanDtService;
import com.loan.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 决策树数据的控制器
 */
@RequestMapping("/loan_dt")
@RestController
public class LoanDtController {

    /**
     * 注入决策树数据接口
     */
    @Autowired
    private LoanDtService loanDtService;

    /**
     * 模糊查询决策树数据
     */
    @RequestMapping("/getAllLoanDt") //标识请求地址
    public ResultObject<List<LoanDt>> getAllLoanDt(LoanDt loanDt, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        // 1.获取决策树数据并分页
        PageInfo<LoanDt> pageInfo = loanDtService.getAllLoanDt(loanDt, limit, page);
        // 2.统一返回
        ResultObject<List<LoanDt>> rs = new ResultObject<>();
        // 3.返回获取到的数据
        List<LoanDt> list = pageInfo.getList();
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("查询成功");
        rs.setData(list);
        rs.setCount(pageInfo.getTotal());
        return rs;
    }
}
