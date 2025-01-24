package initial.simple_crud.service;

import initial.simple_crud.dto.request.GenreRequestDTO;
import initial.simple_crud.dto.response.GenreResponseDTO;
import initial.simple_crud.model.Genre;
import initial.simple_crud.repository.GenreRepository;
import initial.simple_crud.service.implement.GenreImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GenreService implements GenreImplement {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasRole('Super Admin')")
    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    @PreAuthorize("hasRole('Super Admin')")
    public Genre updateGenre(Long id, GenreRequestDTO requestDTO) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        genre.setName(requestDTO.getName());
       return genreRepository.save(genre);
    }
    @Override
    @PreAuthorize("hasRole('Super Admin')")
    public void deleteGenre(Long id) {
        Genre findGenre = genreRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        genreRepository.delete(findGenre);
    }
}
