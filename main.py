from loan_data_etl import start_etl_loan_data
from loan_spark_etl import start_loan_spark_etl
from loan_task import start_loan_spark_task

if __name__ == '__main__':
    # 0.对数据进行清理
    start_etl_loan_data()
    # 1.读取数据进行清洗预测并把数据写入hive和Mysql
    start_loan_spark_etl()
    # 2.读取数据进行统计计算
    start_loan_spark_task()

