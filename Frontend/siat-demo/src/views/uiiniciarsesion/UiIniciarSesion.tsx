//UiIniciarSesion.tsx
import React, { Component    } from 'react';
import { UiIniciarSesionProps } from './UiIniciarSesionProps';
import { UiIniciarSesionState } from './UiIniciarSesionState';
import '../../resources/css/UiIniciarSesion.css';
import '../../resources/css/UiButton.css' 

export class UiIniciarSesion extends Component<UiIniciarSesionProps, UiIniciarSesionState> {
  
  componentDidMount() {
    // Código que necesita ejecutarse después de que el componente se monte.
    console.log('UiIniciarSesion se ha montado');
  }

  state: UiIniciarSesionState = {
    email: '',
    password: '',
  };

  handlerLogin = (event: React.FormEvent) => {
    event.preventDefault();
    const { email, password } = this.state;
    if (this.isValidData(email, password)) {
      this.login(email, password);
    } else {
      window.alert('Por favor, complete ambos campos. plantilla');
    }
  }

  login(email: string, password: string): void {
    // Lógica para iniciar sesión
    window.alert("Click a plantilla iniciar sesión plantilla");
    //console.log(`Iniciando sesión con email: ${email} y contraseña: ${password}`);
    localStorage.setItem('jona_authenticated','true');
    window.location.href = '/';
  }

  irCrearCuenta(): void {
    // Lógica para ir a la página de creación de cuenta
    console.log('Navegando a la página de creación de cuenta plantilla');
  }

  irRecuperarClave(): void {
    // Lógica para ir a la página de recuperación de contraseña
    console.log('Navegando a la página de recuperación de contraseña plantilla');
  }

  isValidData(email: string, password: string): boolean {
    // Lógica para validar los datos de inicio de sesión
    return email !== '' && password !== '';
  }

  handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({ email: e.target.value });
  };

  handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({ password: e.target.value });
  };

  render() {
    const { email, password } = this.state;

    return (
      <div className="container">
        <form className="form">
          <div className="form-group">
            <label htmlFor="email" className="label">Email</label>
            <input
              className="input"
              required              
              id="txtEmail"
              type="email"
              value={email}
              onChange={this.handleEmailChange}
            />
          </div>
          <div className="form-group">
            <label htmlFor="password" className="label">Password</label>
            <input
              className="input"
              required              
              id="txtPassword"
              type="password"
              value={password}
              onChange={this.handlePasswordChange}
            />
          </div>
          <div className="button-container">
            <button 
              type="submit" 
              className="button button-red" 
              onClick={this.handlerLogin}
            >
              Login
            </button>
          </div>
          <div className="w-full flex gap-2 hidden">
            <button 
              type="button" 
              className="button-red" 
              onClick={this.handlerLogin}
            >
              Crear cuenta
            </button>
            <button 
              type="button"
              className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" 
              onClick={this.handlerLogin}
            >
              Recuperar contraseña
            </button>
          </div>
        </form>
      </div>
    );
  }
}

//export default UiIniciarSesion;