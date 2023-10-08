import React, { useEffect, useRef } from 'react'
import { Link } from 'react-router-dom'
import thumbnail from '../assets/image/movie/1.jpg'

const Movie = ({ item }) => {
    const imageRef = useRef()

    useEffect(() => {
        imageRef.current.style.height = (imageRef.current.offsetWidth * 1.5) + 'px'
    }, [])

    return (
        <Link to={`/movie/${item.id}`}>
            <div className='movie'>
                <div className="image">
                    <div style={{ backgroundImage: `url(${item.thumbnailUrl})` }} ref={imageRef}></div>
                </div>
                <div className="name">{item.name}</div>
                <div className="type">
                    {
                        item.categories.map(item => item.name).join(", ")
                    }
                </div>
                {/* <div className="categories">{item.categories.map(c => c.name).join(", ")}</div> */}
            </div>
        </Link>
    )
}

export default Movie