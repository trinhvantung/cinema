import React, { useEffect } from 'react'
import { Link, useParams, useSearchParams } from 'react-router-dom'
import userApi from '../api/userApi'
import { useState } from 'react'
import { Button, Result, Spin } from 'antd'
import { LoadingOutlined } from '@ant-design/icons'

const PaymenResult = () => {

    const [isSuccess, setIsSuccess] = useState(2)
    const [searchParams] = useSearchParams();


    useEffect(() => {
        const result = searchParams.get('vnp_TransactionStatus');
        if(result == '00') {
            setIsSuccess(1)
        } else {
            setIsSuccess(0)
        }
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
                        title="Thanh toán không thành công"
                        subTitle="Đơn hàng của bạn bị hủy"
                    ></Result> : <></>
                }

                {
                    isSuccess === 1 ? <Result
                        status="success"
                        title="Thanh toán thành công"
                        subTitle="Hệ thống đang tiếp tục xử lý đơn hàng"
                    ></Result> : <></>
                }

            </Spin>
        </div >
    )
}

export default PaymenResult