import React from 'react'
import logo from '../assets/image/logo.png'

const CinemaItem = ({ item, isActive, onClick }) => {
    return (
        <div className={`cinema_item ${isActive ? 'active' : ''}`} onClick={onClick}>
            <div className="cinema_item_content">
                <div className="image" >
                    <div style={{ backgroundImage: `url(${logo})` }}></div>
                </div>
                <div className="name">
                    {item.name}
                </div>
            </div>
            <i style={{ position: 'relative', top: 2 }} className="bi bi-chevron-right"></i>
        </div>
    )
}

export default CinemaItem