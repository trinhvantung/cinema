import { Comment } from "@ant-design/compatible";
import { Avatar, Button, Empty, Form, Rate, Tooltip, notification } from "antd";
import TextArea from "antd/es/input/TextArea";
import React, { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom";
import movieApi from "../api/movieApi";
import reviewApi from "../api/reviewApi";
import showApi from "../api/showApi";
import CinemaShowItem from "../components/CinemaShowItem";
import Container from "../components/Container";
import { getTime } from "../utils/dateUtils";

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
// const addDayArr = [-12, -11, -10, -9, -8, -7, -6]
const colorList = ["#f56a00", "#7265e6", "#ffbf00", "#00a2ae"];

const getColorAndName = (name) => {
  const nameArr = name.trim().split(" ");
  const char = nameArr[nameArr.length - 1][0].toUpperCase();
  const charCode = char.charCodeAt(0);
  const colorListLength = colorList.length;

  const pos = charCode % colorListLength;

  return {
    color: colorList[pos],
    name: char,
  };
};

const getReleaseDate = (dateString) => {
  const date = new Date(dateString);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");
  return `${day}/${month}/${year}`;
};

const MovieDetail = () => {
  const currDate = new Date();
  const year = currDate.getFullYear();
  const month = String(currDate.getMonth() + 1).padStart(2, "0");
  const day = String(currDate.getDate()).padStart(2, "0");
  const formattedDate = `${year}-${month}-${day}`;

  const [reviewForm] = Form.useForm();

  const { id } = useParams();
  const [selectdDate, setSelectdDate] = useState(formattedDate);
  const [movie, setMovie] = useState({});
  const [shows, setShows] = useState([]);
  const [reviews, setReviews] = useState([]);
  const [star, setStar] = useState(5);
  const [review, setReview] = useState();
  const [reviewDetail, setReviewDetail] = useState();
  const [contentValue, setContentValue] = useState("");
  const [currentStar, setCurrentStar] = useState(0);

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
    movieApi.getById(id).then((res) => {
      setMovie(res.data);
    });

    showApi.getAllShowByMovie(id, 1, selectdDate).then((res) => {
      setShows(res.data.content.filter((item) => item.shows.length > 0));
    });

    reviewApi.getAllByMovieId(id, 1).then((res) => {
      setReviews(res.data.content);
    });

    reviewApi.getReviewDetailByMovieId(id).then((res) => {
      setReviewDetail(res.data);
    });

    reviewApi.checkExistsByMovieIdAndUserId(id).then((res) => {
      if (res.data.data != "") {
        setReview(res.data);
        reviewForm.setFields([
          {
            name: "content",
            value: res.data.content,
          },
        ]);
      }
    });
  }, []);

  const handleClickDate = (formattedDate) => {
    console.log(formattedDate);
    setSelectdDate(formattedDate);
    showApi.getAllShowByMovie(id, 1, formattedDate).then((res) => {
      console.log(res);
      setShows(res.data.content.filter((item) => item.shows.length > 0));
    });
  };

  const handleReview = (data) => {
    data.star = star;
    reviewApi
      .create(id, data)
      .then((res) => {
        console.log(res);

        const fullName = localStorage.getItem("fullName");
        setReview(res);
        // setContentValue(data.content)
        setReviews((prev) => [{ ...res, user: { fullName } }, ...prev]);
      })
      .catch((err) => {
        notification.error({
          message: `Notification`,
          description: "Error",
          placement: "top",
        });
      });
  };

  const handleGetReviewByStar = (star) => {
    setCurrentStar(star);
    if (star === 0) {
      reviewApi.getAllByMovieId(id, 1).then((res) => {
        setReviews(res.data.content);
      });
    } else {
      console.log(star);
      reviewApi.getAllByMovieIdAndStar(id, 1, star).then((res) => {
        setReviews(res.data.content);
        console.log(res);
      });
    }
  };

  return (
    <Container>
      <div className="movie_detail">
        <div className="image">
          <div style={{ backgroundImage: `url(${movie.thumbnailUrl})` }}></div>
        </div>
        <div className="movie_info">
          <div className="name"> {movie.name}</div>
          <div className="duration">{movie.duration} phút</div>
          <div className="release_date">
            <div>Ngày phát hành: </div>{" "}
            {getReleaseDate(movie.releaseDate || "")}
          </div>
          <div className="categories">
            <div>Danh mục: </div>
            {movie.categories && movie.categories.map((c) => c.name).join(", ")}
          </div>
          <p>Nội dung:</p>
          <div className="content">{movie.description}</div>
        </div>
      </div>
      <div className="show">
        <div className="show_body">
          <div
            className="right"
            style={{ borderRight: "1px solid rgba(20, 20, 20, .07)" }}
          >
            <div className="right_header" ref={rightHeaderRef}>
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
                  shows.map((item) => <CinemaShowItem item={item} />)
                )}
                {/* <div className="show_more">
                                    <Button type="primary" shape="round">Xem thêm</Button>
                                </div> */}
              </div>
            </div>
          </div>
          <div className="left">
            <div className="cinema_search_location">
              <p style={{ fontSize: 18, fontWeight: "bold" }}>
                Đánh giá ({reviewDetail ? reviewDetail.starResult : 5} sao)
              </p>
            </div>

            {localStorage.getItem("accessToken") ? (
              <div
                className="cinema_search"
                style={{ paddingBottom: 0, border: "none" }}
              >
                <div>
                  <Form form={reviewForm} onFinish={handleReview}>
                    <Form.Item
                      style={{ marginBottom: 6 }}
                      name={"content"}
                      rules={[
                        {
                          required: true,
                          message: "Nội dung không được để trống",
                        },
                      ]}
                    >
                      <TextArea
                        value={contentValue}
                        onChange={(v) => setContentValue(v)}
                        placeholder="..."
                        rows={4}
                        style={{ resize: "none" }}
                        disabled={review}
                      />
                    </Form.Item>
                    <div
                      style={{
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                        position: "relative",
                        top: 0,
                      }}
                    >
                      <Rate
                        value={review ? review.star : star}
                        style={{ marginBottom: 12 }}
                        onChange={(v) => setStar(v)}
                        disabled={review}
                      />
                      {!review && (
                        <Form.Item>
                          <Button
                            htmlType="submit"
                            loading={null}
                            onClick={null}
                            type="primary"
                            style={{ marginLeft: "auto" }}
                          >
                            Đánh giá
                          </Button>
                        </Form.Item>
                      )}
                    </div>
                  </Form>
                </div>
              </div>
            ) : (
              <div
                style={{
                  width: "100%",
                  fontSize: 20,
                  padding: 24,
                  textAlign: "center",
                }}
              >
                Đăng nhập để đánh giá
              </div>
            )}
            <div
              style={{
                display: "flex",
                justifyContent: "center",
                fontSize: 8,
                marginTop: 8,
              }}
            >
              <div
                value={"1"}
                onChange={null}
                style={{
                  padding: "0 12px 12px 12px",
                  display: "flex",
                  justifyContent: "center",
                  rowGap: 6,
                  columnGap: 12,
                  flexWrap: "wrap",
                  fontSize: 8,
                }}
              >
                <Button
                  ghost={currentStar === 0}
                  type={currentStar === 0 ? "primary" : "default"}
                  value="1"
                  onClick={() => handleGetReviewByStar(0)}
                >
                  Tất cả ({reviewDetail ? reviewDetail.totalReview : 0})
                </Button>
                <Button
                  ghost={currentStar === 5}
                  type={currentStar === 5 ? "primary" : "default"}
                  value="2"
                  onClick={() => handleGetReviewByStar(5)}
                >
                  5 sao (
                  {reviewDetail ? reviewDetail.reviewStarCounts[0].quantity : 0}
                  )
                </Button>
                <Button
                  ghost={currentStar === 4}
                  type={currentStar === 4 ? "primary" : "default"}
                  value="3"
                  onClick={() => handleGetReviewByStar(4)}
                >
                  4 sao (
                  {reviewDetail ? reviewDetail.reviewStarCounts[1].quantity : 0}
                  )
                </Button>
                <Button
                  ghost={currentStar === 3}
                  type={currentStar === 3 ? "primary" : "default"}
                  value="4"
                  onClick={() => handleGetReviewByStar(3)}
                >
                  3 sao (
                  {reviewDetail ? reviewDetail.reviewStarCounts[2].quantity : 0}
                  )
                </Button>
                <Button
                  ghost={currentStar === 2}
                  type={currentStar === 2 ? "primary" : "default"}
                  value="5"
                  onClick={() => handleGetReviewByStar(2)}
                >
                  2 sao (
                  {reviewDetail ? reviewDetail.reviewStarCounts[3].quantity : 0}
                  )
                </Button>
                <Button
                  ghost={currentStar === 1}
                  type={currentStar === 1 ? "primary" : "default"}
                  value="6"
                  onClick={() => handleGetReviewByStar(1)}
                >
                  1 sao (
                  {reviewDetail ? reviewDetail.reviewStarCounts[4].quantity : 0}
                  )
                </Button>
              </div>
            </div>
            <div className="cinema_list" ref={cinemaListRef}>
              <div className="cinema_list_content" ref={cinemaListContentRef}>
                <div className="comment_list">
                  {reviews.map((r) => (
                    <Comment
                      key={r.id}
                      style={{ padding: 12 }}
                      actions={[
                        <Rate
                          disabled
                          defaultValue={r.star}
                          style={{ fontSize: 16 }}
                        />,
                      ]}
                      author={<a>{r.user.firstName + " " + r.user.lastName}</a>}
                      avatar={
                        <Avatar
                          style={{
                            backgroundColor: getColorAndName(
                              r.user.firstName + " " + r.user.lastName
                            ).color,
                            verticalAlign: "middle",
                          }}
                          size="large"
                        >
                          {
                            getColorAndName(
                              r.user.firstName + " " + r.user.lastName
                            ).name
                          }
                        </Avatar>
                      }
                      content={<p>{r.content}</p>}
                      datetime={
                        <Tooltip title={getTime(r.createdDate)}>
                          <span>{getTime(r.createdDate)}</span>
                        </Tooltip>
                      }
                    />
                  ))}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Container>
  );
};

export default MovieDetail;
