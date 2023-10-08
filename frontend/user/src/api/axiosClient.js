import axios from "axios";
import queryString from "query-string";
import { keycloakInstance } from "../main";

const axiosClient = axios.create({
  baseURL: "http://localhost:9090/api",
  headers: {
    "content-type": "application/json",
    "Access-Control-Allow-Origin": "*",
  },
  paramsSerializer: (params) => queryString.stringify(params),
});

axiosClient.interceptors.request.use(async (config) => {
  const token = keycloakInstance.token;
  if (keycloakInstance.authenticated) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

axiosClient.interceptors.response.use(
  (response) => {
    // if (response && response.data) {
    //   return response.data;
    // }
    return response;
  },
  (error) => {
    throw error;
  }
);

export default axiosClient;
