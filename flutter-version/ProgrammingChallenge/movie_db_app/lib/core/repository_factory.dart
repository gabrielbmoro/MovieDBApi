import 'package:movie_db_app/repository/api/api_repository_impl.dart';
import 'package:movie_db_app/repository/movie_db_api_repository.dart';

class RepositoryFactory {
  static MovieDBRepository _repository;

  static MovieDBRepository getMovieDBRepository() {
    if (_repository == null) {
      _repository = MovieDBRepository(ApiRepositoryImpl());
    }
    return _repository;
  }
}
