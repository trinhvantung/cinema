import axiosClient from "./axiosClient"
const url = "/review-service/reviews"
const reviewApi = {
    getAllByMovieId: (movieId, page) => axiosClient.get(`${url}/movie/${movieId}`, { params: { page } }),
    getAllByMovieIdAndStar: (movieId, page, star) => axiosClient.get(`${url}/movie/${movieId}`, { params: { page, star } }),
    checkExistsByMovieIdAndUserId: (movieId) => axiosClient.get(`${url}/exists/movie/${movieId}`),
    create: (movieId, review) => axiosClient.post(`${url}/movie/${movieId}`, review),
    getReviewDetailByMovieId: (movieId) => axiosClient.get(`${url}/detail/movie/${movieId}`),
}

export default reviewApi