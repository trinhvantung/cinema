import axiosClient from "./axiosClient"
const url = "/payment-service/payment"
const paymentApi = {
    statistic: (start, end) => axiosClient.get(`${url}/statistic`, { params: { start, end } }),
    export: (start, end) => axiosClient.get(`${url}/statistic/export`, {
        params: { start, end },
        responseType: 'blob'
    }),
}

export default paymentApi