package org.scala.slurp

import software.amazon.awssdk.services.iam.IamClient
import software.amazon.awssdk.services.iam.model.{AttachRolePolicyRequest, AttachRolePolicyResponse, CreatePolicyRequest, CreatePolicyResponse, CreateRoleRequest, CreateRoleResponse}


class AccessorCreator(iamClient: IamClient) {
  def createRole(name: String, assumeRolePolicyDocument: String): CreateRoleResponse = {
    val request: CreateRoleRequest = ???

    // HINT: What methods exist for iamClient? Press command + b to view the source code
    ???
  }

  def createPolicy(policyDocument: String): String = {
    val policyRequest: CreatePolicyRequest = ???

    // HINT: What methods exist for iamClient? Press command + b to view the source code
    val response: CreatePolicyResponse = ???

    response.policy().arn()
  }

  def attachPolicy(roleName: String, policyArn: String) : AttachRolePolicyResponse = {
    val rolePolicyRequest: AttachRolePolicyRequest = ???

    // HINT: What methods exist for iamClient? Press command + b to view the source code
    ???
  }
}
