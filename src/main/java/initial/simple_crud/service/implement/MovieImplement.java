package initial.simple_crud.service.implement;

import initial.simple_crud.dto.request.MovieRequestDTO;
import initial.simple_crud.dto.response.MovieByIdResponseDTO;
import initial.simple_crud.dto.response.MovieResponseDTO;
import initial.simple_crud.model.Movie;

import java.util.List;

public interface MovieImplement {
    List<MovieResponseDTO> findAllMovies();
    MovieByIdResponseDTO findMoviesById(Long id);
    Movie addMovies(MovieRequestDTO request);
    Movie updateMovies(Long id, MovieRequestDTO request);
    void deleteMovie(Long id);
}
