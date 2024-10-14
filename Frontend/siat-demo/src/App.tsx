import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';
import UiHome from './views/uihome/UiHome';
import UiSesionHome from './views/uisesionhome/UiSesionHome';
import ProtectedRoute from './ProtectedRouter';

//const uiHome = new UiHome();
//const UiHome = React.lazy(() => import('./views/uihome/UiHome'));

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
          <Route path='/' element={<ProtectedRoute />}>
            <Route index element={
              <>
                <UiSesionHome />
              </>
              } />
          </Route>
          <Route path='/login' element={<UiHome />}/>
        </Routes>
    </BrowserRouter>
   
  );
}

export default App;