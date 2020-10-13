import 'package:movie_db_app/core/repository_factory.dart';
import 'package:movie_db_app/domain/usecase/popular_movies_use_case.dart';
import 'package:movie_db_app/domain/usecase/top_rated_movies_use_case.dart';

class UseCaseFactory {
  static PopularMoviesUseCase getPopularMoviesUseCase() {
    return PopularMoviesUseCase(RepositoryFactory.getMovieDBRepository());
  }

  static TopRatedMoviesUseCase getTopRatedUseCase() {
    return TopRatedMoviesUseCase(RepositoryFactory.getMovieDBRepository());
  }
}
