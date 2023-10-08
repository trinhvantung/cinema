import { Button, Result } from 'antd'
import React from 'react'
import { Link } from 'react-router-dom'

const ActiveAccountResult = () => {
    return (
        <div style={{
            display: 'flex',
            alignItems: 'center',
            height: '100%',
            justifyContent: 'center'
        }}>
            <Result
                title="Kích hoạt tài khoản thành công"
                extra={
                    <Button type="primary" ghost key="console">
                        <Link to={"/login"}>Đăng nhập</Link>
                    </Button>
                }
            />
        </div>
    )
}

export default ActiveAccountResult