package org.scala.slurp

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers


class DataTransformerTest extends AnyFlatSpec with SparkTestContext with Matchers {
  import spark.implicits._

  it should "returns dataframe for legendary Pokemon" in {
    val df = Seq(
      ("Articuno", 1),
      ("Zapdos", 1),
      ("Moltres", 1),
      ("Dratini", 0),
      ("Dragonair", 0),
    ).toDF("name", "is_legendary")
    val transformer = new DataTransformer(spark)
    val result = transformer.getLegendary(df)
    assert(result.count() === 3)
  }

  it should "returns dataframe for non-legendary Pokemon" in {
    val df = Seq(
      ("Articuno", 1),
      ("Zapdos", 1),
      ("Moltres", 1),
      ("Dratini", 0),
      ("Dragonair", 0),
    ).toDF("name", "is_legendary")
    val transformer = new DataTransformer(spark)
    val result = transformer.getNonLegendary(df)
    assert(result.count() === 2)
  }
}
