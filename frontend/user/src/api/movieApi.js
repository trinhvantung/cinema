import axiosClient from "./axiosClient"
const url = "/movie-service/movies"
const movieApi = {
    getAllByPage: (page) => axiosClient.get(url, { params: { page } }),
    getAll: () => axiosClient.get(url),
    getAllByCategory: (categoryId, page) => axiosClient.get(`${url}/category/${categoryId}`, { params: { page } }),
    getById: (id) => axiosClient.get(`${url}/${id}`),
    create: (movie) => axiosClient.post(`${url}`, movie, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }),
    update: (id, movie) => axiosClient.put(`${url}/${id}`, movie, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }),
    deleteById: (id) => axiosClient.delete(`${url}/${id}`),


    search: (query, page) => axiosClient.get('/search-service/movie/search', { params: { query, page} })
}

export default movieApi