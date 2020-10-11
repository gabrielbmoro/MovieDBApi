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
}
