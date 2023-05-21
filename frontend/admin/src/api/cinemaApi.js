import axiosClient from "./axiosClient"
const url = "/cinema-service/cinemas"
const cinemaApi = {
    getAllByPage: (page) => axiosClient.get(`${url}`, { params: { page } }),
    getAll: () => axiosClient.get(`${url}`),
    getAllByCity: (cityId, page) => axiosClient.get(`${url}/city/${cityId}`, { params: { page } }),
    getById: (id) => axiosClient.get(`${url}/${id}`),
    create: (cinema) => axiosClient.post(`${url}`, cinema),
    update: (id, cinema) => axiosClient.put(`${url}/${id}`, cinema),
    deleteById: (id) => axiosClient.delete(`${url}/${id}`)
}

export default cinemaApi