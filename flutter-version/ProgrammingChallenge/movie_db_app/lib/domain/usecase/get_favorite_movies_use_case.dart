import 'dart:async';

import 'package:movie_db_app/repository/movie_db_api_repository.dart';
import 'package:movie_db_app/domain/model/page.dart' as MoviesPage;

class GetFavoriteMoviesUseCase {
  MovieDBRepository _repository;

  GetFavoriteMoviesUseCase(MovieDBRepository repository) {
    _repository = repository;
  }

  Future<MoviesPage.Page> execute() {
    final completer = new Completer<MoviesPage.Page>();
    _repository
        .getFavoriteMovies((elements) => completer.complete(MoviesPage.Page(
      movieList: elements,
      hasMorePages: false,
    )));
    return completer.future;
  }
}