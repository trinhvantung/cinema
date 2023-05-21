import { Button, Form, Input, notification, Radio, Select, Spin } from 'antd';
import React, { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import roleApi from '../../api/roleApi';
import userApi from '../../api/userApi';

const UserForm = (props) => {
    const location = useLocation();
    const { id } = useParams()

    const [isLoading, setIsLoading] = useState(false)
    const [disableStatus, setDisableStatus] = useState(false);
    const [roles, setRoles] = useState([])
    const [form] = Form.useForm()

    const handleSubmit = (data) => {
        setIsLoading(true)
        console.log(data)

        userApi.update(id, data).then(res => {
            notification.info({
                message: `Notification`,
                description: 'Success',
                placement: 'top',
            });
            
        }).catch(err => {
            notification.error({
                message: `Notification`,
                description: 'Error',
                placement: 'top',
            });
        }).finally(() => {
            setIsLoading(false)
        })
    }

    useEffect(() => {
        form.resetFields()

        roleApi.getAll().then(res => {
            setRoles(res.map(role => ({...role, value: role.id, label:role.name})))
        })

        if (id) {
            userApi.getById(id).then(res => {
                console.log(res);
                form.setFields([
                    {
                        name: "id",
                        value: res.id
                    },
                    {
                        name: "fullName",
                        value: res.fullName
                    },
                    {
                        name: "email",
                        value: res.email
                    },
                    {
                        name: "nonLock",
                        value: res.nonLock
                    },
                    {
                        name: "roles",
                        value: res.roles.map(role => role.id)
                    }
                ])

            }).catch(err => {
            })

        } else {

        }

        

    }, [location.pathname])


    return (
        <Spin spinning={isLoading}>
            <Form
                name="form"
                form={form}
                validateTrigger="onSubmit"
                labelCol={{ flex: '110px' }}
                labelAlign="left"
                labelWrap
                wrapperCol={{ flex: 1 }}
                autoComplete="off"
                onFinish={handleSubmit}
                requiredMark={false}
            >
                <Form.Item
                    label="ID"
                    name="id"
                >
                    <Input placeholder="ID" disabled size='large' />
                </Form.Item>
                <Form.Item
                    label="Fullname"
                    name="fullName"
                    rules={[
                        {
                            required: true,
                            message: "Fullname cannot be empty"
                        }
                    ]}
                >
                    <Input placeholder="Fullname" size='large' />
                </Form.Item>
                <Form.Item
                    label="Email"
                    name="email"
                    rules={[
                        {
                            required: true,
                            message: "Email cannot be empty"
                        },
                        {
                            validator: async (_, value) => {
                                if (value && !value.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
                                    return Promise.reject('Email invalidate');
                                }
                                return Promise.resolve()
                            }
                        }
                    ]}
                >
                    <Input placeholder="Email" size='large' />
                </Form.Item>
                <Form.Item
                    label="Password"
                    name="password"
                    rules={[
                        {
                            validator: async (_, value) => {
                                if (value && value.length < 5) {
                                    return Promise.reject('Password at least 5 characters');
                                }
                                return Promise.resolve()
                            }
                        }
                    ]}
                >
                    <Input placeholder="Password" type="password" size='large' />
                </Form.Item>
                <Form.Item
                    label="Status"
                    name="nonLock"
                    initialValue={false}
                >
                    <Radio.Group disabled={disableStatus}>
                        <Radio value={true}>Lock</Radio>
                        <Radio value={false}>Non lock</Radio>
                    </Radio.Group>
                </Form.Item>
                <Form.Item
                    label="Roles"
                    name="roles"
                    rules={[
                        {
                            required: true,
                            message: "Roles cannot be empty"
                        }
                    ]}
                >
                    <Select
                        mode="multiple"
                        style={{
                            width: '100%',
                        }}
                        size='large'
                        placeholder="Select roles"
                        optionLabelProp="label"
                        options={roles}
                    >

                    </Select>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit" disabled={isLoading} size='large'>
                        Save
                    </Button>
                </Form.Item>
            </Form>
        </Spin>
    )
}

export default UserForm