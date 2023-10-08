import { MailOutlined, PhoneOutlined } from "@ant-design/icons";
import {
  Button,
  Descriptions,
  Modal,
  Popconfirm,
  Space,
  Table,
  Tag,
  Typography,
  notification,
} from "antd";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import bookingApi from "../api/bookingApi";
import numberWithCommas from "../utils/numberWithComma";

const getTime = (dateString) => {
  const date = new Date(dateString);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  const hours = String(date.getHours()).padStart(2, "0");
  const minutes = String(date.getMinutes()).padStart(2, "0");

  const formattedDate = `${hours}:${minutes} - ${day}/${month}/${year}`;

  return formattedDate;
};

const formatDate = (dateString) => {
  const date = new Date(dateString);

  const hours = date.getHours();
  const minutes = date.getMinutes();

  return (
    hours.toString().padStart(2, "0") +
    ":" +
    minutes.toString().padStart(2, "0")
  );
};

const gettDate = (dateString) => {
  const date = new Date(dateString);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");
  const formattedDate = `${day}/${month}/${year}`;

  return formattedDate;
};

const getOrderStatus = (statusEnum) => {
  if (statusEnum == "PENDING_PAYMENT") {
    return "Đang chờ thanh toán";
  } else if (statusEnum == "CANCELED") {
    return "Đã  hủy";
  } else if (statusEnum == "CANCELLING") {
    return "Đang hủy";
  } else if (statusEnum == "REFUND_CANCEL_BOOKING_FAIL") {
    return "Hoàn tiền hủy vé thất bại";
  } else if (statusEnum == "COMPLETED") {
    return "Thành công";
  } else if (statusEnum == "PAYMENT_FAIL") {
    return "Thanh toán thất bại";
  } else if (statusEnum == "REFUND_SUCCESS") {
    return "Ghế bị trùng - Hoàn tiền thành công";
  } else if (statusEnum == "REFUND_FAIL") {
    return "Ghế bị trùng - Hoàn tiền thất bại";
  } else if (statusEnum == "DUPLICATE") {
    return "Ghế bị trùng - Đang hoàn tiền";
  } else if (statusEnum == "ACTIVATED_TICKET") {
    return "Đã nhận vé";
  }
};

const columnsOrderItem = [
  {
    title: "Ghế",
    dataIndex: "seat",
    key: "seat",
    render: (value) => value.name,
  },
  {
    title: "Loại ghế",
    dataIndex: "seat",
    key: "seat",
    render: (value) => (
      <Tag color={value.seatType.color}>{value.seatType.name}</Tag>
    ),
  },
  {
    title: "Giá",
    dataIndex: "price",
    key: "price",
    render: (value) => numberWithCommas(value) + " đ",
  },
];

const OrderItem = ({ item }) => {
  const [orderDetail, setOrderDetail] = useState();
  const [open, setOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false);


  const { Text } = Typography;

  const handleViewDetail = (item) => {
    setOpen(true);

    bookingApi.getDetailByCurrentUser(item.id).then((res) => {
      setOrderDetail({ ...res.data, movie: item.show.movie });
      console.log(res);
    });
  };

  const handleCancelOrder = (order) => {
    console.log(order);
    bookingApi
      .cancelling(order.id)
      .then((res) => {
        notification.success({
          message: `Notification`,
          description: "Hệ thống đang thực hiện hoàn tiền",
          placement: "top",
        });
      })
      .catch((err) => {
        notification.error({
          message: `Notification`,
          description: "Error",
          placement: "top",
        });
      });
  };

  return (
    <div className="order_item">
      <div className="image">
        <div
          style={{ backgroundImage: `url(${item.show.movie.thumbnailUrl})` }}
        ></div>
      </div>
      <div className="info">
        <div>
          <div className="name">{item.show.movie.name}</div>
          <div className="cinema">
            <p>Thời gian đặt: </p>
            <p>{getTime(item.createdAt)}</p>
          </div>
          <div className="hall">
            <p>Giờ chiếu:</p>
            <p>
              {formatDate(item.show.start)} - {formatDate(item.show.end)}{" "}
              <span
                style={{
                  fontWeight: 600,
                  color: "#000",
                }}
              >
                ({gettDate(item.show.start)})
              </span>
            </p>
          </div>

          <div className="status">
            <p>Trạng thái: </p>
            <p>
              <Tag
                color={["COMPLETED", "ACTIVATED_TICKET"].includes(item.status) ? "green" : "red"}
              >
                {getOrderStatus(item.status)}
              </Tag>
            </p>
          </div>
        </div>
        <div>
          <div>{numberWithCommas(item.totalPrice)} đ</div>

          <Space>
            {item.canCreateTicket && (
              <Link to={`/ticket/${item.id}`}>
                <Button type="primary">Tạo vé</Button>
              </Link>
            )}
            <Button onClick={() => handleViewDetail(item)}>Xem chi tiết</Button>
          </Space>
        </div>
      </div>

      <Modal
        title=""
        centered
        open={open}
        closable={false}
        onOk={() => {
          setOpen(false);
          setOrderDetail(null);
        }}
        cancelButtonProps={{ style: { display: "none" } }}
        width={1000}
        okText="Close"
      >
        {orderDetail && (
          <>
            <Descriptions title="Order" layout="vertical" bordered>
              <Descriptions.Item label="Phim">
                {orderDetail.movie.name}
              </Descriptions.Item>
              <Descriptions.Item label="Tổng tiền">
                <Text type="success" strong>
                  {numberWithCommas(orderDetail.totalPrice)} đ
                </Text>
              </Descriptions.Item>
              <Descriptions.Item label="Họ tên">
                {orderDetail.fullName}
              </Descriptions.Item>
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
                <Tag color={["COMPLETED", "ACTIVATED_TICKET"].includes(item.status) ? "green" : "red"}>
                  {getOrderStatus(orderDetail.status)}
                </Tag>
              </Descriptions.Item>
              <Descriptions.Item label="Rạp">
                {orderDetail.hall.cinema.name}
              </Descriptions.Item>
              <Descriptions.Item label="Phòng">
                {orderDetail.hall.name}
              </Descriptions.Item>
              <Descriptions.Item label="Thời gian đặt">
                <Tag color="#55acee">{getTime(orderDetail.createdAt)}</Tag>
              </Descriptions.Item>
            </Descriptions>
            {orderDetail.canCancel && (
              <Popconfirm
                title="Xác nhận"
                description="Bạn có muốn hủy đặt tất cả các vé không?"
                onConfirm={() => handleCancelOrder(orderDetail)}
                okText="Có"
                cancelText="Không"
              >
                <br />
                <Button danger loading={isLoading} disabled={isLoading}>
                  Hủy đặt vé
                </Button>
              </Popconfirm>
            )}
            <p
              style={{
                fontWeight: 600,
                fontSize: 16,
                lineHeight: 1.5,
                padding: "30px 0 16px 0",
              }}
            >
              Ghế
            </p>
            <Table
              columns={columnsOrderItem}
              dataSource={orderDetail.bookingItems}
              pagination={false}
              scroll={{
                y: 240,
              }}
            />
          </>
        )}
      </Modal>
    </div>
  );
};

export default OrderItem;
