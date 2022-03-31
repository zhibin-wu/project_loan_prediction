import findspark

findspark.init()

from pyspark.sql import SparkSession
from loan_decision_tree import decision_tree_classifier
from loan_logistic_regression import logistic_regression_classifier


def start_loan_spark_etl():
    # 1.定义连接Mysql的Url
    mysql_url = "jdbc:mysql://localhost:3306/loan?serverTimezone=UTC&useUnicode=true&zeroDateTimeBehavior" \
                "=convertToNull&autoReconnect=true&characterEncoding=utf-8 "
    # 2.定义连接mysql的配置
    prop = {'user': 'root', 'password': 'root', 'driver': "com.mysql.jdbc.Driver"}
    # 3.创建SparkSession
    spark = SparkSession \
        .builder \
        .appName("spark_etl") \
        .config("hadoop.home.dir", "/user/hive/warehouse") \
        .enableHiveSupport() \
        .getOrCreate()
    # 4.读取系统用户数据集
    loan_user = spark \
        .read \
        .format('com.databricks.spark.csv') \
        .options(header='true', inferschema='true', ending='utf-8') \
        .load('data_etl/loan_user.csv')
    # 5.读取贷款违约训练数据集
    train_data = spark \
        .read \
        .option("header", True) \
        .csv('data_etl/train_data.csv')
    # 6.读取贷款违约测试数据集
    predict_data = spark \
        .read \
        .option("header", True) \
        .csv('data_etl/predict_data.csv')
    # 7.将系统用户数据保存到mysql
    loan_user \
        .write \
        .jdbc(mysql_url, 'loan_user', 'overwrite', prop)
    # 8.注册表
    train_data.createOrReplaceTempView("train_data")
    predict_data.createOrReplaceTempView("predict_data")
    # 9.写入到Hive
    spark.sql("CREATE TABLE default.train_data SELECT * FROM train_data")
    # 10.对训练数据集数据类型进行转换
    train_etl_sql = '''
        SELECT
            CAST(id AS INT)                     AS id
            ,CAST(loan_amount AS DOUBLE)        AS loan_amount
            ,CAST(term AS INT)                  AS term
            ,CAST(installment AS DOUBLE)        AS installment
            ,grade                              AS grade
            ,CAST(annual_income AS DOUBLE)      AS annual_income
            ,CAST(employment_length AS INT)     AS employment_length
            ,CAST(purpose AS INT)               AS purpose
            ,CAST(delinuency_2years AS INT)     AS delinuency_2years
            ,CAST(application_type AS INT)      AS application_type
            ,application_type_cn                AS application_type_cn
            ,home_owner_ship                    AS home_owner_ship
            ,issue_date                         AS issue_date
            ,CAST(is_default AS INT)            AS is_default
            ,is_default_cn                      AS is_default_cn
        FROM
            train_data
    '''
    train_data = spark.sql(train_etl_sql)
    # 11.将系统用户数据保存到mysql
    train_data \
        .write \
        .jdbc(mysql_url, 'loan_train', 'overwrite', prop)
    # 10.对预测数据集数据类型进行转换
    predict_etl_sql = '''
        SELECT
            CAST(id AS INT)                     AS id
            ,CAST(loan_amount AS DOUBLE)        AS loan_amount
            ,CAST(term AS INT)                  AS term
            ,CAST(installment AS DOUBLE)        AS installment
            ,grade                              AS grade
            ,CAST(annual_income AS DOUBLE)      AS annual_income
            ,CAST(employment_length AS INT)     AS employment_length
            ,CAST(purpose AS INT)               AS purpose
            ,CAST(delinuency_2years AS INT)     AS delinuency_2years
            ,CAST(application_type AS INT)      AS application_type
            ,application_type_cn                AS application_type_cn
            ,issue_date                         AS issue_date
        FROM
            predict_data
       '''
    predict_data = spark.sql(predict_etl_sql)
    # 11.决策树算法预测
    predict_dt_df = decision_tree_classifier(train_data, predict_data)
    # 12.逻辑回归算法预测
    predict_lr_df = logistic_regression_classifier(train_data, predict_data)
    # 13.注册表
    predict_dt_df.createOrReplaceTempView("predict_dt_df")
    predict_lr_df.createOrReplaceTempView("predict_lr_df")
    # 14.对决策树预测结果进行清洗
    predict_dt_sql = '''
         SELECT
            id
            ,loan_amount
            ,term
            ,installment
            ,annual_income
            ,employment_length
            ,delinuency_2years
            ,application_type_cn
            ,issue_date
            ,CASE WHEN is_default = 0.0 THEN '不违约' ELSE '违约' END AS is_default
        FROM
            predict_dt_df
    '''
    predict_dt_df = spark.sql(predict_dt_sql)
    # 15.对逻辑回归预测结果进行清洗
    predict_lr_sql = '''
        SELECT
            id
            ,loan_amount
            ,term
            ,installment
            ,annual_income
            ,employment_length
            ,delinuency_2years
            ,application_type_cn
            ,issue_date
            ,CASE WHEN is_default = 0.0 THEN '不违约' ELSE '违约' END AS is_default
        FROM
            predict_lr_df
        '''
    predict_lr_df = spark.sql(predict_lr_sql)
    # 16.输出
    predict_dt_df.write.jdbc(mysql_url, 'loan_dt', 'overwrite', prop)
    predict_lr_df.write.jdbc(mysql_url, 'loan_lr', 'overwrite', prop)
