import React, { Component } from "react";
import ContentGridLayout from "../../uilayouts/ContentGridLayout";
import UiButton from '../../components/uibutton/UiButton';
import UiIcon from '../../components/uiicon/UiIcon';
import { UiParametriaGridProps } from './UiParametriaGridProps';
import { UiParametriaGridState } from './UiParametriaGridState';
import Column from '../../uilayouts/Column';
import UiParametriaMantImpl from "../uiparametriamant/UiParametriaMantImpl";

import '../../resources/css/UiGrid.css';
class UiParametriaGrid extends Component<UiParametriaGridProps, UiParametriaGridState> {
    constructor(props: UiParametriaGridProps) {
        super(props);

        this.state = {
            ...this.state,
            data: [],
            modalOpen: false,
            modalMode: 'create',
            selected: null,
            currentPage: 1,
            isLoading: false,
            headers: [
                { header: 'ID', accessor: 'id' },
                { header: 'Descripcion', accessor: 'descripcion' },
                { header: 'Abrev', accessor: 'abreviatura' },
                { header: 'Codigo', accessor: 'codigo' }
            ]
        };
        this.handleScroll = this.handleScroll.bind(this);
    }

    componentDidMount() {
        window.addEventListener('scroll', this.handleScroll);
    }

    componentWillUnmount() {
        window.removeEventListener('scroll', this.handleScroll);
    }

    handleScroll() {
        const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
        if (scrollTop + clientHeight >= scrollHeight - 100 && !this.state.isLoading) {
            this.props.loadingData?.(this.state.currentPage);
        }
    }

    createModal = () => {
        this.setState({
            modalOpen: true,
            modalMode: 'create',
        });
    }

    reloadData = () => {
        console.log("reloadData");
    }

    exportarPdf = () => {
        console.log("exportarPdf");
    }

    exportarExcel = () => {
        console.log("exportarExcel");
    }

    editModal = (parametria: any) => {
        this.setState({
            modalOpen: true,
            modalMode: 'edit',
            selected: parametria
        });
    }

    viewModal = (parametrie: any) => {
        this.setState({
            modalOpen: true,
            modalMode: 'view',
            selected: parametrie
        });
    }

    deleteModal = (parametria: any) => {
        this.setState({
            modalOpen: true,
            modalMode: 'delete',
            selected: parametria
        });
    }

    closeModal = () => {
        this.setState({
            modalOpen: false,
            selected: null
        });
    }

    callbackModal = async () => {
        console.log('callback');
        //this.props.reloadData?.();
        this.reloadData
    }

    /*componentDidUpdate(prevProps: UiParametriaGridProps) {
        if (prevProps.data !== this.props.data) {
            this.setState({ data: this.props.data });
        }
    }*/

    render() {
        const { headers, data } = this.state;
        //console.log("Data in render:", data);
        const { modalOpen, modalMode, selected, isLoading } = this.state;
        //var datita=[{"id":1,"version":1728010959491,"isPersistente":true,"codigo":"codtest","descripcion":"pruebaprocedure parent","abreviatura":"tst","orden":1,"children":null},{"id":2,"version":1728011000133,"isPersistente":true,"codigo":"tstng","descripcion":"prueba parametro","abreviatura":"abrv","orden":1,"parent":{"id":1,"version":1728010959491,"isPersistente":true,"codigo":"codtest","descripcion":"pruebaprocedure parent","abreviatura":"tst","orden":1},"children":null},{"id":3,"version":1728011042726,"isPersistente":true,"codigo":"tstng","descripcion":"prueba parametro","abreviatura":"abrv","orden":1,"children":null}]
        //console.log(headers);
        const buttons = (
            <>
                <UiButton
                    type="button"
                    text={'Crear Parametria'}
                    color={'dark'}
                    icon="Add"
                    callback={this.createModal}
                />
                <UiButton
                    type="button"
                    text={'Recargar'}
                    color={'purple'}
                    callback={this.reloadData}
                />
                <UiButton
                    type="button"
                    text={'Exportar Excel'}
                    color={'green'}
                    icon="Export"
                    callback={this.exportarExcel}
                />
                <UiButton
                    type="button"
                    text={'Exportar PDF'}
                    color={'red'}
                    icon="Export"
                    callback={this.exportarPdf}
                />
            </>
        );

        const search = (
            <>
                <UiIcon name="Search" />
                <input
                    type="text"
                    id="table-search-users"
                    className="search-input"
                    placeholder="Buscar"
                />
            </>
        );

        return (
            <>
                <ContentGridLayout
                    buttons={buttons}
                    search={search}
                    columns={headers as Column[]}
                    data={data}
                    onEdit={this.editModal}
                    onView={this.viewModal}
                    onDelete={this.deleteModal}
                />

                {modalOpen && (
                    <UiParametriaMantImpl
                        onClose={this.closeModal}
                        onSubmit={this.callbackModal}
                        mode={modalMode}
                        data={selected}
                    />
                )}

                {isLoading && (
                    <div className="text-center">
                        <span>Loading...</span>
                    </div>
                )}
            </>
        );
    }

}

export default UiParametriaGrid;