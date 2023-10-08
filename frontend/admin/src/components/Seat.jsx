import React, { useState } from 'react'

const Seat = ({ seat, handleClick: onClick, isSelected, isEmpty}) => {
    const [selected, setSelected] = useState(isSelected)

    const handleClick = (item) => {
        onClick(item)
        setSelected(!selected)
    }
    return (
        <div
            onClick={() => handleClick({ id: seat.id, name: seat.name })} key={seat.id}
            className={`seat active ${isEmpty ? 'empty' : ''}`}
            // ${selected ? 'selected' : ''}
            style={{ backgroundColor: seat.seatType.color }}
        >
            {seat.name}
        </div>
    )
}

export default Seat