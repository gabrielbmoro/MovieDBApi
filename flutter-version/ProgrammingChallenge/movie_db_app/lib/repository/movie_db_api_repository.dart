import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/domain/model/page.dart' as MoviesPage;
import 'package:movie_db_app/repository/api/api_repository_impl.dart';
import 'database/database_repository_impl.dart';

class MovieDBRepository {
  ApiRepositoryImpl api;
  DatabaseRepositoryImpl database;

  MovieDBRepository({@required this.api, @required this.database});

  Future<MoviesPage.Page> getPopularMovies({int pageNumber}) {
    return api.getPopularMovies(
      pageNumber: pageNumber,
    );
  }

  Future<MoviesPage.Page> getTopRatedMovies({int pageNumber}) {
    return api.getTopRatedMovies(
      pageNumber: pageNumber,
    );
  }

  getFavoriteMovies(Function(List<Movie>) onResultCallback) {
    database.favoriteMoviesDAO.select(onResultCallback);
  }

  void saveFavoriteMovie({
    @required Movie movie,
    @required VoidCallback onSuccess,
    @required VoidCallback onFail,
  }) {
    database.favoriteMoviesDAO.insert(
      title: movie.title,
      isVideo: movie.isVideo,
      votes: movie.votes,
      votesAverage: movie.votesAverage,
      popularity: movie.popularity,
      posterPath: movie.postPath,
      originalLanguage: movie.originalLanguage,
      originalTitle: movie.originalTitle,
      backdropPath: movie.backdropPath,
      isAdult: movie.isAdult,
      overview: movie.overview,
      releaseDate: movie.releaseDate,
      onSuccess: onSuccess,
      onFail: onFail,
    );
  }

  void removeFavoriteMovie({
    @required Movie movie,
    @required VoidCallback onSuccess,
    @required VoidCallback onFail,
  }) {
    database.favoriteMoviesDAO.remove(
      movieId: movie.id,
      onSuccess: onSuccess,
      onFail: onFail,
    );
  }
}
