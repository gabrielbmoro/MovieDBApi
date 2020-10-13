import 'package:flutter/material.dart';

class Movie {
  Movie({
    @required int id,
    @required double votesAverage,
    @required String title,
    @required bool isVideo,
    @required int votes,
    @required double popularity,
    @required String postPath,
    @required String originalLanguage,
    @required String originalTitle,
    @required String backdropPath,
    @required bool isAdult,
    @required String overview,
    @required String releaseDate,
    bool isFavorite = false,
  });

  factory Movie.fromJson(Map<String, dynamic> json) {
    final int votes = json['vote_count'];
    final bool isVideo = json['video'];
    final double votesAverage = json['vote_average'];
    final String title = json['title'];
    final double popularity = json['popularity'];
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
      id: null,
    );
  }
}
