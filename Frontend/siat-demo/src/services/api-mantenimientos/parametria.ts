import toast from 'react-hot-toast';
import axios, { AxiosRequestConfig } from 'axios';
import { getToken } from '../../methods/storage';

import { InterUiParametriaMantCreate, InterUiParametriaMantEdit, InterUiParametriaMantDelete } from '../../views/uiparametriamant/StructureUiParametriaMant';

export const getParametriasAll = async () => {
    const token = getToken()
    const config: AxiosRequestConfig = {
        method: 'post',
        maxBodyLength: Infinity,        
        url: `${process.env.REACT_APP_API_SIAT_URL}/parametrias/listar/all`,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
    };

    try {
        const response = await axios.request(config);
        if (response.status === 200) {
            const responseData = response.data;
            return responseData;
        } else {
            console.error(`Unexpected response status: ${response.status}`);
            toast.error(`Unexpected response status: ${response.status}`, { position: 'bottom-right'});
            return false;
        }
    } catch (error) {
        console.error(error);
        toast.error(String(error), { position: 'bottom-right'});
        throw new Error(String(error));
    }

}

export const createParametria = async (data: InterUiParametriaMantCreate | InterUiParametriaMantEdit) => {
    const token = getToken()
    const config: AxiosRequestConfig = {
        method: 'post',
        maxBodyLength: Infinity,
        url: `${process.env.REACT_APP_API_SIAT_URL}/parametrias/saveProcedure`,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        data
    };

    try {
        const response = await axios.request(config);
        if (response.status === 200) {
            const responseData = response.data;
            toast.success('Parametria creado', { position: 'bottom-right',duration:3000});
            return responseData;
        } else {
            console.error(`Unexpected response status: ${response.status}`);
            toast.error(`Unexpected response status: ${response.status}`, { position: 'bottom-right'});
            return false;
        }
    } catch (error) {
        console.error(error);
        toast.error(String(error), { position: 'bottom-right'});
        throw new Error(String(error));
    }
}

export const updateParametria = async (data: InterUiParametriaMantCreate | InterUiParametriaMantEdit) => {
    const token = getToken()
    const config: AxiosRequestConfig = {
        method: 'put',
        maxBodyLength: Infinity,
        url: `${process.env.REACT_APP_API_SIAT_URL}/parametrias/updateProcedure`,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        data
    };

    try {
        const response = await axios.request(config);
        if (response.status === 200) {
            const responseData = response.data;
            toast.success('Parametria actualizado', { position: 'bottom-right'});
            return responseData;
        } else {
            console.error(`Unexpected response status: ${response.status}`);
            toast.error(`Unexpected response status: ${response.status}`, { position: 'bottom-right'});
            return false;
        }
    } catch (error) {
        console.error(error);
        toast.error(String(error), { position: 'bottom-right'});
        throw new Error(String(error));
    }
}

export const deleteParametria = async (data: InterUiParametriaMantDelete) => {
    const token = getToken()
    const config: AxiosRequestConfig = {
        method: 'delete',
        maxBodyLength: Infinity,
        url: `${process.env.REACT_APP_API_SIAT_URL}/parametrias/deleteProcedure`,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        data
    };

    try {
        const response = await axios.request(config);
        if (response.status === 200) {
            const responseData = response.data;
            toast.success('Parametria eliminado', { position: 'bottom-right'});
            return responseData;
        } else {
            console.error(`Unexpected response status: ${response.status}`);
            toast.error(`Unexpected response status: ${response.status}`, { position: 'bottom-right'});
            return false;
        }
    } catch (error) {
        console.error(error);
        toast.error(String(error), { position: 'bottom-right'});
        throw new Error(String(error));
    }
}

export const exportarPdf = async () => {
    const token = getToken()
    const config: AxiosRequestConfig = {
        method: 'post',
        maxBodyLength: Infinity,
        url: `${process.env.REACT_APP_API_SIAT_REPORTES_URL}/parametria/pdf`,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },        
        responseType: 'blob', 
    };

    try {
        const response = await axios.request(config);
        if (response.status === 200) {            
            toast.success('Exportado Correctamente', { position: 'bottom-right'});
            return response;
        } else {
            console.error(`Unexpected response status: ${response.status}`);
            toast.error(`Unexpected response status: ${response.status}`, { position: 'bottom-right'});
            return false;
        }
    } catch (error) {
        console.error(error);
        toast.error(String(error), { position: 'bottom-right'});
        throw new Error(String(error));
    }
}

export const exportarExcel = async () => {
    const token = getToken()
    const config: AxiosRequestConfig = {
        method: 'post',
        maxBodyLength: Infinity,
        url: `${process.env.REACT_APP_API_SIAT_REPORTES_URL}/parametria/excel`,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },        
        responseType: 'blob', 
    };

    try {
        const response = await axios.request(config);
        if (response.status === 200) {            
            toast.success('Exportado Correctamente', { position: 'bottom-right'});
            return response;
        } else {
            console.error(`Unexpected response status: ${response.status}`);
            toast.error(`Unexpected response status: ${response.status}`, { position: 'bottom-right'});
            return false;
        }
    } catch (error) {
        console.error(error);
        toast.error(String(error), { position: 'bottom-right'});
        throw new Error(String(error));
    }
}