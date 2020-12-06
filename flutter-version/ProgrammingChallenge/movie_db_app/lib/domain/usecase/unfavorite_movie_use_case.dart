import 'dart:async';
import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/repository/movie_db_api_repository.dart';

class UnFavoriteMovieUseCase {
  MovieDBRepository _repository;

  UnFavoriteMovieUseCase(MovieDBRepository repository) {
    _repository = repository;
  }

  Future<bool> execute({
    @required Movie movie,
  }) {
    final completer = new Completer<bool>();
    _repository.removeFavoriteMovie(
      movie: movie,
      onSuccess: () => {completer.complete(true)},
      onFail: () => {completer.complete(false)},
    );
    return completer.future;
  }
}
