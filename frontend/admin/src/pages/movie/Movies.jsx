import { DeleteOutlined, EditOutlined } from '@ant-design/icons';
import { Button, Image, Popconfirm, Space, Table, Tag, Typography } from 'antd';
import queryString from 'query-string';
import React, { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom';
import movieApi from '../../api/movieApi';





const Movies = () => {
    const location = useLocation()
    const searchParam = queryString.parse(location.search)

    const [isLoading, setIsLoading] = useState(false)
    const [data, setData] = useState([])
    const [currentPage, setCurrentPage] = useState(searchParam.page ? searchParam.page : 1)

    const handleDelete = id => {
        movieApi.deleteById(id).then(res => {
            movieApi.getAllByPage(currentPage).then(res => {
                setData(res)
                console.log(res)
                setIsLoading(false)
            })
        })
    }

    useEffect(() => {
        setIsLoading(true)
        console.log(currentPage)
        movieApi.getAllByPage(currentPage).then(res => {
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
            title: 'Image',
            dataIndex: 'thumbnailUrl',
            key: 'thumbnailUrl',
            render: value => <Image
                width={100}
                src={value}
            />
        },
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name'
        },
        {
            title: 'Duration',
            dataIndex: 'duration',
            key: 'duration',
        },
        {
            title: 'Description',
            dataIndex: 'description',
            key: 'description',
            render: value => (
                <Typography.Paragraph
                    ellipsis={{
                        rows: 2,
                        expandable: true,
                        symbol: 'More',
                    }}
                >
                    {value}
                </Typography.Paragraph>
            )
        },
        {
            title: 'ReleaseDate',
            dataIndex: 'releaseDate',
            key: 'releaseDate'
        },
        {
            title: 'Categories',
            dataIndex: 'categories',
            key: 'categories',
            render: (c) => c.map(i => i.name).join(", ")
        },
        {
            title: 'Action',
            key: 'action',
            render: (_, row) => (
                <Space size="middle">
                    <Link to={`/movie/update/${row.id}`} style={{ color: 'blue' }}>
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

    return (
        <div>
            <Table columns={columns} dataSource={data.content} rowKey={'id'} loading={isLoading}
                pagination={{ total: data.totalElements || 0, pageSize: data.size, onChange: handleChangePage }} />
        </div>
    )
}

export default Movies