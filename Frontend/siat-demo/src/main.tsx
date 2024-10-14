import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import './resources/css/index.css';

const rootElement = document.getElementById('root')!;
const root = ReactDOM.createRoot(rootElement);

// Renderización inicial
root.render(<App />);

// Habilitar HMR usando la API clásica de Webpack
if (module.hot) {
  module.hot.accept('./App', () => {
    const NextApp = require('./App').default;
    root.render(<NextApp />);
  });
}
