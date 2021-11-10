package org.scala.slurp

import org.apache.spark.sql.{DataFrame, SparkSession}

class Ingester(spark: SparkSession) {

  def read(): DataFrame = {

    // HINT: What options do you need to add in order to process this csv?

    val df = spark.read
      .csv("src/main/resources/pokemon.csv")
    df
  }
}
