
const AccommodationsResult = ({ accommodations }) => {
  const loading = accommodations == null;
  
  return (
    <div>
      <h2>Accommodations</h2>
      {loading ? (
        <div>Loading...</div>
        ) : (
          <div>
          {accommodations.options
            .sort((a, b) => b.score - a.score)
            .map((option, index) => (
              <div key={index}>
                <h3>Option {index + 1}</h3>
                <p>Rating: {option.rating}</p>
                <p>Name: {option.name}</p>
                <p>Type: {option.type}</p>
              </div>
            ))}
        </div>
      )}
    </div>
  );
};

export default AccommodationsResult;
