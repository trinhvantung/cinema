import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import bookingApi from "../api/bookingApi";
import { LoadingOutlined } from "@ant-design/icons";
import { Button, Result, Spin } from "antd";
import movieApi from "../api/movieApi";

const ActiveTicket = () => {
  const { ticketCode } = useParams();
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [booking, setBooking] = useState();
  const [movieName, setMovieName] = useState();

  useEffect(() => {
    bookingApi
      .activeTicket(ticketCode)
      .then((res) => {
        console.log(res);
        setBooking(res);
        movieApi.getById(res.showResponse.movieId).then((res) => {
          setMovieName(res.name);
        });
      })
      .catch((err) => {
        console.log(err);
        console.log(err.response.data);
        setError(err.response.data);
      });
  }, []);

  return (
    <div className="active_ticket">
      {isLoading ? (
        <div
          className="spin"
          style={{
            height: 400,
          }}
        >
          <Spin
            indicator={
              <LoadingOutlined
                style={{
                  fontSize: 40,
                }}
                spin
              />
            }
            tip="Checking ticket"
          />
        </div>
      ) : error ? (
        <Result
          status="error"
          title="Ticket booking failed"
          subTitle={error.message}
        ></Result>
      ) : (
        <Result
          status="success"
          title="Ticket booking successful!"
          extra={[
            <div className="active_ticket_result">
              <div className="movie_name">{movieName}</div>
              <div className="hall_name">
                {booking &&
                  booking.hall.cinema.name + " - " + booking.hall.name}
              </div>
              <div className="seat_list_name">
                <span>Ghế:</span>
                <span>
                  {booking &&
                    booking.bookingItems
                      .map((item) => item.seat.name)
                      .join(", ")}
                </span>
              </div>
            </div>,
          ]}
        />
      )}
    </div>
  );
};

export default ActiveTicket;
