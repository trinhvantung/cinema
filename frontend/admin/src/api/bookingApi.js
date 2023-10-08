import axiosClient from "./axiosClient"
import queryString from "query-string"

const url = "/booking-service/bookings"
const base = "http://localhost:8080/api"

const bookingApi = {
    getAllByPage: (page) => axiosClient.get(`${url}`, { params: { page } }),
    getDetail: (id) => axiosClient.get(`${url}/${id}`),
    deleteById: (id) => axiosClient.delete(`${url}/${id}`),
    getOrderStatistic: (start, end) => axiosClient.get(`${url}/statistic/order`, { params: { start, end } }),
    getTopRevenueByMovie: (start, end, limit) => axiosClient.get(`${url}/top/revenue/movie`, { params: { start, end, limit } }),
    exportOrderStatistic: (start, end) => axiosClient.get(`${url}/statistic/order/export`, {
        params: { start, end },
        responseType: 'blob'
    }),
    exportTopRevenueByMovie: (start, end, limit) => axiosClient.get(`${url}/top/revenue/movie/export`, {
        params: { start, end, limit },
        responseType: 'blob'
    }),
    activeTicket: (ticketCode) => axiosClient.put(`${url}/ticket/active/${ticketCode}`),
    // Client
    create: (order) => axiosClient.post(`${url}`, order),
    getAllByCurrentUser: (page) => axiosClient.get(`${url}/current-user`, { params: { page } }),
    getDetailByCurrentUser: (id) => axiosClient.get(`${url}/${id}/current-user`),
}


export default bookingApi