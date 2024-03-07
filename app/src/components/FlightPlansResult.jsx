const FlightPlansResult = ({ flightPlans }) => {
  const loading = flightPlans == null;

  return (
    <div>
      <h2>Flight Plans</h2>
      {loading ? (
        <div>Loading...</div>
      ) : (
        <div>
          {flightPlans.options
            .sort((a, b) => b.score - a.score)
            .map((option, index) => (
              <div key={index}>
                <h3>Option {index + 1}</h3>
                <p>Rating: {option.rating}</p>
                <p>
                  Outward Flight: {option.outwardFlight.departure} -{' '}
                  {option.outwardFlight.arrival}
                </p>
                <p>
                  Return Flight: {option.returnFlight.departure} -{' '}
                  {option.returnFlight.arrival}
                </p>
              </div>
            ))}
        </div>
      )}
    </div>
  );
};

export default FlightPlansResult;