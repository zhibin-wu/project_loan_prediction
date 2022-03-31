package com.loan.mapper;

import com.loan.bean.LoanTrain;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 训练数据集dao层接口
 */
public interface LoanTrainMapper extends Mapper<LoanTrain> {

    /**
     * 逐年贷款数量趋势图分析
     */
    @Select("SELECT * FROM loan_year ORDER BY issue_year ASC")
    List<Map<String, Integer>> getLoanYear();

    /**
     * 工作年限与贷款违约的关联分析
     */
    @Select("SELECT * FROM loan_employment ORDER BY employment_length ASC")
    List<Map<String, Integer>> getLoanEmployment();

    /**
     * 贷款类别与贷款违约的关联分析
     */
    @Select("SELECT * FROM loan_application ORDER BY counts DESC")
    List<Map<String, Integer>> getLoanApplication();

    /**
     * 年收入与贷款
     */
    @Select("SELECT * FROM loan_annual ORDER BY annual_income_range_id ASC")
    List<Map<String, Integer>> getLoanAnnual();


}
