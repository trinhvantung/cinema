import { EditOutlined } from '@ant-design/icons';
import { Button, Space, Table, Tag } from 'antd';
import queryString from 'query-string';
import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import userApi from '../../api/userApi';

const columns = [
    {
        title: 'ID',
        dataIndex: 'id',
        key: 'id',
        render: (text) => <a>{text}</a>,
    },
    {
        title: 'Name',
        dataIndex: 'fullName',
        key: 'fullName'
    },
    {
        title: 'Email',
        dataIndex: 'email',
        key: 'email',
    },
    {
        title: 'Roles',
        key: 'roles',
        dataIndex: 'roles',
        render: (_, { roles }) => (
            <>
                {roles && roles.map((tag) => {
                    let color = tag.id % 2 == 1 ? 'geekblue' : 'green';

                    return (
                        <Tag color={color} key={tag.id}>
                            {tag.name.toUpperCase()}
                        </Tag>
                    );
                })}
            </>
        ),
    },
    {
        title: 'Status',
        dataIndex: 'nonLock',
        key: 'nonLock',
        render: (value) => <Tag color={value ? 'processing' : 'error'}>{value ? "Non lock" : "Lock"}</Tag>
    },
    {
        title: 'Enable',
        dataIndex: 'enable',
        key: 'enable',
        render: (value) => <Tag color={value ? 'processing' : 'error'}>{value ? "Enable" : "Not Enable"}</Tag>
    },
    {
        title: 'Action',
        key: 'action',
        render: (_, row) => (
            <Space size="middle">
                <Link to={`/user/update/${row.id}`} style={{ color: 'blue' }}>
                    <Button type='primary' ghost shape='circle' size='large'><EditOutlined /></Button>
                </Link>
            </Space>
        ),
    },
];

const Users = () => {
    const location = useLocation()
    const searchParam = queryString.parse(location.search)

    const [isLoading, setIsLoading] = useState(false)
    const [data, setData] = useState([])
    const [currentPage, setCurrentPage] = useState(searchParam.page ? searchParam.page : 1)


    useEffect(() => {
        setIsLoading(true)
        console.log(currentPage)
        userApi.getAllByPage(currentPage).then(res => {
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
            <Table columns={columns} dataSource={data.content} rowKey={'id'} loading={isLoading}
                pagination={{ total: data.totalElements || 0, pageSize: data.size, onChange: handleChangePage }} />
        </div>
    )
}

export default Users