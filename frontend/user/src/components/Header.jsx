import { CaretDownOutlined, LogoutOutlined, UserOutlined } from '@ant-design/icons';
import { Dropdown } from 'antd';
import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import logo from '../assets/image/logo.png';
import Container from './Container';
import CustomLink from './CustomLink';


const Header = () => {
    const navigate = useNavigate()

    const items = [
        {
            key: '1',
            label: (
                <Link to={"/account/profile"} style={{ fontWeight: '600' }}>
                    <UserOutlined /> Tài khoản
                </Link>
            ),
        },
        {
            key: '2',
            label: (
                <Link to={"/account/order"} style={{ fontWeight: '600' }}>
                    <i style={{ marginRight: 6 }} className="bi bi-bag"></i>Đơn hàng
                </Link>
            ),
        },
        {
            key: '3',
            label: (
                <div style={{ fontWeight: '600' }} onClick={() => {
                    localStorage.removeItem("accessToken")
                    localStorage.removeItem("fullName")
                    localStorage.removeItem("roles")

                    navigate("/login")
                }}>
                    <LogoutOutlined style={{ fontWeight: 600, marginRight: 6 }} />Đăng xuất
                </div>
            ),
        },
    ];



    return (
        <header className="header">
            <Container>
                <div className="header__bar">
                    <div className="header__logo">
                        <Link to="/"><img className="header__logo__image" src={logo} /></Link>
                    </div>

                    <div className="header__menu">
                        <div className="header__menu__list">
                            <CustomLink to="/" className="header__menu__item">
                                <div className="header__menu__item__label">
                                    Rạp chiếu
                                </div>
                            </CustomLink>

                            <CustomLink to="/movie" className="header__menu__item">
                                <div className="header__menu__item__label">
                                    Phim
                                </div>
                            </CustomLink>
                            {
                                localStorage.getItem("accessToken") ? (
                                    <Dropdown
                                        menu={{
                                            items,
                                        }}
                                        placement="bottom"
                                        arrow={{
                                            pointAtCenter: true,
                                        }}
                                    >
                                        <CustomLink to="/account/profile" className="header__menu__item">
                                            <div className="header__menu__item__label">
                                                {localStorage.getItem("fullName")} <CaretDownOutlined style={{ top: 1, position: 'relative' }} />
                                            </div>

                                        </CustomLink>
                                    </Dropdown>
                                ) : (
                                    <CustomLink to="/login" className="header__menu__item">
                                        <div className="header__menu__item__label">
                                            Đăng nhập
                                        </div>
                                    </CustomLink>
                                )
                            }


                        </div>
                    </div>
                </div>
            </Container>
        </header>
    )
}

export default Header