const { MockEvent, EventSource } = require('mocksse');

function generateRandomUUID() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    const r = (Math.random() * 16) | 0;
    const v = c === 'x' ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
}

function getRandomValue(values) {
  return values[Math.floor(Math.random() * values.length)];
}

function getRandomCountry() {
  const countries = ['United States', 'Canada', 'United Kingdom', 'Australia', 'Germany', 'France', 'Japan', 'Brazil', 'India', 'South Africa'];
  return getRandomValue(countries);
}

function getRandomAccomodationType() {
  const countries = ['APARTMENT_HOTEL', 'B_AND_B', 'BOUTIQUE_HOTEL', 'GUEST_HOUSE', 'HOSTEL', 'HOTEL', 'INN', 'RESORT', 'VACATION_RENTAL'];
  return getRandomValue(countries);
}

function getRandomActivityType() {
  const countries = ['ADVENTURE_ACTIVITIES', 'ART_AND_CRAFT_WORKSHOPS', 'CULTURAL_TOURS', 'ENTERTAINMENT_SHOWS', 'FAMILY_FRIENDLY', 'HISTORICAL_TOURS', 'NATURE_EXCURSIONS', 'SHOPPING_DISTRICTS', 'SPORTS_AND_RECREATION', 'WATER_SPORTS', 'WELLNESS_RETREATS'];
  return getRandomValue(countries);
}

function getRandomActivityDifficult() {
  const countries = ['ADVANCED', 'CHALLENGING', 'EASY', 'EXPERT', 'MODERATE'];
  return getRandomValue(countries);
}

function generateAirportCode() {
  const alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
  return Array.from({ length: 3 }, () => alphabet[Math.floor(Math.random() * alphabet.length)]).join('');
}

function generateRandomName() {
  const adjectives = ['Happy', 'Sunny', 'Cozy', 'Vibrant', 'Playful', 'Fiery', 'Giddy', 'Dynamic', 'Chirpy', 'Breezy'];
  const animals = ['Cat', 'Dog', 'Tiger', 'Elephant', 'Koala', 'Penguin', 'Giraffe', 'Cheetah', 'Owl', 'Fox'];

  const randomAdjective = getRandomValue(adjectives);
  const randomAnimal = getRandomValue(animals);

  return `${randomAdjective} ${randomAnimal}`;
}

function generateRandomFutureDate() {
  const futureDate = new Date(Date.now() + Math.floor(Math.random() * 30) * 86400000);
  return futureDate.toISOString().split('T')[0];
}

function generateArray(objectSupplier) {
  const numberOfExecutions = Math.random() * 10;
  return Array.from({ length: numberOfExecutions }, objectSupplier);
}

const criteria = {
  searchTerm: '',
  weather: {
    temperature: {
      min: 1,
      max: 15
    },
    conditions: []
  },
  language: {
    languageProficiencies: []
  },
  budget: {
    classification: 'LOW'
  },
  lifestyle: {
    accommodationPreferences: [],
    foodPreferences: []
  },
  departureDateRange: {
    startDate: generateRandomFutureDate(),
    endDate: generateRandomFutureDate()
  },
  arrivalDateRange: {
    startDate: generateRandomFutureDate(),
    endDate: generateRandomFutureDate()
  },
  activity: {
    activityPreferences: [],
    difficultyLevel: 'EASY'
  },
  culture: {
    culturalPreferences: ['ETHNIC_DIVERSITY']
  },
  withPets: Math.random() > 0.5,
  withChildren: Math.random() > 0.5,
}

function getOption() {
  return {
    score: Math.random() * 4 + 1,
    rating: Math.random() * 4 + 1
  }
}

function getDestination() {
  return {
    ...getOption(),
    destination: getRandomCountry(),
    code: generateRandomUUID()
  }
}

function getFlightPlan() {
  return {
    ...getOption(),
    returnFlight: {
      code: generateRandomUUID(),
      departure: generateRandomFutureDate(),
      arrival: generateRandomFutureDate(),
      airport: generateAirportCode()
    },
    outwardFlight: {
      code: generateRandomUUID(),
      departure: generateRandomFutureDate(),
      arrival: generateRandomFutureDate(),
      airport: generateAirportCode()
    }
  }
}

function getAccommodation() {
  return {
    ...getOption(),
    type: getRandomAccomodationType(),
    code: generateRandomUUID(),
    name: generateRandomName(),
    address: ''
  }
}

function getActivity() {
  return {
    ...getOption(),
    code: generateRandomUUID(),
    name: generateRandomName(),
    description: '',
    address: '',
    type: getRandomActivityType(),
    difficulty: getRandomActivityDifficult()
  }
}

function getTravelTip() {
  return {
    ...getOption(),
    content: 'tip'
  }
}

const destinations = {
  criteria,
  options: generateArray(getDestination)
}

const flightPlans = {
  criteria,
  options: generateArray(getFlightPlan)
}

const accommodations = {
  criteria,
  options: generateArray(getAccommodation)
}

const activities = {
  criteria,
  options: generateArray(getActivity)
}

const travelTips = {
  criteria,
  options: generateArray(getTravelTip)
}

const optimal = {
  criteria,
  destination: getDestination(),
  flightPlan: getFlightPlan(),
  accommodation: getAccommodation(),
  activities: activities,
  tips: travelTips
}

const mockData = [
  { type: 'criteria', data: criteria },
  { type: 'destination', data: destinations },
  { type: 'flightplan', data: flightPlans },
  { type: 'accommodation', data: accommodations },
  { type: 'activity', data: activities },
  { type: 'traveltip', data: travelTips },
  { type: 'optimal', data: optimal },
];

export const setupMockSSE = (interval) => {

  new MockEvent({
    url: `${process.env.REACT_APP_SERVER_URL}/api/v1/travel`,
    setInterval: interval,
    responses: mockData
  });
  window.EventSource = EventSource;

}
