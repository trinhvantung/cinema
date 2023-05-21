import { Button, Col, Form, Input, Row, notification } from 'antd';
import React, { useState } from 'react';
import Container from '../components/Container';
import userApi from '../api/userApi';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const navigate = useNavigate()

    const [loginForm] = Form.useForm();
    const [registerForm] = Form.useForm();
    const [forgotPasswordForm] = Form.useForm();
    const [loginStatus, setLoginStatus] = useState(1);
    const [isLoading, setIsLoading] = useState(false)
    const [isDisable, setIsDisable] = useState(false)

    const handleLogin = (data) => {
        setIsLoading(true)

        userApi.login(data.email, data.password).then(res => {
            localStorage.setItem('roles', JSON.stringify(res.roles))
            localStorage.setItem('accessToken', res.access_token)
            localStorage.setItem('fullName', res.full_name)
            setIsLoading(false)
            console.log(res)
            navigate('/')
        }).catch(err => {
            const data = err.response.data
            console.log(data)
            if (data.error == 'invalid_grant') {
                if (data.error_description == 'Bad credentials') {
                    notification.error({
                        message: `Notification`,
                        description: 'Sai thông tin đăng nhập',
                        placement: 'top',
                    });
                } else if (data.error_description == 'User is disabled') {
                    notification.error({
                        message: `Notification`,
                        description: 'Tài khoản chưa kích hoạt',
                        placement: 'top',
                    });
                } else if (data.error_description == 'User account is locked') {
                    notification.error({
                        message: `Notification`,
                        description: 'Tài khoản đã bị khóa',
                        placement: 'top',
                    });
                }
            } else {
                notification.error({
                    message: `Notification`,
                    description: 'Error',
                    placement: 'top',
                });
            }
            setIsLoading(false)
        })

    }

    const handleRegister = (data) => {
        setIsLoading(true)

        userApi.register(data).then(res => {
            setIsLoading(false)
            notification.success({
                message: `Notification`,
                description: 'Đăng ký thành công, kiểm tra email để kích hoạt tài khoản',
                placement: 'top',
            });
            registerForm.resetFields()
        }).catch(err => {
            const data = err.response.data
            console.log(data)
            if (data.error == 'duplicate_user_email') {
                registerForm.setFields([
                    {
                        name: 'email',
                        errors: ['Email đã được sử dụng']
                    }
                ])
            } else if(!data.err) {
                if(data.email) {
                    registerForm.setFields([
                        {
                            name: 'email',
                            errors: [data.email]
                        }
                    ])
                }
            }

            setIsLoading(false)
        })

    }

    const handleForgotPassword = (data) => {
        setIsLoading(true)

        userApi.savePassword(data).then(res => {
            setIsLoading(false)
            notification.success({
                message: `Notification`,
                description: 'Success',
                placement: 'top',
            });
            registerForm.resetFields()
        }).catch(err => {
            const data = err.response.data

            notification.error({
                message: `Notification`,
                description: 'Error',
                placement: 'top',
            });
            setIsLoading(false)
        })
    }

    const handleSendOTP = () => {
        const email = forgotPasswordForm.getFieldsValue().email

        if (!email) {
            forgotPasswordForm.setFields([
                {
                    name: 'email',
                    errors: ['Email không được để trống']
                }
            ])
        } else {
            userApi.resetPassword(email).then(res => {
                setIsDisable(true)
                notification.success({
                    message: `Notification`,
                    description: 'Kiểm tra email để nhận mã kích hoạt',
                    placement: 'top',
                });
                // forgotPasswordForm.resetFields()
            }).catch(err => {
                const data = err.response.data
                if (data.error == 'user_not_found') {
                    forgotPasswordForm.setFields([
                        {
                            name: 'email',
                            errors: ['Email không không chính xác']
                        }
                    ])
                }
            })
        }
    }

    return (
        <Container>
            <div className="login">
                <div className="login_content">
                    <div className={loginStatus === 3 ? 'd-none' : ''}>
                        <div className="login_tab_list">
                            <div className={`login_tab_button${loginStatus === 1 ? ' active' : ''}`}
                                onClick={() => setLoginStatus(1)}>Đăng nhập</div>
                            <div className={`login_tab_button${loginStatus === 2 ? ' active' : ''}`}
                                onClick={() => setLoginStatus(2)}>Đăng ký</div>
                        </div>
                        <div className="login_body">
                            <div className={loginStatus === 2 ? 'd-none' : ''}>
                                <Form
                                    form={loginForm}
                                    layout="vertical"
                                    onFinish={handleLogin}
                                    requiredMark={false}
                                >
                                    <Form.Item label="Email"
                                        rules={[
                                            {
                                                required: true,
                                                message: "Email không được để trống"
                                            }
                                        ]}
                                        name={"email"}>
                                        <Input placeholder="Email" size="large" />
                                    </Form.Item>
                                    <Form.Item
                                        label="Mật khẩu"
                                        name="password"
                                        rules={[
                                            {
                                                required: true,
                                                message: "Password không được để trống"
                                            }
                                        ]}
                                    >
                                        <Input.Password placeholder="Mật khẩu" type="password" size="large" />
                                    </Form.Item>
                                    <Form.Item>
                                        <div className="login_button">
                                            <Button type="primary" size="large" htmlType='submit'
                                                disabled={isLoading} loading={isLoading}>Đăng nhập</Button>
                                        </div>
                                    </Form.Item>
                                    <Form.Item>
                                        <div className="forgot_password_button" onClick={() => setLoginStatus(3)}>Quên mật khẩu</div>
                                    </Form.Item>
                                </Form>
                            </div>

                            <div className={loginStatus === 1 ? 'd-none' : ''}>
                                <Form
                                    form={registerForm}
                                    layout="vertical"
                                    onFinish={handleRegister}
                                    requiredMark={false}
                                >
                                    <Form.Item label="Email" name={"email"}
                                        rules={[
                                            {
                                                required: true,
                                                message: "Email không được để trống"
                                            }
                                        ]}>
                                        <Input placeholder="Email" size="large" />
                                    </Form.Item>
                                    <Form.Item label="Họ tên" name={"fullName"}>
                                        <Input placeholder="Họ tên" size="large" />
                                    </Form.Item>
                                    <Form.Item
                                        label="Mật khẩu"
                                        name="password"
                                        rules={[
                                            {
                                                required: true,
                                                message: "Password không được để trống"
                                            }
                                        ]}
                                    >
                                        <Input.Password placeholder="Mật khẩu" type="password" size="large" />
                                    </Form.Item>
                                    <Form.Item>
                                        <div className="login_button">
                                            <Button type="primary" size="large" htmlType='submit'
                                                disabled={isLoading} loading={isLoading}>Đăng ký</Button>
                                        </div>
                                    </Form.Item>
                                </Form>
                            </div>
                        </div>
                    </div>
                    <div className={loginStatus !== 3 ? 'd-none' : ''}>
                        <div className="forgot_password_title">KHÔI PHỤC MẬT KHẨU</div>
                        <div className="login_body">
                            <Form
                                form={forgotPasswordForm}
                                layout="vertical"
                                onFinish={handleForgotPassword}
                                requiredMark={false}
                            >
                                <Form.Item label="Email">
                                    <Row gutter={8}>
                                        <Col span={18}>
                                            <Form.Item
                                                name="email"
                                                noStyle
                                                rules={[
                                                    {
                                                        required: true,
                                                        message: "Email không được để trống"
                                                    }
                                                ]}
                                            >
                                                <Input placeholder="Email" size="large" />
                                            </Form.Item>
                                        </Col>
                                        <Col span={6}>
                                            <Button onClick={handleSendOTP} style={{ width: '100%' }} size="large"
                                                disabled={isDisable}>Gửi OTP</Button>
                                        </Col>
                                    </Row>
                                </Form.Item>
                                <Form.Item label="Mã xác nhận OTP" name={"token"}
                                    rules={[
                                        {
                                            required: true,
                                            message: "Mã xác nhận không được để trống"
                                        }
                                    ]}>
                                    <Input placeholder="Mã xác nhận OTP" size="large" />
                                </Form.Item>
                                <Form.Item
                                    label="Mật khẩu mới"
                                    name={"newPassword"}
                                    rules={[
                                        {
                                            required: true,
                                            message: "Password không được để trống"
                                        }
                                    ]}
                                >
                                    <Input.Password placeholder="Mật khẩu mới" type="password" size="large" />
                                </Form.Item>
                                <Form.Item>
                                    <div className="login_button">
                                        <Button type="primary" size="large" htmlType='submit'
                                            disabled={isLoading} loading={isLoading}>Đổi mật khẩu</Button>
                                    </div>
                                </Form.Item>
                                <Form.Item>
                                    <div className="login_button">
                                        <Button size="large" onClick={() => setLoginStatus(1)}>Quay lại</Button>
                                    </div>
                                </Form.Item>
                            </Form>
                        </div>
                    </div>
                </div>
            </div>
        </Container>
    )
}

export default Login