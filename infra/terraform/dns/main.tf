resource "azurerm_dns_zone" "dns" {
  name                = var.public_domain
  resource_group_name = var.resource_group_name
}

resource "azurerm_dns_a_record" "main" {
  name                = "@"
  zone_name           = azurerm_dns_zone.dns.name
  resource_group_name = var.resource_group_name
  ttl                 = 300
  records             = ["20.120.202.177"]
}

resource "azurerm_dns_a_record" "api" {
  name                = "api"
  zone_name           = azurerm_dns_zone.dns.name
  resource_group_name = var.resource_group_name
  ttl                 = 300
  records             = ["20.120.202.255"]
}


resource "azurerm_dns_cname_record" "www" {
  name                = "www"
  zone_name           = azurerm_dns_zone.dns.name
  resource_group_name = var.resource_group_name
  ttl                 = 300
  record              = var.public_domain
}