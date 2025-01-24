package initial.simple_crud.service;

import initial.simple_crud.dto.request.MovieGenreRequestDTO;
import initial.simple_crud.dto.request.MovieRequestDTO;
import initial.simple_crud.dto.response.MovieByIdResponseDTO;
import initial.simple_crud.dto.response.MovieResponseDTO;
import initial.simple_crud.model.Movie;
import initial.simple_crud.repository.MovieRepository;
import initial.simple_crud.service.implement.MovieImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService implements MovieImplement{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieResponseDTO> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        return movies.stream().map(movie -> MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .build()).collect(Collectors.toList());
    }

    @Override
    public MovieByIdResponseDTO findMoviesById(Long id) {
        Movie movies = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return MovieByIdResponseDTO.builder()
                .id(movies.getId())
                .title(movies.getTitle())
                .description(movies.getDescription())
                .build();
    }

    @Override
    @PreAuthorize("hasRole('Super Admin')")
    public Movie addMovies(MovieRequestDTO request) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());

        movieRepository.save(movie);

        return movie;
    }

    @Override
    @PreAuthorize("hasRole('Super Admin')")
    public Movie updateMovies(Long id, MovieRequestDTO request) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());

        movieRepository.save(movie);

        return movie;
    }

    @Override
    @PreAuthorize("hasRole('Super Admin')")
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        movieRepository.delete(movie);
    }
}
