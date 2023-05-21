import { DeleteOutlined, EditOutlined } from '@ant-design/icons';
import { Button, Popconfirm, Select, Space, Table, Typography } from 'antd';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import hallApi from '../../api/hallApi';






const Halls = () => {

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
      title: 'Cinema',
      dataIndex: 'cinema',
      key: 'cinema',
      render: row => row.name
    },
    {
      title: 'Address',
      dataIndex: 'cinema',
      key: 'cinema',
      render: row => row.address
    },
    {
      title: 'Action',
      key: 'action',
      render: (_, row) => (
        <Space size="middle">
          <Link to={`/hall/update/${row.id}`} style={{ color: 'blue' }}>
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



  const [isLoading, setIsLoading] = useState(false)
  const [data, setData] = useState([])
  const [currentPage, setCurrentPage] = useState(1)
  const [sort, setSort] = useState('id-desc')

  const handleDelete = id => {
    hallApi.deleteById(id).then(res => {
      hallApi.getAllByPage(currentPage).then(res => {
        setData(res)
        console.log(res)
        setIsLoading(false)
      })
    })
  }

  useEffect(() => {
    setIsLoading(true)
    console.log(currentPage)
    hallApi.getAllByPage(currentPage, sort).then(res => {
      setData(res)
      console.log(res)
      setIsLoading(false)
    })
  }, [currentPage, sort])


  const handleChangePage = (i) => {
    setCurrentPage(i)
  }




  const handleChangeSort = (v) => {
    setSort(v)
    setCurrentPage(1)
  }




  return (
    <div>
      <Space style={{
        marginBottom: 20
      }}>
        <Typography.Text strong>Sắp xếp: </Typography.Text>
        <Select
          defaultValue="id-desc"
          style={{
            width: 300,
          }}
          onChange={handleChangeSort}
          options={[
            {
              label: 'Mới nhất',
              value: 'id-desc',
            },
            {
              label: 'Cũ nhất',
              value: 'id-asc',
            },
            {
              label: 'Tên rạp giảm dần',
              value: 'cinema-desc',
            },
            {
              label: 'Tên rạp tăng dần',
              value: 'cinema-asc'
            },
            {
              label: 'Tên phòng giảm dần',
              value: 'name-desc'
            },
            {
              label: 'Tên phòng tăng dần',
              value: 'name-asc'
            }
          ]}
        />
      </Space>

      <Table columns={columns} dataSource={data.content} rowKey={'id'} loading={isLoading}
        pagination={{ total: data.totalElements || 0, pageSize: data.size, onChange: handleChangePage }} />
    </div>
  )
}


export default Halls