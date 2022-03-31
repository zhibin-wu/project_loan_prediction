package com.loan.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loan.bean.LoanDt;
import com.loan.bean.LoanTrain;
import com.loan.mapper.LoanTrainMapper;
import com.loan.service.LoanTrainService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * 训练数据集数据接口实现类
 */
@Service //标识为服务类
public class LoanTrainServiceImpl implements LoanTrainService {

    /**
     * 注入训练数据集dao层接口
     */
    @Autowired
    private LoanTrainMapper loanTrainMapper;

    @Override
    public PageInfo<LoanTrain> getAlLoanTrain(LoanTrain loanTrain, int limit, int page) {
        PageHelper.startPage(page, limit);
        Example example = new Example(loanTrain.getClass());
        Example.Criteria criteria = example.createCriteria();
        if (null != loanTrain.getTerm()) {
            criteria.andEqualTo("term", loanTrain.getTerm());
        }
        if (!StringUtils.isNullOrEmpty(loanTrain.getGrade())) {
            criteria.andLike("grade", "%" + loanTrain.getGrade() + "%");
        }
        if (null != loanTrain.getPurpose()) {
            criteria.andEqualTo("purpose", loanTrain.getPurpose());
        }
        if (!StringUtils.isNullOrEmpty(loanTrain.getIssue_date())) {
            criteria.andLike("issue_date", "%" + loanTrain.getIssue_date() + "%");
        }
        if (!StringUtils.isNullOrEmpty(loanTrain.getIs_default_cn())) {
            criteria.andEqualTo("is_default_cn", loanTrain.getIs_default_cn());
        }
        example.setOrderByClause("issue_date asc");
        List<LoanTrain> list = loanTrainMapper.selectByExample(example);
        PageInfo<LoanTrain> pageInfo = new PageInfo<LoanTrain>(list);
        return pageInfo;
    }

    /**
     * 逐年贷款数量趋势图分析
     */
    @Override
    public List getLoanYear() {
        return loanTrainMapper.getLoanYear();
    }

    /**
     * 工作年限与贷款违约的关联分析
     */
    @Override
    public List getLoanEmployment() {
        return loanTrainMapper.getLoanEmployment();
    }

    /**
     * 贷款类别与贷款违约的关联分析
     */
    @Override
    public List getLoanApplication() {
        return loanTrainMapper.getLoanApplication();
    }

    /**
     * 年收入与贷款
     */
    @Override
    public List getLoanAnnual() {
        return loanTrainMapper.getLoanAnnual();
    }
}
