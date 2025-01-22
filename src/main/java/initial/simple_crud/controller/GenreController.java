package initial.simple_crud.controller;

import initial.simple_crud.dto.request.GenreRequestDTO;
import initial.simple_crud.model.Genre;
import initial.simple_crud.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/genre")
@RestController
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> fetchGenre = genreService.findAllGenres();
        return new ResponseEntity<>(fetchGenre, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre genres = genreService.addGenre(genre);
        return new ResponseEntity<>(genres, HttpStatus.CREATED);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable long id) {
        Genre genre = genreService.findGenreById(id);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable long id, @RequestBody GenreRequestDTO genre) {
        var genreUpdated = genreService.updateGenre(id, genre);
        return new ResponseEntity<>(genreUpdated, HttpStatus.OK);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteGenreById(@PathVariable long id) {
        return new ResponseEntity<>("Genre Has been Deleted", HttpStatus.OK);
    }
}
