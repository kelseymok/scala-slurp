package org.scala.slurp

import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CreateBucketRequest

class S3BucketCreator(s3Client: S3Client) {

  def create(name: String): Unit = {
    s3Client.createBucket(CreateBucketRequest.builder().bucket(name).build())
  }

}
