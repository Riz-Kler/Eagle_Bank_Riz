import axios from 'axios';
export const api = axios.create({ baseURL: import.meta.env.VITE_API_BASE ?? 'http://localhost:8080' });

api.interceptors.request.use(cfg => {
    const token = localStorage.getItem('jwt');
    if (token) cfg.headers.Authorization = `Bearer ${token}`;
    return cfg;
});
