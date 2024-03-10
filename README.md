# SAS Travel Planner

## Overview

Welcome to the Travel Planner project! This application is designed to make your travel planning experience seamless and enjoyable. Whether you're a frequent globetrotter or planning your dream vacation, this tool is here to assist you in creating the perfect itinerary.

## Documenation

See [Official Documentation](../blob/master/docs/README.md)

## Roadmap

### Phase 0 - Design Model and API's
- [x] Define documentation
- [x] Design models
- [x] Design database
- [x] Define microservices and boundaries
- [x] Define communication between backend and frontend
- [ ] Define communication between microservices
- [x] Define endpoints and interactions
- [ ] Define how to deploy frontend application (simple nginx image or Azure Static Web Apps)

### Phase 1 - Frontend + Mock API
- [x] Create React.Js project
- [ ] Create simple login page
- [x] Create simple search criteria page
- [x] Create simple results page
- [ ] Create Dockerfile 
- [ ] Configure linter and prettier

### Phase 2 - Frontend + Backend + Mock Data
- [ ] Create Dockerfile for each deployable artifact
- [x] Java + Maven + Spring Boot + Spring Reactive
- [x] Parent project
- [x] Module boilerplate travel-service (entrypoint)
- [x] Module boilerplate accommodation-service
- [x] Module boilerplate activity-service
- [x] Module boilerplate criteria-service
- [x] Module boilerplate destination-service
- [x] Module boilerplate flight-service
- [x] Module boilerplate tips-service
- [x] Dummy endpoint to integrate with frontend
- [x] Module implementation travel-service (entrypoint)
- [x] Module implementation accommodation-service
- [x] Module implementation activity-service
- [x] Module implementation criteria-service
- [x] Module implementation destination-service
- [x] Module implementation flight-service
- [x] Module implementation tips-service
- [ ] Microservice travel-service (entrypoint)
- [ ] Microservice accommodation-service
- [ ] Microservice activity-service
- [ ] Microservice criteria-service
- [ ] Microservice destination-service
- [ ] Microservice flight-service
- [ ] Microservice tips-service

### Phase 3 - Frontend + Backend + Storage
- [ ] To be defined in Phase 0 

### Phase 4 - Frontend + Backend + Storage + Cloud Deployment
- [ ] Create resource group 
- [ ] Create and configure database (to be defined in Phase 0)
- [ ] Create and configure ACR
- [ ] Customize Dockerfiles to match Cloud requirements
- [ ] Deploy frontend application (to be defined in Phase 0)
- [ ] Create and configure AKS 
- [ ] Create and configure Github Actions

### Phase 5 - Enhancements
- [ ] Responsive design
- [ ] Enhance frontend validations and error handling
- [ ] Consider using Azure Monitor
- [ ] Consider using Azure Log Analytics
- [ ] Consider using Azure Key Vault
- [ ] Add repository badges
- [ ] Add SonarQube