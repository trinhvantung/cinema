import {
  FileExcelOutlined
} from '@ant-design/icons';
import { Button, Card, Col, Form, Input, Row } from 'antd';
import { CategoryScale } from 'chart.js';
import Chart from 'chart.js/auto';
import React, { useEffect, useState } from 'react';
import { Bar, Line, Pie } from 'react-chartjs-2';
import bookingApi from '../../api/bookingApi';
import movieApi from '../../api/movieApi';
import paymentApi from '../../api/paymentApi';

Chart.register(CategoryScale);

const backgroundColors = [
  "#F44336",
  "#E91E63",
  "#9C27B0",
  "#673AB7",
  "#3F51B5",
  "#2196F3",
  "#03A9F4",
  "#00BCD4",
  "#009688",
  "#4CAF50",
  "#8BC34A",
  "#CDDC39",
  "#FFEB3B",
  "#FFC107",
  "#FF9800",
  "#FF5722",
  "#795548",
  "#9E9E9E",
  "#607D8B",
  "#000000"
];

function formatDate(date) {
  var year = date.getFullYear();
  var month = String(date.getMonth() + 1).padStart(2, '0');
  var day = String(date.getDate()).padStart(2, '0');
  return year + '-' + month + '-' + day;
}

function formatDate2(date) {
  var year = date.getFullYear();
  var month = String(date.getMonth() + 1).padStart(2, '0');
  var day = String(date.getDate()).padStart(2, '0');
  return year + '/' + month + '/' + day;
}

var today = new Date();

// Lấy ngày của 5 tháng trước
var fiveMonthsAgo = new Date();
fiveMonthsAgo.setMonth(fiveMonthsAgo.getMonth() - 5);
var todayFormatted = formatDate(today);
var fiveMonthsAgoFormatted = formatDate(fiveMonthsAgo);


