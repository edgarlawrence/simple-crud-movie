package initial.simple_crud.service.implement;

import initial.simple_crud.dto.request.GenreRequestDTO;
import initial.simple_crud.dto.response.GenreResponseDTO;
import initial.simple_crud.model.Genre;

import java.util.List;

public interface GenreImplement {
    List<Genre> findAllGenres();
    Genre findGenreById(Long id);
    Genre addGenre(Genre genre);
    Genre updateGenre(Long id, GenreRequestDTO requestDTO);
    void deleteGenre(Long id);
}
