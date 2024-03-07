const TravelTipsResult = ({ travelTips }) => {
  const loading = travelTips == null;
  
  return (
    <div>
      <h2>Travel Tips</h2>
      {loading ? (
        <div>Loading...</div>
        ) : (
          <div>
          {travelTips.options
            .sort((a, b) => b.score - a.score)
            .map((tip, index) => (
              <div key={index}>
                <h3>Tip {index + 1}</h3>
                <p>Rating: {tip.rating}</p>
                <p>Content: {tip.content}</p>
              </div>
            ))}
        </div>
      )}
    </div>
  );
};

export default TravelTipsResult;
