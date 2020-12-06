import 'dart:async';
import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/repository/movie_db_api_repository.dart';

class CheckMovieIsFavoriteUseCase {
  MovieDBRepository _repository;

  CheckMovieIsFavoriteUseCase(MovieDBRepository repository) {
    _repository = repository;
  }

  Future<bool> execute({
    @required Movie movie,
  }) {
    final completer = new Completer<bool>();
    _repository.getFavoriteMovies(
      (elements) => completer.complete(
        elements.any(
          (element) => element.originalTitle == movie.originalTitle,
        ),
      ),
    );
    return completer.future;
  }
}
