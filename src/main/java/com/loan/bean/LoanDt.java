package com.loan.bean;

/**
 * 决策树
 */
public class LoanDt {

    /**
     * ID
     */
    private Integer id;

    /**
     * 贷款金额
     */
    private Double loan_amount;

    /**
     * 贷款期限
     */
    private Integer term;

    /**
     * 分期付款金额
     */
    private Double installment;

    /**
     * 年收入
     */
    private Double annual_income;

    /**
     * 工作年限
     */
    private Integer employment_length;

    /**
     * 贷款2年内违约事件数
     */
    private Integer delinuency_2years;

    /**
     * 贷款类别
     */
    private String application_type_cn;

    /**
     * 申请贷款日期
     */
    private String issue_date;

    /**
     * 是否违约
     */
    private String is_default;

    public LoanDt() {
    }

    public LoanDt(Integer id, Double loan_amount, Integer term, Double installment, Double annual_income, Integer employment_length, Integer delinuency_2years, String application_type_cn, String issue_date, String is_default) {
        this.id = id;
        this.loan_amount = loan_amount;
        this.term = term;
        this.installment = installment;
        this.annual_income = annual_income;
        this.employment_length = employment_length;
        this.delinuency_2years = delinuency_2years;
        this.application_type_cn = application_type_cn;
        this.issue_date = issue_date;
        this.is_default = is_default;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(Double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Double getInstallment() {
        return installment;
    }

    public void setInstallment(Double installment) {
        this.installment = installment;
    }

    public Double getAnnual_income() {
        return annual_income;
    }

    public void setAnnual_income(Double annual_income) {
        this.annual_income = annual_income;
    }

    public Integer getEmployment_length() {
        return employment_length;
    }

    public void setEmployment_length(Integer employment_length) {
        this.employment_length = employment_length;
    }

    public Integer getDelinuency_2years() {
        return delinuency_2years;
    }

    public void setDelinuency_2years(Integer delinuency_2years) {
        this.delinuency_2years = delinuency_2years;
    }

    public String getApplication_type_cn() {
        return application_type_cn;
    }

    public void setApplication_type_cn(String application_type_cn) {
        this.application_type_cn = application_type_cn;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }
}
