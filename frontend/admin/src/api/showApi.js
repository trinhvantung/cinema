import axiosClient from "./axiosClient"
const url = "/show-service/shows"
const showApi = {
    // getAllByPage: (page) => axiosClient.get(`${url}`, { params: { page } }),

    getAllByPage: (page, filter, sort) => axiosClient.get(`${url}`, { params: { page, sort, ...filter } }),

    getById: (id) => axiosClient.get(`${url}/${id}`),
    create: (show) => axiosClient.post(`${url}`, show),
    update: (id, show) => axiosClient.put(`${url}/${id}`, show),
    deleteById: (id) => axiosClient.delete(`${url}/${id}`),



    getDetailShow: (id) => axiosClient.get(`${url}/detail/${id}`),
    getAllShowByCinema: (cinemaId, page, date) => axiosClient.get(`${url}/cinema/${cinemaId}`, { params: { page, date } }),
    getAllShowByMovie: (movieId, page, date) => axiosClient.get(`${url}/movie/${movieId}`, { params: { page, date } }),
}

export default showApi