package vn.trinhtung.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer>, JpaSpecificationExecutor<Show> {
    @Query("SELECT DISTINCT s.movieId FROM Show s WHERE s.cinemaId = :cinemaId "
            + "AND s.start BETWEEN :startOfDay AND :endOfDay")
    Page<Integer> findAllMovieByCinemaAndStart(LocalDateTime startOfDay, LocalDateTime endOfDay,
                                               Integer cinemaId, Pageable pageable);

    @Query("SELECT s FROM Show s WHERE s.cinemaId = :cinemaId " + "AND s.movieId IN :movieIds"
            + " AND s.start BETWEEN :startOfDay AND :endOfDay")
    List<Show> findAllShowByMoviesAndCinemaAndStart(LocalDateTime startOfDay, LocalDateTime endOfDay,
                                                   Integer cinemaId, List<Integer> movieIds);

    @Query("select s from Show s where s.hallId = :hallId and :start between s.start and s.end")
    Optional<Show> findByHallIdAndStart(Integer hallId, LocalDateTime start);

    @Query("SELECT DISTINCT s.cinemaId FROM Show s WHERE s.movieId = :movieId "
            + "AND s.start BETWEEN :startOfDay AND :endOfDay")
    Page<Integer> findAllCinemaByMovieAndStart(LocalDateTime startOfDay, LocalDateTime endOfDay,
                                               Integer movieId, Pageable pageable);


    @Query("SELECT s FROM Show s WHERE s.movieId = :movieId " + "AND s.cinemaId IN :cinemaIds"
            + " AND s.start BETWEEN :startOfDay AND :endOfDay")
    List<Show> findAllShowByMovieAndCinemasAndStart(LocalDateTime startOfDay, LocalDateTime endOfDay,
                                                    Integer movieId, List<Integer> cinemaIds);
}
