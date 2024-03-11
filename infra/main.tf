resource "azurerm_resource_group" "rg" {
  name     = "sas"
  location = var.location
}

resource "azurerm_cosmosdb_account" "account" {
  name                            = var.db_account_name
  location                        = var.location
  resource_group_name             = azurerm_resource_group.rg.name
  offer_type                      = "Standard"
  kind                            = "GlobalDocumentDB"
  enable_automatic_failover       = false
  enable_multiple_write_locations = false
  geo_location {
    location          = var.location
    failover_priority = 0
  }
  consistency_policy {
    consistency_level       = "Session"
    max_interval_in_seconds = 10
    max_staleness_prefix    = 200
  }
  depends_on = [
    azurerm_resource_group.rg
  ]
}

resource "azurerm_cosmosdb_sql_database" "db" {
  name                = var.db_name
  resource_group_name = azurerm_resource_group.rg.name
  account_name        = azurerm_cosmosdb_account.account.name
}

resource "azurerm_cosmosdb_sql_container" "destinations" {
  name                  = "destinations"
  resource_group_name   = azurerm_resource_group.rg.name
  account_name          = azurerm_cosmosdb_account.account.name
  database_name         = azurerm_cosmosdb_sql_database.db.name
  partition_key_path    = "/code"
}

resource "azurerm_cosmosdb_sql_container" "accommodations" {
  name                  = "accommodations"
  resource_group_name   = azurerm_resource_group.rg.name
  account_name          = azurerm_cosmosdb_account.account.name
  database_name         = azurerm_cosmosdb_sql_database.db.name
  partition_key_path    = "/type"
}

resource "azurerm_cosmosdb_sql_container" "activities" {
  name                  = "activities"
  resource_group_name   = azurerm_resource_group.rg.name
  account_name          = azurerm_cosmosdb_account.account.name
  database_name         = azurerm_cosmosdb_sql_database.db.name
  partition_key_path    = "/code"
}

resource "azurerm_cosmosdb_sql_container" "tips" {
  name                  = "tips"
  resource_group_name   = azurerm_resource_group.rg.name
  account_name          = azurerm_cosmosdb_account.account.name
  database_name         = azurerm_cosmosdb_sql_database.db.name
  partition_key_path    = "/destinationCode"
}

resource "azurerm_cosmosdb_sql_container" "flights" {
  name                  = "flights"
  resource_group_name   = azurerm_resource_group.rg.name
  account_name          = azurerm_cosmosdb_account.account.name
  database_name         = azurerm_cosmosdb_sql_database.db.name
  partition_key_path    = "/code"
}

resource "azurerm_cosmosdb_sql_container" "airports" {
  name                  = "airports"
  resource_group_name   = azurerm_resource_group.rg.name
  account_name          = azurerm_cosmosdb_account.account.name
  database_name         = azurerm_cosmosdb_sql_database.db.name
  partition_key_path    = "/code"
}
