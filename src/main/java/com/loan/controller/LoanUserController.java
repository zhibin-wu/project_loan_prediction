package com.loan.controller;

import com.loan.util.Constant;
import com.loan.bean.LoanUser;
import com.loan.bean.ResultObject;
import com.loan.service.ILoanUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户的控制器
 */
@RequestMapping("/loan_user")
@RestController//标识为返回类型为Json的控制器
public class LoanUserController {

    //自动注入服务类
    @Autowired
    private ILoanUserService iLoanUserService;

    /**
     * 用户登录
     */
    @RequestMapping("/adminlogin")
    public ResultObject<LoanUser> adminlogin(LoanUser loanUser, HttpServletRequest request) {
        System.out.println("adminlogin");
        ResultObject<LoanUser> rs = new ResultObject<LoanUser>();
        // 1.获取session对象
        HttpSession session = request.getSession(true);
        // 2.查询用户列表
        LoanUser loginResult = iLoanUserService.login(loanUser);
        if (loginResult == null) {
            // 3.查询用户列表
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            // 4.提示
            rs.setMsg("用户名密码错误");
        } else {
            // 5.状态码
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            session.setAttribute("user", loginResult);
            // 6.提示
            rs.setMsg("登录成功");
        }
        // 7.数据
        rs.setData(loginResult);
        return rs;
    }

    /**
     * 获取当前登录的用户信息
     */
    @RequestMapping("/getLoginUser")
    public ResultObject<Object> getLoginUser(HttpServletRequest request) {
        // 1.查询用户列表
        ResultObject<Object> rs = new ResultObject<Object>();
        if (request.getSession().getAttribute("user") != null) {
            LoanUser loginUser = (LoanUser) request.getSession().getAttribute("user");
            // 2.数据
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("user");
            rs.setData(loginUser);
        }
        return rs;
    }

}
