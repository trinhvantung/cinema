import { Breadcrumb, Input, Pagination, Select } from "antd";
import React, { useEffect, useState } from "react";
import categoryApi from "../api/categoryApi";
import movieApi from "../api/movieApi";
import Container from "../components/Container";
import GridContainer from "../components/GridContainer";
import Movie from "../components/Movie";
import { Link } from "react-router-dom";

const Movies = () => {
  const [categories, setCategories] = useState([]);
  // const [movies, setMovies] = useState([])
  const [moviePage, setMoviePage] = useState({});
  const [search, setSearch] = useState("");
  const [currentCategory, setCurrentCategory] = useState();
  const [currentPage, setCurrentPage] = useState(1);

  useEffect(() => {
    categoryApi.getAll().then((res) => {
      setCategories([
        { value: 0, label: "Tất cả" },
        ...res.data.map((c) => ({ ...c, label: c.name, value: c.id })),
      ]);
    });

    movieApi.getAllByPage(1).then((res) => {
      setMoviePage(res.data);
    });
  }, []);

  const handleChangeCategory = (c) => {
    console.log(c);
    setCurrentCategory(c);
    setCurrentPage(1);
    if (c === 0) {
      movieApi.getAllByPage(1).then((res) => {
        setMoviePage(res.data);
      });
    } else {
      movieApi.getAllByCategory(c, 1).then((res) => {
        setMoviePage(res.data);
      });
    }
  };

  const handleChangePage = (p) => {
    setCurrentPage(p);
    if (search) {
    } else {
      if (currentCategory && currentCategory !== 0) {
        movieApi.getAllByCategory(currentCategory, p).then((res) => {
          setMoviePage(res.data);
        });
      } else {
        movieApi.getAllByPage(p).then((res) => {
          setMoviePage(res.data);
        });
      }
    }
  };

  useEffect(() => {
    if (search) {
      movieApi.search(search, 1).then((res) => {
        console.log(res);
        setMoviePage(res.data);
      });
    } else {
      movieApi.getAllByPage(1).then((res) => {
        setMoviePage(res.data);
      });
    }
  }, [search]);

  return (
    <Container>
      <Breadcrumb
        items={[
          {
            title: <Link to={"/"}>Home</Link>,
          },
          {
            title: "Danh sách phim"
          }
        ]}
      />
      <div className="movie_search">
        <div>Tìm kiếm phim</div>
        <div>
          <Input.Search
            onSearch={(e) => setSearch(e)}
            placeholder="Tìm theo tên phim"
            size="large"
          />
          <Select
            showSearch
            style={{
              width: 400,
              marginLeft: 12,
            }}
            defaultValue={0}
            onChange={handleChangeCategory}
            size="large"
            placeholder="Danh mục"
            optionFilterProp="children"
            filterOption={(input, option) => {
              return (option?.label ?? "").includes(input);
            }}
            filterSort={(optionA, optionB) => optionA.value - optionB.value}
            options={categories}
          />
        </div>
      </div>
      <div className="movie_list">
        <GridContainer columns={5} gap={12}>
          {moviePage.content &&
            moviePage.content.map((m) => <Movie item={m} key={m.id} />)}
        </GridContainer>
        <div className="pagination">
          <Pagination
            defaultCurrent={1}
            current={currentPage}
            total={moviePage.totalElements || 0}
            pageSize={moviePage.size || 10}
            size="default"
            onChange={handleChangePage}
          />
        </div>
      </div>
    </Container>
  );
};

export default Movies;
