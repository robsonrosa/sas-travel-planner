variable "resource_group_name" {
  type        = string
  description = "Resource group name to create the database"
}

variable "location" {
  type        = string
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