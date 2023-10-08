import axiosClient from "./axiosClient"
const url = "/cinema-service/seats"
const seatApi = {
    getAllByHall: (hallId) => axiosClient.get(`${url}/hall/${hallId}`),
    save: (hallId, seats) => axiosClient.post(`${url}/hall/${hallId}`, seats)
}

export default seatApi