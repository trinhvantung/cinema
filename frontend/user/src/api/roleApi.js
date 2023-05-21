import axiosClient from "./axiosClient"
const url = "/user-service/roles"
const roleApi = {
    getAll: () =>  axiosClient.get(url)
}

export default roleApi