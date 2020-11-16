import 'package:flutter/material.dart';

class Movie {
  int id;
  num votesAverage;
  String title;
  bool isVideo;
  int votes;
  num popularity;
  String postPath;
  String originalLanguage;
  String originalTitle;
  String backdropPath;
  bool isAdult;
  String overview;
  String releaseDate;
  bool isFavorite = false;

  Movie({
    this.id,
    @required this.votesAverage,
    @required this.title,
    @required this.isVideo,
    @required this.votes,
    @required this.popularity,
    @required this.postPath,
    @required this.originalLanguage,
    @required this.originalTitle,
    @required this.backdropPath,
    @required this.isAdult,
    @required this.overview,
    @required this.releaseDate,
  });

  String imageAddress() {
    return "https://image.tmdb.org/t/p/w500$postPath";
  }

  @override
  String toString() {
    return "$title -> popularity $popularity, votes $votesAverage";
  }
}
