import { Button, Form, Input, InputNumber, notification, Radio, Select, Space, Spin } from 'antd';
import React, { useEffect, useState } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import cinemaApi from '../../api/cinemaApi';
import hallApi from '../../api/hallApi';
import Seat from '../../components/Seat';
import seatApi from '../../api/seatApi';




const HallForm = (props) => {
    const location = useLocation();
    const { id } = useParams()

    const [isLoading, setIsLoading] = useState(false)

    const [changeData, setChangeData] = useState(true)
    const [disableStatus, setDisableStatus] = useState(false);
    const [form] = Form.useForm()
    const [formSize] = Form.useForm()
    const [value, setValue] = useState(1);
    const [size, setSize] = useState({ row: 0, column: 0 })
    const [seatData, setSeatData] = useState([])
    const [isEditSeat, setIsEditSeat] = useState(false)
    const [cinemas, setCinemas] = useState([])
    const [canCreateSeats, setCanCreateSeats] = useState(false)
    const [savedHallId, setSavedHallId] = useState()


    const [seatType, setSeatType] = useState([
        {
            id: 1,
            name: 'Ghế thường',
            color: '#3abc2e'
        }, {
            id: 2,
            name: 'Ghế VIP',
            color: '#2266f5'
        }
    ])

    const handleSubmit = (data) => {
        setIsLoading(true)
        console.log(data)

        if (id) {
            hallApi.update(id, data).then(res => {
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
            hallApi.create(data).then(res => {
                setCanCreateSeats(true)
                setSavedHallId(res.id)
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
        }
    }

    useEffect(() => {
        form.resetFields()

        cinemaApi.getAll().then(res => {
            setCinemas(res.map(c => ({ ...c, value: c.id, label: c.name + ' - ' + c.address })))
        })

        if (id) {
            hallApi.getById(id).then(res => {
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
                        name: "cinemaId",
                        value: res.cinema.id
                    }
                ])

            }).catch(err => {
            })

        } else {

        }

    }, [location.pathname])



    const handleSubmitFormSize = data => {
        console.log(data)
        setSize(data)
        const temp = []
        for (let i = 1; i <= data.row; i++) {
            const rowTemp = []
            for (let j = 1; j <= data.column; j++) {
                rowTemp.push({
                    rowIndex: i,
                    columnIndex: j,
                    name: String.fromCharCode(64 + i) + ('0' + j).slice(-2),
                    seatType: seatType[0]
                })
            }
            temp.push(rowTemp)
        }
        console.log(temp)
        setSeatData(temp)
    }


    const onChange = (e) => {
        console.log('radio checked', e.target.value);
        setValue(e.target.value);
    };

    const handleClickBtnEditSeat = () => {
        setIsEditSeat(true)
        if (id) {
            seatApi.getAllByHall(id).then(res => {
                console.log(res)

                let maxRowIndex = 0;
                let maxColumnIndex = 0;

                for (let item of res) {
                    if (item.rowIndex > maxRowIndex) {
                        maxRowIndex = item.rowIndex;
                    }
                    if (item.columnIndex > maxColumnIndex) {
                        maxColumnIndex = item.columnIndex;
                    }
                }
                const dataTemp = []
                for (let i = 1; i <= maxRowIndex; i++) {
                    const temp = []
                    for (let j = 1; j <= maxColumnIndex; j++) {
                        const x = res.find(s => s.columnIndex === j && s.rowIndex == i)
                        if (x) {
                            temp.push(x)
                        } else {
                            temp.push({
                                rowIndex: i,
                                columnIndex: j,
                                name: "",
                                seatType: { id: 0 }
                            })
                        }
                    }
                    dataTemp.push(temp)
                }
                setSeatData(dataTemp)
                console.log(dataTemp)

            })
        }
    }

    const handleClickSeat = item => {
        const type = seatType.find(st => st.id === value)

        // Không phải loại rỗng
        if (type) {
            const temp = type.id === item.seatType.id
            // Chọn loại ghế khác
            if (!temp) {
                // Khôi phuc ghế rỗng
                if (item.seatType.id === 0) {
                    let index = 0;
                    const row = seatData[item.rowIndex - 1]
                    for (let s of row) {
                        if (s.seatType.id !== 0 || s.columnIndex === item.columnIndex) {
                            index += 1
                            console.log(index)
                            s.name = String.fromCharCode(64 + (item.rowIndex)) + ('0' + (index)).slice(-2)
                            if (s.columnIndex === item.columnIndex) {
                                s.seatType = type
                            }
                        }
                    }
                    setChangeData(prev => !prev)
                }
                // Thay loại khác từ 1 loại không rỗng
                else {
                    seatData[item.rowIndex - 1][item.columnIndex - 1].seatType = type
                    setChangeData(prev => !prev)
                }
            }
        }
        // Chọn ghế rỗng
        else {
            let index = 0;

            const row = seatData[item.rowIndex - 1]
            for (let s of row) {
                if (s.columnIndex === item.columnIndex) {
                    s.name = ''
                    s.seatType = { id: 0 }
                } else {
                    if (s.seatType.id !== 0) {
                        index += 1
                        console.log(index)
                        s.name = String.fromCharCode(64 + (item.rowIndex)) + ('0' + (index)).slice(-2)
                    }
                }
            }
            setChangeData(prev => !prev)
            console.log(seatData)
        }
    }

    const handleSaveSeat = () => {
        const temp = []
        for (const row of seatData) {
            for (const i of row) {
                if (i.seatType.id !== 0) {
                    i.seatTypeId = i.seatType.id
                    temp.push(i)
                }
            }
        }
        if (id) {
            seatApi.save(id, temp).then(res => {
                notification.success({
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
            })
        } else {
            if (canCreateSeats) {
                seatApi.save(savedHallId, temp).then(res => {
                    notification.success({
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
                })
            }
        }
        console.log(temp)
    }

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
                    <Input placeholder="Name" size='large' disabled={canCreateSeats} />
                </Form.Item>
                <Form.Item
                    label="Cinema"
                    name="cinemaId"
                    rules={[
                        {
                            required: true,
                            message: "Cinema cannot be empty"
                        }
                    ]}
                >
                    <Select
                        style={{
                            width: '100%',
                        }}
                        placeholder="Search cinema"
                        optionLabelProp="label"
                        size='large'
                        options={cinemas}
                        disabled={canCreateSeats}
                    >

                    </Select>
                </Form.Item>


                <Form.Item>
                    <Space size={'middle'}>
                        <Button type="primary" htmlType="submit" disabled={isLoading || canCreateSeats} size='large'>
                            Save
                        </Button>
                        {
                            !isEditSeat && (canCreateSeats || id) && (
                                <Button type="primary" ghost disabled={isLoading} size='large' onClick={handleClickBtnEditSeat}>
                                    Edit Seats
                                </Button>
                            )
                        }
                    </Space>
                </Form.Item>
            </Form>

            {
                isEditSeat && (
                    <div className="edit_seats">
                        <Form name="formSize"
                            form={formSize}
                            validateTrigger="onSubmit"
                            labelCol={{ flex: '110px' }}
                            labelAlign="left"
                            labelWrap
                            wrapperCol={{ flex: 1 }}
                            autoComplete="off"
                            onFinish={handleSubmitFormSize}
                            initialValues={{ row: 8, column: 8 }}
                            requiredMark={false}>
                            <div style={{
                                width: '100%',
                                display: 'flex',
                                marginBottom: 16,
                                alignItems: 'start'
                            }}>
                                <Form.Item
                                    name="row"
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Missing row',
                                        },
                                    ]}
                                    style={{ flexGrow: 1, marginBottom: 0 }}
                                >
                                    <InputNumber placeholder="Row" style={{ width: '100%' }} size='large' />
                                </Form.Item>
                                <Form.Item
                                    style={{ flexGrow: 1, marginBottom: 0, marginLeft: 16 }}
                                    name="column"
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Missing column',
                                        },
                                    ]}
                                >
                                    <InputNumber placeholder="Column" style={{ width: '100%' }} size='large' />
                                </Form.Item>
                                <Button type="primary" htmlType="submit" disabled={isLoading} size='large' style={{ marginLeft: 16 }}>
                                    Generate
                                </Button>
                            </div>
                        </Form>
                        <div className="seats_wrapper">
                            <div className="seats_header">
                                <Radio.Group onChange={onChange} value={value}>
                                    {
                                        seatType.map(i => <Radio key={i.id} value={i.id} style={{ color: i.color }}>{i.name}</Radio>)
                                    }
                                    <Radio value={0}>Trống</Radio>
                                </Radio.Group>

                            </div>
                            <div className="seats_body">
                                <div className="seats" >

                                    {
                                        seatData.map((row, i) => <div key={i} className='seat_row'>
                                            {row.map((item, index) => <Seat key={index} seat={item} isEmpty={item.seatType.id === 0}
                                                handleClick={() => { handleClickSeat(item) }}

                                            />

                                            )}
                                        </div>)
                                    }
                                </div>
                            </div>
                        </div>
                        <Button onClick={handleSaveSeat} type="primary" htmlType="submit" disabled={isLoading} size='large'>
                            Save seats
                        </Button>

                    </div>
                )
            }
        </Spin>
    )
}

export default HallForm