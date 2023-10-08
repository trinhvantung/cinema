import { Button, Form, Input, Select, Spin, notification } from 'antd';
import React, { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import cinemaApi from '../../api/cinemaApi';
import cityApi from '../../api/cityApi';

const CinemaForm = (props) => {
    
    const location = useLocation();
    const { id } = useParams()

    const [isLoading, setIsLoading] = useState(false)
    const [cities, setCities] = useState([])

    const [form] = Form.useForm()

    const handleSubmit = (data) => {
        setIsLoading(true)
        console.log(data)

        if (id) {
            cinemaApi.update(id, data).then(res => {
                console.log(res);
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
        } else {
            delete data.id
            console.log(data)
            cinemaApi.create(data).then(res => {
                console.log(res);
                notification.info({
                    message: `Notification`,
                    description: 'Success',
                    placement: 'top',
                });
            }).catch(err => {
            }).finally(() => {
                setIsLoading(false)
            })
        }
    }

    useEffect(() => {
        form.resetFields()

        cityApi.getAll().then(res => {
            setCities(res.map(city => ({...city, value:city.id, label:city.name})))
        })

        if (id) {
            cinemaApi.getById(id).then(res => {
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
                        name: "address",
                        value: res.address
                    },
                    {
                        name: "cityId",
                        value: res.city.id
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
                    label="Address"
                    name="address"
                    rules={[
                        {
                            required: true,
                            message: "Address cannot be empty"
                        }
                    ]}
                >
                    <Input placeholder="Address" size='large' />
                </Form.Item>
                <Form.Item
                    label="City"
                    name="cityId"
                    rules={[
                        {
                            required: true,
                            message: "City cannot be empty"
                        }
                    ]}
                >
                    <Select
                        showSearch
                        style={{
                            width: '100%'
                        }}
                        size='large'
                        placeholder="City"
                        optionFilterProp="children"
                        filterOption={(input, option) => {

                            return (option?.label ?? '').includes(input)
                        }}
                        filterSort={(optionA, optionB) =>
                            (optionA?.label ?? '').toLowerCase().localeCompare((optionB?.label ?? '').toLowerCase())
                        }
                        options={cities}
                    />
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

export default CinemaForm