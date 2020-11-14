import 'package:movie_db_app/domain/model/page.dart';
import 'package:movie_db_app/repository/movie_db_api_repository.dart';

class PopularMoviesUseCase {
  MovieDBRepository _repository;

  PopularMoviesUseCase(MovieDBRepository repository) {
    _repository = repository;
  }

  Future<Page> execute(int pageNumber) {
    return _repository.getPopularMovies(
      pageNumber: pageNumber,
    );
  }
}
