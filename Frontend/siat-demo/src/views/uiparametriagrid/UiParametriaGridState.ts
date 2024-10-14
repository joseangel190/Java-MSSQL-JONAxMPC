import { UiParametriaGridProps } from "./UiParametriaGridProps";
import  Column  from '../../uilayouts/Column';

export interface UiParametriaGridState {
    modalOpen: boolean;
    modalMode: 'edit' | 'create' | 'view' | 'delete';
    selected: UiParametriaGridProps | null;
    currentPage: number;
    isLoading: boolean;
    headers?: Column[];
    data:any[];
}