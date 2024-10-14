/* FormMantLayout.tsx */
import React from "react";
import UiIcon from '../components/uiicon/UiIcon';
import UiButton from '../components/uibutton/UiButton';

interface FormMantLayoutProps {
  title: string;
  onClose: () => void;
  onSubmit: () => void;
  children: React.ReactNode;
  isDeleteMode?: boolean;
}

const FormMantLayout: React.FC<FormMantLayoutProps> = ({ title, onClose, onSubmit, children, isDeleteMode = false }) => {
  return (
    <div id="crud-modal" className="crud-modal" onClick={onClose}>
      <div className="crud-modal-content" onClick={(e) => e.stopPropagation()}>
        <div className="crud-modal-header">
          <h3 className="crud-modal-title">{title}</h3>
          <button type="button" className="crud-modal-close-button" onClick={onClose}>
            <UiIcon name="Close" />
          </button>
        </div>

        <div className="crud-modal-body">
          {isDeleteMode ? (
            <div>
              <p className="crud-modal-text">¿Está seguro de que desea eliminar?</p>
              <div className="crud-modal-actions">
                <UiButton
                  type={'button'}
                  color={'dark'}
                  callback={onClose}
                  className={'justify-center'}
                  text={'Cancelar'}
                />
                <UiButton
                  type={'button'}
                  color={'red'}
                  className={'justify-center'}
                  callback={onSubmit}
                  text={'Eliminar'}
                />
              </div>
            </div>
          ) : (
            children
          )}
        </div>
      </div>
    </div>
  );
}

export default FormMantLayout;