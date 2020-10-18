import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';

// ignore: must_be_immutable
class MovieListCell extends StatelessWidget {
  Movie _movie;

  MovieListCell(Movie movie) {
    _movie = movie;
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Row(
        children: [
          Expanded(
            flex: 2,
            child: Image(
              image: NetworkImage(_movie.imageAddress()),
            ),
          ),
          Expanded(
            child: Column(
              children: [
                Text(_movie.title),
                Text(_movie.releaseDate),
                Text(_movie.votesAverage.toString()),
              ],
            ),
          )
        ],
      ),
    );
  }
}
