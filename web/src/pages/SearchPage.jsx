import React, { useState } from 'react';
import Planning from '@components/PlanningComponent';
import Search from '@components/SearchComponent';

const SearchPage = () => {

  const [result, setResult] = useState();
  
  return (
    <div>
      <Search onSearch={setResult} />
      <Planning result={result} />
    </div>
  );
};

export default SearchPage;