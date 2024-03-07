import TravelCriteria from "./TravelCriteriaComponent";
import DestinationsResult from "./DestinationsResultComponent";
import FlightPlansResult from "./FlightPlansResultComponent";
import AccommodationsResult from "./AccomodationsResultComponent";
import ActivitiesResult from "./ActivitiesResultComponent";
import TravelTipsResult from "./TravelTipsResultComponent";

const Planning = ({ result }) => {

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

export default Planning;