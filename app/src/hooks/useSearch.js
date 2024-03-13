import { useState } from 'react';
import { SSE } from 'sse.js';

const eventListeners = {
  criteria: { key: 'criteria' },
  destination: { key: 'destinations' },
  flightplan: { key: 'flightPlans' },
  accommodation: { key: 'accommodations' },
  activity: { key: 'activities' },
  traveltip: { key: 'travelTips' },
  optimal: { key: 'optimal', finished: true },
}

const method = 'POST'
const headers = {
  'Content-Type': 'application/json',
  'Accept': 'text/event-stream',
};

const useSearch = () => {
  const [eventSource, setEventSource] = useState('');
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
    const url = `${process.env.REACT_APP_SERVER_URL}/api/v1/travel`;
    const payload = JSON.stringify({ searchTerm });
    const request = { headers, payload, method };

    console.info('url', url);
    console.info('request', request);

    const eventSource = new SSE(url, request);

    // start
    setResult((prevResult) => ({ ...prevResult, started: true }));

    // get events
    eventSource.onmessage = (event) => {
      console.info('onmessage', event.data)
      const { type, data } = JSON.parse(event.data);
      const { key, finished } = eventListeners[type];
      console.info(key, data)
      setResult((prevResult) => ({ ...prevResult, [key]: JSON.parse(data), finished }));
    }

    // onerror
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
