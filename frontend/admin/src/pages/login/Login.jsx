import { Button, Form, Input, notification } from 'antd';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import userApi from '../../api/userApi';

const Login = () => {
    const [form] = Form.useForm();

    const [isLoading, setIsLoading] = useState(false)

    const navigate = useNavigate()

    const handleSubmit = data => {
        setIsLoading(true)   
        console.log(data)

        userApi.login(data.email, data.password).then(res => {
            localStorage.setItem('roles', JSON.stringify(res.roles))
            localStorage.setItem('accessToken', res.access_token)
            setIsLoading(false)
            navigate('/')
        }).catch(err => {
            notification.error({
                message: `Notification`,
                description: 'Error',
                placement: 'top',
            });
            setIsLoading(false)
        })



    }

    return (
        <>
            <div className="login" style={{
                width: '100%',
                height: '100vh',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                flexDirection: 'column'
            }}>
                <div style={{
                    fontSize: 30,
                    marginBottom: 36
                }}>Login</div>
                <div className="login_body" style={{
                    width: 500
                }}>
                    <Form
                        form={form}
                        layout="vertical"
                        onFinish={handleSubmit}


                    >
                        <Form.Item label="Email" name='email'>
                            <Input placeholder="Email" size="large" name='email' />
                        </Form.Item>
                        <Form.Item
                            name='password'
                            label="Mật khẩu"
                        >
                            <Input.Password placeholder="Mật khẩu" type="password" name='password' size="large" />
                        </Form.Item>
                        <Form.Item>
                            <div className="login_button" style={{
                                width: '100%',
                                textAlign: 'center'
                            }}>
                                <Button style={{
                                    width: 200
                                }} type="primary" htmlType='submit' size="large" disabled={isLoading}>Đăng nhập</Button>
                            </div>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        </>
    )
}

export default Login