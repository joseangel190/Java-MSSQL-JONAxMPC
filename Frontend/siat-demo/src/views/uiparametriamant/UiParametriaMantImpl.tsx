import UiParametriaMant from "./UiParametriaMant";
import { UiParametriaMantProps } from "./UiParametriaMantProps";
import { InterUiParametriaMantCreate, InterUiParametriaMantEdit, InterUiParametriaMantDelete } from "./StructureUiParametriaMant";
import { createParametria, updateParametria, deleteParametria} from "../../services/api-mantenimientos/parametria";

class UiParametriaMantImpl extends UiParametriaMant {
    constructor(props: UiParametriaMantProps) {
        super(props);
    }

    handleCreate = async (data: InterUiParametriaMantCreate) => {
        const dataCreate = await createParametria(data);
        //showToast({ type: 'success', message: 'Parametria creado', options: {} })
        this.props.onSubmit(dataCreate);
        this.props.onClose();
    }

    handleUpdate = async (data: InterUiParametriaMantEdit) => {
        const dataUpdate = await updateParametria(data);
        //showToast({ type: 'success', message: 'Parametria creado', options: {} })
        this.props.onSubmit(dataUpdate);
        this.props.onClose();
    }

    handleDelete = async (data: InterUiParametriaMantDelete) => {
        const dataDelete = await deleteParametria(data)
        ///console.log('dataDelete', dataDelete)
        this.props.onSubmit(dataDelete);
        this.props.onClose();
    }

    render() {
        return (
            <UiParametriaMant
                {...this.props}
                handleCreate={this.handleCreate}
                handleUpdate={this.handleUpdate}
                handleDelete={this.handleDelete}
            />
        );
    }
}

export default UiParametriaMantImpl;