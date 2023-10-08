import { Button, Form, Input, InputNumber, notification, Spin } from 'antd';
import React, { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import categoryApi from '../../api/categoryApi';

const CategoryForm = (props) => {
    const location = useLocation();
    const { id } = useParams()

    const [isLoading, setIsLoading] = useState(false)
    const [form] = Form.useForm()

    const handleSubmit = (data) => {
        setIsLoading(true)

        if (id) {
            categoryApi.update(id, data).then(res => {
                notification.info({
                    message: `Notification`,
                    description: 'Success',
                    placement: 'top',
                });
            }).catch(err => {
            }).finally(() => {
                setIsLoading(false)
            })
        } else {
            delete data.id
            categoryApi.create(data).then(res => {
                notification.info({
                    message: `Notification`,
                    description: 'Success',
                    placement: 'top',
                });
            }).catch(err => {
                // console.log(err);
                // const data = err.response.data
                // if (data.code === 6000) {
                //     form.setFields([
                //         {
                //             name: "name",
                //             errors: [data.message]
                //         }
                //     ])
                // }
            }).finally(() => {
                setIsLoading(false)
            })
        }
    }

    useEffect(() => {
        form.resetFields()

        if (id) {
            categoryApi.getById(id).then(res => {
                console.log(res);
                form.setFields([
                    {
                        name: "id",
                        value: res.id
                    },
                    {
                        name: "name",
                        value: res.name
                    },
                    {
                        name: "displayOrder",
                        value: res.displayOrder
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
                    label="Name"
                    name="name"
                    rules={[
                        {
                            required: true,
                            message: "Name cannot be empty"
                        }
                    ]}
                >
                    <Input placeholder="Name" size='large' />
                </Form.Item>
                <Form.Item
                    label="Display order"
                    name="displayOrder"
                    rules={[
                        {
                            required: true,
                            message: "Display order cannot be empty"
                        }
                    ]}
                >
                    <InputNumber placeholder="Display order" style={{ width: '100%' }} size='large' />
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

export default CategoryForm