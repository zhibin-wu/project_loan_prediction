package com.loan.service;


import com.loan.bean.LoanUser;

/**
 * 用户服务接口
 */
public interface ILoanUserService {

    /**
     * 用户登录
     */
    LoanUser login(LoanUser loanUser);

    /**
     * 用户注册
     */
    Integer reg(LoanUser loanUser);

}
