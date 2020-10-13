import 'package:movie_db_app/domain/model/page.dart';
import 'package:movie_db_app/repository/api/api_repository_impl.dart';

class MovieDBRepository {
  ApiRepositoryImpl _api;

  MovieDBRepository(ApiRepositoryImpl api) {
    _api = api;
  }

  Future<Page> getMovies({String apiKey, String sortBy, int pageNumber}) {
    final page = _api.getMovies(
      apiKey: apiKey,
      sortBy: sortBy,
      pageNumber: pageNumber,
    );
    return page;
  }
}
