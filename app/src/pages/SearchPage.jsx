import React, { useState } from 'react';
import SearchResult from '@components/SearchResult';
import SearchComponent from '@components/SearchComponent';

const SearchPage = () => {

  const [result, setResult] = useState();
  
  return (
    <div>
      <SearchComponent onSearch={setResult} />
      <SearchResult result={result} />
    </div>
  );
};

export default SearchPage;