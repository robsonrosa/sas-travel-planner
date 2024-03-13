import { ResultContainer, ResultHeader, LoadingMessage, OptionRating, OptionItem, OptionHeader, OptionData } from '@styles/ResultStyle';

const AccommodationsResult = ({ accommodations }) => {
  const loading = accommodations == null;
  
  return (
    <ResultContainer>
      <ResultHeader>Accommodations</ResultHeader>
      {loading ? (
        <LoadingMessage>Loading...</LoadingMessage>
        ) : (
          <div>
          {accommodations.options
            .sort((a, b) => b.score - a.score)
            .map((option, index) => (
              <OptionItem key={index}>
                <OptionHeader>Option {index + 1}</OptionHeader>
                <OptionRating>Rating: {option.rating}</OptionRating>
                <OptionData>Name: {option.name}</OptionData>
                <OptionData>Type: {option.type}</OptionData>
              </OptionItem>
            ))}
        </div>
      )}
    </ResultContainer>
  );
};

export default AccommodationsResult;
