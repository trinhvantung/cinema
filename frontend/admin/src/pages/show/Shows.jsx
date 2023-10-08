import { EditOutlined } from "@ant-design/icons";
import {
  Button,
  DatePicker,
  Form,
  Select,
  Space,
  Table,
  Tag,
  Typography,
} from "antd";
import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import cinemaApi from "../../api/cinemaApi";
import hallApi from "../../api/hallApi";
import movieApi from "../../api/movieApi";
import showApi from "../../api/showApi";

const Shows = () => {
  const columns = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
    },
    {
      title: "Movie",
      dataIndex: "movie",
      key: "movie",
      render: (value) => value.name,
    },
    {
      title: "Start",
      dataIndex: "start",
      key: "start",
      render: (value) => {
        const date = new Date(value);
        const hours = date.getHours().toString().padStart(2, "0");
        const minutes = date.getMinutes().toString().padStart(2, "0");
        const day = date.getDate().toString().padStart(2, "0");
        const month = (date.getMonth() + 1).toString().padStart(2, "0");
        const year = date.getFullYear();
        return (
          <Tag color="cyan">{`${hours}:${minutes} ${day}/${month}/${year}`}</Tag>
        );
      },
    },
    {
      title: "End",
      dataIndex: "end",
      key: "end",
      render: (value) => {
        const date = new Date(value);
        const hours = date.getHours().toString().padStart(2, "0");
        const minutes = date.getMinutes().toString().padStart(2, "0");
        const day = date.getDate().toString().padStart(2, "0");
        const month = (date.getMonth() + 1).toString().padStart(2, "0");
        const year = date.getFullYear();
        return (
          <Tag color="error">{`${hours}:${minutes} ${day}/${month}/${year}`}</Tag>
        );
      },
    },
    {
      title: "Hall",
      dataIndex: "hall",
      key: "hall",
      render: (value) => value.name,
    },
    {
      title: "Cinema",
      dataIndex: "hall",
      key: "hall",
      render: (value) => value.cinema.name + " - " + value.cinema.address,
    },
    {
      title: "Action",
      key: "action",
      render: (_, row) => (
        <Space size="middle">
          <Link to={`/show/update/${row.id}`} style={{ color: "blue" }}>
            <Button type="primary" ghost shape="circle" size="large">
              <EditOutlined />
            </Button>
          </Link>
          {/* <Button danger shape='circle' size='large'><DeleteOutlined /></Button> */}
        </Space>
      ),
    },
  ];

  const location = useLocation();

  const [isLoading, setIsLoading] = useState(false);
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);

  const [halls, setHalls] = useState([]);
  const [movies, setMovies] = useState([]);
  const [cinemas, setCinemas] = useState([]);
  const [form] = Form.useForm();

  const [sort, setSort] = useState("id-desc");

  const [filter, setFilter] = useState({});

  const handleDelete = (id) => {
    showApi.deleteById(id).then((res) => {
      showApi.getAllByPage(currentPage).then((res) => {
        setData(res);
        console.log(res);
        setIsLoading(false);
      });
    });
  };

  useEffect(() => {
    setIsLoading(true);
    console.log("123456");
    console.log(filter);
    console.log(currentPage);
    showApi.getAllByPage(currentPage, filter, sort).then((res) => {
      setData(res);
      console.log(res);
      setIsLoading(false);
    });
  }, [currentPage, filter, sort]);

  useEffect(() => {
    setIsLoading(true);
    cinemaApi.getAll().then((res) => {
      setCinemas(res.map((i) => ({ value: i.id, label: i.name })));
    });

    hallApi.getAll().then((res) => {
      setHalls(
        res.map((i) => ({
          value: i.id,
          label: i.name + " - " + i.cinema.name + " - " + i.cinema.address,
        }))
      );
    });

    movieApi.getAll().then((res) => {
      setMovies(res.map((i) => ({ value: i.id, label: i.name })));
    });
  }, []);

  const handleChangePage = (i) => {
    setCurrentPage(i);
  };

  const handleSubmit = (data) => {
    for (const key in data) {
      if (!data[key]) {
        console.log("111111");
        delete data[key];
      }
    }
    if (data.time) {
      data.start = data.time[0].format("YYYY-MM-DDTHH:mm:ss");
      data.end = data.time[1].format("YYYY-MM-DDTHH:mm:ss");
    }

    setFilter(data);
  };

  const handleChangeSort = (v) => {
    setSort(v);
    setCurrentPage(1);
  };

  return (
    <div>
      <Form
        name="form"
        form={form}
        validateTrigger="onSubmit"
        labelCol={{ flex: "110px" }}
        labelAlign="left"
        labelWrap
        wrapperCol={{ flex: 1 }}
        autoComplete="off"
        onFinish={handleSubmit}
        requiredMark={false}
      >
        <Form.Item label="Show time" name="time">
          <DatePicker.RangePicker
            format={"YYYY-MM-DD HH:mm"}
            showTime
            style={{ width: "100%" }}
            size="large"
          />
        </Form.Item>

        <Form.Item label="Movie" name="movieId">
          <Select
            style={{
              width: "100%",
            }}
            placeholder="Search movie"
            optionLabelProp="label"
            size="large"
            options={movies}
          ></Select>
        </Form.Item>
        <Form.Item label="Cinema" name="cinemaId">
          <Select
            style={{
              width: "100%",
            }}
            placeholder="Select cinema"
            optionLabelProp="label"
            size="large"
            options={cinemas}
          ></Select>
        </Form.Item>
        <Form.Item label="Hall" name="hallId">
          <Select
            style={{
              width: "100%",
            }}
            placeholder="Select hall"
            optionLabelProp="label"
            size="large"
            options={halls}
          ></Select>
        </Form.Item>
        <Form.Item>
          <Space>
            <Button size="large" type="primary" htmlType="submit">
              Filter
            </Button>
            <Button
              size="large"
              type="primary"
              ghost
              htmlType="submit"
              onClick={() => {
                form.resetFields();
              }}
            >
              Reset
            </Button>
          </Space>
        </Form.Item>
      </Form>

      <Space
        style={{
          marginBottom: 20,
        }}
      >
        <Typography.Text strong>Sắp xếp: </Typography.Text>
        <Select
          defaultValue="id-desc"
          style={{
            width: 300,
          }}
          onChange={handleChangeSort}
          options={[
            {
              label: "Mới nhất",
              value: "id-desc",
            },
            {
              label: "Cũ nhất",
              value: "id-asc",
            },
            {
              label: "Thời gian chiếu sớm nhất",
              value: "start-asc",
            },
            {
              label: "Thời gian chiếu muộn nhất",
              value: "start-desc",
            },
          ]}
        />
      </Space>

      <Table
        columns={columns}
        dataSource={data.content}
        rowKey={"id"}
        loading={isLoading}
        pagination={{
          total: data.totalElements || 0,
          pageSize: data.size,
          onChange: handleChangePage,
        }}
      />
    </div>
  );
};

export default Shows;
