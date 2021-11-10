package org.scala.slurp

import software.amazon.awssdk.services.iam.IamClient
import software.amazon.awssdk.services.iam.model.{AttachRolePolicyRequest, AttachRolePolicyResponse, CreatePolicyRequest, CreateRoleRequest, CreateRoleResponse}


class AccessorCreator(iamClient: IamClient) {
  def createRole(name: String, assumeRolePolicyDocument: String): CreateRoleResponse = {
    val request = CreateRoleRequest.builder()
      .roleName(name)
      .assumeRolePolicyDocument(assumeRolePolicyDocument)
      .build()
    iamClient.createRole(request)
  }

  def createPolicy(policyDocument: String): String = {
    val policyRequest = CreatePolicyRequest.builder().policyDocument(policyDocument).build()
    iamClient.createPolicy(policyRequest).policy().arn()
  }

  def attachPolicy(roleName: String, policyArn: String) : AttachRolePolicyResponse = {
    val rolePolicyRequest = AttachRolePolicyRequest.builder().roleName(roleName).policyArn(policyArn).build()
    iamClient.attachRolePolicy(rolePolicyRequest)
  }
}
