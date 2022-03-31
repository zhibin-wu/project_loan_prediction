package com.loan.service;


import com.github.pagehelper.PageInfo;
import com.loan.bean.LoanTrain;

import java.util.List;


/**
 * 训练数据集数据接口
 */
public interface LoanTrainService {

    /**
     * 模糊查询训练数据集
     */
    PageInfo<LoanTrain> getAlLoanTrain(LoanTrain loanTrain, int limit, int page);

    /**
     * 逐年贷款数量趋势图分析
     */
    List<Integer> getLoanYear();

    /**
     * 工作年限与贷款违约的关联分析
     */
    List<Integer> getLoanEmployment();

    /**
     * 贷款类别与贷款违约的关联分析
     */
    List<Integer> getLoanApplication();

    /**
     * 年收入与贷款
     */
    List<Integer> getLoanAnnual();
}
