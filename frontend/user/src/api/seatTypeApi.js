import axiosClient from "./axiosClient"
const url = "/cinema-service/seat-types"
const seatTypeApi = {
    getAll: () => axiosClient.get(url),
    getById: (id) => axiosClient.get(`${url}/${id}`),
    getAllByHall: (hallId) => axiosClient.get(`${url}/hall/${hallId}`),
    create: (seatType) => axiosClient.post(`${url}`, seatType),
    update: (id, seatType) => axiosClient.put(`${url}/${id}`, seatType),
    deleteById: (id) => axiosClient.delete(`${url}/${id}`)
}

export default seatTypeApi