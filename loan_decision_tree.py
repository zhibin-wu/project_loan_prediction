from pyspark.ml.classification import DecisionTreeClassifier, GBTClassifier
from pyspark.ml.feature import VectorAssembler


def decision_tree_classifier(train_df, predict_df):
    # 1.预处理训练数据
    input_clos = ['loan_amount', 'term', 'installment', 'annual_income', 'employment_length', 'delinuency_2years',
                  'application_type']
    assembler_train = VectorAssembler(inputCols=input_clos, outputCol="loan_feature")
    assembler_train_df = assembler_train.transform(train_df).persist()
    # 2.预处理测试数据
    assembler_predict = VectorAssembler(inputCols=input_clos, outputCol="loan_feature")
    assembler_predict_df = assembler_predict.transform(predict_df)
    # 4.创建决策树模型类
    classifier = GBTClassifier() \
        .setSeed(100) \
        .setLabelCol("is_default") \
        .setFeaturesCol("loan_feature") \
        .setPredictionCol("prediction")
    # 5.训练决策树模型
    model = classifier.fit(assembler_train_df)
    # 6.进行决策树预测
    predict_df = model.transform(assembler_predict_df)
    # 7.对数据进行清洗
    predict_dt_df = predict_df \
        .select("id", "loan_amount", "term", "installment", "annual_income", "employment_length",
                "delinuency_2years", "application_type_cn", "issue_date", "prediction") \
        .withColumnRenamed("prediction", "is_default")
    # 返回预测结果
    return predict_dt_df
