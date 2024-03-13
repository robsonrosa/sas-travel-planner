import { ResultContainer, ResultHeader, LoadingMessage, OptionRating, OptionItem, OptionHeader, OptionData } from '@styles/ResultStyle';

const FlightPlansResult = ({ flightPlans }) => {
  const loading = flightPlans == null;

  return (
    <ResultContainer>
      <ResultHeader>Flight Plans</ResultHeader>
      {loading ? (
        <LoadingMessage>Loading...</LoadingMessage>
      ) : (
        <div>
          {flightPlans.options
            .sort((a, b) => b.score - a.score)
            .map((option, index) => (
              <OptionItem key={index}>
                <OptionHeader>Option {index + 1}</OptionHeader>
                <OptionRating>Rating: {option.rating}</OptionRating>
                <OptionData>
                  Outward Flight: {option.outwardFlight.departure} -{' '}
                  {option.outwardFlight.arrival}
                </OptionData>
                <OptionData>
                  Return Flight: {option.returnFlight.departure} -{' '}
                  {option.returnFlight.arrival}
                </OptionData>
              </OptionItem>
            ))}
        </div>
      )}
    </ResultContainer>
  );
};

export default FlightPlansResult;