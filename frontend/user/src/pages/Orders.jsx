import React, { useEffect, useState } from 'react'
import OrderItem from '../components/OrderItem'
import { Empty, Pagination } from 'antd'
import bookingApi from '../api/bookingApi'

const Orders = () => {
    const [orderPage, setOrderPage] = useState()

    useEffect(() => {
        bookingApi.getAllByCurrentUser(1).then(res => {
            console.log(res)
            setOrderPage(res.data)
        })
    }, [])


    const handleChangePage = (p) => {
        bookingApi.getAllByCurrentUser(p).then(res => {
            console.log(res)
            setOrderPage(res.data)
        })
    }

    return (
        <div>
            <div className="account_page_title">
                Danh sách đơn hàng
            </div>
            {
                orderPage ? (orderPage.content.length !== 0 ? orderPage.content.map(item => <OrderItem key={item.id} item={item} />) : <Empty />) : <Empty />
            }
            <div className="pagination">
                <Pagination defaultCurrent={1}
                    // current={currentPage}
                    total={orderPage ? orderPage.totalElements : 10}
                    pageSize={orderPage ? orderPage.size : 10} size='default'
                    onChange={handleChangePage} />
            </div>
        </div>
    )
}

export default Orders