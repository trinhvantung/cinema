import { AreaChartOutlined, ClockCircleOutlined, CopyOutlined, DashboardOutlined, DiffOutlined, LaptopOutlined, NotificationOutlined, ShoppingCartOutlined, UserOutlined } from '@ant-design/icons';
import { Layout, Menu, theme } from 'antd';
import React from 'react';
import { Link, Route, Routes, useNavigate } from 'react-router-dom';
import logo from './assets/image/logo.png';
import Categories from './pages/category/Categories';
import CategoryForm from './pages/category/CategoryForm';
import CinemaForm from './pages/cinema/CinemaForm';
import Cinemas from './pages/cinema/Cinemas';
import Cities from './pages/city/Cities';
import CityForm from './pages/city/CityForm';
import HallForm from './pages/hall/HallForm';
import Halls from './pages/hall/Halls';
import MovieForm from './pages/movie/MovieForm';
import Movies from './pages/movie/Movies';
import Orders from './pages/order/Orders';
import ShowForm from './pages/show/ShowForm';
import Shows from './pages/show/Shows';
import UserForm from './pages/user/UserForm';
import Users from './pages/user/Users';
import './scss/index.scss';
import Private from './components/Private';
import Login from './pages/login/Login';
import DashBoard from './pages/dashboard/DashBoard';
import NotFound from './pages/error/NotFound';


const { Content, Sider } = Layout;
const items = [
  {
    label: 'Dashboard',
    url: '/',
    icon: DashboardOutlined
  },
  {
    label: 'User',
    url: '/user',
    icon: UserOutlined
  }, {
    label: 'Cinema',
    children: [
      {
        label: 'List',
        url: '/cinema',
      }, {
        label: 'Create',
        url: '/cinema/create',
      }
    ],
    icon: LaptopOutlined
  }, {
    label: 'Hall',
    children: [
      {
        label: 'List',
        url: '/hall',
      }, {
        label: 'Create',
        url: '/hall/create',
      }
    ],
    icon: NotificationOutlined
  }, {
    label: 'Category',
    icon: ShoppingCartOutlined,
    children: [
      {
        label: 'List',
        url: '/category',
      }, {
        label: 'Create',
        url: '/category/create',
      }
    ]
  }, {
    label: 'City',
    icon: AreaChartOutlined,
    children: [
      {
        label: 'List',
        url: '/city',
      }, {
        label: 'Create',
        url: '/city/create',
      }
    ]
  }, {
    label: 'Movie',
    children: [
      {
        label: 'List',
        url: '/movie',
      }, {
        label: 'Create',
        url: '/movie/create',
      }
    ],
    icon: DiffOutlined
  }, {
    label: 'Show',
    children: [
      {
        label: 'List',
        url: '/show',
      }, {
        label: 'Create',
        url: '/show/create',
      }
    ],
    icon: ClockCircleOutlined
  }, {
    label: 'Order',
    url: '/order',
    icon: CopyOutlined
  }
].map((item, index) => {
  const key = String(index + 1);

  if (!item.children) {
    return {
      key: `sub${key}`,
      icon: React.createElement(item.icon),
      label: <Link to={item.url}>{item.label}</Link>,
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
  const navigate = useNavigate()


  const handleLogOut = () => {
    localStorage.removeItem('roles')
    localStorage.removeItem("accessToken")
    localStorage.removeItem('fullName')

    navigate("/login")
  }


  const {
    token: { colorBgContainer },
  } = theme.useToken();
  return (
    <Routes>
      <Route path='/login' element={<Login />} />

      <Route path='*' element={
        <Layout style={{ height: '100vh' }}>
          <Layout>
            <Sider
              width={260}
              style={{
                background: colorBgContainer,
              }}
            >
              <div className='logo'>
                <div className="image">
                  <div style={{ backgroundImage: `url(${logo})` }}></div>
                </div>
                <div className='name'>
                  Cinema Admin
                </div>
              </div>
              <Menu
                mode="inline"
                defaultSelectedKeys={['1']}
                defaultOpenKeys={['sub1']}
                style={{
                  borderRight: 0,
                  overflow: 'auto',
                  height: 'calc(100% - 60px)'
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
                      <li>Trịnh Văn Tùng</li>
                      <li onClick={handleLogOut}>Đăng xuất</li>
                    </ul>
                  </div>
                </div>
              </div>
              <Layout style={{
                padding: '0 24px',
                overflowY: 'auto'
              }}>
                <Content
                  style={{
                    // margin: '24px 0',
                    // padding: 24,
                    // minHeight: 280,
                    // height: 'max-content',
                    // background: colorBgContainer,
                  }}
                >
                  <div style={{
                    margin: '24px 0',
                    padding: 24,
                    background: colorBgContainer,
                    minHeight: 'calc(100% - 24px)'
                  }}>
                    <Routes>
                      <Route path="/" element={<Private component={DashBoard} rolesAccept={['ADMIN']} />} />

                      <Route path="/user" element={<Private component={Users} rolesAccept={['ADMIN']} />} />
                      <Route path="/user/update/:id" element={<Private component={UserForm} rolesAccept={['ADMIN']} />} />

                      <Route path="/city" element={<Private component={Cities} rolesAccept={['ADMIN']} />} />
                      <Route path="/city/create" element={<Private component={CityForm} rolesAccept={['ADMIN']} />} />
                      <Route path="/city/update/:id" element={<Private component={CityForm} rolesAccept={['ADMIN']} />} />

                      <Route path="/category" element={<Private component={Categories} rolesAccept={['ADMIN']} />} />
                      <Route path="/category/update/:id" element={<Private component={CategoryForm} rolesAccept={['ADMIN']} />} />
                      <Route path="/category/create" element={<Private component={CategoryForm} rolesAccept={['ADMIN']} />} />

                      <Route path="/cinema" element={<Private component={Cinemas} rolesAccept={['ADMIN']} />} />
                      <Route path="/cinema/create" element={<Private component={CinemaForm} rolesAccept={['ADMIN']} />} />
                      <Route path="/cinema/update/:id" element={<Private component={CinemaForm} rolesAccept={['ADMIN']} />} />

                      <Route path="/movie" element={<Private component={Movies} rolesAccept={['ADMIN']} />} />
                      <Route path="/movie/create" element={<Private component={MovieForm} rolesAccept={['ADMIN']} />} />
                      <Route path="/movie/update/:id" element={<Private component={MovieForm} rolesAccept={['ADMIN']} />} />

                      <Route path="/order" element={<Private component={Orders} rolesAccept={['ADMIN']} />} />

                      <Route path="/show" element={<Private component={Shows} rolesAccept={['ADMIN']} />} />
                      <Route path="/show/create" element={<Private component={ShowForm} rolesAccept={['ADMIN']} />} />
                      <Route path="/show/update/:id" element={<Private component={ShowForm} rolesAccept={['ADMIN']} />} />

                      <Route path="/hall" element={<Private component={Halls} rolesAccept={['ADMIN']} />} />
                      <Route path="/hall/create" element={<Private component={HallForm} rolesAccept={['ADMIN']} />} />
                      <Route path="/hall/update/:id" element={<Private component={HallForm} rolesAccept={['ADMIN']} />} />

                    </Routes>
                  </div>
                </Content>
              </Layout>
            </Layout>
          </Layout>
        </Layout>
      } />

      {/* <Route path='*' element={<NotFound />} /> */}
    </Routes>
  );
};
export default App;


