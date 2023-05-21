import React, { useEffect } from 'react'
import { Link, useParams } from 'react-router-dom'
import userApi from '../api/userApi'
import { useState } from 'react'
import { Button, Result, Spin } from 'antd'
import { LoadingOutlined } from '@ant-design/icons'

const Verify = () => {
    const { activationCode } = useParams()

    const [isSuccess, setIsSuccess] = useState(2)

    useEffect(() => {
        userApi.verifyRegister(activationCode).then(res => {
            setIsSuccess(1)
        }).catch(err => {
            setIsSuccess(0)
        })
    }, [])

    return (
        <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            width: '100%',
            height: '100%'
        }}>

            <Spin spinning={isSuccess === 2}
                indicator={<LoadingOutlined style={{
                    fontSize: 50
                }} spin />}>
                {
                    isSuccess === 0 ? <Result
                        status="error"
                        title="Lỗi"
                        subTitle="Kích hoạt tài khoản không thành công"
                    ></Result> : <></>
                }

                {
                    isSuccess === 1 ? <Result
                        status="success"
                        title="Thành công"
                        subTitle="Kích hoạt tài khoản thành công"
                        extra={[
                            <Link to={"/login"}>
                                <Button type="primary" key="console">
                                    Đăng nhập
                                </Button>
                            </Link>
                        ]}
                    ></Result> : <></>
                }

            </Spin>
        </div >
    )
}

export default Verify