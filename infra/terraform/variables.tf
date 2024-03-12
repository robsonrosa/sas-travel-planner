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

variable "subscription_id" {
  type = string
}
variable "tenant_id" {
  type = string
}
variable "client_id" {
  type = string
}
variable "client_secret" {
  type = string
}