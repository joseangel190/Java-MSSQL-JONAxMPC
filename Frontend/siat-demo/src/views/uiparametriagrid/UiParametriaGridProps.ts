import { InterUiParametriaGrid } from "./InterUiParametriaGrid";
import  Column  from '../../uilayouts/Column';

export interface UiParametriaGridProps extends InterUiParametriaGrid {    
//export interface UiParametriaGridProps {
    data?: any[];
    currentPage?: number;
    loadingData?: (page: number) => void;
    //reloadData?: () => void;
    //exportarPdf?: () => void;
    //exportarExcel?: () => void;
    id?: number;
    descripcion?: string;
    codigo?: string;
    abreviatura?: string;
    orden?: number;
}