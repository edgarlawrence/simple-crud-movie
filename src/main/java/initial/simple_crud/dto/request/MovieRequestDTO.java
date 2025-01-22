package initial.simple_crud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRequestDTO {
    private String title;
    private String description;
    private List<MovieGenreRequestDTO> genres;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MovieGenreRequestDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<MovieGenreRequestDTO> genres) {
        this.genres = genres;
    }
}
