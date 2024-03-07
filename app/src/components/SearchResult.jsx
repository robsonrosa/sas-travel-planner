import TravelCriteria from "./TravelCriteria";
import DestinationsResult from "./DestinationsResult";
import FlightPlansResult from "./FlightPlansResult";
import AccommodationsResult from "./AccomodationsResult";
import ActivitiesResult from "./ActivitiesResult";
import TravelTipsResult from "./TravelTipsResult";

const SearchResult = ({ result }) => {

  if (!result?.started) {
    return;
  }

  const loading = result.started && !result.criteria

  return (
    <div>
      {loading ? (
        <div>Loading...</div>
      ) : (
        <div>
          <TravelCriteria criteria={result.criteria} />
          <DestinationsResult destinations={result.destinations} />
          <FlightPlansResult flightPlans={result.flightPlans} />
          <AccommodationsResult accommodations={result.accommodations} />
          <ActivitiesResult activities={result.activities} />
          <TravelTipsResult travelTips={result.travelTips} />
        </div>)}
    </div>
  );
};

export default SearchResult;