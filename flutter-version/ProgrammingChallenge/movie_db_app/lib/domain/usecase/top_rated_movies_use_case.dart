import 'package:movie_db_app/main.dart';
import 'package:movie_db_app/repository/movie_db_api_repository.dart';

class TopRatedMoviesUseCase {
  MovieDBRepository _repository;

  TopRatedMoviesUseCase(MovieDBRepository repository) {
    _repository = repository;
  }

  execute(int pageNumber) {
    return _repository.getMovies(
        apiKey: TOKEN,
        pageNumber: pageNumber,
        sortBy: PARAMETER_TOP_RATED_MOVIES);
  }
}
