import { ResultContainer, ResultHeader, LoadingMessage, OptionRating, OptionItem, OptionHeader, OptionData } from '@styles/ResultStyle';

const ActivitiesResult = ({ activities }) => {
  const loading = activities == null;
  
  return (
    <ResultContainer>
      <ResultHeader>Activities</ResultHeader>
      {loading ? (
        <LoadingMessage>Loading...</LoadingMessage>
      ) : (
        <div>
          {activities.options
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

export default ActivitiesResult;
