import React, { useState } from 'react';

const SearchPage = () => {
  const [query, setQuery] = useState('');
  const [result, setResult] = useState({
    flightplan: null,
    accommodation: null,
    activity: null,
    traveltip: null,
    optimal: null,
    destination: null
  });
  const [loading, setLoading] = useState(false);

  const handleSearch = async () => {
    try {
      setLoading(true);

      // const response = await fetch(`${process.env.REACT_APP_SERVER_URL}/api/travel`, {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json',
      //   },
      //   body: JSON.stringify({ query }),
      // });

      // console.log('RESPONSE: ', response);

      const eventSource = new EventSource(`${process.env.REACT_APP_SERVER_URL}/api/travel`);

      eventSource.onerror = (error) => {
        console.error('Error:', error);
      };
      
      eventSource.addEventListener('flightplan', ({ data }) => {
        setResult((prevResult) => ({ ...prevResult, flightplan: data }));
        console.log('flightplan', data);
      });
      eventSource.addEventListener('accommodation', ({ data }) => {
        setResult((prevResult) => ({ ...prevResult, accommodation: data }));
        console.log('accommodation', data);
      });
      eventSource.addEventListener('activity', ({ data }) => {
        setResult((prevResult) => ({ ...prevResult, activity: data }));
        console.log('activity', data);
      });
      eventSource.addEventListener('traveltip', ({ data }) => {
        setResult((prevResult) => ({ ...prevResult, traveltip: data }));
        console.log('traveltip', data);
      });
      eventSource.addEventListener('optimal', ({ data }) => {
        setResult((prevResult) => ({ ...prevResult, optimal: data }));
        console.log('optimal', data);
      });
      eventSource.addEventListener('destination', ({ data }) => {
        setResult((prevResult) => ({ ...prevResult, destination: data }));
        console.log('destination', data);
      });

      return () => {
        eventSource.close();
      };

    } catch (error) {
      console.error('Error:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Digite sua busca..."
      />
      <button onClick={handleSearch} disabled={loading}>
        Pesquisar
      </button>

      {loading && <div>Carregando...</div>}

      <pre>
        {JSON.stringify(result, null, 2)}
      </pre>
    </div>
  );
};

export default SearchPage;