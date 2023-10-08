import React, { useState } from 'react'

const Seat = ({ seat, onClick, booked }) => {
    const [clicked, setClicked] = useState(false)

    const handleClick = (item) => {
        onClick(item)
        setClicked(!clicked)
    }
    return (
        <div
            onClick={() => handleClick({ id: seat.id, name: seat.name })} key={seat.id}
            className={`seat active ${clicked && !booked ? 'clicked' : ''} ${booked ? 'booked' : ''}`}
            style={{ backgroundColor: seat.seatType.color }}>
            {seat.name}
        </div>
    )
}

export default Seat