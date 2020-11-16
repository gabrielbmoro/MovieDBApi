import 'package:movie_db_app/domain/model/page.dart';
import 'package:movie_db_app/repository/movie_db_api_repository.dart';

class GetTopRatedMoviesUseCase {
  MovieDBRepository _repository;

  // ignore: non_constant_identifier_names
  GetTopRatedMoviesUseCase(MovieDBRepository repository) {
    _repository = repository;
  }

  Future<Page> execute(int pageNumber) {
    return _repository.getTopRatedMovies(
      pageNumber: pageNumber,
    );
  }
}
