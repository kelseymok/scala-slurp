name := "scala-slurp"

version := "0.1"

scalaVersion := "2.12.10"

idePackagePrefix := Some("org.scala.slurp")

val sparkVersion = "3.2.0"
val awsVersion = "2.17.56"

libraryDependencies ++= Seq(
  "software.amazon.awssdk" % "core" % awsVersion,
  "software.amazon.awssdk" % "s3" % awsVersion,
  "software.amazon.awssdk" % "iam" % awsVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion % "test",
  "org.apache.spark" %% "spark-hive" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.mockito"   % "mockito-core" % "4.0.0" % "test",
  "org.scalactic" %% "scalactic" % "3.2.10" % "test",
  "org.scalatest" %% "scalatest" % "3.2.10" % "test",
  "org.scalatest" %% "scalatest-matchers-core" % "3.2.10" % "test",
  "io.delta" %% "delta-core" % "1.0.0",
  "org.apache.hadoop"      % "hadoop-aws"                       % "3.1.1"
)

