import { useEffect } from 'react';
import useSearch from '@hooks/useSearch';

const Search = ({ onSearch }) => {
  const { result, searchTerm, setSearchTerm, handleButtonClick } = useSearch();

  useEffect(() => {
    onSearch(result);
  }, [result, onSearch]);

  return (
    <div>
      <input
        type="text"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        placeholder="Enter search term"
      />
      <button onClick={handleButtonClick}>Search</button>
    </div>
  );
};

export default Search;