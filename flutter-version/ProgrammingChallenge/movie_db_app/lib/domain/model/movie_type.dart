const TOP_RATED_MOVIES_VALUE = 1;
const FAVORITE_MOVIES_VALUE = 2;
const POPULAR_MOVIES_VALUE = 3;

enum MovieType { TOP_RATED_MOVIES, FAVORITE_MOVIES, POPULAR_MOVIES }

MovieType getMovieType(int code) {
  switch (code) {
    case TOP_RATED_MOVIES_VALUE:
      return MovieType.TOP_RATED_MOVIES;
    case FAVORITE_MOVIES_VALUE:
      return MovieType.FAVORITE_MOVIES;
    case POPULAR_MOVIES_VALUE:
      return MovieType.POPULAR_MOVIES;
    default:
      return null;
  }
}

extension MovieTypeExtension on MovieType {
  List<MovieType> values() {
    return [
      MovieType.TOP_RATED_MOVIES,
      MovieType.POPULAR_MOVIES,
      MovieType.FAVORITE_MOVIES
    ];
  }
}
