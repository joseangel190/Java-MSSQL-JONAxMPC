import React, { Component } from "react";
import { UiParametriaMantState } from './UiParametriaMantState';
import { UiParametriaMantProps } from './UiParametriaMantProps';
import { InterUiParametriaMantCreate, InterUiParametriaMantEdit, InterUiParametriaMantDelete } from "./StructureUiParametriaMant";
import { InterUiParametriaMantTitleCrud } from "./StructureUiParametriaMant";
import FormMantLayout from "../../uilayouts/FormMantLayout";
import UiParametriaMantForm from "./UiParametriaMantForm";

import '../../resources/css/UiMant.css';
class UiParametriaMant extends Component<UiParametriaMantProps, UiParametriaMantState> {
    constructor(props: UiParametriaMantProps) {
        super(props);

        const { mode, data } = props;

        const defaultData: InterUiParametriaMantCreate | InterUiParametriaMantEdit = {
            id: data?.id || 0,
            descripcion: data?.descripcion || "",
            abreviatura: data?.abreviatura || "",
            codigo: data?.codigo || "",
            orden: data?.orden || 0,
        };

        if (mode !== 'create') {
            (defaultData as InterUiParametriaMantEdit).id = data?.id;
        }

        this.state = {
            defaultData
        };
    }

    getTitle = (mode: 'delete' | 'edit' | 'create' | 'view') => {
        const titles: InterUiParametriaMantTitleCrud = {
            delete: 'Eliminar Parametria',
            edit: 'Editar Parametria',
            create: 'Crear Parametria',
            view: 'Ver Parametria',
        };

        return titles[mode];
    }

    handleSubmit = async (data: InterUiParametriaMantCreate | InterUiParametriaMantEdit | InterUiParametriaMantDelete) => {
        if (this.props.mode === 'create') {
          await this.props.handleCreate?.(data as InterUiParametriaMantCreate);
        }
        if (this.props.mode === 'edit') {
          await this.props.handleUpdate?.(data as InterUiParametriaMantEdit);
        }
        if (this.props.mode === 'delete') {
          this.props.handleDelete?.(data as InterUiParametriaMantDelete);
        }
      };

    render() {
        const { onClose, mode } = this.props;
        const { defaultData } = this.state;
    
        return (
          <FormMantLayout
            title={this.getTitle(mode)}
            onClose={onClose}
            onSubmit={() => this.handleSubmit({ id: (defaultData as InterUiParametriaMantEdit).id })}
            isDeleteMode={mode === 'delete'}
          >
            {mode !== 'delete' && (
              <UiParametriaMantForm
                mode={mode}
                initialValues={defaultData}
                handleSubmit={this.handleSubmit}
              />
            )}
          </FormMantLayout>
        );
      }
    
}

export default UiParametriaMant;