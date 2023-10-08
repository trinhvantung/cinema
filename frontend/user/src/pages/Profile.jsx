import { Button, Col, Form, Input, Row, Space, notification } from "antd";
import React, { useEffect, useState } from "react";
import userApi from "../api/userApi";
import { Link } from "react-router-dom";

const Profile = () => {
  const [profileForm] = Form.useForm();
  const [updatePasswordForm] = Form.useForm();
  const [isLoading, setIsLoading] = useState(0);

  useEffect(() => {
    userApi.getProfile().then((res) => {
      profileForm.setFields([
        {
          name: "email",
          value: res.data.email,
        },
        {
          name: "firstName",
          value: res.data.firstName,
        },
        {
          name: "lastName",
          value: res.data.lastName,
        },
      ]);
    });
  }, []);

  const handleSubmitProfileForm = (data) => {
    setIsLoading(1);

    userApi
      .updateProfile(data)
      .then((res) => {
        notification.success({
          message: `Notification`,
          description: "Success",
          placement: "top",
        });
        localStorage.setItem("fullName", data.fullName);
      })
      .catch((err) => {
        notification.error({
          message: `Notification`,
          description: "Error",
          placement: "top",
        });
      })
      .finally(() => {
        setIsLoading(0);
      });
  };

  const handleSubmitUpdatePasswordForm = (data) => {
    setIsLoading(2);

    userApi
      .updatePassword(data)
      .then((res) => {
        notification.success({
          message: `Notification`,
          description: "Success",
          placement: "top",
        });
      })
      .catch((err) => {
        const data = err.response.data;
        if (data.error == "user_password_not_match") {
          updatePasswordForm.setFields([
            {
              name: "currentPassword",
              errors: ["Mật khẩu không chính xác"],
            },
          ]);
        } else {
          notification.error({
            message: `Notification`,
            description: "Error",
            placement: "top",
          });
        }
      })
      .finally(() => {
        setIsLoading(0);
      });
  };

  return (
    <div>
      <div className="account_page_title">Thông tin tài khoản</div>
      <Form
        layout="vertical"
        form={profileForm}
        onFinish={handleSubmitProfileForm}
        style={{
          width: '60%'
        }}
      >
        <Form.Item label="Email" name={"email"}>
          <Input placeholder="Email" disabled size="large" />
        </Form.Item>
        <Form.Item label="Họ" name={"firstName"}>
          <Input placeholder="Họ" size="large" />
        </Form.Item>
        <Form.Item label="Tên" name={"lastName"}>
          <Input placeholder="Tên" size="large" />
        </Form.Item>

        <Form.Item>
          <Space>
            <Button
              type="primary"
              htmlType="submit"
              loading={isLoading === 1}
              disabled={isLoading === 1}
            >
              Lưu thay đổi
            </Button>
            <Link
              to={
                "http://localhost:8080/realms/master/account/password?referrer=security-admin-console"
              }
              target="_blank"
            >
              <Button type="default">Đổi mật khẩu</Button>
            </Link>
          </Space>
        </Form.Item>
      </Form>
    </div>
  );
};

export default Profile;
