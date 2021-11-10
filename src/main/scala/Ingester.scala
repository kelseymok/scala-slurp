package org.scala.slurp

import org.apache.spark.sql.{DataFrame, SparkSession}

class Ingester(spark: SparkSession) {

  def read(): DataFrame = {
    val df = spark.read
      .option("delimiter", ",")
      .option("header", "true")
      .option("quote","")
      .option("inferSchema", "true")
      .csv("src/main/resources/pokemon.csv")
    df
  }
}
