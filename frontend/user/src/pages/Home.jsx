import { Button, Col, Empty, Input, List, Modal, Row } from "antd";
import React, { useEffect, useRef, useState } from "react";
import CinemaItem from "../components/CinemaItem";
import Container from "../components/Container";
import ShowItem from "../components/ShowItem";
import cinemaApi from "../api/cinemaApi";
import cityApi from "../api/cityApi";
import showApi from "../api/showApi";
import { FieldTimeOutlined } from "@ant-design/icons";

const daysOfWeek = [
  "Chủ nhật",
  "Thứ hai",
  "Thứ ba",
  "Thứ tư",
  "Thứ năm",
  "Thứ sáu",
  "Thứ bảy",
];
const addDayArr = [0, 1, 2, 3, 4, 5, 6];

const Show = () => {
  const currDate = new Date();
  const year = currDate.getFullYear();
  const month = String(currDate.getMonth() + 1).padStart(2, "0");
  const day = String(currDate.getDate()).padStart(2, "0");
  const formattedDate = `${year}-${month}-${day}`;

  const [openSelectCity, setOpenSelectCity] = useState(false);
  const [selectdDate, setSelectdDate] = useState(formattedDate);
  const [cities, setCities] = useState([]);
  const [currentCityIndex, setCurrentCityIndex] = useState(0);
  const [cinemas, setCinemas] = useState([]);
  const [shows, setShows] = useState([]);
  const [currentCinema, setCurrentCinema] = useState();

  const cinemaListRef = useRef();
  const cinemaListContentRef = useRef();
  const rightHeaderRef = useRef();
  const showListRef = useRef();

  useEffect(() => {
    cinemaListContentRef.current.style.height =
      cinemaListRef.current.offsetHeight + "px";
    showListRef.current.style.height =
      700 - rightHeaderRef.current.offsetHeight + "px";
  }, []);

  useEffect(() => {
    if (cities.length !== 0) {
      cinemaApi.getAllByCity(cities[currentCityIndex].id, 1).then((res) => {
        setCinemas(res.data.content);
        if (res.data.content.length) {
          handleClickCinema(res.data.content[0]);
        }
      });
    }
  }, [currentCityIndex]);

  useEffect(() => {
    cityApi.getAll().then((res) => {
      
      setCities(res.data);

      cinemaApi.getAllByCity(res.data[0].id, 1).then((res) => {
        setCinemas(res.data.content);
        if (res.data.content.length) {
          handleClickCinema(res.data.content[0]);
        }
      });
    });
  }, []);

  const handleClickCinema = (item) => {
    setCurrentCinema(item);
    showApi.getAllShowByCinema(item.id, 1, selectdDate).then((res) => {
      setShows(res.data.content.filter((item) => item.shows.length > 0));
    });
  };

  const handleClickDate = (formattedDate) => {
    setSelectdDate(formattedDate);
    if (currentCinema) {
      showApi
        .getAllShowByCinema(currentCinema.id, 1, formattedDate)
        .then((res) => {
          setShows(res.data.content.filter((item) => item.shows.length > 0));
        });
    }
  };

  const handleClickCity = (id) => {
    setCurrentCityIndex(id);
    setOpenSelectCity(false);
  };

  return (
    <Container>
      <div className="page_title">
        <div>
          <div className="page_title_icon">
            <FieldTimeOutlined />
          </div>
          <div className="page_title_txt">Lịch chiếu phim</div>
        </div>
      </div>
      <div className="show">
        <div className="show_body">
          <div className="left">
            <div className="cinema_search_location">
              <Button
                onClick={() => setOpenSelectCity(true)}
                icon={
                  <i style={{ marginRight: 8 }} className="bi bi-geo-alt"></i>
                }
                type="default"
                size={"large"}
                shape="default"
              >
                <div style={{ display: "inline-flex" }}>
                  <div>
                    {cities[currentCityIndex]
                      ? cities[currentCityIndex].name
                      : ""}
                  </div>
                  <div style={{ paddingLeft: 20, fontWeight: 100 }}>
                    <i
                      style={{ position: "relative", top: 2 }}
                      className="bi bi-chevron-down"
                    ></i>
                  </div>
                </div>
              </Button>
            </div>
            <div className="cinema_list" ref={cinemaListRef}>
              <div className="cinema_list_content" ref={cinemaListContentRef}>
                <List
                  dataSource={cinemas}
                  renderItem={(item) => (
                    <CinemaItem
                      isActive={currentCinema && currentCinema.id === item.id}
                      onClick={() => handleClickCinema(item)}
                      item={item}
                    />
                  )}
                />
              </div>
            </div>
          </div>
          <div className="right">
            <div className="right_header" ref={rightHeaderRef}>
              <div className="cinema_info">
                <h1>
                  Rạp chiếu phim {currentCinema ? currentCinema.name : ""}
                </h1>
                <p>{currentCinema ? currentCinema.address : "Chưa chọn rạp"}</p>
              </div>
              <div className="show_date">
                {addDayArr.map((i) => {
                  const date = new Date();
                  date.setDate(date.getDate() + i);

                  const year = date.getFullYear();
                  const month = String(date.getMonth() + 1).padStart(2, "0");
                  const day = String(date.getDate()).padStart(2, "0");
                  const formattedDate = `${year}-${month}-${day}`;
                  let dayOfWeek = daysOfWeek[date.getDay()];

                  const today = new Date();
                  const isToday = date.toDateString() === today.toDateString();
                  dayOfWeek = isToday ? "Hôm nay" : dayOfWeek;

                  const isActive = selectdDate
                    ? selectdDate === formattedDate
                    : i === 0;

                  return (
                    <Button
                      key={i}
                      style={{ height: "unset", padding: 0 }}
                      className={`show_date_item ${isActive ? "active" : ""}`}
                      onClick={() => handleClickDate(formattedDate)}
                    >
                      <div>
                        <div>{day}</div>
                        <div>{dayOfWeek}</div>
                      </div>
                    </Button>
                  );
                })}
              </div>
            </div>
            <div className="right_body">
              <div className="show_list" ref={showListRef}>
                {shows.length === 0 ? (
                  <Empty
                    description="Không tìm thấy suất chiếu"
                    style={{
                      height: "100%",
                      alignItems: "center",
                      display: "flex",
                      flexDirection: "column",
                      justifyContent: "center",
                    }}
                  />
                ) : (
                  shows.map((item, index) => (
                    <ShowItem key={index} item={item} />
                  ))
                )}
              </div>
            </div>
          </div>
        </div>
      </div>

      <Modal
        title="Chọn địa điểm"
        centered
        cancelButtonProps={{ style: { display: "none" } }}
        open={openSelectCity}
        onOk={() => {
          setOpenSelectCity(false);
        }}
        width={800}
        okText="Đóng"
      >
        <Row gutter={[12, 12]}>
          {cities.map((item, index) => (
            <Col key={item.id} span={6}>
              <Button
                type={index === currentCityIndex ? "primary" : "text"}
                ghost={index === currentCityIndex}
                onClick={() => handleClickCity(index)}
                style={{
                  width: "100%",
                  fontSize: 14,
                }}
              >
                {item.name}
              </Button>
            </Col>
          ))}
        </Row>
      </Modal>
    </Container>
  );
};

export default Show;
