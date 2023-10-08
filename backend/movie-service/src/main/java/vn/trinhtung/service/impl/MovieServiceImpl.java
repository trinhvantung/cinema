package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.trinhtung.dto.CategoryMovieCountResponseDTO;
import vn.trinhtung.dto.MovieRequestDTO;
import vn.trinhtung.dto.MovieResponseDTO;
import vn.trinhtung.entity.Category;
import vn.trinhtung.entity.Movie;
import vn.trinhtung.event.MovieCreatedEvent;
import vn.trinhtung.event.MovieDeletedEvent;
import vn.trinhtung.exception.ApplicationException;
import vn.trinhtung.grpc.MovieResponse;
import vn.trinhtung.mapper.MovieMapper;
import vn.trinhtung.repository.MovieRepository;
import vn.trinhtung.service.CloudinaryService;
import vn.trinhtung.service.MovieService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final CloudinaryService cloudinaryService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void create(MultipartFile thumbnailFile, MovieRequestDTO request) {
        if (movieRepository.findByName(request.getName()).isPresent()) {
            throw new ApplicationException("duplicate_movie_name", "Duplicate movie name");
        }
        Movie movie = movieMapper.movieRequestDTOToMovie(request);

        if (thumbnailFile == null || thumbnailFile.getOriginalFilename().isBlank()) {
            throw new ApplicationException("thumbnail_empty", "Thumbnail empty");
        }

        String publicId = cloudinaryService.upload("movie", thumbnailFile);
        movie.setThumbnail(publicId);

        Movie savedMovie = movieRepository.save(movie);

        kafkaTemplate.send("create-movie",
                new MovieCreatedEvent(movieMapper.movieToMovieResponseDTO(savedMovie)));
    }

    @Override
    public void update(Integer movieId, MultipartFile thumbnailFile, MovieRequestDTO request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ApplicationException("movie_not_found", "Movie not found"));

        Optional<Movie> movieByName = movieRepository.findByName(request.getName());

        if (movieByName.isPresent() && movieByName.get().getId() != movieId) {
            throw new ApplicationException("duplicate_movie_name", "Duplicate movie name");
        }

        // Upload ảnh
        if (thumbnailFile != null && !thumbnailFile.getOriginalFilename().isBlank()) {
            cloudinaryService.delete(movie.getThumbnail());
            String publicId = cloudinaryService.upload("movie", thumbnailFile);
            movie.setThumbnail(publicId);
        }

        movie.setDescription(request.getDescription());
        movie.setDuration(request.getDuration());
        movie.setName(request.getName());
        movie.setReleaseDate(request.getReleaseDate());

        movie.getCategories().clear();

        request.getCategories().forEach(categoryId -> movie.getCategories().
                add(Category.builder().id(categoryId).build()));

        Movie savedMovie = movieRepository.save(movie);


        kafkaTemplate.send("update-movie",
                new MovieCreatedEvent(movieMapper.movieToMovieResponseDTO(savedMovie)));
    }

    @Override
    public void delete(Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ApplicationException("movie_not_found", "Movie not found"));
        movieRepository.delete(movie);
        cloudinaryService.delete(movie.getThumbnail());


        kafkaTemplate.send("delete-movie", new MovieDeletedEvent(movieId));
    }

    @Override
    public MovieResponseDTO getById(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("movie_not_found", "Movie not found"));
        return movieMapper.movieToMovieResponseDTO(movie);
    }

    @Override
    public MovieResponse getMovieById(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("movie_not_found", "Movie not found"));
        return movieMapper.movieToMovieResponse(movie);
    }

    @Override
    public Page<MovieResponseDTO> getAll(Integer page) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        return movieRepository.findAll(pageable).map(movieMapper::movieToMovieResponseDTO);
    }

    @Override
    public Page<MovieResponseDTO> getAllByCategory(Integer categoryId, Integer page) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        return movieRepository.findAllByCategoryId(categoryId, pageable)
                .map(movieMapper::movieToMovieResponseDTO);
    }

    @Override
    public List<MovieResponse> getMoviesByIds(List<Integer> movieIds) {
        List<Movie> movies = movieRepository.findAllById(movieIds);
        return movies.stream().map(movieMapper::movieToMovieResponse).collect(Collectors.toList());
    }

    @Override
    public List<MovieResponseDTO> getAll() {
        return movieRepository.findAll().stream().map(movieMapper::movieToMovieResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return movieRepository.count();
    }

    @Override
    public List<CategoryMovieCountResponseDTO> countByCategory() {
        List<Map<String, Object>> counts = movieRepository.countByCategory();
        return counts.stream().map(count -> {
            CategoryMovieCountResponseDTO dto = new CategoryMovieCountResponseDTO();
            dto.setCategoryId((Integer) count.get("categoryId"));
            dto.setCategoryName((String) count.get("categoryName"));
            dto.setMovieCount((Long) count.get("count"));

            return dto;
        }).collect(Collectors.toList());
    }
}
