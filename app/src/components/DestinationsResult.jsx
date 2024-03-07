const DestinationsResult = ({ destinations }) => {
  const loading = destinations == null;

  return (
    <div>
      <h2>Destinations</h2>
      {loading ? (
        <div>Loading...</div>
      ) : (
        <div>
          {destinations.options
            .sort((a, b) => b.score - a.score)
            .map((option, index) => (
              <div key={index}>
                <h3>Option {index + 1}</h3>
                <p>Rating: {option.rating}</p>
                <p>Code: {option.code}</p>
                <p>Destination: {option.destination}</p>
              </div>
            ))}
        </div>
      )}
    </div>
  );
};

export default DestinationsResult;

