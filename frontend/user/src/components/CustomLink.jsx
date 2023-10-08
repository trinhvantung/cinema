import React from 'react'
import { Link, useMatch, useResolvedPath } from 'react-router-dom'

const CustomLink = ({ children, to, className, ...props }) => {
    const resolved = useResolvedPath(to)
    const match = useMatch({ path: resolved.pathname, end: true })
    return (
        <Link to={to} {...props} className={`${className} ${match ? " active" : ""}`}>
            {children}
        </Link>
    )
}
export default CustomLink