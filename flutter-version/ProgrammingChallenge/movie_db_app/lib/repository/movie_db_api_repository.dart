import 'package:movie_db_app/domain/model/page.dart';
import 'package:movie_db_app/repository/api/api_repository_impl.dart';

class MovieDBRepository {
  ApiRepositoryImpl _api;

  MovieDBRepository(ApiRepositoryImpl api) {
    _api = api;
  }

  Future<Page> getPopularMovies({int pageNumber}) {
    return _api.getPopularMovies(
      pageNumber: pageNumber,
    );
  }

  Future<Page> getTopRatedMovies({int pageNumber}) {
    return _api.getTopRatedMovies(
      pageNumber: pageNumber,
    );
  }
}
