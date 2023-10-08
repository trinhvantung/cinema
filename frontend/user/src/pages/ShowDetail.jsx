import { LoadingOutlined } from "@ant-design/icons";
import {
  Breadcrumb,
  Button,
  Card,
  Col,
  Form,
  Input,
  Row,
  Space,
  Spin,
  Table,
} from "antd";
import React, { useEffect, useRef, useState } from "react";
import { Link, useParams } from "react-router-dom";
import bookingApi from "../api/bookingApi";
import showApi from "../api/showApi";
import Container from "../components/Container";
import PaymentMethod from "../components/PaymentMethod";
import Seat from "../components/Seat";
import numberWithCommas from "../utils/numberWithComma";
import useKeycloak from "../hooks/useKeycloak";

const columns = [
  {
    title: "Ghế",
    dataIndex: "seats",
    render: (item) => item.join(", "),
  },
  {
    title: "Loại",
    dataIndex: "seatType",
    render: (item) => item.name,
  },
  {
    title: "Thành tiền",
    dataIndex: "totalPrice",
    render: (price) => numberWithCommas(price) + " đ",
  },
];

const getTime = (dateString) => {
  const date = new Date(dateString);
  // date.setDate(date.getDate() + i)

  // const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  const hours = String(date.getHours()).padStart(2, "0");
  const minutes = String(date.getMinutes()).padStart(2, "0");

  const today = new Date();
  const isToday = date.toDateString() === today.toDateString();

  const formattedDate = `${hours}:${minutes} - ${
    isToday ? "Hôm nay," : ""
  } ${day}/${month}`;

  return formattedDate;
};

