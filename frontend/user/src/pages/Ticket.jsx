import React, { useEffect, useState } from "react";
// import qr from "../assets/image/qr.jpg";
import { ExclamationCircleFilled, LeftOutlined } from "@ant-design/icons";
import { Link, useParams } from "react-router-dom";
import bookingApi from "../api/bookingApi";
import { getTime } from "../utils/dateUtils";
import movieApi from "../api/movieApi";
import { Button, Modal, Result } from "antd";

let targetTime = new Date(new Date().getTime() + 1 * 4 * 1000);
// https://products.aspose.app/barcode/vi/recognize/qr#/recognized

const Ticket = () => {
  const [timeLeft, setTimeLeft] = useState({
    minutes: -1,
    seconds: 0,
  });
  const [expireTime, setExpireTime] = useState();
  const [qr, setQr] = useState();
  const [booking, setBooking] = useState(null);
  const [movieName, setMovieName] = useState();
  const [error, setError] = useState();

  const { bookingId } = useParams();

  useEffect(() => {
    bookingApi
      .getDetailByCurrentUser(bookingId)
      .then((res) => {
        setBooking(res.data);
        movieApi.getById(res.data.showResponse.movieId).then((res) => {
          setMovieName(res.data.name);
        });
      })
      .catch((err) => {
        console.log(err);
      });

    getQrCode();
  }, []);

  useEffect(() => {
    if (expireTime) {
      const interval = setInterval(() => {
        const currentTime = new Date();
        const timeRemaining = expireTime.getTime() - currentTime.getTime();

        const minutes = Math.floor((timeRemaining % 3600000) / 60000);
        const seconds = Math.floor((timeRemaining % 60000) / 1000);

        setTimeLeft({ minutes, seconds });

        if (minutes === 0 && seconds === 0) {
          clearInterval(interval);

          console.log("OK");

          Modal.confirm({
            title: "Do you Want to delete these items?",
            icon: <ExclamationCircleFilled />,
            content: "Some descriptions",
            onOk() {
              console.log("OK");
              getQrCode();
            },
            onCancel() {
              console.log("Cancel");
            },
          });
        }
      }, 1000);

      return () => {
        clearInterval(interval);
      };
    }
  }, [expireTime]);

  const getQrCode = () => {
    bookingApi
      .generateTicketQrCode(bookingId)
      .then((res) => {
        setExpireTime(new Date(res.headers["qr-expire"]));
        const blob = new Blob([res.data], { type: "image/png" });
        const qrCodeImageUrl = URL.createObjectURL(blob);
        setQr(qrCodeImageUrl);
      })
      .catch((err) => {
        const errorMessage = new TextDecoder("utf-8").decode(new Uint8Array(err.response.data));
        setError(JSON.parse(errorMessage));
      });
  };

  return (
    <div className="ticket">
      {error ? (
        <Result
          status="error"
          title="Có lỗi xảy ra"
          subTitle={error.message}
        />
      ) : (
        <div className="wrap">
          <div className="ticket_top">
            <div
              className="qr_image"
              style={{
                backgroundImage: `url(${qr})`,
              }}
            ></div>
          </div>
          <div className="ticket_border"></div>
          <div className="ticket_center">
            <div className="movie_name">{movieName}</div>
            <div className="hall_name">
              {booking && booking.hall.cinema.name + " - " + booking.hall.name}
            </div>
            <div className="seat_list_name">
              <span>Ghế:</span>
              <span>
                {booking &&
                  booking.bookingItems.map((item) => item.seat.name).join(", ")}
              </span>
            </div>
            <div className="valid_to">
              {timeLeft.minutes !== -1 &&
                (timeLeft.minutes === 0 && timeLeft.seconds === 0 ? (
                  <div>QR Code has expired</div>
                ) : (
                  <>
                    <div>Valid to:</div>
                    <div>
                      {timeLeft.minutes.toString().padStart(2, "0")}:
                      {timeLeft.seconds.toString().padStart(2, "0")}
                    </div>
                  </>
                ))}
            </div>
          </div>
          <div className="ticket_bottom">
            <span>Start:</span>
            <span>{booking && getTime(booking.showResponse.start)}</span>
          </div>
        </div>
      )}

      <Link to={"/account/order"}>
        <LeftOutlined />
        <span>Quay lại</span>
      </Link>
    </div>
  );
};

export default Ticket;
