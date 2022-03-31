package com.loan.service;


import com.github.pagehelper.PageInfo;
import com.loan.bean.LoanLr;


/**
 * 逻辑回归数据接口
 */
public interface LoanLrService {

    /**
     * 模糊查询逻辑回归数据
     */
    PageInfo<LoanLr> getAllLoanLr(LoanLr loanLr, int limit, int page);

}
