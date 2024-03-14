resource "azurerm_dns_zone" "dns" {
  name                = var.public_domain
  resource_group_name = var.resource_group_name
}

resource "azurerm_dns_cname_record" "www" {
  name                = "www"
  zone_name           = azurerm_dns_zone.dns.name
  resource_group_name = var.resource_group_name
  ttl                 = 300
  record              = var.public_domain
}

resource "azurerm_public_ip" "api-ip" {
  name                = "api-ip"
  resource_group_name = var.resource_group_name
  location            = var.location
  allocation_method   = "Static"
  sku                 = "Standard"
}

resource "azurerm_public_ip" "web-ip" {
  name                = "web-ip"
  resource_group_name = var.resource_group_name
  location            = var.location
  allocation_method   = "Static"
  sku                 = "Standard"
}

resource "azurerm_dns_a_record" "api" {
  name                = "api"
  zone_name           = azurerm_dns_zone.dns.name
  resource_group_name = var.resource_group_name
  ttl                 = 300
  target_resource_id  = azurerm_public_ip.api-ip.id
}

resource "azurerm_dns_a_record" "web" {
  name                = "@"
  zone_name           = azurerm_dns_zone.dns.name
  resource_group_name = var.resource_group_name
  ttl                 = 300
  target_resource_id  = azurerm_public_ip.web-ip.id
}
