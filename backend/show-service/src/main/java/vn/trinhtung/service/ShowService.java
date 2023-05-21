package vn.trinhtung.service;

import org.springframework.data.domain.Page;

import vn.trinhtung.dto.*;
import vn.trinhtung.grpc.ShowResponse;

import java.util.List;

public interface ShowService {
	ShowResponseDTO getById(Integer showId);

	ShowResponse getShowById(Integer showId);

	ShowDetailResponseDTO getDetail(Integer showId);

	Page<ShowResponseDTO> getAll(Integer page);

	List<ShowResponse> getShowsByIds(List<Integer> ids);

	Page<MovieShowResponseDTO> getAllByCinema(Integer page, Integer cinemaId, String date);

	Page<CinemaShowResponseDTO> getAllByMovie(Integer page, Integer movieId, String date);

	void create(ShowRequestDTO show);

	void update(Integer showId, ShowRequestDTO show);

	void delete(Integer showId);

    Page<ShowResponseDTO> getAll(Integer page, ShowFilterDTO showFilter, String sortString);

    Long count();
}
