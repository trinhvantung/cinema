import React, { useEffect } from 'react'
import Container from '../components/Container'
import { Col, Input, Pagination, Row, Select } from 'antd'
import Movie from '../components/Movie'
import { useState } from 'react'
import categoryApi from '../api/categoryApi'
import movieApi from '../api/movieApi'

const Movies = () => {
    const [categories, setCategories] = useState([])
    // const [movies, setMovies] = useState([])
    const [moviePage, setMoviePage] = useState({})
    const [search, setSearch] = useState('')
    const [currentCategory, setCurrentCategory] = useState()
    const [currentPage, setCurrentPage] = useState(1)

    useEffect(() => {
        categoryApi.getAll().then(res => {
            setCategories([{ value: 0, label: 'Tất cả' }, ...res.map(c => ({ ...c, label: c.name, value: c.id }))])
        })

        movieApi.getAllByPage(1).then(res => {
            setMoviePage(res)
            console.log(res)
        })
    }, [])


    const handleChangeCategory = (c) => {
        console.log(c)
        setCurrentCategory(c)
        setCurrentPage(1)
        if (c === 0) {
            movieApi.getAllByPage(1).then(res => {
                setMoviePage(res)
            })
        } else {
            movieApi.getAllByCategory(c, 1).then(res => {
                setMoviePage(res)
                console.log(res)
            })
        }
    }

    const handleChangePage = (p) => {
        setCurrentPage(p)
        if (search) {

        } else {
            if (currentCategory && currentCategory !== 0) {
                movieApi.getAllByCategory(currentCategory, p).then(res => {
                    setMoviePage(res)
                })
            } else {
                movieApi.getAllByPage(p).then(res => {
                    setMoviePage(res)
                })
            }
        }
    }

    useEffect(() => {
        if (search) {
            movieApi.search(search, 1).then(res => {
                console.log(res)
                setMoviePage(res)
            })
        } else {
            movieApi.getAllByPage(1).then(res => {
                setMoviePage(res)
            })
        }
    }, [search])

    return (
        <Container>
            <div className="page_title">
                Danh sách phim
            </div>
            <div className="movie_search">
                <div>Tìm kiếm phim</div>
                <div>
                    <Input.Search onSearch={(e) => setSearch(e)} placeholder="Tìm theo tên phim" size='large' />
                    <Select
                        showSearch
                        style={{
                            width: 400,
                            marginLeft: 12
                        }}
                        defaultValue={0}
                        onChange={handleChangeCategory}
                        size='large'
                        placeholder="Danh mục"
                        optionFilterProp="children"
                        filterOption={(input, option) => {

                            return (option?.label ?? '').includes(input)
                        }}
                        filterSort={(optionA, optionB) => optionA.value - optionB.value
                            // (optionA?.label ?? '').toLowerCase().localeCompare((optionB?.label ?? '').toLowerCase())
                        }
                        options={categories}
                    />
                </div>
            </div>
            <div className="movie_list">
                <Row gutter={[12, 20]}>
                    {
                        moviePage.content && moviePage.content.map(m => (
                            <Col key={m.id} span={6}>
                                <Movie item={m} />
                            </Col>
                        ))
                    }
                </Row>
                <div className="pagination">
                    <Pagination defaultCurrent={1}
                        current={currentPage}
                        total={moviePage.totalElements || 0}
                        pageSize={moviePage.size || 10} size='default'
                        onChange={handleChangePage} />
                </div>
            </div>
        </Container>
    )
}

export default Movies