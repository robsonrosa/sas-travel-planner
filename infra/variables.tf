variable "location" {
  type        = string
  default     = "westus2"
  description = "Resource group location"
}

variable "db_account_name" {
  type        = string
  default     = "travel-service-account"
  description = "Cosmos database account for travel planning application"
}

variable "db_name" {
  type        = string
  default     = "travel-planning"
  description = "Cosmos database for travel planning application"
}

variable "throughput" {
  type        = number
  default     = 100
  description = "Cosmos db database throughput"
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