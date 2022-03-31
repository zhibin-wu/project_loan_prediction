package com.loan.bean;

/**
 * 训练数据集
 */
public class LoanTrain {

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
     * 贷款等级
     */
    private String grade;

    /**
     * 年收入
     */
    private Double annual_income;

    /**
     * 工作年限
     */
    private Integer employment_length;

    /**
     * 贷款用途类别
     */
    private Integer purpose;

    /**
     * 贷款2年内违约事件数
     */
    private Integer delinuency_2years;

    /**
     * 贷款类别ID
     */
    private Integer application_type;

    /**
     * 贷款类别中文
     */
    private String application_type_cn;

    /**
     * 房屋所有权情况
     */
    private String home_owner_ship;

    /**
     * 申请贷款日期
     */
    private String issue_date;

    /**
     * 是否违约
     */
    private Integer is_default;

    /**
     * 是否违约中文
     */
    private String is_default_cn;

    public LoanTrain() {
    }

    public LoanTrain(Integer id, Double loan_amount, Integer term, Double installment, String grade, Double annual_income, Integer employment_length, Integer purpose, Integer delinuency_2years, Integer application_type, String application_type_cn, String home_owner_ship, String issue_date, Integer is_default, String is_default_cn) {
        this.id = id;
        this.loan_amount = loan_amount;
        this.term = term;
        this.installment = installment;
        this.grade = grade;
        this.annual_income = annual_income;
        this.employment_length = employment_length;
        this.purpose = purpose;
        this.delinuency_2years = delinuency_2years;
        this.application_type = application_type;
        this.application_type_cn = application_type_cn;
        this.home_owner_ship = home_owner_ship;
        this.issue_date = issue_date;
        this.is_default = is_default;
        this.is_default_cn = is_default_cn;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public Integer getDelinuency_2years() {
        return delinuency_2years;
    }

    public void setDelinuency_2years(Integer delinuency_2years) {
        this.delinuency_2years = delinuency_2years;
    }

    public Integer getApplication_type() {
        return application_type;
    }

    public void setApplication_type(Integer application_type) {
        this.application_type = application_type;
    }

    public String getApplication_type_cn() {
        return application_type_cn;
    }

    public void setApplication_type_cn(String application_type_cn) {
        this.application_type_cn = application_type_cn;
    }

    public String getHome_owner_ship() {
        return home_owner_ship;
    }

    public void setHome_owner_ship(String home_owner_ship) {
        this.home_owner_ship = home_owner_ship;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }

    public String getIs_default_cn() {
        return is_default_cn;
    }

    public void setIs_default_cn(String is_default_cn) {
        this.is_default_cn = is_default_cn;
    }

    @Override
    public String toString() {
        return "LoanTrain{" +
                "id=" + id +
                ", loan_amount=" + loan_amount +
                ", term=" + term +
                ", installment=" + installment +
                ", grade='" + grade + '\'' +
                ", annual_income=" + annual_income +
                ", employment_length=" + employment_length +
                ", purpose=" + purpose +
                ", delinuency_2years=" + delinuency_2years +
                ", application_type=" + application_type +
                ", application_type_cn='" + application_type_cn + '\'' +
                ", home_owner_ship='" + home_owner_ship + '\'' +
                ", issue_date='" + issue_date + '\'' +
                ", is_default=" + is_default +
                ", is_default_cn='" + is_default_cn + '\'' +
                '}';
    }
}
