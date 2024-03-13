import { ResultContainer, ResultHeader, LoadingMessage } from '@styles/ResultStyle';

const OptimalDestination = styled.h3`
  color: #00f; // Cor azul para ênfase
`;

const OptimalResult = ({ optimal }) => {
  const loading = optimal == null;

  return (
    <ResultContainer>
      <ResultHeader>Optimal</ResultHeader>
      {loading ? (
        <LoadingMessage>Loading...</LoadingMessage>
      ) : (
        <div>
          <OptimalDestination>Destination: {optimal.destination.destination}</OptimalDestination>
          <OptionRating>Rating: {optimal.destination.rating}</OptionRating>
        </div>
      )}
    </ResultContainer>
  );
};

export default OptimalResult;