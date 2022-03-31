package com.loan.controller;

import com.github.pagehelper.PageInfo;
import com.loan.bean.LoanTrain;
import com.loan.bean.ResultObject;
import com.loan.service.LoanTrainService;
import com.loan.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 训练数据集的控制器
 */
@RequestMapping("/loan_train")
@RestController
public class LoanTrainController {

    /**
     * 注入训练数据集数据接口
     */
    @Autowired
    private LoanTrainService loanTrainService;

    /**
     * 模糊查询训练数据集
     */
    @RequestMapping("/getAlLoanTrain") //标识请求地址
    public ResultObject<List<LoanTrain>> getAlLoanTrain(LoanTrain loanTrain, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        System.out.println(loanTrain);
        // 1.获取训练数据集并分页
        PageInfo<LoanTrain> pageInfo = loanTrainService.getAlLoanTrain(loanTrain, limit, page);
        // 2.统一返回
        ResultObject<List<LoanTrain>> rs = new ResultObject<>();
        // 3.返回获取到的数据
        List<LoanTrain> list = pageInfo.getList();
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("查询成功");
        rs.setData(list);
        rs.setCount(pageInfo.getTotal());
        return rs;
    }

    /**
     * 逐年贷款数量趋势图分析
     */
    @RequestMapping("/getLoanYear") //标识请求地址
    public ResultObject<Object> getLoanYear() {
        // 1.统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        // 2.逐年贷款数量趋势图分析
        List dataList = loanTrainService.getLoanYear();
        // 3.返回获取到的数据
        rs.setData(dataList);
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("success");
        return rs;
    }

    /**
     * 工作年限与贷款违约的关联分析
     */
    @RequestMapping("/getLoanEmployment") //标识请求地址
    public ResultObject<Object> getLoanEmployment() {
        // 1.统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        // 2.工作年限与贷款违约的关联分析
        List dataList = loanTrainService.getLoanEmployment();
        // 3.返回获取到的数据
        rs.setData(dataList);
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("success");
        return rs;
    }

    /**
     * 贷款类别与贷款违约的关联分析
     */
    @RequestMapping("/getLoanApplication") //标识请求地址
    public ResultObject<Object> getLoanApplication( ) {
        // 1.统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        // 2.贷款类别与贷款违约的关联分析
        List dataList = loanTrainService.getLoanApplication();
        // 3.返回获取到的数据
        rs.setData(dataList);
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("success");
        return rs;
    }

    /**
     * 年收入与贷款
     */
    @RequestMapping("/getLoanAnnual") //标识请求地址
    public ResultObject<Object> getWeiboQuality( ) {
        // 1.统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        // 2.年收入与贷款
        List dataList = loanTrainService.getLoanAnnual();
        // 3.返回获取到的数据
        rs.setData(dataList);
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("success");
        return rs;
    }

}
