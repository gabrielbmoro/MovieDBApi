import 'package:flutter/cupertino.dart';
import 'package:movie_db_app/domain/model/movie_type.dart';

// ignore: must_be_immutable
class MovieList extends StatelessWidget {
  MovieType _type;

  MovieList(MovieType type) {
    _type = type;
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text(_type.name()),
    );
  }
}