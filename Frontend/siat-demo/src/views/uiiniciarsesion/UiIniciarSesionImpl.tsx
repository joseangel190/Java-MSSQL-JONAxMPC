//UiIniciarSesionImpl.tsx
import  {UiIniciarSesion}  from './UiIniciarSesion';
import {UiIniciarSesionProps} from './UiIniciarSesionProps';

/* Servicios API */
import { getLogin } from '../../services/api-auth/auth';

// Clase de implementación que hereda de la clase plantilla UiIniciarSesion
export class UiIniciarSesionImpl extends UiIniciarSesion {

  componentDidMount() {
    // Código que necesita ejecutarse después de que el componente se monte.
    console.log('UiIniciarSesionImpl se ha montado');
  }

  // Sobrescribir el método de inicio de sesión  
  login = async (email: string, password: string): Promise<void> => {
    //window.alert("New Click a iniciar sesión");
    //console.log(`New Iniciando sesión desde la clase de implementación con email: ${email} y contraseña: ${password}`);
    // Aquí puedes agregar la lógica específica de inicio de sesión, como enviar una solicitud al servidor
    try {
      // Lógica de autenticación (supongamos que aquí hay una llamada a una API)
      const response = await getLogin({email, password})
      
      if (response) {
        // Autenticación exitosa, redirigir a la página de inicio
        window.location.href = '/';
      } else {
        // Mostrar mensaje de error
        window.alert(response.error.message || 'Error de inicio de sesión');
        //window.alert('Error de inicio de sesión');
      }
    } catch (error) {
      console.error('Error al iniciar sesión:', error);
      window.alert('Error al iniciar sesión');
    }
  }  

  render() {
    return (
      <div>
        {super.render()}
      </div>
    );
  }
}