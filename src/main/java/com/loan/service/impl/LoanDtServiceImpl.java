package com.loan.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loan.bean.LoanDt;
import com.loan.mapper.LoanDtMapper;
import com.loan.service.LoanDtService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * 决策树数据接口实现类
 */
@Service //标识为服务类
public class LoanDtServiceImpl implements LoanDtService {

    /**
     * 注入决策树数据dao层接口
     */
    @Autowired
    private LoanDtMapper loanDtMapper;

    @Override
    public PageInfo<LoanDt> getAllLoanDt(LoanDt loanDt, int limit, int page) {
        PageHelper.startPage(page, limit);
        Example example = new Example(loanDt.getClass());
        Example.Criteria criteria = example.createCriteria();
        if (null != loanDt.getTerm()) {
            criteria.andEqualTo("term", loanDt.getTerm());
        }
        if (null != loanDt.getEmployment_length()) {
            criteria.andEqualTo("employment_length", loanDt.getEmployment_length());
        }
        if (!StringUtils.isNullOrEmpty(loanDt.getApplication_type_cn())) {
            criteria.andLike("application_type_cn", "%" + loanDt.getApplication_type_cn() + "%");
        }
        if (!StringUtils.isNullOrEmpty(loanDt.getIssue_date())) {
            criteria.andLike("issue_date", "%" + loanDt.getIssue_date() + "%");
        }
        if (!StringUtils.isNullOrEmpty(loanDt.getIs_default())) {
            criteria.andEqualTo("is_default", loanDt.getIs_default());
        }
        example.setOrderByClause("issue_date asc");
        List<LoanDt> list = loanDtMapper.selectByExample(example);
        PageInfo<LoanDt> pageInfo = new PageInfo<LoanDt>(list);
        return pageInfo;
    }
}
