import React from 'react'
import logo from '../assets/image/logo.png'
import { Button } from 'antd'
import { Link } from 'react-router-dom'

const formatDate = (dateString) => {
    const date = new Date(dateString);

    const hours = date.getHours();
    const minutes = date.getMinutes();

   return hours.toString().padStart(2, "0") + ":" + minutes.toString().padStart(2, "0");
    
}

const CinemaShowItem = ({ item }) => {
    // console.log(item)
    return (
        <div className='cinema_show_item'>
            <div className="cinema_detail">
                <div className='image'>
                    <div style={{ backgroundImage: `url(${logo})` }}></div>
                </div>
                <div className="cinema_info">
                    <div>{item.cinema.name}</div>
                    <div>{item.cinema.address}</div>
                </div>
            </div>
            <div className="show_item_time_list">
                {
                    item.shows && item.shows.map(show => {
                        return <Link key={show.id} to={`/show/${show.id}`}>
                            <Button >{formatDate(show.start)} - {formatDate(show.end)}</Button>
                        </Link>
                    })
                }

                {/* <Link to={"/show"}>
                    <Button >17:30 - 18:30</Button>
                </Link>
                <Link to={"/show"}>
                    <Button >19:30 - 20:30</Button>
                </Link>
                <Link to={"/show"}>
                    <Button >18:30 - 15:30</Button>
                </Link>
                <Link to={"/show"}>
                    <Button >14:30 - 14:30</Button>
                </Link>
                <Link to={"/show"}>
                    <Button >11:30 - 16:30</Button>
                </Link>
                <Link to={"/show"}>
                    <Button >12:30 - 19:30</Button>
                </Link> */}
            </div>
        </div>
    )
}

export default CinemaShowItem