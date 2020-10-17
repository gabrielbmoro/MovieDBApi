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

  moviesList() {
    return _movieList;
  }

  hasMorePages() {
    return _hasMorePages;
  }
}
