import axiosClient from "./axiosClient"
const url = "/cinema-service/cities"
const cityApi = {
    getAll: () => axiosClient.get(url),
    getAllByPage: (page) => axiosClient.get(`${url}`, { params: { page } }),
    getById: (id) => axiosClient.get(`${url}/${id}`),
    create: (city) => axiosClient.post(`${url}`, city),
    update: (id, city) => axiosClient.put(`${url}/${id}`, city),
    deleteById: (id) => axiosClient.delete(`${url}/${id}`)
}

export default cityApi