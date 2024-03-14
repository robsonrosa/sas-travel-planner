variable "resource_group_name" {
  type        = string
  description = "Resource group name to create the database"
}

variable "location" {
  type        = string
  description = "Resource group location"
}

variable "public_domain" {
  type        = string
  default     = "sastravelplanner.online"
  description = "Registered public domain to externally access the application"
}
