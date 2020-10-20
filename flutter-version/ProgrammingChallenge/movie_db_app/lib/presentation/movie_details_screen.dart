import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';

// ignore: must_be_immutable
class MovieDetailsScreen extends StatefulWidget {
  Movie _movie;

  MovieDetailsScreen(this._movie);

  @override
  State<StatefulWidget> createState() {
    return _MovieDetailsScreen(_movie);
  }

  static launch(BuildContext context, Movie movie) {
    Navigator.of(context).push(
      MaterialPageRoute(builder: (context) => MovieDetailsScreen(movie)),
    );
  }
}

class _MovieDetailsScreen extends State<MovieDetailsScreen> {
  Movie _movie;

  _MovieDetailsScreen(this._movie);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Text(_movie.title),
      ),
    );
  }
}
