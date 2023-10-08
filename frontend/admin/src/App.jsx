import {
  AreaChartOutlined,
  ClockCircleOutlined,
  CopyOutlined,
  DashboardOutlined,
  DiffOutlined,
  LaptopOutlined,
  NotificationOutlined,
  ShoppingCartOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Layout, Menu, theme } from "antd";
import React from "react";
import { Link, Route, Routes } from "react-router-dom";
import logo from "./assets/image/logo.png";
import Private from "./components/Private";
import useKeycloak from "./hooks/useKeycloak";
import Categories from "./pages/category/Categories";
import CategoryForm from "./pages/category/CategoryForm";
import CinemaForm from "./pages/cinema/CinemaForm";
import Cinemas from "./pages/cinema/Cinemas";
import Cities from "./pages/city/Cities";
import CityForm from "./pages/city/CityForm";
import DashBoard from "./pages/dashboard/DashBoard";
import HallForm from "./pages/hall/HallForm";
import Halls from "./pages/hall/Halls";
import MovieForm from "./pages/movie/MovieForm";
import Movies from "./pages/movie/Movies";
import Orders from "./pages/order/Orders";
import ShowForm from "./pages/show/ShowForm";
import Shows from "./pages/show/Shows";
import UserForm from "./pages/user/UserForm";
import Users from "./pages/user/Users";
import "./scss/index.scss";
import ActiveTicket from "./pages/ActiveTicket";

const { Content, Sider } = Layout;
const items = [
  {
    label: "Dashboard",
    url: "/",
    icon: DashboardOutlined,
  },
  {
    label: "User",
    url: "http://localhost:8080/admin/master/console/#/master/users",
    icon: UserOutlined,
  },
  {
    label: "Cinema",
    children: [
      {
        label: "List",
        url: "/cinema",
      },
      {
        label: "Create",
        url: "/cinema/create",
      },
    ],
    icon: LaptopOutlined,
  },
  {
    label: "Hall",
    children: [
      {
        label: "List",
        url: "/hall",
      },
      {
        label: "Create",
        url: "/hall/create",
      },
    ],
    icon: NotificationOutlined,
  },
  {
    label: "Category",
    icon: ShoppingCartOutlined,
    children: [
      {
        label: "List",
        url: "/category",
      },
      {
        label: "Create",
        url: "/category/create",
      },
    ],
  },
  {
    label: "City",
    icon: AreaChartOutlined,
    children: [
      {
        label: "List",
        url: "/city",
      },
      {
        label: "Create",
        url: "/city/create",
      },
    ],
  },
  {
    label: "Movie",
    children: [
      {
        label: "List",
        url: "/movie",
      },
      {
        label: "Create",
        url: "/movie/create",
      },
    ],
    icon: DiffOutlined,
  },
  {
    label: "Show",
    children: [
      {
        label: "List",
        url: "/show",
      },
      {
        label: "Create",
        url: "/show/create",
      },
    ],
    icon: ClockCircleOutlined,
  },
  {
    label: "Order",
    url: "/order",
    icon: CopyOutlined,
  },
].map((item, index) => {
  const key = String(index + 1);

  if (!item.children) {
    return {
      key: `sub${key}`,
      icon: React.createElement(item.icon),
      label:
        item.label === "User" ? (
          <Link to={item.url} target="_blank">
            {item.label}
          </Link>
        ) : (
          <Link to={item.url}>{item.label}</Link>
        ),
    };
  }

  return {
    key: `sub${key}`,
    icon: React.createElement(item.icon),
    label: item.label,
    children: item.children.map((it, j) => {
      const subKey = index * 4 + j + 1;
      return {
        key: subKey,
        label: <Link to={it.url}>{it.label}</Link>,
      };
    }),
  };
});

