import { CheckCircleOutlined } from '@ant-design/icons';
import React from 'react';
import vnpay from '../assets/image/icon/vnpay.png';



const PaymentMethod = ({ active }) => {
  return (
    <div className={`payment_method ${active ? 'active' : ''}`} >
      <div className="image">
        <div style={{ backgroundImage: `url(${vnpay})` }}></div>
      </div>
      <div className="name">Ví VnPay</div>
      <div className='icon'>
        <CheckCircleOutlined />
      </div>
    </div>
  )
}

export default PaymentMethod