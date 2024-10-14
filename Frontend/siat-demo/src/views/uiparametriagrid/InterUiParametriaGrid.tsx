export interface InterUiParametriaGrid {
    loadingData?: (page: number) => void;
    reloadData?: () => void;
    exportarPdf?: () => void;
    exportarExcel?: () => void;
}