const App = () => {
  const { keycloak } = useKeycloak();

  const handleLogOut = () => {
    keycloak.logout();
  };

  const {
    token: { colorBgContainer },
  } = theme.useToken();
  return (
    <Routes>
      <Route
        path="*"
        element={
          <Layout style={{ height: "100vh" }}>
            <Layout>
              <Sider
                width={260}
                style={{
                  background: colorBgContainer,
                }}
              >
                <div className="logo">
                  <div className="image">
                    <div style={{ backgroundImage: `url(${logo})` }}></div>
                  </div>
                  <div className="name">Cinema Admin</div>
                </div>
                <Menu
                  mode="inline"
                  defaultSelectedKeys={["1"]}
                  defaultOpenKeys={["sub1"]}
                  style={{
                    borderRight: 0,
                    overflow: "auto",
                    height: "calc(100% - 60px)",
                  }}
                  items={items}
                />
              </Sider>

              <Layout>
                <div className="header">
                  <div className="menu">
                    <div className="menu_left"></div>
                    <div className="menu_right">
                      <ul>
                        <li>
                          {keycloak.tokenParsed.given_name +
                            " " +
                            keycloak.tokenParsed.family_name}
                        </li>
                        <li onClick={handleLogOut}>Đăng xuất</li>
                      </ul>
                    </div>
                  </div>
                </div>
                <Layout
                  style={{
                    padding: "0 0 0 24px",
                    overflowX: 'hidden'
                  }}
                >
                  <Content>
                    <div
                      style={{
                        margin: "24px 0",
                        padding: 24,
                        background: colorBgContainer,
                        minHeight: "calc(100% - 24px)",
                      }}
                    >
                      <Routes>
                        <Route
                          path="/"
                          element={
                            <Private page={DashBoard} rolesAccept={["ADMIN"]} />
                          }
                        />

                        <Route
                          path="/user"
                          element={
                            <Private page={Users} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/user/update/:id"
                          element={
                            <Private page={UserForm} rolesAccept={["ADMIN"]} />
                          }
                        />

                        <Route
                          path="/city"
                          element={
                            <Private page={Cities} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/city/create"
                          element={
                            <Private page={CityForm} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/city/update/:id"
                          element={
                            <Private page={CityForm} rolesAccept={["ADMIN"]} />
                          }
                        />

                        <Route
                          path="/category"
                          element={
                            <Private
                              page={Categories}
                              rolesAccept={["ADMIN"]}
                            />
                          }
                        />
                        <Route
                          path="/category/update/:id"
                          element={
                            <Private
                              page={CategoryForm}
                              rolesAccept={["ADMIN"]}
                            />
                          }
                        />
                        <Route
                          path="/category/create"
                          element={
                            <Private
                              page={CategoryForm}
                              rolesAccept={["ADMIN"]}
                            />
                          }
                        />

                        <Route
                          path="/cinema"
                          element={
                            <Private page={Cinemas} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/cinema/create"
                          element={
                            <Private
                              page={CinemaForm}
                              rolesAccept={["ADMIN"]}
                            />
                          }
                        />
                        <Route
                          path="/cinema/update/:id"
                          element={
                            <Private
                              page={CinemaForm}
                              rolesAccept={["ADMIN"]}
                            />
                          }
                        />

                        <Route
                          path="/movie"
                          element={
                            <Private page={Movies} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/movie/create"
                          element={
                            <Private page={MovieForm} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/movie/update/:id"
                          element={
                            <Private page={MovieForm} rolesAccept={["ADMIN"]} />
                          }
                        />

                        <Route
                          path="/order"
                          element={
                            <Private page={Orders} rolesAccept={["ADMIN"]} />
                          }
                        />

                        <Route
                          path="/show"
                          element={
                            <Private page={Shows} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/show/create"
                          element={
                            <Private page={ShowForm} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/show/update/:id"
                          element={
                            <Private page={ShowForm} rolesAccept={["ADMIN"]} />
                          }
                        />

                        <Route
                          path="/hall"
                          element={
                            <Private page={Halls} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/hall/create"
                          element={
                            <Private page={HallForm} rolesAccept={["ADMIN"]} />
                          }
                        />
                        <Route
                          path="/hall/update/:id"
                          element={
                            <Private page={HallForm} rolesAccept={["ADMIN"]} />
                          }
                        />
                        
                        <Route
                          path="/active-ticket/:ticketCode"
                          element={
                            <Private page={ActiveTicket} rolesAccept={["ADMIN"]} />
                          }
                        />
                      </Routes>
                    </div>
                  </Content>
                </Layout>
              </Layout>
            </Layout>
          </Layout>
        }
      />
    </Routes>
  );
};
export default App;
