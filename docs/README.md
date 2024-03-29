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

```yaml
components:
  schemas:
    SearchTerm:
      type: string
      description: Represents the search term used to specify the trip's requirements.
```

### TravelPlanningCriteria
- The result of criteria extraction from SearchTerm.
- The input for every travel resource option provider.

```yaml
components:
  schemas:
    TravelPlanningCriteria:
      type: object
      description: Represents the criteria used for planning a trip.
      properties:
        searchTerm:
          $ref: '#/components/schemas/SearchTerm'
        weather:
          $ref: '#/components/schemas/WeatherCriteria'
        language:
          $ref: '#/components/schemas/LanguageCriteria'
        budget:
          $ref: '#/components/schemas/BudgetCriteria'
          description: The budget constraint for the trip.
        lifestyle:
          $ref: '#/components/schemas/LifestyleCriteria'
        departureDateRange:
          $ref: '#/components/schemas/DateRange'
          description: Range of dates for the departure.
        arrivalDateRange:
          $ref: '#/components/schemas/DateRange'
          description: Range of dates for the arrival.
        activity:
          $ref: '#/components/schemas/ActivityCriteria'
        culture:
          $ref: '#/components/schemas/CulturalCriteria'
        withPets:
          type: boolean
          description: Indicates whether the traveler will be accompanied by pets.
        withChildren:
          type: boolean
          description: Indicates whether the traveler will be accompanied by children.
      example: 'Adventure travel with kids in Europe at December'

    DateRange:
      type: object
      description: Represents a range of dates.
      properties:
        startDate:
          type: string
          format: date
          description: Start date of the range.
        endDate:
          type: string
          format: date
          description: End date of the range.

    WeatherCriteria:
      type: object
      description: Represents criteria related to weather conditions.
      properties:
        temperature:
          type: object
          properties:
            min:
              type: number
              description: Minimum desired temperature.
            max:
              type: number
              description: Maximum desired temperature.
        conditions:
          type: array
          items:
            type: string
          description: List of desired weather conditions.

    BudgetCriteria:
      type: object
      description: Represents budget constraints for the trip.
      properties:
        classification:
          type: string
          description: The classification of how much the trip can cost.
          enum:
            - "MINIMUM"
            - "LOW"
            - "MID_RANGE"
            - "MODERATE"
            - "HIGH"
            - "LUXURY"
            - "UNLIMITED"

    LifestyleCriteria:
      type: object
      description: Represents lifestyle preferences for accommodations and activities.
      properties:
        accommodationPreferences:
          type: array
          items:
            $ref: '#/components/schemas/AccomodationType'
        foodPreferences:
          type: array
          items:
            type: string
            enum:
              - "BUFFET"
              - "CAFE"
              - "CASUAL_DINING"
              - "ETHNIC_CUISINE"
              - "FAMILY_FRIENDLY"
              - "FARM_TO_TABLE"
              - "FAST_FOOD"
              - "FINE_DINING"
              - "FOOD_TRUCK"
              - "VEGETARIAN_VEGAN"
          description: List of preferred dining options.

    ActivityCriteria:
      type: object
      description: Represents preferences for activities during the trip.
      properties:
        activityPreferences:
          type: array 
          items:
            $ref: '#/components/schemas/ActivityType'
          description: Preferred types of activity.
        difficultyLevel:
            $ref: '#/components/schemas/ActivityDifficulty'

    CulturalCriteria:
      type: object
      properties:
        culturalPreferences:
          type: array
          items:
            type: string
            enum:
              - "ARCHITECTURAL_STYLES"
              - "ART_MUSEUMS"
              - "CONTEMPORARY_ARTS"
              - "CULINARY_TRADITIONS"
              - "CULTURAL_ETIQUETTE"
              - "CULTURAL_EXCHANGE"
              - "CULTURAL_HERITAGE_MUSEUMS"
              - "CULTURAL_LANDSCAPES"
              - "CULTURAL_PERFORMANCES"
              - "CULTURAL_WORKSHOPS"
              - "ETHNIC_DIVERSITY"
              - "HANDICRAFTS_ARTISAN_MARKETS"
              - "HERITAGE_PRESERVATION"
              - "HISTORICAL_SIGNIFICANCE"
              - "LANGUAGE_LITERATURE"
              - "LITERARY_FILM_CONNECTIONS"
              - "LOCAL_CLOTHING_FASHION"
              - "LOCAL_FESTIVALS_EVENTS"
              - "MUSIC_DANCE_FORMS"
              - "RELIGIOUS_HERITAGE"
      description: Represents cultural preferences for the destination.

    LanguageCriteria:
      type: object
      properties:
        languageProficiencies:
          type: array
          items:
            type: object
            properties:
              language:
                type: string
              proficiency:
                type: string
                enum:
                  - "BASIC"
                  - "INTERMEDIATE"
                  - "ADVANCED"
                  - "FLUENT"

    AccomodationType:
      type: string
      enum:
        - "APARTMENT_HOTEL"docs/REA
        - "B_AND_B"
        - "BOUTIQUE_HOTEL"
        - "GUEST_HOUSE"
        - "HOSTEL"
        - "HOTEL"
        - "INN"
        - "RESORT"
        - "VACATION_RENTAL"

    ActivityType:
      type: string
      enum:
        - "ADVENTURE_ACTIVITIES"
        - "ART_AND_CRAFT_WORKSHOPS"
        - "CULTURAL_TOURS"
        - "ENTERTAINMENT_SHOWS"
        - "FAMILY_FRIENDLY"
        - "HISTORICAL_TOURS"
        - "NATURE_EXCURSIONS"
        - "SHOPPING_DISTRICTS"
        - "SPORTS_AND_RECREATION"
        - "WATER_SPORTS"
        - "WELLNESS_RETREATS"

    ActivityDifficulty:
      type: string
      enum:
        - "ADVANCED"
        - "CHALLENGING"
        - "EASY"
        - "EXPERT"
        - "MODERATE"
      description: Difficulty level of the activity.
```

