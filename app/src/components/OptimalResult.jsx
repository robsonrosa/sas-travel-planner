const OptimalResult = ({ optimal }) => {
  const loading = optimal == null;
  
  return (
    <div>
      <h2>Optimal</h2>
      {loading ? (
        <div>Loading...</div>
        ) : (
          <div>
          <h3>Destination: {optimal.destination.destination}</h3>
          <p>Rating: {optimal.destination.rating}</p>
          {/* Renderizar outros detalhes específicos para Optimal */}
        </div>
      )}
    </div>
  );
};

export default OptimalResult;