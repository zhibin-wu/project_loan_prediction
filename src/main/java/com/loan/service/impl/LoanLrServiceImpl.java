package com.loan.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loan.bean.LoanLr;
import com.loan.mapper.LoanLrMapper;
import com.loan.service.LoanLrService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * 逻辑回归数据接口实现类
 */
@Service //标识为服务类
public class LoanLrServiceImpl implements LoanLrService {

    /**
     * 注入逻辑回归数据dao层接口
     */
    @Autowired
    private LoanLrMapper loanLrMapper;

    @Override
    public PageInfo<LoanLr> getAllLoanLr(LoanLr loanLr, int limit, int page) {
        PageHelper.startPage(page, limit);
        Example example = new Example(loanLr.getClass());
        Example.Criteria criteria = example.createCriteria();
        if (null != loanLr.getTerm()) {
            criteria.andEqualTo("term", loanLr.getTerm());
        }
        if (null != loanLr.getEmployment_length()) {
            criteria.andEqualTo("employment_length", loanLr.getEmployment_length());
        }
        if (!StringUtils.isNullOrEmpty(loanLr.getApplication_type_cn())) {
            criteria.andLike("application_type_cn", "%" + loanLr.getApplication_type_cn() + "%");
        }
        if (!StringUtils.isNullOrEmpty(loanLr.getIssue_date())) {
            criteria.andLike("issue_date", "%" + loanLr.getIssue_date() + "%");
        }
        if (!StringUtils.isNullOrEmpty(loanLr.getIs_default())) {
            criteria.andEqualTo("is_default", loanLr.getIs_default());
        }
        example.setOrderByClause("issue_date asc");
        List<LoanLr> list = loanLrMapper.selectByExample(example);
        PageInfo<LoanLr> pageInfo = new PageInfo<LoanLr>(list);
        return pageInfo;
    }
}
