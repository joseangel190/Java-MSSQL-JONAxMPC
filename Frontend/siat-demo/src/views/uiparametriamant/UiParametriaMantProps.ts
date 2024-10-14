import * as Yup from 'yup';
import { INVALID } from '../../constants/validation';
import { InterUiParametriaMant } from "./InterUiParametriaMant";
import { UiParametriaGridProps } from "../uiparametriagrid/UiParametriaGridProps";


export interface UiParametriaMantProps extends InterUiParametriaMant {
//export interface UiParametriaMantProps {
    mode: 'create' | 'edit' | 'view' | 'delete'
    data?: UiParametriaGridProps | null;
    //data?:  null;
}

export const validationUpdateSchema = Yup.object({
    id: Yup.number().required(INVALID.REQUIRED).positive(INVALID.ID),
    descripcion: Yup.string().required('Descripcion es requerido'),
    abreviatura: Yup.string().required('Abreviatura es requerido'),
    codigo: Yup.string().required('Codigo es requerido'),
    orden: Yup.number().required('Orden es requerido'),
});


export const validationCreateSchema = Yup.object({
    descripcion: Yup.string().required('Descripcion es requerido'),
    abreviatura: Yup.string().required('Abreviatura es requerido'),
    codigo: Yup.string().required('Codigo es requerido'),
    orden: Yup.number().required('Orden es requerido'),
});