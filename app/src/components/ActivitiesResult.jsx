

const ActivitiesResult = ({ activities }) => {
  const loading = activities == null;
  
  return (
    <div>
      <h2>Activities</h2>
      {loading ? (
        <div>Loading...</div>
      ) : (
        <div>
          {activities.options
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

export default ActivitiesResult;
