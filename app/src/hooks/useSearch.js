import { useState } from 'react';

const eventListeners = [
  { event: 'criteria', key: 'criteria' },
  { event: 'destination', key: 'destinations' },
  { event: 'flightplan', key: 'flightPlans' },
  { event: 'accommodation', key: 'accommodations' },
  { event: 'activity', key: 'activities' },
  { event: 'traveltip', key: 'travelTips' },
  { event: 'optimal', key: 'optimal', finished: true },
];

const useSearch = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [result, setResult] = useState({
    started: false,
    finished: false,
    criteria: null,
    destinations: null,
    flightPlans: null,
    accommodations: null,
    activities: null,
    travelTips: null,
    optimal: null,
  });

  const handleSearch = () => {
    const eventSource = new EventSource(`${process.env.REACT_APP_SERVER_URL}/api/travel`);
    setResult((prevResult) => ({ ...prevResult, started: true }));

    const addEventListener = ({ event, key, finished }) => {
      eventSource.addEventListener(event, ({ data }) => {
        setResult((prevResult) => ({ ...prevResult, [key]: data, finished }));
      });
    };
    eventListeners.forEach(addEventListener);

    eventSource.onerror = (error) => {
      setResult((prevResult) => ({ ...prevResult, finished: true }));
    };

    return () => {
      eventSource.close();
    };
  };

  const handleButtonClick = () => {
    handleSearch();
  };

  return { result, searchTerm, setSearchTerm, handleButtonClick };
};

export default useSearch;
