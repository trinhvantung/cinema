import axiosClient from "./axiosClient"
const url = "/cinema-service/halls"
const hallApi = {
    getAllByPage: (page) => axiosClient.get(`${url}`, { params: { page } }),
    getAll: () => axiosClient.get(`${url}`),
    getById: (id) => axiosClient.get(`${url}/${id}`),
    create: (hall) => axiosClient.post(`${url}`, hall),
    update: (id, hall) => axiosClient.put(`${url}/${id}`, hall),
    deleteById: (id) => axiosClient.delete(`${url}/${id}`)
}

export default hallApi