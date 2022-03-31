package com.loan.service;


import com.github.pagehelper.PageInfo;
import com.loan.bean.LoanDt;


/**
 *决策树数据接口
 */
public interface LoanDtService {

    /**
     * 模糊查询决策树数据
     */
    PageInfo<LoanDt> getAllLoanDt(LoanDt loanDt, int limit, int page);

}
