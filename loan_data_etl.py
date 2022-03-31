import csv
import re
import pandas as pd
from pandas import DataFrame


def etl_loan_user_data():
    # 1.打开文件，追加a
    out = open(r'data_etl/loan_user.csv', 'w', newline='', encoding='utf-8')
    # 2.设置写入模式
    csv_write = csv.writer(out, dialect='excel')
    # 3.设置用户csv文件头行
    user_head = ['userid', 'username', 'password', 'realname']
    # 4.写入头行
    csv_write.writerow(user_head)
    # 5.写入用户
    csv_write.writerow([1, 'loan', 'loan', '小李'])


def get_employment_length_mode(train_csv):
    # 0.定义工作年限列列表
    employment_length_list = []
    # 1.遍历贷款违约训练数据集
    for i in range(len(train_csv)):
        # 2.获取获取工作年限列
        employment_length = str(train_csv['employmentLength'][i])
        # 3.过滤空数据
        if employment_length is not None:
            # 4.删除中文
            employment_length = re.sub(u"([^\u0030-\u0039])", "", employment_length)
            # 5.转为数字
            if len(employment_length) > 0:
                # 6.追加到列表当中
                employment_length_list.append(int(employment_length))
    # 7.工作年限列表转字典
    employment_length_list_dic = {"employment_length": employment_length_list}
    # 8.字典转DataFrame
    df = DataFrame(employment_length_list_dic)
    # 9.返回众数
    return df.mode()['employment_length'][0]


def get_employment_length(employment_length, employment_length_mode):

    if employment_length is None and len(employment_length) == 0:
        employment_length = employment_length_mode
    else:
        employment_length = re.sub(u"([^\u0030-\u0039])", "", employment_length)
        if len(employment_length) == 0:
            employment_length = employment_length_mode
        else:
            employment_length = int(employment_length)
    return employment_length


def get_application_type_cn(application_type):
    if application_type == "0":
        return "个人申请"
    if application_type == "1":
        return "与他人联合申请"


def get_is_default_cn(is_default):
    if is_default == "0":
        return "不违约"
    if is_default == "1":
        return "违约"


def etl_loan_train_data():
    # 1.打开文件，追加a
    out = open(r'data_etl/train_data.csv', 'w', newline='', encoding='utf-8')
    # 2.设置写入模式
    csv_write = csv.writer(out, dialect='excel')
    # 3.设置贷款违约训练数据集数据头标记
    train_head = ['id', 'loan_amount', 'term', 'installment', 'grade', 'annual_income', 'employment_length', 'purpose',
                  'delinuency_2years', 'application_type', 'application_type_cn', 'home_owner_ship', 'issue_date',
                  'is_default', 'is_default_cn']
    # 4.写入头行
    csv_write.writerow(train_head)
    # 5.读取贷款违约训练数据集并去掉重复数据
    train_csv = pd.read_csv('data/train_data.csv').drop_duplicates()
    # 6.获取工作年限众数
    employment_length_mode = get_employment_length_mode(train_csv)
    # 7.遍历贷款违约训练数据集
    for i in range(len(train_csv)):
        # 8.获取ID
        id = train_csv['id'][i]
        # 9.获取贷款金额
        loan_amount = train_csv['loanAmnt'][i]
        # 10.获取贷款期限
        term = train_csv['term'][i]
        # 11.获取分期付款金额
        installment = train_csv['installment'][i]
        # 12.获取贷款等级
        grade = train_csv['grade'][i]
        # 13.获取年收入
        annual_income = train_csv['annualIncome'][i]
        # 14.获取工作年限
        employment_length = get_employment_length(str(train_csv['employmentLength'][i]), employment_length_mode)
        # 15.获取贷款用途类别
        purpose = train_csv['purpose'][i]
        # 16.获取贷款2年内违约事件数
        delinquency_2years = train_csv['delinquency_2years'][i]
        # 17.获取贷款类别
        application_type = str(train_csv['applicationType'][i])
        application_type_cn = get_application_type_cn(application_type)
        # 18.获取借款人在登记时提供的房屋所有权状况
        home_owner_ship = train_csv['homeOwnership'][i]
        # 19.获取申请贷款日期
        issue_date = train_csv['issueDate'][i]
        # 20.获取是否违约
        is_default = str(train_csv['isDefault'][i])
        is_default_cn = get_is_default_cn(is_default)
        # 21.将loanAmnt字段为空的记录值过滤掉、将issueDate字段为空的记录值过滤掉
        if loan_amount is not None and issue_date is not None:
            # 22.构建数据
            train_data = [id, loan_amount, term, installment, grade, annual_income, employment_length, purpose,
                          delinquency_2years, application_type, application_type_cn, home_owner_ship, issue_date,
                          is_default, is_default_cn]
            # 23.输出数据信息
            print(train_data)
            # 24.写入数据
            csv_write.writerow(train_data)


def etl_loan_predict_data():
    # 1.打开文件，追加a
    out = open(r'data_etl/predict_data.csv', 'w', newline='', encoding='utf-8')
    # 2.设置写入模式
    csv_write = csv.writer(out, dialect='excel')
    # 3.设置贷款违约训练数据集数据头标记
    train_head = ['id', 'loan_amount', 'term', 'installment', 'grade', 'annual_income', 'employment_length', 'purpose',
                  'delinuency_2years', 'application_type', 'application_type_cn', 'issue_date',]
    # 4.写入头行
    csv_write.writerow(train_head)
    # 5.读取贷款违约训练数据集并去掉重复数据
    train_csv = pd.read_csv('data/predict_data.csv').drop_duplicates()
    # 6.获取工作年限众数
    employment_length_mode = get_employment_length_mode(train_csv)
    # 7.遍历贷款违约训练数据集
    for i in range(len(train_csv)):
        # 8.获取ID
        id = train_csv['id'][i]
        # 9.获取贷款金额
        loan_amount = train_csv['loanAmnt'][i]
        # 10.获取贷款期限
        term = train_csv['term'][i]
        # 11.获取分期付款金额
        installment = train_csv['installment'][i]
        # 12.获取贷款等级
        grade = train_csv['grade'][i]
        # 13.获取年收入
        annual_income = train_csv['annualIncome'][i]
        # 14.获取工作年限
        employment_length = get_employment_length(str(train_csv['employmentLength'][i]), employment_length_mode)
        # 15.获取贷款用途类别
        purpose = train_csv['purpose'][i]
        # 16.获取贷款2年内违约事件数
        delinquency_2years = train_csv['delinquency_2years'][i]
        # 17.获取贷款类别
        application_type = str(train_csv['applicationType'][i])
        application_type_cn = get_application_type_cn(application_type)
        # 19.获取申请贷款日期
        issue_date = train_csv['issueDate'][i]
        # 21.将loanAmnt字段为空的记录值过滤掉、将issueDate字段为空的记录值过滤掉
        if loan_amount is not None and issue_date is not None:
            # 22.构建数据
            train_data = [id, loan_amount, term, installment, grade, annual_income, employment_length, purpose,
                          delinquency_2years, application_type, application_type_cn, issue_date]
            # 23.输出数据信息
            print(train_data)
            # 24.写入数据
            csv_write.writerow(train_data)


def start_etl_loan_data():
    # 0.建立系统用户数据
    etl_loan_user_data()
    # 1.对训练数据进行清理
    etl_loan_train_data()
    # 2.对预测数据进行清理
    etl_loan_predict_data()
