module "main" {
  source              = "./main"
  location            = var.location
  resource_group_name = var.resource_group_name
}

module "db" {
  source              = "./db"
  location            = var.location
  resource_group_name = var.resource_group_name
}

module "aks" {
  source              = "./aks"
  location            = var.location
  resource_group_name = var.resource_group_name
  client_id           = var.client_id
  client_secret       = var.client_secret
}

