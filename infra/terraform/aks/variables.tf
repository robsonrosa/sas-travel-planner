variable "resource_group_name" {
  type        = string
  description = "Resource group name to create the database"
}

variable "location" {
  type        = string
  description = "Resource group location"
}

variable "acr_name" {
  type        = string
  default     = "sastravelplanneracr"
  description = "Name of the main container registry"
}