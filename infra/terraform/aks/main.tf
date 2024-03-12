resource "azurerm_container_registry" "acr" {
  name                = var.acr_name
  resource_group_name = var.resource_group_name
  location            = var.location
  sku                 = "Standard"
  admin_enabled       = false
}

resource "azurerm_kubernetes_cluster" "aks" {
  name                = var.aks_name
  resource_group_name = var.resource_group_name
  location            = var.location
  dns_prefix          = "${var.aks_name}-dns"

  default_node_pool {
    name            = var.nodepool_name
    node_count      = var.nodepool_count
    vm_size         = var.nodepool_vm_size
  }

  service_principal {
    client_id     = var.client_id
    client_secret = var.client_secret
  }

  role_based_access_control_enabled = true
}