### Destination
- The result of the search for destination options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

```yaml
components:
  schemas:
    DestinationOptions:
      allOf:
        - $ref: '#/components/schemas/TravelResultBase'
        - type: object
            properties:
              options:
                type: array
                items:
                  $ref: '#/components/schemas/Destination'
        

    Destination:
      allOf:
        - $ref: '#/components/schemas/TravelOptionBase'
        - type: object
            description: Represents a trip destination recommendation.
            properties:
              destination:
                type: string
                description: The recommended destination for the trip.
              code:
                type: string
                description: The unique identifier of the destination.
    
    TravelResultBase:
      type: object
        criteria:
          $ref: '#/components/schemas/TravelPlanningCriteria'

    TravelOptionBase:
      type: object
      properties:
        score:
          $ref: '#/components/schemas/Score'
        rating:
          $ref: '#/components/schemas/Rating'

    Rating:
      type: number
      minimum: 1
      maximum: 5
      description: "A numerical rating between 1 and 5 (inclusive)."

    Score:
      type: number
      minimum: 1
      maximum: 5
      description: 'The similarity score indicating how well the result matches the user's criteria.'
```

### Flight
- The result of the search for flight options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

```yaml
components:
  schemas:
    FlightPlanOptions:
      allOf:
        - $ref: '#/components/schemas/TravelResultBase'
        - type: object
            properties:
              options:
                type: array
                items:
                  $ref: '#/components/schemas/FlightPlan'
        

    FlightPlan:
      allOf:
        - $ref: '#/components/schemas/TravelOptionBase'
        - type: object
            description: Represents a flight plan recommendation.
            properties:
              returnFlight:
                $ref: '#/components/schemas/Flight'
                description: Represents the return flight of the trip
              outwardFlight:
                $ref: '#/components/schemas/Flight'
                description: Represents the outward flight of the trip

    Flight:
      type: object
      properties:
        code: 
          type: string
          description: unique identifier of the flight
        departure:
          type: DateTime
        arrival:
          type: DateTime
        airport:
          $ref: '#/components/schemas/Airport'
    
    Airport:
      type: object
        properties:
          code: 
            type: string
            pattern: '^[A-Z]{3}$'
            description: unique identifier of the airport containing 3 capital letters
          name:
            type: string
          address:
            type: string
```

