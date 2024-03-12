variable "resource_group_name" {
  type        = string
  default     = "sas"
  description = "Name of the main resource group for this aplication"
}

variable "location" {
  type        = string
  default     = "westus2"
  description = "Resource group location"
}

variable "ARM_SUBSCRIPTION_ID" {
  type = string
}
variable "ARM_TENANT_ID" {
  type = string
}
variable "ARM_CLIENT_ID" {
  type = string
}
variable "ARM_CLIENT_SECRET" {
  type = string
}