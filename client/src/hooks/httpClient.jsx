import axios from 'axios';

export const httpClient = axios.create({
    baseURL: process.env.REACT_APP_BASE_URL,
    headers: {
        'Content-Type': 'application/json',

    },
});

httpClient.interceptors.request.use(request => {
    if (request.data && request.headers['Content-Type'] === 'application/json') {
        request.data = JSON.stringify(request.data);
    }
    return request;
}, error => {
    return Promise.reject(error);
});

