package org.scala.slurp

import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.sql.{ SparkSession }

trait SparkEnvironment {

  @transient private var _sc: SparkContext = _
  @transient private var _ss: SparkSession = _

  def sparkContext: SparkContext = _sc
  def sparkSession: SparkSession = _ss
  lazy val spark: SparkSession = _ss

  def initializeContext(): Unit = {
    val master = "local[*]"
    val appName = "app"
    val conf: SparkConf = new SparkConf()
      .setMaster(master)
      .setAppName(appName)
      .set("spark.driver.allowMultipleContexts", "false")
      .set("spark.ui.enabled", "false")
      .set("fs.s3a.aws.credentials.provider", "com.amazonaws.auth.DefaultAWSCredentialsProviderChain")

    _ss = SparkSession.builder().config(conf).getOrCreate()
    _sc = sparkSession.sparkContext
  }
  initializeContext()

}
