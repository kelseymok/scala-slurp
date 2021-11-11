package org.scala.slurp

import org.apache.spark.sql.DataFrame
import software.amazon.awssdk.services.iam.IamClient
import software.amazon.awssdk.services.s3.S3Client


object Main extends App with SparkEnvironment {

  val bucketName: String = ???

  // HINT: We need a bucket to write to
  /// HINT: initialise an S3 Client
  val s3Client: S3Client = ???
  /// HINT: Create a bucket using the s3Client and bucketName
  ???

  // HINT: Ingest data here
  val df: DataFrame = ???

  // HINT: Initialise Transformer
  val transformer: DataTransformer = ???

  // HINT: Transform data
  val legendaryPokemonDf: DataFrame = ???
  val nonLegendaryPokemonDf: DataFrame = ???

  // HINT: Write both legendary and nonLegendary to S3 Bucket with two different bucket paths
  ???
  ???

  // HINT: Define a role name
  val roleName: String = ???

  // HINT: Who can assume this role? Does scala have JSON -> String libraries?
  val assumeRolePolicyDocument: String = ???

  // HINT:
  val iamClient: IamClient = ???

  // HINT: Initialise AccessorCreator
  val accessorCreator = new AccessorCreator(iamClient = iamClient)

  // HINT: use the accessorCreator to create a role with the assumeRolePolicyDocument
  ???

  // HINT: what kind of actions will this role carry out. Avoid using only "*" in the actions and resources.
  val policyDocument: String = ???

  // HINT: use the accessorCreator to create a policy using the policyDocument
  val policyArn = ???

  // HINT: use the accessorCreator to attach the policy to your role
  ???
}