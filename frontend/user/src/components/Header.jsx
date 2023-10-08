import {
  FundOutlined,
  LogoutOutlined,
  NotificationOutlined,
  SnippetsOutlined,
  UserOutlined
} from "@ant-design/icons";
import React, { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import logo from "../assets/image/logo.png";
import useKeycloak from "../hooks/useKeycloak";
import Container from "./Container";

const Header = () => {
  const { keycloak } = useKeycloak();
  const location = useLocation()

  const items = [
    {
      key: "1",
      label: (
        <Link to={"/account/profile"} style={{ fontWeight: "600" }}>
          <UserOutlined /> Tài khoản
        </Link>
      ),
    },
    {
      key: "2",
      label: (
        <Link to={"/account/order"} style={{ fontWeight: "600" }}>
          <i style={{ marginRight: 6 }} className="bi bi-bag"></i>Đơn hàng
        </Link>
      ),
    },
    {
      key: "3",
      label: (
        <div
          style={{ fontWeight: "600" }}
          onClick={() => {
            keycloak.logout();
          }}
        >
          <LogoutOutlined style={{ fontWeight: 600, marginRight: 6 }} />
          Đăng xuất
        </div>
      ),
    },
  ];

  return (
    <header className="header">
      <Container>
        <div className="header__bar">
          <div className="header__logo">
            <Link to="/">
              <img className="header__logo__image" src={logo} />
            </Link>
          </div>
          <div className="header_menu">
            <ul>
              <li
                className={
                  location.pathname === "/" ? "active" : ""
                }
              >
                <div style={{ position: "relative" }}>
                  <Link to="/">
                    <NotificationOutlined className="header_icon" />
                    <span className="label">Rạp chiếu</span>
                  </Link>
                </div>
              </li>
              <li className={location.pathname.startsWith("/movie") ? "active" : ""}>
                <div style={{ position: "relative" }}>
                  <Link to="/movie">
                    <FundOutlined className="header_icon" />
                    <span className="label">Phim</span>
                  </Link>
                </div>
              </li>

              {keycloak.authenticated ? (
                <>
                  <li className={location.pathname === "/account/order" ? "active" : ""}>
                    <div>
                      <Link to="/account/order">
                        <SnippetsOutlined className="header_icon" />
                        <span className="label">Đơn hàng</span>
                      </Link>
                    </div>
                  </li>
                  <li  className={location.pathname === "/account/profile" ? "active" : ""}
                  >
                    <div>
                      <Link to="/account/profile">
                        <UserOutlined className="header_icon" />
                        <span className="label">
                          {keycloak.tokenParsed.given_name +
                            " " +
                            keycloak.tokenParsed.family_name}{" "}
                        </span>
                      </Link>
                    </div>
                  </li>
                  <li
                    onClick={() => {
                      keycloak.logout();
                    }}
                  >
                    <div>
                      <a>
                        <LogoutOutlined className="header_icon" />
                        <span className="label">Đăng xuất</span>
                      </a>
                    </div>
                  </li>
                </>
              ) : (
                <li
                  onClick={() => {
                    keycloak.login();
                  }}
                >
                  <div style={{ position: "relative" }}>
                    <a>
                      <UserOutlined className="header_icon" />
                      <span className="label">Đăng nhập</span>
                    </a>
                  </div>
                </li>
              )}
            </ul>
          </div>
        </div>
      </Container>
    </header>
  );
};

export default Header;
