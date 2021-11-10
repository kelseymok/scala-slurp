package org.scala.slurp

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

class IngesterTest extends AnyFlatSpec with SparkTestContext with Matchers {

  it should "return dataframe with 802 records" in {
    val reader = new Ingester(sparkSession)
    val df = reader.read()
    assert(df.count() === 801)
  }
}
