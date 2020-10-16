import 'package:flutter/material.dart';

class Movie {
  int id;
  int votesAverage;
  String title;
  bool isVideo;
  int votes;
  int popularity;
  String postPath;
  String originalLanguage;
  String originalTitle;
  String backdropPath;
  bool isAdult;
  String overview;
  String releaseDate;
  bool isFavorite = false;

  Movie(
      {@required int votesAverage,
      @required String title,
      @required bool isVideo,
      @required int votes,
      @required int popularity,
      @required String postPath,
      @required String originalLanguage,
      @required String originalTitle,
      @required String backdropPath,
      @required bool isAdult,
      @required String overview,
      @required String releaseDate}) {
    this.id = id;
    this.votesAverage = votesAverage;
    this.title = title;
    this.isVideo = isVideo;
    this.votes = votes;
    this.popularity = popularity;
    this.postPath = postPath;
    this.originalLanguage = originalLanguage;
    this.originalTitle = originalTitle;
    this.backdropPath = backdropPath;
    this.isAdult = isAdult;
    this.overview = overview;
    this.releaseDate = releaseDate;
  }

  factory Movie.fromJson(Map<String, dynamic> json) {
    final int votes = json['vote_count'];
    final bool isVideo = json['video'];
    final int votesAverage = 0; //json['vote_average'];
    final String title = json['title'];
    final int popularity = 0; //json['popularity'];
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

  @override
  String toString() {
    return "$title -> popularity $popularity, votes $votesAverage";
  }
}
