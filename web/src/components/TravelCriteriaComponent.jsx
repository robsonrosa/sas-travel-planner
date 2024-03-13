import { ResultContainer, ResultHeader, LoadingMessage } from '@styles/ResultStyle';

const TravelCriteria = ({ criteria }) => {
  const loading = criteria == null;

  return (
    <ResultContainer>
      <ResultHeader>Travel Criteria</ResultHeader>
      {loading ? (
        <LoadingMessage>Loading...</LoadingMessage>
      ) : (
        <div>
          <ul>
            <li>Search Term: {criteria.searchTerm}</li>
            <li>
              Weather:
              <ul>
                <li>Min Temperature: {criteria.weather.temperature.min}</li>
                <li>Max Temperature: {criteria.weather.temperature.max}</li>
                <li>Conditions: {criteria.weather.conditions.join(', ')}</li>
              </ul>
            </li>
            <li>Language Proficiencies: {criteria.language.languageProficiencies.join(', ')}</li>
            <li>Budget Classification: {criteria.budget.classification}</li>
            <li>
              Lifestyle:
              <ul>
                <li>Accommodation Preferences: {criteria.lifestyle.accommodationPreferences.join(', ')}</li>
                <li>Food Preferences: {criteria.lifestyle.foodPreferences.join(', ')}</li>
              </ul>
            </li>
            <li>
              Departure Date Range:
              <ul>
                <li>Start Date: {criteria.departureDateRange.startDate}</li>
                <li>End Date: {criteria.departureDateRange.endDate}</li>
              </ul>
            </li>
            <li>
              Arrival Date Range:
              <ul>
                <li>Start Date: {criteria.arrivalDateRange.startDate}</li>
                <li>End Date: {criteria.arrivalDateRange.endDate}</li>
              </ul>
            </li>
            <li>
              Activity:
              <ul>
                <li>Activity Preferences: {criteria.activity.activityPreferences.join(', ')}</li>
                <li>Difficulty Level: {criteria.activity.difficultyLevel}</li>
              </ul>
            </li>
            <li>Culture Preferences: {criteria.culture.culturalPreferences.join(', ')}</li>
            <li>With Pets: {criteria.withPets ? 'Yes' : 'No'}</li>
            <li>With Children: {criteria.withChildren ? 'Yes' : 'No'}</li>
          </ul>
        </div>
      )}
    </ResultContainer>
  );
};

export default TravelCriteria;
