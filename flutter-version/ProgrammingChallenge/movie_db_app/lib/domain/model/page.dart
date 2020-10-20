import 'package:flutter/material.dart';

import 'movie.dart';

class Page {
  List<Movie> movieList;
  bool hasMorePages;

  Page({
    @required this.movieList,
    @required this.hasMorePages,
  });
}
