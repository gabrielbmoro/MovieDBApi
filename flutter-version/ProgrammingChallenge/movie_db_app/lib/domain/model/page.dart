import 'package:flutter/material.dart';

import 'movie.dart';

class Page {
  Page({
    @required List<Movie> movies,
    @required bool hasMorePages,
  });

  factory Page.fromJson(Map<String, dynamic> json) {
    int page = json['page'];
    int totalPages = json['total_pages'];
    List<Map<String, dynamic>> results = json['results'];
    List<Movie> movies = [];

    movies.addAll(results.map((e) => Movie.fromJson(e)));

    return Page(movies: movies, hasMorePages: (page < totalPages));
  }
}
