import { Col, Row } from 'antd'
import React from 'react'
import Container from './Container'

const Footer = () => {
    return (
        <footer className="footer">
            <Container>
                <div className="footer_copyrights">Made with <span>ReactJS, Spring Boot</span></div>
            </Container>
        </footer>
    )
}

export default Footer