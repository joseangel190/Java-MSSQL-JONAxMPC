/* React */
import React from 'react';

/* Imagenes */
//import {Logo} from './src/resources/images/logo-minjus.jpg';

/* Interfaces */
import { TitleBarProps } from './InterUiTitleBar';

/* Estilos */
import '../../resources/css/UiTitleBar.css'

const UiTitleBar: React.FC<TitleBarProps> = () => {
  return (
    <>
      <header className="header">
        <div className="container">
          <div className="logo">
          </div>
          <div className="title">
            <h3>SIAT</h3>
          </div>
        </div>
      </header>
    </>
  );
};

export default UiTitleBar;