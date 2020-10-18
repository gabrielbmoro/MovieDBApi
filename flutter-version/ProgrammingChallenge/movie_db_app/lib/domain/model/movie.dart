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

  Movie({@required int votesAverage,
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

  String imageAddress() {
    return "https://image.tmdb.org/t/p/w500$postPath";
  }

  @override
  String toString() {
    return "$title -> popularity $popularity, votes $votesAverage";
  }
}
