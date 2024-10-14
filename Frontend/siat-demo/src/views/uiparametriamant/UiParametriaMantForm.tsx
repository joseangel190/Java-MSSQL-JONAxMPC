import React from "react";
import { validationUpdateSchema, validationCreateSchema } from './UiParametriaMantProps';
import { InterUiParametriaMantCreate, InterUiParametriaMantEdit } from "./StructureUiParametriaMant";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import UiButton from '../../components/uibutton/UiButton';

interface UiParametriaFormProps {
    //mode: 'create' | 'edit';
    mode: 'create' | 'edit' | 'view' | 'delete'
    initialValues: InterUiParametriaMantCreate | InterUiParametriaMantEdit;
    //tiposDocumento: any[];
    //sexos: any[];
    //sedes: any[];
    //handleChangeDepartamento: (e: React.ChangeEvent<HTMLSelectElement>) => void;
    handleSubmit: (values: InterUiParametriaMantCreate | InterUiParametriaMantEdit) => void;
}

const UiParametriaMantForm: React.FC<UiParametriaFormProps> = ({
    mode,
    initialValues,/*
    tiposDocumento,
    sexos,
    sedes,*/
    handleSubmit
  }) => {
    const isEditable = mode === 'edit' || mode === 'create';
  
    return (
      <Formik
        initialValues={initialValues}
        validationSchema={mode === 'edit' ? validationUpdateSchema : validationCreateSchema}
        onSubmit={(values, { setSubmitting }) => {
          const formattedValues = {
            ...values,
          };
          if (mode === 'edit') {
            (formattedValues as InterUiParametriaMantEdit).id = Number((values as InterUiParametriaMantEdit).id);
          }
          handleSubmit(formattedValues);
          setSubmitting(false);
        }}
      >
        {({ isSubmitting, setFieldValue }) => (
          <Form className="crud-modal-form">
            <div className="crud-modal-grid">
  
              <div className="crud-modal-field">
                <label htmlFor="codigo" className="crud-modal-label">Codigo</label>
                <Field
                  type="text"
                  name="codigo"
                  id="codigo"
                  className="crud-modal-input"
                  placeholder="Escribe el Codigo"
                  readOnly={!isEditable}
                />
                {isEditable && <ErrorMessage name="codigo" component="div" className="crud-modal-error-message" />}
              </div>
  
              <div className="crud-modal-field">
                <label htmlFor="abreviatura" className="crud-modal-label">Abreviatura</label>
                <Field
                  type="text"
                  name="abreviatura"
                  id="abreviatura"
                  className="crud-modal-input"
                  placeholder="Escribe Abreviatura"
                  readOnly={!isEditable}
                />
                {isEditable && <ErrorMessage name="abreviatura" component="div" className="crud-modal-error-message" />}
              </div>
  
              <div className="crud-modal-field">
                <label htmlFor="descripcion" className="crud-modal-label">Descripcion</label>
                <Field
                  type="text"
                  name="descripcion"
                  id="descripcion"
                  className="crud-modal-input"
                  placeholder="Escribe Descripcion"
                  readOnly={!isEditable}
                />
                {isEditable && <ErrorMessage name="descripcion" component="div" className="crud-modal-error-message" />}
              </div>

              <div className="crud-modal-field">
                <label htmlFor="orden" className="crud-modal-label">Orden</label>
                <Field
                  type="text"
                  name="orden"
                  id="orden"
                  className="crud-modal-input"
                  placeholder="Escribe Orden"
                  readOnly={!isEditable}
                />
                {isEditable && <ErrorMessage name="orden" component="div" className="crud-modal-error-message" />}
              </div>              
  
              <div className="crud-modal-divider"></div>
  
              <div className="crud-modal-actions">
                {(mode === 'edit' || mode === 'create') && (
                  <>
                    {isSubmitting}
                    <UiButton
                      type={'submit'}
                      disabled={isSubmitting}
                      color={'green'}
                      icon={'Save'}
                      text={mode === 'edit' ? 'Guardar' : 'Crear'}
                    />
                  </>
                )}
              </div>
            </div>
          </Form>
        )}
      </Formik>
    );
  };
  
  export default UiParametriaMantForm;