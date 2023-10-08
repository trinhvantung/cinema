import { Button } from 'antd'
import React from 'react'
import { Link } from 'react-router-dom'

const formatDate = (dateString) => {
    const date = new Date(dateString);

    const hours = date.getHours();
    const minutes = date.getMinutes();

   return hours.toString().padStart(2, "0") + ":" + minutes.toString().padStart(2, "0");
    
}

const ShowItem = ({ item }) => {
    return (
        <div className='show_item'>

            <div className="show_item_image">
                <div style={{ backgroundImage: `url(${item.movie.thumbnailUrl})` }}></div>
            </div>
            <div className="show_item_content">
                <div className="show_item_name"><Link to={`/movie/${item.movie.id}`}>{item.movie.name}</Link></div>
                <div className="show_item_categories">{item.movie.categories.map(i => i.name).join(", ")}</div>
                <div className="show_item_time_list">
                    {
                        item.shows.map(show => {
                            return <Link key={show.id} to={`/show/${show.id}`}>
                                <Button >{formatDate(show.start)} - {formatDate(show.end)}</Button>
                            </Link>
                        })
                    }
                </div>
            </div>
        </div>
    )
}

export default ShowItem