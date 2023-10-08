import axiosClient from "./axiosClient"
const url = "/user-service/users"
const userApi = {
    getAllByPage: (page) => axiosClient.get(url, { params: { page } }),
    getById: (id) => axiosClient.get(`${url}/${id}`),
    update: (id, user) => axiosClient.put(`${url}/${id}`, user),
    deleteById: (id) => axiosClient.delete(`${url}/${id}`),
    updateStatus: (id, status) => axiosClient.post(`${url}/status/${id}`, {}, { params: { status } }),





    // Client
    getProfile: () => axiosClient.get(`${url}/profile`),
    updateProfile: (profile) => axiosClient.put(`${url}/profile`, profile),
    register: (userRegistrationData) => axiosClient.post(`${url}/registration`, userRegistrationData),
    verifyRegister: (code) => axiosClient.post(`${url}/registration/verification/${code}`),
    updatePassword: (updatePasswordData) => axiosClient.put(`${url}/update-password`, updatePasswordData),
    resetPassword: (email) => axiosClient.post(`${url}/reset-password`, {}, { params: { email } }),
    savePassword: (saveResetPasswordData) => axiosClient.post(`${url}/reset-password/save-password`, saveResetPasswordData),


    login: (email, password) => {
        let data = new FormData();
        data.append('grant_type', 'password');
        data.append('username', email);
        data.append('password', password);
        data.append('client_id', 'client');
        data.append('client_secret', '12345');
        // let config = {
        //     method: 'post',
        //     maxBodyLength: Infinity,
        //     url: 'http://localhost:8080/api/auth-service/oauth/token',
        //     headers: {
        //         ...data.getHeaders()
        //     },
        //     data: data
        // };

        // axios.request(config)
        return axiosClient.post("/auth-service/oauth/token", data, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    }
}

export default userApi