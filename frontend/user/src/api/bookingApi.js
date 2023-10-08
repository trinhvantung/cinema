import axiosClient from "./axiosClient";
const url = "/booking-service/bookings";
const bookingApi = {
  getAllByPage: (page) => axiosClient.get(`${url}`, { params: { page } }),
  getDetail: (id) => axiosClient.get(`${url}/${id}`),
  deleteById: (id) => axiosClient.delete(`${url}/${id}`),

  // Client
  create: (order) => axiosClient.post(`${url}`, order),
  getAllByCurrentUser: (page) =>
    axiosClient.get(`${url}/current-user`, { params: { page } }),
  getDetailByCurrentUser: (id) => axiosClient.get(`${url}/${id}/current-user`),
  getAllBookedSeatsByShow: (showId) =>
    axiosClient.get(`${url}/seats/booked/show/${showId}`),
  cancelling: (bookingId) => axiosClient.post(`${url}/cancelling/${bookingId}`),
  generateTicketQrCode: (bookingId) =>
    axiosClient.get(`${url}/ticket/qr/${bookingId}`, {
      responseType: "arraybuffer",
    }),
};

export default bookingApi;