### Accommodation
- The result of the search for accommodation options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

```yaml
components:
  schemas:
    AccomodationOptions:
      allOf:
        - $ref: '#/components/schemas/TravelResultBase'
        - type: object
            properties:
              options:
                type: array
                items:
                  $ref: '#/components/schemas/Accomodation'
        

    Accomodation:
      allOf:
        - $ref: '#/components/schemas/TravelOptionBase'
        - type: object
            description: Represents an accomodation recommendation.
            properties:
              code: 
                type: string
                description: unique identifier of the accomodation
              name:
                type: string
              address:
                type: string
              type:
                $ref: '#/components/schema/AccomodationType'
```

### Activity
- The result of the search for activity options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

```yaml
components:
  schemas:
    ActivityOptions:
      allOf:
        - $ref: '#/components/schemas/TravelResultBase'
        - type: object
            properties:
              options:
                type: array
                items:
                  $ref: '#/components/schemas/Activity'
        

    Activity:
      allOf:
        - $ref: '#/components/schemas/TravelOptionBase'
        - type: object
            description: Represents an activity recommendation.
            properties:
              code: 
                type: string
                description: unique identifier of the activity
              name:
                type: string
              description:
                type: string
              address:
                type: string
              type:
                $ref: '#/components/schema/ActivityType'
              difficult:
                $ref: '#/components/schema/ActivityDifficult'
```

### TravelTip
- Simple string text containing useful information for a specific travel.
- The result of the search for activity options for the travel.
- It includes a score to identify how well it matches the search criteria.
- It includes the search criteria itself.

```yaml
components:
  schemas:
    TravelTips:
      allOf:
        - $ref: '#/components/schemas/TravelResultBase'
        - type: object
            properties:
              options:
                type: array
                items:
                  $ref: '#/components/schemas/TravelTip'
        

    TravelTip:
      allOf:
        - $ref: '#/components/schemas/TravelOptionBase'
        - type: object
          properties:
            content:
              type: string 
```

### Travel
- Represents the whole information given a search criteria.
- It includes destination options, flight options, accommodation options, activity options, and travel tips.
- It includes the search criteria itself.

```yaml
components:
  schemas:
    OptimalTravelPlanning:
      allOf:
        - $ref: '#/components/schemas/TravelResultBase'
        - type: object        
          description: Represents the best travel option based on search criteria.
          properties:
            destination:
              $ref: '#/components/schemas/Destination'
            flight:
              $ref: '#/components/schemas/FlightPlan'
            accomodation:
              $ref: '#/components/schemas/Accomodation'
            activities:
              type: array
              items:
                $ref: '#/components/schemas/Activity'
            tips:
              type: array
              items:
                $ref: '#/components/schemas/TravelTip'
        

```

## Database

### CosmosDB

- NoSQL
- Compatibility with Spring Boot Reactive
- Azure Cloud ready

## Microservices

- Initally a modularized monolith
- Spring Boot
- WebFlux
  - The search tends to be a computationally expensive blocking process
  - Non blocking database access (using R2DBC)
  - Easy Server Sent Events (SSE) implementation
- Modules:
  - TravelService (entrypoint)
  - CriteriaService
  - DestinationService
  - FlightService
  - AccommodationService
  - ActivityService
  - TravelTipService

## Frontend

- React.Js
- Javascript
- EventSource to communicate with SSE

