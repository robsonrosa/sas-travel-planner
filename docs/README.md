# SAS Travel Planner

## Features

### Search Criteria Extraction
- Based on a single text, the system provides appropriate resource criteria (other features).

### Destination Recommendation
- Based on criteria like budget, date, weather, language, and culture, the system provides destination options.

### Flight Recommendation
- Based on criteria like budget, date, and destination, the system provides flight options.

### Accommodation Recommendation
- Based on criteria like budget, date, destination, and lifestyle, the system provides accommodation options.

### Activities Recommendation
- Based on criteria like budget, date, destination, and lifestyle, the system provides activity options.

### Travel Tips Recommendation
- Based on criteria like budget, date, and destination, the system provides travel tips, including documentation, vaccines, and warnings.

### User Authentication
- Provides user registration and authentication.

## Models

### SearchTerm
- Simple string input using natural language.

### SearchCriteria
- The result of criteria extraction from SearchTerm.
- The input for every travel resource option provider.

### Destination
- The result of the search for destination options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

### Flight
- The result of the search for flight options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

### Accommodation
- The result of the search for accommodation options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

### Activity
- The result of the search for activity options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

### TravelTip
- Simple string text containing useful information for a specific travel.
- The result of the search for activity options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

### Travel
- Represents the whole information given a search criteria.
- It includes destination options, flight options, accommodation options, activity options, and travel tips.
- It includes the search criteria itself.
