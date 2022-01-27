package day02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {

    List<Movie> movies = new ArrayList<>();

    public List<Movie> getMovies() {
        return new ArrayList<>(movies);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getMoviesWithActor(String actor) {
        return movies.stream()
                .filter(movie -> movie.getActors().contains(actor))
                .collect(Collectors.toList());
    }

    public int getLongestMovie() {
        return movies.stream()
                .mapToInt(Movie::getLength)
                .max()
                .orElseThrow(() -> new IllegalStateException("List is empty"));
    }
}
