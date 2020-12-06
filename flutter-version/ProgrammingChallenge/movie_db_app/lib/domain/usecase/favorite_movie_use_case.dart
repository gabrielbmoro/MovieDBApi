import 'dart:async';
import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/repository/movie_db_api_repository.dart';

class FavoriteMovieUseCase {
  MovieDBRepository _repository;

  FavoriteMovieUseCase(MovieDBRepository repository) {
    _repository = repository;
  }

  Future<bool> execute({
    @required Movie movie,
  }) {
    final completer = new Completer<bool>();
    _repository.saveFavoriteMovie(
      movie: movie,
      onSuccess: () => {completer.complete(true)},
      onFail: () => {completer.complete(false)},
    );
    return completer.future;
  }
}
