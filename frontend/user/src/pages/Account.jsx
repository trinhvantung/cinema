import { ShoppingCartOutlined, UserOutlined } from "@ant-design/icons";
import { Col, Menu, Row } from "antd";
import React from "react";
import { Link, Route, Routes, useLocation } from "react-router-dom";
import Container from "../components/Container";
import Orders from "./Orders";
import Profile from "./Profile";

const Account = () => {
  const location = useLocation();
  return (
    <Container>
      <Row gutter={12} style={{ height: "100%" }}>
        <Col span={6}>
          <Menu selectedKeys={location.pathname} style={{ height: "100%" }}>
            <Menu.Item key={"/account/profile"} icon={<UserOutlined />}>
              <Link to={"/account/profile"}>Tài khoản</Link>
            </Menu.Item>

            <Menu.Item key={"/account/order"} icon={<ShoppingCartOutlined />}>
              <Link to={"/account/order"}>Đơn hàng</Link>
            </Menu.Item>
          </Menu>
        </Col>
        <Col span={18}>
          <div className="account_content">
            <Routes>
              <Route path="/profile" element={<Profile />} />
              <Route path="/order" element={<Orders />} />
            </Routes>
          </div>
        </Col>
      </Row>
    </Container>
  );
};

export default Account;
