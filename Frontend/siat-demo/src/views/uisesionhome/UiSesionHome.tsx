import React, { Component } from 'react';
import BorderLayout from '../../uilayouts/BorderLayout';
import Header from '../../uiutils/Header';
import Footer from '../../uiutils/Footer';
import UiParametriaGridImpl from '../uiparametriagrid/UiParametriaGridImpl';
import UiTitleBar from '../../uiutils/uititlebarsession/UiTitleBar';

class UiSesionHome extends Component {    
  
    render() {
        return (
          <BorderLayout
      north={<UiTitleBar />}
      south={<Footer />}
      center={<UiParametriaGridImpl />}
    />
        );
      }
}

export default UiSesionHome;