import 'dart:async';

import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/domain/model/page.dart' as MoviesPage;
import 'package:movie_db_app/repository/movie_db_api_repository.dart';

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

class FavoriteMovieUseCase {
  MovieDBRepository _repository;

  FavoriteMovieUseCase(MovieDBRepository repository) {
    _repository = repository;
  }

  Future<bool> execute({
    @required Movie movie,
    @required bool isToFavoriteOrNot,
  }) {
    final completer = new Completer<bool>();
    if (isToFavoriteOrNot) {
      _repository.saveFavoriteMovie(
        movie: movie,
        onSuccess: () => {completer.complete(true)},
        onFail: () => {completer.complete(false)},
      );
    } else {
      _repository.removeFavoriteMovie(
        movie: movie,
        onSuccess: () => {completer.complete(true)},
        onFail: () => {completer.complete(false)},
      );
    }
    return completer.future;
  }
}
