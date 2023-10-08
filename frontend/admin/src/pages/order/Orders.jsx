import { Button, Card, Descriptions, Modal, Popconfirm, Space, Table, Tag, Typography } from 'antd';
import React, { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom';
import numberWithCommas from '../../utils/numberWithComma'
import { EyeOutlined, MailOutlined, PhoneOutlined } from '@ant-design/icons';
import bookingApi from '../../api/bookingApi';



const getOrderStatus = statusEnum => {
    if (statusEnum == 'PENDING_PAYMENT') {
        return 'Đang chờ thanh toán'
    } else if (statusEnum == 'CANCELED') {
        return 'Đã  hủy'
    } else if (statusEnum == 'CANCELLING') {
        return 'Đang hủy'
    } else if (statusEnum == 'REFUND_CANCEL_BOOKING_FAIL') {
        return 'Hoàn tiền hủy vé thất bại'
    } else if (statusEnum == 'COMPLETED') {
        return 'Thành công'
    } else if (statusEnum == 'PAYMENT_FAIL') {
        return 'Thanh toán thất bại'
    } else if (statusEnum == 'REFUND_SUCCESS') {
        return 'Ghế bị trùng - Hoàn tiền thành công'
    } else if (statusEnum == 'REFUND_FAIL') {
        return 'Ghế bị trùng - Hoàn tiền thất bại'
    } else if (statusEnum == 'DUPLICATE') {
        return 'Ghế bị trùng - Đang hoàn tiền'
    } else if (statusEnum == "ACTIVATED_TICKET") {
        return "Đã nhận vé";
      }
}

const columnsOrderItem = [
    {
        title: 'Ghế',
        dataIndex: 'seat',
        key: 'seat',
        render: (value) => value.name
    },
    {
        title: 'Loại ghế',
        dataIndex: 'seat',
        key: 'seat',
        render: value => <Tag color={value.seatType.color}>{value.seatType.name}</Tag>
    },
    {
        title: 'Giá',
        dataIndex: 'price',
        key: 'price',
        render: (value) => numberWithCommas(value) + " đ"
    }
];


const getTime = (dateString) => {
    const date = new Date(dateString)
    // date.setDate(date.getDate() + i)

    const year = date.getFullYear()
    const month = String(date.getMonth() + 1).padStart(2, '0')
    const day = String(date.getDate()).padStart(2, '0')

    const hours = String(date.getHours()).padStart(2, '0')
    const minutes = String(date.getMinutes()).padStart(2, '0')

    const today = new Date()
    const isToday = date.toDateString() === today.toDateString()

    const formattedDate = `${hours}:${minutes} - ${day}/${month}/${year}`

    return formattedDate;
}

const Orders = () => {
    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
            key: 'id',
            render: (text) => <a>{text}</a>,
        },
        {
            title: 'Movie',
            dataIndex: 'show',
            key: 'show',
            render: (item) => item.movie.name
        },
        {
            title: 'Total price',
            dataIndex: 'totalPrice',
            key: 'totalPrice',
            render: (value) => numberWithCommas(value) + " đ"
        },
        {
            title: 'Full name',
            dataIndex: 'fullName',
            key: 'fullName'
        },
        {
            title: 'Email',
            dataIndex: 'email',
            key: 'email'
        },
        {
            title: 'Phone number',
            dataIndex: 'phoneNumber',
            key: 'phoneNumber'
        },
        {
            title: 'Status',
            dataIndex: 'status',
            key: 'status',
            render: v => <Tag color={["COMPLETED", "ACTIVATED_TICKET"].includes(v) ? 'green' : 'red'}>
                {getOrderStatus(v)}
            </Tag>
        },
        {
            title: 'Action',
            key: 'action',
            render: (_, record) => (
                <Space size="middle">
                    <Button onClick={() => showDetailOrder(record)} type='primary' ghost shape='circle' size='large'><EyeOutlined /></Button>
                </Space>
            ),
        }
    ];

    const location = useLocation()


    const { Text, Link } = Typography;
    const [open, setOpen] = useState(false);
    const [isLoading, setIsLoading] = useState(false)
    const [data, setData] = useState([])
    const [currentPage, setCurrentPage] = useState(1)
    const [orderDetail, setOrderDetail] = useState()



    const showDetailOrder = (item) => {
        setOpen(true)

        bookingApi.getDetail(item.id).then(res => {
            setOrderDetail(({ ...res, movie: item.show.movie }))
            console.log(res)
        })
    }

    useEffect(() => {
        setIsLoading(true)
        console.log(currentPage)
        bookingApi.getAllByPage(currentPage).then(res => {
            setData(res)
            console.log(res)
            setIsLoading(false)
        })
    }, [currentPage])


    const handleChangePage = (i) => {
        setCurrentPage(i)
    }


    return (
        <div>
            <div>
                <Table columns={columns} dataSource={data.content} rowKey={'id'} loading={isLoading}
                    pagination={{ total: data.totalElements || 0, pageSize: data.size, onChange: handleChangePage }} />
            </div>
            <Modal
                title=""
                centered
                open={open}
                closable={false}
                onOk={() => {
                    setOpen(false)
                    setOrderDetail(null)
                }}
                cancelButtonProps={{ style: { display: 'none' } }}
                width={1000}
                okText="Close"
            >
                {
                    orderDetail && (
                        <>

                            <Descriptions title="Order" layout="vertical" bordered>
                                <Descriptions.Item label="Phim">{orderDetail.movie.name}</Descriptions.Item>
                                <Descriptions.Item label="Tổng tiền">
                                    <Text type="success" strong>
                                        {numberWithCommas(orderDetail.totalPrice)} đ
                                    </Text>
                                </Descriptions.Item>
                                <Descriptions.Item label="Họ tên">{orderDetail.fullName}</Descriptions.Item>
                                <Descriptions.Item label="Số điện thoại">
                                    <Tag icon={<PhoneOutlined />} color="processing">
                                        {orderDetail.phoneNumber}
                                    </Tag>
                                </Descriptions.Item>
                                <Descriptions.Item label="Email">
                                    <Tag icon={<MailOutlined />} color="#cd201f">
                                        {orderDetail.email}
                                    </Tag>
                                </Descriptions.Item>
                                <Descriptions.Item label="Trạng thái">
                                    <Tag color={["COMPLETED", "ACTIVATED_TICKET"].includes(orderDetail.status) ? 'green' : 'red'}>
                                        {getOrderStatus(orderDetail.status)}
                                    </Tag>
                                </Descriptions.Item>
                                <Descriptions.Item label="Rạp">{orderDetail.hall.cinema.name}</Descriptions.Item>
                                <Descriptions.Item label="Phòng">{orderDetail.hall.name}</Descriptions.Item>
                                <Descriptions.Item label="Thời gian đặt"><Tag color="#55acee">
                                    {getTime(orderDetail.createdAt)}
                                </Tag></Descriptions.Item>

                            </Descriptions>
                            {
                                orderDetail.canCancel && <Popconfirm
                                    title="Xác nhận"
                                    description="Bạn có muốn hủy đặt tất cả các vé không?"
                                    onConfirm={() => handleCancelOrder(orderDetail)}
                                    // onCancel={() => console.log(orderDetail)}
                                    okText="Có"
                                    cancelText="Không"
                                >
                                    <br />
                                    <Button danger loading={isLoading} disabled={isLoading}>Hủy đặt vé</Button>
                                </Popconfirm>
                            }

                            <p style={{
                                fontWeight: 600,
                                fontSize: 16,
                                lineHeight: 1.5,
                                padding: '30px 0 16px 0'
                            }}>Ghế</p>
                            <Table columns={columnsOrderItem} dataSource={orderDetail.bookingItems} pagination={false}
                                scroll={{
                                    y: 240,
                                }} />
                        </>
                    )
                }
            </Modal>
        </div>
    )
}

export default Orders