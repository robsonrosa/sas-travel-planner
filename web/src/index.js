import React from 'react';
import ReactDOM from 'react-dom/client';
import SearchPage from '@pages/SearchPage';
import { setupMockSSE } from '@utils/MockEventSource';

if (process.env.NODE_ENV === 'development') {
  setupMockSSE(2000);
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <SearchPage />
  </React.StrictMode>
);