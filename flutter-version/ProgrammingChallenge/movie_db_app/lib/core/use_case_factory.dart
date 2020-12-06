import 'package:movie_db_app/core/repository_factory.dart';
import 'package:movie_db_app/domain/usecase/check_movie_is_favorite_use_case.dart';
import 'package:movie_db_app/domain/usecase/favorite_movie_use_case.dart';
import 'package:movie_db_app/domain/usecase/get_favorite_movies_use_case.dart';
import 'package:movie_db_app/domain/usecase/popular_movies_use_case.dart';
import 'package:movie_db_app/domain/usecase/top_rated_movies_use_case.dart';
import 'package:movie_db_app/domain/usecase/unfavorite_movie_use_case.dart';

class UseCaseFactory {
  static GetPopularMoviesUseCase getPopularMoviesUseCase() {
    return GetPopularMoviesUseCase(RepositoryFactory.getMovieDBRepository());
  }

  static GetTopRatedMoviesUseCase getTopRatedUseCase() {
    return GetTopRatedMoviesUseCase(RepositoryFactory.getMovieDBRepository());
  }

  static GetFavoriteMoviesUseCase getFavoriteMoviesUseCase() {
    return GetFavoriteMoviesUseCase(RepositoryFactory.getMovieDBRepository());
  }

  static FavoriteMovieUseCase favoriteMoviesUseCase() {
    return FavoriteMovieUseCase(RepositoryFactory.getMovieDBRepository());
  }

  static UnFavoriteMovieUseCase unFavoriteMovieUseCase(){
    return UnFavoriteMovieUseCase(RepositoryFactory.getMovieDBRepository());
  }

  static CheckMovieIsFavoriteUseCase checkMovieIsFavoriteUseCase(){
    return CheckMovieIsFavoriteUseCase(RepositoryFactory.getMovieDBRepository());
  }
}
