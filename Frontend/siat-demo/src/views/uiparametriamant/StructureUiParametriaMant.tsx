export interface InterUiParametriaMantCreate {
    codigo: string;
    descripcion: string;
    abreviatura: string;
    orden: number;
}

export interface InterUiParametriaMantEdit extends InterUiParametriaMantCreate {
    id: number | undefined;
}


export interface InterUiParametriaMantDelete {
    id: number | undefined;
}

export interface InterUiParametriaMantTitleCrud {
    edit: string;
    create: string;
    view: string;
    delete: string;
}