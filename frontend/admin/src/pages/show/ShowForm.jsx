import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons';
import { Button, DatePicker, Form, Input, InputNumber, Select, Spin, notification } from 'antd';
import dayjs from 'dayjs';
import React, { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import hallApi from '../../api/hallApi';
import movieApi from '../../api/movieApi';
import seatTypeApi from '../../api/seatTypeApi';
import showApi from '../../api/showApi';

const ShowForm = (props) => {
    const location = useLocation();
    const { id } = useParams()

    const [isLoading, setIsLoading] = useState(false)
    const [halls, setHalls] = useState([])
    const [movies, setMovies] = useState([])
    const [seatTypes, setSeatTypes] = useState([])
    const [form] = Form.useForm()

    const handleSubmit = (data) => {
        // setIsLoading(true)
        console.log(data)
        data.start = data.time[0].format('YYYY-MM-DDTHH:mm:ss')
        data.end = data.time[1].format('YYYY-MM-DDTHH:mm:ss')

        delete data.time
        // console.log(data.time[1].format('YYYY/MM/DDTHH:mm:ss'))

        if (id) {
            showApi.update(id, data).then(res => {
                console.log(res);
                notification.info({
                    message: `Notification`,
                    description: 'Success',
                    placement: 'top',
                });
            }).catch(err => {
                console.log(err);
                const data = err.response.data
                if (data.error == 'show_duplicate_time') {
                    form.setFields([
                        {
                            name: "time",
                            errors: ["Trùng lặp thời gian trong phòng"]
                        }
                    ])
                }
            }).finally(() => {
                setIsLoading(false)
            })
        } else {
            delete data.id
            console.log(data)
            showApi.create(data).then(res => {
                console.log(res);
                notification.info({
                    message: `Notification`,
                    description: 'Success',
                    placement: 'top',
                });
            }).catch(err => {
                console.log(err);
                const data = err.response.data
                if (data.error == 'show_duplicate_time') {
                    form.setFields([
                        {
                            name: "time",
                            errors: ["Trùng lặp thời gian trong phòng"]
                        }
                    ])
                }
            }).finally(() => {
                setIsLoading(false)
            })
        }
    }

    const changeHall = e => {
        console.log(e)
        seatTypeApi.getAllByHall(e).then(res => {
            setSeatTypes(res.map(st => ({ ...st, value: st.id, label: st.name })))
            console.log(res)
            const temp = res.map(st => ({
                seatTypeId: st.id,
                // price: 0
            }))
            form.setFieldsValue({ showPrices: temp })
        })
    }

    useEffect(() => {
        form.resetFields()

        hallApi.getAll().then(res => {
            setHalls(res.sort((h1, h2) => {
                if (h1.cinema.name < h2.cinema.name) {
                    return -1;
                }
                if (h1.cinema.name > h2.cinema.name) {
                    return 1;
                }
                return 0;
            })
                .map(hall => ({ ...hall, value: hall.id, label: hall.name + " - " + hall.cinema.name + " - " + hall.cinema.address })))
        })

        movieApi.getAll().then(res => {
            setMovies(res.map(m => ({ ...m, value: m.id, label: m.name })))
        })

        if (id) {
            showApi.getById(id).then(res => {
                console.log(res);

                seatTypeApi.getAllByHall(res.hallId).then(res => {
                    setSeatTypes(res.map(st => ({ ...st, value: st.id, label: st.name })))
                })

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
                        name: "hallId",
                        value: res.hallId
                    },
                    {
                        name: "movieId",
                        value: res.movieId
                    },
                    {
                        name: "time",
                        value: [dayjs(res.start, 'YYYY-MM-DDThh:mm:ss'), dayjs(res.end, 'YYYY-MM-DDThh:mm:ss')]
                    }
                ])

                form.setFieldsValue({ showPrices: res.showPrices })
                console.log(res.showPrices)
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
                    label="Show time"
                    name="time"
                    rules={[
                        {
                            required: true,
                            message: "Show time cannot be empty"
                        }
                    ]}
                >
                    <DatePicker.RangePicker format={'YYYY-MM-DD HH:mm'} showTime style={{ width: '100%' }} size='large'
                        // defaultValue={['2023-05-08 00:00', '2023-05-08 02:00']}
                        defaultValue={[dayjs('2023-05-09 00:00', 'YYYY-MM-DD HH:mm'), dayjs('2023-05-09 01:00', 'YYYY-MM-DD HH:mm')]}
                    />
                </Form.Item>

                <Form.Item
                    label="Movie"
                    name="movieId"
                    rules={[
                        {
                            required: true,
                            message: "Movie cannot be empty"
                        }
                    ]}
                >
                    <Select
                        style={{
                            width: '100%',
                        }}
                        placeholder="Search movie"
                        // defaultValue={['china']}
                        // onChange={handleChange}
                        optionLabelProp="label"
                        size='large'
                        options={movies}
                    >

                    </Select>
                </Form.Item>
                <Form.Item
                    label="Hall"
                    name="hallId"
                    rules={[
                        {
                            required: true,
                            message: "Hall cannot be empty"
                        }
                    ]}
                >
                    <Select
                        style={{
                            width: '100%',
                        }}
                        onChange={changeHall}
                        placeholder="Select hall"
                        // defaultValue={['china']}
                        // onChange={handleChange}
                        optionLabelProp="label"
                        size='large'
                        options={halls}
                    >

                    </Select>
                </Form.Item>
                {
                    (id || seatTypes.length !== 0) && (
                        <Form.Item
                            label="Show price"
                        // name="showPrices"
                        >
                            <Form.List label="Show price"
                                name="showPrices"  >
                                {(fields, { add, remove }) => (
                                    <>
                                        {fields.map(({ key, name, ...restField }) => (
                                            <div key={key} style={{
                                                width: '100%',
                                                display: 'flex',
                                                marginBottom: 16,
                                                alignItems: 'start'
                                            }}>
                                                <Form.Item
                                                    {...restField}
                                                    // name={['seatTypeId']}
                                                    name={[name, 'seatTypeId']}
                                                    rules={[
                                                        {
                                                            required: true,
                                                            message: 'Missing seat type',
                                                        },
                                                    ]}
                                                    style={{ width: '50%', marginBottom: 0 }}
                                                >
                                                    <Select
                                                        style={{
                                                            width: '100%',
                                                        }}
                                                        placeholder="Select seat type"
                                                        optionLabelProp="label"
                                                        size='large'
                                                        options={seatTypes}
                                                        disabled
                                                    ></Select>
                                                </Form.Item>
                                                <Form.Item
                                                    style={{ width: '50%', marginBottom: 0, marginLeft: 16 }}
                                                    {...restField}
                                                    name={[name, 'price']}
                                                    rules={[
                                                        {
                                                            required: true,
                                                            message: 'Missing price',
                                                        },
                                                    ]}
                                                >

                                                    <InputNumber placeholder="Price" style={{ width: '100%' }} size='large' />

                                                </Form.Item>
                                            </div>
                                        ))}
                                    </>
                                )}
                            </Form.List>
                        </Form.Item>

                    )
                }
                <Form.Item>
                    <Button size='large' type="primary" htmlType="submit" disabled={isLoading}>
                        Save
                    </Button>
                </Form.Item>
            </Form>
        </Spin>
    )
}

export default ShowForm