import { InterUiParametriaMantCreate, InterUiParametriaMantDelete, InterUiParametriaMantEdit } from './StructureUiParametriaMant';

export interface InterUiParametriaMant {
    loadingData?: () => void
    handleCreate?: (data: InterUiParametriaMantCreate) => Promise<void>;
    handleUpdate?: (data: InterUiParametriaMantEdit) => Promise<void>;
    handleDelete?: (data: InterUiParametriaMantDelete) => Promise<void>;
    onClose: () => void;
    onSubmit: (data: InterUiParametriaMantCreate | InterUiParametriaMantEdit) => void;    
}