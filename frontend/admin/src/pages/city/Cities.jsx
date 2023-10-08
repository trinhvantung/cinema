import { DeleteOutlined, EditOutlined } from '@ant-design/icons';
import { Button, Popconfirm, Space, Table, Tag } from 'antd';
import React, { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom';
import cityApi from '../../api/cityApi';
import queryString from 'query-string';




const Cities = () => {
  const location = useLocation()
  const searchParam = queryString.parse(location.search)

  const [isLoading, setIsLoading] = useState(false)
  const [data, setData] = useState([])
  const [currentPage, setCurrentPage] = useState(searchParam.page ? searchParam.page : 1)

  const handleDelete = id => {
    cityApi.deleteById(id).then(res => {
      cityApi.getAllByPage(currentPage).then(res => {
        setData(res)
        console.log(res)
        setIsLoading(false)
      })
    })
  }

  useEffect(() => {
    setIsLoading(true)
    console.log(currentPage)
    cityApi.getAllByPage(currentPage).then(res => {
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
      title: 'Display Order',
      dataIndex: 'displayOrder',
      key: 'displayOrder',
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, row) => (
        <Space size="middle">
          <Link to={`/city/update/${row.id}`} style={{ color: 'blue' }}>
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
      <Table columns={columns} dataSource={data.content} rowKey={'id'} loading={isLoading}
        pagination={{ total: data.totalElements || 0, pageSize: data.size, onChange: handleChangePage }} />
    </div>
  )
}

export default Cities