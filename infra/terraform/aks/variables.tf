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

variable "aks_name" {
  type        = string
  default     = "sasaks"
}

variable "nodepool_name" {
  default     = "sasnodepool"
}

variable "nodepool_count" {
  type        = string
  default     = 2
}

variable "nodepool_vm_size" {
  default     = "Standard_DS2_v2"
}

variable "client_id" { }

variable "client_secret" { }