const DashBoard = () => {
  const [countMovieByCategory, setCountMovieByCategory] = useState()
  const [orderStatistic, setOrderStatistic] = useState()
  const [topRevenueByMovie, setTopRevenueByMovie] = useState()
  const [paymentStatistic, setPaymentStatistic] = useState()
  const [orderStatisticForm] = Form.useForm()
  const [topRevenueByMovieForm] = Form.useForm()
  const [paymentStatisticForm] = Form.useForm()

  useEffect(() => {
    movieApi.countMovieByCategory().then(res => {
      console.log(res)
      setCountMovieByCategory({
        labels: res.map(i => i.categoryName),
        datasets: [{
          data: res.map(i => i.movieCount),
          backgroundColor: backgroundColors
        }]
      })
    })

    bookingApi.getOrderStatistic(fiveMonthsAgoFormatted, todayFormatted).then(res => {
      setOrderStatistic({
        labels: res.map(i => i.time),
        datasets: [
          {
            label: "Đơn hàng",
            data: res.map(i => i.count),
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1,
          }
        ]
      })
    })

    bookingApi.getTopRevenueByMovie(fiveMonthsAgoFormatted, todayFormatted, 5).then(res => {
      setTopRevenueByMovie({
        labels: res.map(i => i.movieName),
        datasets: [
          {
            label: ["1", '2'],
            data: res.map(i => i.amount),
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1,
            backgroundColor: backgroundColors
          }
        ]
      })
    })


    paymentApi.statistic(fiveMonthsAgoFormatted, todayFormatted).then(res => {
      console.log(res)
      setPaymentStatistic({
        labels: res.map(i => i.time),
        datasets: [
          {
            label: "Doanh thu",
            data: res.map(i => i.amount),
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
          }
        ]
      })
    })

  }, [])

  const onFinishOrderStatistic = (data) => {
    bookingApi.getOrderStatistic(data.start, data.end).then(res => {
      setOrderStatistic({
        labels: res.map(i => i.time),
        datasets: [
          {
            label: "Đơn hàng",
            data: res.map(i => i.count),
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1,
          }
        ]
      })
    })
  }

  const onFinishTopRevenueByMovie = (data) => {
    bookingApi.getTopRevenueByMovie(data.start, data.end, data.limit).then(res => {
      setTopRevenueByMovie({
        labels: res.map(i => i.movieName),
        datasets: [
          {
            label: "Đơn hàng",
            data: res.map(i => i.amount),
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1,
            backgroundColor: backgroundColors
          }
        ]
      })
    })
  }


  const onFinishPaymentStatistic = (data) => {
    paymentApi.statistic(data.start, data.end).then(res => {
      console.log(res)
      setPaymentStatistic({
        labels: res.map(i => i.time),
        datasets: [
          {
            label: "Doanh thu",
            data: res.map(i => i.amount),
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1,
          }
        ]
      })
    })
  }

  const exportOrderStatistic = () => {
    const data = orderStatisticForm.getFieldsValue()
    bookingApi.exportOrderStatistic(data.start, data.end).then((response) => {
      const href = URL.createObjectURL(response);

      const link = document.createElement('a');
      link.href = href;
      link.setAttribute('download', 'OrderStatistic.xlsx');
      document.body.appendChild(link);
      link.click();

      document.body.removeChild(link);
      URL.revokeObjectURL(href);
    })
  }
  const exportTopRevenueByMovie = () => {
    const data = topRevenueByMovieForm.getFieldsValue()
    bookingApi.exportTopRevenueByMovie(data.start, data.end, data.limit).then((response) => {
      const href = URL.createObjectURL(response);

      const link = document.createElement('a');
      link.href = href;
      link.setAttribute('download', 'TopRevenueByMovie.xlsx');
      document.body.appendChild(link);
      link.click();

      document.body.removeChild(link);
      URL.revokeObjectURL(href);
    })
  }
  const exportPaymentStatistic = () => {
    const data = topRevenueByMovieForm.getFieldsValue()
    paymentApi.export(data.start, data.end).then((response) => {
      const href = URL.createObjectURL(response);

      const link = document.createElement('a');
      link.href = href;
      link.setAttribute('download', 'PaymentStatistic.xlsx');
      document.body.appendChild(link);
      link.click();

      document.body.removeChild(link);
      URL.revokeObjectURL(href);
    })
  }


  return (
    <div>

      <Row gutter={[100, 100]}>
        <Col span={10}>
          <Card
            title="Tổng số phim các danh mục"
            bordered={true}
            style={{
            }}
          >
            {
              countMovieByCategory && <Pie data={countMovieByCategory} style={{}} />
            }
          </Card>
        </Col>
        <Col span={14}>

          <Card
            title="Tổng số đơn hàng qua các tháng"
            bordered={true}
            style={{
            }}
          >
            {
              orderStatistic && <Line data={orderStatistic} />
            }
            <Form
              name="customized_form_controls"
              layout="inline"
              form={orderStatisticForm}
              onFinish={onFinishOrderStatistic}
              style={{
                marginTop: 20,
                justifyContent: 'center'
              }}
              initialValues={{
                end: formatDate(today),
                start: formatDate(fiveMonthsAgo)
              }}
            >
              <Form.Item
                name="start"
                label="Start"
              >
                <Input
                  type="date"
                  style={{
                  }}
                />
              </Form.Item>
              <Form.Item
                name="end"
                label="End"
              >
                <Input
                  type="date"
                  style={{
                  }}
                />
              </Form.Item>
              <Form.Item>
                <Button type="primary" htmlType="submit">
                  Submit
                </Button>
              </Form.Item>
              <Form.Item>
                <Button icon={<FileExcelOutlined />} type='primary' ghost onClick={exportOrderStatistic}>
                  Export to Excel
                </Button>
              </Form.Item>
            </Form>
          </Card>
        </Col>

        <Col span={24}>

          <Card
            title="Tổng số đơn hàng qua các tháng"
            bordered={true}
            style={{
            }}
          >
            {
              topRevenueByMovie && <Bar data={topRevenueByMovie} options={{
                plugins: {
                  legend: {
                    display: false, // Loại bỏ hiển thị nhãn (label)
                  },
                },
              }} />
            }
            <Form
              name="customized_form_controls"
              layout="inline"
              form={topRevenueByMovieForm}
              onFinish={onFinishTopRevenueByMovie}
              style={{
                marginTop: 20,
                justifyContent: 'center'
              }}
              initialValues={{
                end: formatDate(today),
                start: formatDate(fiveMonthsAgo),
                limit: 5
              }}
            >
              <Form.Item
                name="start"
                label="Start"
              >
                <Input
                  type="date"
                  style={{
                  }}
                />
              </Form.Item>
              <Form.Item
                name="end"
                label="End"
              >
                <Input
                  type="date"
                  style={{
                  }}
                />
              </Form.Item>
              <Form.Item
                name="limit"
                label="Limit"
              >
                <Input
                  type="number"
                  style={{
                    width: 50
                  }}
                />
              </Form.Item>
              <Form.Item>
                <Button type="primary" htmlType="submit">
                  Submit
                </Button>
              </Form.Item>
              <Form.Item>
                <Button icon={<FileExcelOutlined />} type='primary' ghost onClick={exportTopRevenueByMovie}>
                  Export to Excel
                </Button>
              </Form.Item>
            </Form>
          </Card>
        </Col>

        <Col span={24}>

          <Card
            title="Doanh thu qua các tháng"
            bordered={true}
            style={{
            }}
          >
            {
              paymentStatistic && <Line data={paymentStatistic} />
            }
            <Form
              name="customized_form_controls"
              layout="inline"
              form={paymentStatisticForm}
              onFinish={onFinishPaymentStatistic}
              style={{
                marginTop: 20,
                justifyContent: 'center'
              }}
              initialValues={{
                end: formatDate(today),
                start: formatDate(fiveMonthsAgo),
              }}
            >
              <Form.Item
                name="start"
                label="Start"
              >
                <Input
                  type="date"
                  style={{
                  }}
                />
              </Form.Item>
              <Form.Item
                name="end"
                label="End"
              >
                <Input
                  type="date"
                  style={{
                  }}
                />
              </Form.Item>
              <Form.Item>
                <Button type="primary" htmlType="submit">
                  Submit
                </Button>
              </Form.Item>
              <Form.Item>
                <Button icon={<FileExcelOutlined />} type='primary' ghost onClick={exportPaymentStatistic}>
                  Export to Excel
                </Button>
              </Form.Item>
            </Form>
          </Card>
        </Col>
      </Row>
    </div >
  );
}

export default DashBoard