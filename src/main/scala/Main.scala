package org.scala.slurp

import software.amazon.awssdk.services.iam.IamClient
import software.amazon.awssdk.services.s3.S3Client


object Main extends App with SparkEnvironment {

  val bucketName: String = "some-awesome-bucket"

  new S3BucketCreator(s3Client = S3Client.builder.build()).create(bucketName)

  val df = new Ingester(spark).read()
  val transformer = new DataTransformer(spark)

  transformer.writeToS3Bucket(bucketName, "legendary", transformer.getLegendary(df))
  transformer.writeToS3Bucket(bucketName, "nonLegendary", transformer.getNonLegendary(df))

  val roleName: String = "my-role"
  val assumeRolePolicyDocument: String = ""
  val accessorCreator = new AccessorCreator(iamClient = IamClient.builder().build())
  accessorCreator.createRole(roleName, assumeRolePolicyDocument)

  val policyDocument: String = ""
  val policyArn = accessorCreator.createPolicy(policyDocument)

  accessorCreator.attachPolicy(roleName = roleName, policyArn = policyArn)
}