package initial.simple_crud.controller;

import initial.simple_crud.dto.request.MovieRequestDTO;
import initial.simple_crud.dto.response.MovieByIdResponseDTO;
import initial.simple_crud.dto.response.MovieResponseDTO;
import initial.simple_crud.model.Movie;
import initial.simple_crud.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/movie")
@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        var movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        var movies = movieService.addMovies(movieRequestDTO);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieByIdResponseDTO> getMovie(@PathVariable Long id) {
        var movies = movieService.findMoviesById(id);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequestDTO) {
        var movies = movieService.updateMovies(id, movieRequestDTO);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        return new ResponseEntity<>("Movie Has been Deleted", HttpStatus.OK);
    }
}
