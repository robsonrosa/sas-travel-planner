import { ResultContainer, ResultHeader, LoadingMessage, OptionRating, OptionItem, OptionHeader, OptionData } from '@styles/ResultStyle';

const DestinationsResult = ({ destinations }) => {
  const loading = destinations == null;

  return (
    <ResultContainer>
      <ResultHeader>Destinations</ResultHeader>
      {loading ? (
        <LoadingMessage>Loading...</LoadingMessage>
      ) : (
        <div>
          {destinations.options
            .sort((a, b) => b.score - a.score)
            .map((option, index) => (
              <OptionItem key={index}>
                <OptionHeader>Option {index + 1}</OptionHeader>
                <OptionRating>Rating: {option.rating}</OptionRating>
                <OptionData>Code: {option.code}</OptionData>
                <OptionData>Destination: {option.destination}</OptionData>
              </OptionItem>
            ))}
        </div>
      )}
    </ResultContainer>
  );
};

export default DestinationsResult;

