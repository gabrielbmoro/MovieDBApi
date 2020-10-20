import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/domain/model/page.dart';

class PageMapper {
  MovieMapper _movieMapper = MovieMapper();

  fromJson(Map<String, dynamic> json) {
    int page = json['page'];
    int totalPages = json['total_pages'];
    List<dynamic> results = json['results'];
    List<Movie> movies = [];

    results.forEach((element) {
      var movie = _movieMapper.fromJson(element);
      if (movie != null) {
        movies.add(movie);
      }
    });
    return Page(movieList: movies, hasMorePages: (page < totalPages));
  }
}

class MovieMapper {
  fromJson(Map<String, dynamic> json) {
    final int votes = json['vote_count'];
    final bool isVideo = json['video'];
    final num votesAverage = json['vote_average'];
    final String title = json['title'];
    final num popularity = json['popularity'];
    final String posterPath = json['poster_path'];
    final String originalLanguage = json['original_language'];
    final String originalTitle = json['original_title'];
    final String backdropPath = json['backdrop_path'];
    final bool isAdult = json['adult'];
    final String overview = json['overview'];
    final String releaseDate = json['release_date'];

    return Movie(
      votes: votes,
      isVideo: isVideo,
      votesAverage: votesAverage,
      title: title,
      popularity: popularity,
      postPath: posterPath,
      originalLanguage: originalLanguage,
      originalTitle: originalTitle,
      backdropPath: backdropPath,
      isAdult: isAdult,
      overview: overview,
      releaseDate: releaseDate,
    );
  }
}
