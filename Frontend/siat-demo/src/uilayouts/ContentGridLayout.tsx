/* React */
import React, { ReactNode } from "react";
import UiIcon from '../components/uiicon/UiIcon';
import Column from './Column';

interface ContentGridLayoutProps {
    buttons: ReactNode;
    search: ReactNode;
    columns: Column[];
    data: any[];
    onEdit: (data: any) => void;
    onView: (data: any) => void;
    onDelete: (data: any) => void;
}

const ContentGridLayout: React.FC<ContentGridLayoutProps> = ({ buttons, search, columns, data, onEdit, onView, onDelete }) => {
    return (
        <div className="relative-overflow-x-auto">
            <div className="flex-container md-flex-row">
                <div className="flex-gap-2">
                    {buttons}
                </div>
                <div className="relative">
                    <div className="search-icon-container">
                        {search}
                    </div>
                </div>
            </div>
            <table className="table">
                <thead className="table-head">
                    <tr>
                        {columns?.map((column, index) => (
                            <th key={index} scope="col">{column.header}</th>
                        ))}
                        <th scope="col">Acción</th>
                    </tr>
                </thead>
                <tbody>
                    {data?.map((item, index) => (
                        <tr
                            key={`data-${item.id}`}
                            className={`table-row ${index % 2 === 0 ? 'table-row-white' : 'table-row-gray'}`}
                        >
                            {columns.map((column, colIndex) => (
                                <td key={colIndex} className="table-cell">{item[column.accessor]}</td>
                            ))}
                            <td className="table-cell">
                                <div className="group-button">
                                    <button
                                        type="button"
                                        className="table-action-button"
                                        onClick={() => onView(item)}
                                    >
                                        <UiIcon name="View" />
                                    </button>
                                    <button
                                        type="button"
                                        className="table-action-button"
                                        onClick={() => onEdit(item)}
                                    >
                                        <UiIcon name="Edit" />
                                    </button>
                                    <button
                                        type="button"
                                        className="table-action-button delete"
                                        onClick={() => onDelete(item)}
                                    >
                                        <UiIcon name="Delete" />
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ContentGridLayout;