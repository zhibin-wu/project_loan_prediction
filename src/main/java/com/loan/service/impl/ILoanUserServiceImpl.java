package com.loan.service.impl;


import com.loan.bean.LoanUser;
import com.loan.mapper.LoanUserMapper;
import com.loan.service.ILoanUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户服务接口实现类
 */
@Service //标识为服务类
public class ILoanUserServiceImpl implements ILoanUserService {

    /**
     * 注入用户管理接口
     */
    @Autowired
    private LoanUserMapper loanUserMapper;

    /**
     * 用户注册
     */
    @Override
    public Integer reg(LoanUser user) {
        return loanUserMapper.insert(user);
    }

    /**
     * 用户登录
     */
    @Override
    public LoanUser login(LoanUser user) {
        Example example = new Example(LoanUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        criteria.andEqualTo("password", user.getPassword());
        return loanUserMapper.selectOneByExample(example);
    }

}
