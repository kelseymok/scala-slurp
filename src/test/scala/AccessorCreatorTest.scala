package org.scala.slurp

import org.mockito.Mockito.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import software.amazon.awssdk.services.iam.IamClient
import software.amazon.awssdk.services.iam.model.{AttachRolePolicyRequest, AttachRolePolicyResponse, CreatePolicyRequest, CreatePolicyResponse, CreateRoleRequest, CreateRoleResponse, Policy, Role}

class AccessorCreatorTest extends AnyFlatSpec with Matchers {

  it should "return a Role with an ARN" in {
    val iamClient = mock(classOf[IamClient])
    val randomInt = scala.util.Random.nextInt(1000)
    val roleName = s"some-role-name-$randomInt"
    val createRoleRequest = CreateRoleRequest.builder().roleName(roleName).assumeRolePolicyDocument("some-policy-doc")build()
    val roleResponse = Role.builder().roleName(roleName).arn(s"some-awesome-arn-$randomInt").build()
    val createRoleResponse = CreateRoleResponse.builder().role(roleResponse).build()
    when(iamClient.createRole(createRoleRequest)).thenReturn(createRoleResponse)
    val accessorCreator = new AccessorCreator(iamClient)

    assert(accessorCreator.createRole(roleName, "some-policy-doc").role().arn() === s"some-awesome-arn-$randomInt")
  }

  it should "create a policy and return an ARN" in {
    val iamClient = mock(classOf[IamClient])
    val randomInt = scala.util.Random.nextInt(1000)
    val createPolicyRequest = CreatePolicyRequest.builder().policyDocument("some-policy-doc").build()
    val policy = Policy.builder().arn(s"some-awesome-arn-$randomInt").build()
    val createPolicyResponse = CreatePolicyResponse.builder().policy(policy).build()
    when(iamClient.createPolicy(createPolicyRequest)).thenReturn(createPolicyResponse)

    val accessorCreator = new AccessorCreator(iamClient)

    assert(accessorCreator.createPolicy("some-policy-doc") === s"some-awesome-arn-$randomInt")
  }

  it should "attach a policy" in {
    val iamClient = mock(classOf[IamClient])
    val attachRolePolicyRequest = AttachRolePolicyRequest.builder().roleName("some-role-name").policyArn("some-policy-arn")build()
    val attachRolePolicyResponse = AttachRolePolicyResponse.builder().build()
    when(iamClient.attachRolePolicy(attachRolePolicyRequest)).thenReturn(attachRolePolicyResponse)

    val accessorCreator = new AccessorCreator(iamClient)

    assert(accessorCreator.attachPolicy("some-role-name", "some-policy-arn").getClass === classOf[AttachRolePolicyResponse])
  }


}