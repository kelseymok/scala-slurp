package org.scala.slurp

import org.apache.spark.sql.{DataFrame, SparkSession}

class DataTransformer(spark: SparkSession) {
  import spark.implicits._

  def getNonLegendary(df: DataFrame): DataFrame = {
    df.filter($"is_legendary" === 0)
  }

  def getLegendary(df: DataFrame): DataFrame = {
    df.filter($"is_legendary" > 0)
  }

  def writeToS3Bucket(bucketName: String, bucketPath: String, df: DataFrame): Unit = {
    df.write
      .format("delta")
      .option("header", "true")
      .mode("Overwrite")
      .save(s"s3a://$bucketName/$bucketPath/")
  }
}
