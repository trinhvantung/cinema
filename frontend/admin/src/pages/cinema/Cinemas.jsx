import { DeleteOutlined, EditOutlined } from '@ant-design/icons';
import { Button, Popconfirm, Space, Table, Tag } from 'antd';
import queryString from 'query-string';
import React, { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom';
import cinemaApi from '../../api/cinemaApi';







const Cinemas = () => {
    const location = useLocation()

    const [isLoading, setIsLoading] = useState(false)
    const [data, setData] = useState([])
    const [currentPage, setCurrentPage] = useState(1)

    const handleDelete = id => {
        cinemaApi.deleteById(id).then(res => {
            cinemaApi.getAllByPage(currentPage).then(res => {
                setData(res)
                console.log(res)
                setIsLoading(false)
            })
        })
    }

    useEffect(() => {
        setIsLoading(true)
        console.log(currentPage)
        cinemaApi.getAllByPage(currentPage).then(res => {
            setData(res)
            console.log(res)
            setIsLoading(false)
        })
    }, [currentPage])


    const handleChangePage = (i) => {
        setCurrentPage(i)
    }


    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
            key: 'id',
            render: (text) => <a>{text}</a>,
        },
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name'
        },
        {
            title: 'Address',
            dataIndex: 'address',
            key: 'address',
        },
        {
            title: 'City',
            dataIndex: 'city',
            key: 'city',
            render: (item) => item.name
        },
        {
            title: 'Action',
            key: 'action',
            render: (_, row) => (
                <Space size="middle">
                    <Link to={`/cinema/update/${row.id}`} style={{ color: 'blue' }}>
                        <Button type='primary' ghost shape='circle' size='large'><EditOutlined /></Button>
                    </Link>
                    <Popconfirm
                        title="Delete the task"
                        description="Are you sure to delete this task?"
                        onConfirm={() => handleDelete(row.id)}
                        // onCancel={}
                        okText="Yes"
                        cancelText="No"
                    >
                        <Button danger shape='circle' size='large'><DeleteOutlined /></Button>
                    </Popconfirm>
                </Space>
            ),
        }
    ];
    return (
        <div>
            <div>
                <Table columns={columns} dataSource={data.content} rowKey={'id'} loading={isLoading}
                    pagination={{ total: data.totalElements || 0, pageSize: data.size, onChange: handleChangePage }} />
            </div>
        </div>
    )
}

export default Cinemas