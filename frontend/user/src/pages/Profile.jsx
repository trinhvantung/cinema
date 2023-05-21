import { Button, Col, Form, Input, Row, notification } from 'antd'
import React, { useEffect, useState } from 'react'
import userApi from '../api/userApi'

const Profile = () => {
    const [profileForm] = Form.useForm()
    const [updatePasswordForm] = Form.useForm()
    const [isLoading, setIsLoading] = useState(0)

    useEffect(() => {
        userApi.getProfile().then(res => {
            profileForm.setFields([
                {
                    name: 'email',
                    value: res.email
                }, {
                    name: 'fullName',
                    value: res.fullName
                }
            ])
        })
    }, [])

    const handleSubmitProfileForm = data => {
        setIsLoading(1)

        userApi.updateProfile(data).then(res => {
            notification.success({
                message: `Notification`,
                description: 'Success',
                placement: 'top',
            });
            localStorage.setItem("fullName", data.fullName)
        }).catch(err => {
            notification.error({
                message: `Notification`,
                description: 'Error',
                placement: 'top',
            });
        }).finally(() => {
            setIsLoading(0)
        })
    }

    const handleSubmitUpdatePasswordForm = data => {
        setIsLoading(2)

        userApi.updatePassword(data).then(res => {
            notification.success({
                message: `Notification`,
                description: 'Success',
                placement: 'top',
            });
        }).catch(err => {
            const data = err.response.data
            if(data.error == 'user_password_not_match') {
                updatePasswordForm.setFields([
                    {
                        name: 'currentPassword',
                        errors: ['Mật khẩu không chính xác']
                    }
                ])
            } else {
                notification.error({
                    message: `Notification`,
                    description: 'Error',
                    placement: 'top',
                });
            }
            
        }).finally(() => {
            setIsLoading(0)
        })
    }

    return (
        <div className="block">
            <Row gutter={16}>
                <Col span={14}>
                    <div className="page_title">
                        Thông tin tài khoản
                    </div>
                    <Form
                        layout="vertical"
                        form={profileForm}
                        onFinish={handleSubmitProfileForm}
                    >
                        <Form.Item label="Email" name={'email'} >
                            <Input placeholder="Email" disabled />
                        </Form.Item>
                        <Form.Item label="Họ tên" name={'fullName'}>
                            <Input placeholder="Họ tên" />
                        </Form.Item>

                        <Form.Item>
                            <Button type="primary" htmlType='submit' loading={isLoading === 1} disabled={isLoading === 1}>Lưu thay đổi</Button>
                        </Form.Item>
                    </Form>
                </Col>
                <Col span={10}>
                    <div className="page_title">
                        Đổi mật khẩu
                    </div>
                    <Form
                        layout="vertical"
                        form={updatePasswordForm}
                        onFinish={handleSubmitUpdatePasswordForm}
                        requiredMark={false}
                    >
                        <Form.Item label="Mật khẩu cũ" name={'currentPassword'}
                            rules={[
                                {
                                    required: true,
                                    message: "Password không được để trống"
                                }
                            ]} >
                            <Input placeholder="Mật khẩu cũ" />
                        </Form.Item>
                        <Form.Item label="Mật khẩu mới" name={'newPassword'}
                            rules={[
                                {
                                    required: true,
                                    message: "Password không được để trống"
                                }
                            ]}>
                            <Input.Password placeholder="Mật khẩu mới" />
                        </Form.Item>

                        <Form.Item>
                            <Button type="primary" htmlType='submit' loading={isLoading === 2} disabled={isLoading === 2}>Đổi mật khẩu</Button>
                        </Form.Item>
                    </Form>
                </Col>
            </Row>
        </div>
    )
}

export default Profile