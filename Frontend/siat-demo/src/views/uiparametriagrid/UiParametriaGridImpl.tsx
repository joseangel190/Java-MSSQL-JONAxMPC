import React from "react";
import UiParametriaGrid from "./UiParametriaGrid";
import { UiParametriaGridProps } from "./UiParametriaGridProps";
import { getParametriasAll } from "../../services/api-mantenimientos/parametria";
import { exportarPdf, exportarExcel} from "../../services/api-mantenimientos/parametria";

class UiParametriaGridImpl extends UiParametriaGrid {
    constructor(props: UiParametriaGridProps) {
        super(props);
        this.state = {
            ...this.state,
            data: [],
            currentPage: 0,
            isLoading: false,
        };
    }

    loadingData = async (page: number = 0) => {
        console.log("loadingData");
        if (this.state.isLoading) return;
        this.setState({ isLoading: true });
        try {
            const data = await getParametriasAll();
            //console.log('handleScroll', page);
            //console.log('data', data);
            this.setState(prevState => {
                //console.log('prevState.data before update:', prevState.data);
                //console.log('New data:', data);
                return {
                    data: [...prevState.data, ...data],
                    currentPage: prevState.currentPage + 1,
                    isLoading: false
                }
            });
        } catch (error) {
            console.error("Error fetching data:", error);
            this.setState({ isLoading: false });
        }
    }

    reloadData = async () => {
        console.log("reloadData implement");
        this.setState({
            data: [],
            currentPage: 1,
            isLoading: false
        }, () => {
            this.loadingData();
        });
    }

    callbackModal = async () => {
        console.log('callback impl');
        this.setState({
            data: [],
            currentPage: 1,
            isLoading: false
        }, () => {
            this.loadingData();
        });
    }

    exportarPdf = async () =>{
        const response:any = await exportarPdf();
        const url = window.URL.createObjectURL(new Blob([response.data], { type: 'application/pdf' }));
        const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'reportParametria.pdf'); // Nombre del archivo que se descargará

            // Simular un clic en el enlace para iniciar la descarga
            link.style.display = 'none';
            document.body.appendChild(link);
            link.click();

            // Limpiar el enlace creado
            document.body.removeChild(link);
    }

    exportarExcel = async () =>{
        const response:any = await exportarExcel();
        const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

            // Crear un enlace para descargar el archivo
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', 'reportParametria.xlsx'); // Nombre del archivo que se descargará

            // Simular un clic en el enlace para iniciar la descarga
            link.style.display = 'none';
            document.body.appendChild(link);
            link.click();

            // Limpiar el enlace creado
            document.body.removeChild(link);
    }

    componentDidMount() {
        console.log("componentDidMount impl");
        this.loadingData(0);
        window.addEventListener('scroll', this.handleScroll);
    }

    componentWillUnmount() {
        console.log("componentWillUnmount impl");
        window.removeEventListener('scroll', this.handleScroll);
    }

    render() {
        return (
            <div>
                {super.render()}
            </div>
        );
    }

}
export default UiParametriaGridImpl;