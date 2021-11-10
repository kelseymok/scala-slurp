package org.scala.slurp

import org.mockito.Mockito.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.CreateBucketRequest

class S3BucketCreatorTest extends AnyFlatSpec with Matchers {

  it should "return nothing when successful" in {
    val randomInt = scala.util.Random.nextInt(1000)
    val bucketName = s"my-awesome-bucket-$randomInt"
    val s3Client = mock(classOf[S3Client])
    val request = CreateBucketRequest.builder().bucket(bucketName).build()
    when(s3Client.createBucket(request)).thenReturn(null)
    try {
      new S3BucketCreator(s3Client).create(bucketName)
    } catch {
      case _: Throwable => println("Should not have thrown any exception")
    }
  }
}