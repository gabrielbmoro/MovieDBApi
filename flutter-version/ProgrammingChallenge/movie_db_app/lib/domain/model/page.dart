import 'package:flutter/material.dart';

import 'movie.dart';

class Page {
  List<Movie> _movieList;
  bool _hasMorePages;

  Page({
    @required List<Movie> movies,
    @required bool hasMorePages,
  }) {
    _movieList = movies;
    _hasMorePages = hasMorePages;
  }

  factory Page.fromJson(Map<String, dynamic> json) {
    int page = json['page'];
    int totalPages = json['total_pages'];
    List<dynamic> results = json['results'];
    List<Movie> movies = [];

    results.forEach((element) {
      var movie = Movie.fromJson(element);
      if (movie != null) {
        movies.add(movie);
      }
    });

    return Page(movies: movies, hasMorePages: (page < totalPages));
  }

  moviesList() {
    return _movieList;
  }

  hasMorePages() {
    return _hasMorePages;
  }
}
