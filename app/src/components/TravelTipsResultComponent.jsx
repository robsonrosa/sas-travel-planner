import { ResultContainer, ResultHeader, LoadingMessage, OptionRating, OptionItem, OptionHeader, OptionData } from '@styles/ResultStyle';

const TravelTipsResult = ({ travelTips }) => {
  const loading = travelTips == null;
  
  return (
    <ResultContainer>
      <ResultHeader>Travel Tips</ResultHeader>
      {loading ? (
        <LoadingMessage>Loading...</LoadingMessage>
        ) : (
          <div>
          {travelTips.options
            .sort((a, b) => b.score - a.score)
            .map((tip, index) => (
              <OptionItem key={index}>
                <OptionHeader>Tip {index + 1}</OptionHeader>
                <OptionRating>Rating: {tip.rating}</OptionRating>
                <OptionData>Content: {tip.content}</OptionData>
              </OptionItem>
            ))}
        </div>
      )}
    </ResultContainer>
  );
};

export default TravelTipsResult;
