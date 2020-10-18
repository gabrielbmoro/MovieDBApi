import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';

import 'movie_list_item.dart';

// ignore: must_be_immutable
class MovieList extends StatelessWidget {
  List<Movie> _movies;

  MovieList(List<Movie> movies) {
    _movies = movies;
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: _movies.length,
      itemBuilder: (context, position) => Container(
        padding: EdgeInsets.all(8),
        child: MovieListCell(_movies[position]),
      ),
    );
  }
}
