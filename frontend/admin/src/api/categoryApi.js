import axiosClient from "./axiosClient"
const url = "/movie-service/categories"
const categoryApi = {
    getAllByPage: (page) => axiosClient.get(url, { params: { page } }),
    getAll: () => axiosClient.get(url),
    getById: (id) => axiosClient.get(`${url}/${id}`),
    create: (category) => axiosClient.post(`${url}`, category),
    update: (id, category) => axiosClient.put(`${url}/${id}`, category),
    deleteById: (id) => axiosClient.delete(`${url}/${id}`)
}

export default categoryApi