const ShowDetail = () => {
  const { id } = useParams();
  const { keycloak } = useKeycloak();

  const [isLoading, setIsLoading] = useState(false);
  const [data, setData] = useState([]);
  const [seatClicked, setSeatClicked] = useState([]);
  const [seatClickedDetail, setSeatClickedDetail] = useState([]);
  const [seatType, setSeatType] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [scale, setScale] = useState(1);
  const [pos, setPos] = useState({ x: 0, y: 0 });
  const [dragging, setDragging] = useState(false);
  const [startPos, setStartPos] = useState({ x: 0, y: 0 });
  const [step, setStep] = useState(1);
  const [showPrice, setShowPrice] = useState();
  const [show, setShow] = useState();
  const [bookedSeat, setBookedSeat] = useState([]);

  const refSeatDetail = useRef();
  const refDetail = useRef();

  const [form] = Form.useForm();

  useEffect(() => {
    const detail = seatClicked.reduce((result, value) => {
      const temp = result.find((i) => i.seatType.id === value.seatType.id);
      if (!temp) {
        console.log("Not Contain");
        result.push({
          key: value.seatType.id,
          seatType: value.seatType,
          seats: [value.name],
          totalPrice: showPrice.find(
            (sp) => sp.seatTypeId === value.seatType.id
          ).price,
        });
      } else {
        result = result.map((i) => {
          if (i.seatType.id === value.seatType.id) {
            const totalPrice =
              i.totalPrice +
              showPrice.find((sp) => sp.seatTypeId === value.seatType.id).price;
            return {
              ...i,
              seats: [...i.seats, value.name],
              totalPrice: totalPrice,
            };
          } else {
            return i;
          }
        });
      }
      return result;
    }, []);
    setSeatClickedDetail(detail);
    console.log(detail);
  }, [seatClicked]);

  useEffect(() => {
    showApi.getDetailShow(id).then((res) => {
      console.log(res);
      // setSeats(res.seats)
      setShowPrice(res.data.showPrices);
      setShow(res.data);
    });

    bookingApi.getAllBookedSeatsByShow(id).then((res) => {
      console.log(res);
      setBookedSeat(res.data);
    });
  }, []);

  useEffect(() => {
    if (show) {
      let maxRowIndex = 0;
      let maxColumnIndex = 0;
      const seatTypeTemp = [];

      for (let item of show.seats) {
        if (!seatTypeTemp.find((st) => st.id === item.seatType.id)) {
          seatTypeTemp.push(item.seatType);
        }
        if (item.rowIndex > maxRowIndex) {
          maxRowIndex = item.rowIndex;
        }
        if (item.columnIndex > maxColumnIndex) {
          maxColumnIndex = item.columnIndex;
        }
      }
      setSeatType(seatTypeTemp);
      const dataTemp = [];
      for (let i = 1; i <= maxRowIndex; i++) {
        const temp = [];
        for (let j = 1; j <= maxColumnIndex; j++) {
          temp.push(
            show.seats.find((s) => s.columnIndex === j && s.rowIndex == i)
          );
        }
        dataTemp.push(temp);
      }
      setData((prev) => [...prev, dataTemp]);
    }
  }, [show]);

  const handleWheel = (event) => {
    const delta = event.deltaY;
    const newScale = scale - delta * 0.001;
    if (newScale > 1 || newScale < 0.5) {
      return;
    }
    setScale(newScale);
  };
  const handleMouseDown = (event) => {
    setDragging(true);
    setStartPos({ x: event.clientX, y: event.clientY });
  };

  const handleMouseMove = (event) => {
    if (dragging) {
      const dx = event.clientX - startPos.x;
      const dy = event.clientY - startPos.y;
      setStartPos({ x: event.clientX, y: event.clientY });
      setPos({ x: pos.x + dx, y: pos.y + dy });
    }
  };

  const handleMouseUp = () => {
    setDragging(false);
  };

  const handleClickSeat = (s) => {
    const price = showPrice.find((sp) => sp.seatTypeId == s.seatType.id).price;
    if (!bookedSeat.find((bs) => bs.seat.id === s.id)) {
      if (seatClicked.find((i) => i.id === s.id)) {
        setSeatClicked(seatClicked.filter((i) => i.id != s.id));
        setTotalPrice((prev) => prev - price);
      } else {
        setSeatClicked((prev) => [...prev, s]);
        setTotalPrice((prev) => prev + price);
      }
    }
  };

  const handleBooking = () => {
    form.validateFields().then((data) => {
      setIsLoading(true);
      data.showId = Number(id);
      data.bookingItems = seatClicked.map((seat) => ({ seatId: seat.id }));
      // console.log(seatClicked)
      console.log(data);

      bookingApi
        .create(data)
        .then((res) => {
          console.log(res);

          window.location.replace(res.data.paymentUrl);
        })
        .catch((err) => {
          notification.error({
            message: `Notification`,
            description: "Có lỗi xảy ra",
            placement: "top",
          });
        });
    });
  };

  return (
    <Spin
      spinning={isLoading}
      indicator={
        <LoadingOutlined
          style={{
            fontSize: 50,
          }}
        />
      }
    >
      <Container>
        <Breadcrumb
          items={[
            {
              title: <Link to={"/"}>Home</Link>,
            },
            {
              title: "Đặt vé phim",
            },
          ]}
        />
        <div className="show_detail_body">
          <Row gutter={24}>
            <Col span={16}>
              <div className={`${step === 2 ? "d-none" : ""}`}>
                <div style={{ overflow: "hidden" }} ref={refDetail}>
                  <div
                    style={{
                      transform: `scale(${scale}) translate(${pos.x}px, ${pos.y}px)`,
                    }}
                    onWheel={handleWheel}
                    onMouseDown={handleMouseDown}
                    onMouseMove={handleMouseMove}
                    onMouseUp={handleMouseUp}
                  >
                    <div className="screen">Màn hình</div>
                    <div className="seat_detail" ref={refSeatDetail}>
                      <div className="data">
                        {data.map((dataItem, idx) => (
                          <div className="seats" key={idx}>
                            {dataItem.map((item, index) => (
                              <div key={index} className="seat-row">
                                {item.map((s, id) =>
                                  s ? (
                                    <Seat
                                      booked={bookedSeat.find(
                                        (bs) => bs.seat.id === s.id
                                      )}
                                      key={id}
                                      onClick={() => handleClickSeat(s)}
                                      seat={s}
                                    />
                                  ) : (
                                    <div key={id} className="seat"></div>
                                  )
                                )}
                              </div>
                            ))}
                          </div>
                        ))}
                      </div>
                    </div>
                  </div>
                </div>

                <div className="seat_type">
                  <Row gutter={24} justify={"center"}>
                    <Col>
                      <div className="seat_type_item">
                        <div
                          className="color"
                          style={{ backgroundColor: "#e5e5e5" }}
                        ></div>
                        <div className="name">Đã chọn</div>
                      </div>
                    </Col>
                    <Col>
                      <div className="seat_type_item">
                        <div
                          className="color"
                          style={{ backgroundColor: "#555555", color: "white" }}
                        ></div>
                        <div className="name">Đã bán</div>
                      </div>
                    </Col>
                    {seatType.map((st) => (
                      <Col key={st.id}>
                        <div className="seat_type_item">
                          <div
                            className="color"
                            style={{ backgroundColor: `${st.color}` }}
                          ></div>
                          <div className="name">{st.name}</div>
                        </div>
                      </Col>
                    ))}
                  </Row>
                </div>
              </div>
              <Space
                direction="vertical"
                size={16}
                style={{ width: "100%" }}
                className={`${step === 1 ? "d-none" : ""}`}
              >
                <Card
                  title="Tóm tắt đơn hàng"
                  style={{
                    width: "100%",
                  }}
                >
                  <Table
                    columns={columns}
                    dataSource={seatClickedDetail}
                    pagination={false}
                  />
                </Card>
                <Card
                  title="Thông tin khách hàng"
                  style={{
                    width: "100%",
                  }}
                >
                  <Form form={form} layout="vertical">
                    <Form.Item
                      label="Họ tên"
                      name={"fullName"}
                      rules={[
                        {
                          required: true,
                          message: "Họ tên không được để trống",
                        },
                      ]}
                    >
                      <Input placeholder="Họ tên" size="large" />
                    </Form.Item>
                    <Form.Item
                      label="Email"
                      name={"email"}
                      rules={[
                        {
                          required: true,
                          message: "Email không được để trống",
                        },
                      ]}
                    >
                      <Input placeholder="Email" size="large" />
                    </Form.Item>
                    <Form.Item
                      label="Số điện thoại"
                      name={"phoneNumber"}
                      rules={[
                        {
                          required: true,
                          message: "Số điện thoại không được để trống",
                        },
                      ]}
                    >
                      <Input placeholder="Số điện thoại" size="large" />
                    </Form.Item>
                  </Form>
                </Card>

                <Card
                  title="Phương thức thanh toán"
                  style={{
                    width: "100%",
                  }}
                >
                  <PaymentMethod active={true} />
                </Card>
              </Space>
            </Col>
            <Col span={8}>
              <div className="show_info">
                <div className="show_info_title">Tóm tắt đặt ghế</div>
                <div className="show_info_content">
                  <div className="show_info_item">
                    <div>Phim:</div>
                    <div>{show ? show.movie.name : ""}</div>
                  </div>
                  <div className="show_info_item">
                    <div>Phòng chiếu:</div>
                    <div>{show ? show.hall.name : ""}</div>
                  </div>
                  <div className="show_info_item">
                    <div>Giờ chiếu:</div>
                    <div>{show ? getTime(show.start) : ""}</div>
                  </div>
                  <div className="show_info_item">
                    <div>Rạp:</div>
                    <div>{show ? show.hall.cinema.name : ""}</div>
                  </div>
                  <div className="show_info_item">
                    <div>Ghế:</div>
                    <div>
                      {seatClicked.length > 0
                        ? seatClicked.map((s) => s.name).join(", ")
                        : "Chưa chọn"}
                    </div>
                  </div>
                </div>
              </div>
              <div className="show_total_price">
                <div>Tổng đơn hàng:</div>
                <div>{numberWithCommas(totalPrice)} đ</div>
              </div>
              {step === 1 && (
                <Button
                  type="primary"
                  size="large"
                  style={{ width: "100%" }}
                  onClick={() => {
                    if (!keycloak.authenticated) {
                      keycloak.login();
                    }
                    setStep(2);
                  }}
                  disabled={!totalPrice}
                >
                  Tiếp tục
                </Button>
              )}
              {step === 2 && (
                <>
                  <Button
                    onClick={handleBooking}
                    type="primary"
                    size="large"
                    style={{ width: "100%" }}
                  >
                    Thanh toán
                  </Button>
                  <Button
                    type="default"
                    size="large"
                    style={{ width: "100%", marginTop: 12 }}
                    onClick={() => setStep(1)}
                  >
                    Quay lại
                  </Button>
                </>
              )}
            </Col>
          </Row>
        </div>
      </Container>
    </Spin>
  );
};

export default ShowDetail;
