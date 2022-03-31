from pyspark.sql import SparkSession


def start_loan_spark_task():
    # 1.定义连接Mysql的Url
    mysql_url = "jdbc:mysql://localhost:3306/loan?serverTimezone=UTC&useUnicode=true&zeroDateTimeBehavior" \
                "=convertToNull&autoReconnect=true&characterEncoding=utf-8 "
    # 2.定义连接mysql的配置
    prop = {'user': 'root', 'password': 'root', 'driver': "com.mysql.jdbc.Driver"}
    # 3.创建SparkSession
    spark = SparkSession.builder.appName("spark_etl"). \
        config("hadoop.home.dir", "/user/hive/warehouse"). \
        enableHiveSupport(). \
        getOrCreate()
    # 4.读取Hive数据
    train_data = spark.sql('SELECT * FROM default.train_data')
    # 5.创建临时表
    train_data.createOrReplaceTempView("train_data")
    # 6.逐年贷款数量趋势图分析
    loan_year_sql = '''
        SELECT
            YEAR(issue_date)    AS issue_year
            ,COUNT(1)           AS counts
        FROM
            train_data
        GROUP BY
            YEAR(issue_date)
    '''
    spark.sql(loan_year_sql).write.jdbc(mysql_url, 'loan_year', 'overwrite', prop)
    # 6.工作年限与贷款违约的关联分析
    employment_sql = '''
        SELECT
            CAST(employment_length AS INT) AS employment_length
            ,counts
        FROM
            (
            SELECT
                employment_length   AS employment_length
                ,COUNT(1)           AS counts
            FROM
                train_data
            WHERE
                is_default = 1
            GROUP BY
                employment_length
            )
    '''
    spark.sql(employment_sql).write.jdbc(mysql_url, 'loan_employment', 'overwrite', prop)
    # 6.贷款类别与贷款违约的关联分析
    application_sql = '''
        SELECT
            application_type_cn AS application_type
            ,COUNT(1)           AS counts
        FROM
            train_data
        WHERE
            is_default = 1
        GROUP BY
            application_type_cn
    '''
    spark.sql(application_sql).write.jdbc(mysql_url, 'loan_application', 'overwrite', prop)
    # 6.年收入与贷款
    annual_sql = '''
        SELECT
            annual_income_range     AS annual_income_range
            ,annual_income_range_id AS annual_income_range_id
            ,count(1)               AS counts
        FROM 
            (
            SELECT
                CASE
                    WHEN annual_income < 50000 THEN '<5万'
                    WHEN annual_income < 60000 THEN '5万~6万'
                    WHEN annual_income < 70000 THEN '6万~7万'
                    WHEN annual_income < 80000 THEN '7万~8万'
                    WHEN annual_income < 90000 THEN '8万~9万'
                    WHEN annual_income < 100000 THEN '9万~10万'
                    WHEN annual_income < 200000 THEN '10万~20万'
                    WHEN annual_income < 300000 THEN '20万~30万'
                    WHEN annual_income < 500000 THEN '30万~50万'
                    WHEN annual_income < 700000 THEN '50万~70万'
                    WHEN annual_income < 1000000 THEN '70万~100万'
                ELSE '>100万' END AS annual_income_range
            ,CASE
                WHEN annual_income < 50000 THEN 1
                WHEN annual_income < 60000 THEN 2
                WHEN annual_income < 70000 THEN 3
                WHEN annual_income < 80000 THEN 4
                WHEN annual_income < 90000 THEN 5
                WHEN annual_income < 100000 THEN 6
                WHEN annual_income < 200000 THEN 7
                WHEN annual_income < 300000 THEN 8
                WHEN annual_income < 500000 THEN 9
                WHEN annual_income < 700000 THEN 10
                WHEN annual_income < 1000000 THEN 11
                ELSE 12 END AS annual_income_range_id
            FROM
                train_data
            ) AS temp
        GROUP BY
            annual_income_range
            ,annual_income_range_id
    '''
    spark.sql(annual_sql).write.jdbc(mysql_url, 'loan_annual', 'overwrite', prop)

