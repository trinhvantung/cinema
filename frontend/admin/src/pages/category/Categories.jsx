import { DeleteOutlined, EditOutlined } from '@ant-design/icons';
import { Button, Popconfirm, Space, Table } from 'antd';
import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import categoryApi from '../../api/categoryApi';
import queryString from 'query-string'






const Categories = () => {
    const location = useLocation()
    const searchParam = queryString.parse(location.search)

    const [isLoading, setIsLoading] = useState(false)
    const [data, setData] = useState([])
    const [currentPage, setCurrentPage] = useState(searchParam.page ? searchParam.page : 1)


    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
            key: 'id'
        },
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name'
        },
        {
            title: 'Display Order',
            dataIndex: 'displayOrder',
            key: 'displayOrder',
        },
        {
            title: 'Action',
            key: 'action',
            render: (_, row) => (
                <Space size="middle">
                    <Link to={`/category/update/${row.id}`} style={{ color: 'blue' }}>
                        <Button type='primary' ghost shape='circle' size='large'><EditOutlined /></Button>
                    </Link>
                    {/* <Popconfirm
                        title="Delete the task"
                        description="Are you sure to delete this task?"
                        onConfirm={() => handleDelete(row.id)}
                        // onCancel={}
                        okText="Yes"
                        cancelText="No"
                    >
                        <Button danger shape='circle' size='large'><DeleteOutlined /></Button>
                    </Popconfirm> */}
                </Space>
            ),
        }
    ];


    const handleDelete = id => {
        categoryApi.deleteById(id).then(res => {
            categoryApi.getAllByPage(currentPage).then(res => {
                setData(res)
                setIsLoading(false)
            })
        })
    }

    useEffect(() => {
        setIsLoading(true)
        categoryApi.getAllByPage(currentPage).then(res => {
            setData(res)
            setIsLoading(false)
        })
    }, [currentPage])


    const handleChangePage = (i) => {
        setCurrentPage(i)
    }

    return (
        <div>
            <Table columns={columns} dataSource={data.content} rowKey={'id'} loading={isLoading}
                pagination={{ total: data.totalElements || 0, pageSize: data.size, onChange: handleChangePage }} />
        </div>
    )
}

export default Categories