import React from 'react';
//import Logo from '../../resources/images/logo-minjus.jpg'
import { getToken } from '../../methods/storage';
import { logout } from '../../services/api-auth/auth';
import { TitleBarProps } from './InterUiTitleBar';
import UiButton from '../../components/uibutton/UiButton';
import '../../resources/css/UiTitleBar.css'

const UiTitleBar: React.FC<TitleBarProps> = ({ data }) => {
  const auth = getToken()

  const handleLogout = async () => {
   const response =  await logout();
   if(response) {
    window.location.href = '/'
   }
  }

  return (
    <>
    { auth ? (
      <header className="header">
          <div className="container">
            <div className="logo">
              
            </div>
            <h1>Demo SIAT App</h1>
            <div className="menu">
              { data && (
                <div className="user-info">
                  <p className="user-username">{data.username}</p>
                  <p className="user-fullname">{data.firstname} {data.lastname}</p>
                </div>
              )}
              <UiButton
                  type={'button'}
                  text={''}
                  icon={'Power'}
                  callback={ handleLogout } 
                />
            </div>
          </div>
      </header>
    ) : ("")}
    </>
  );
};

export default UiTitleBar;