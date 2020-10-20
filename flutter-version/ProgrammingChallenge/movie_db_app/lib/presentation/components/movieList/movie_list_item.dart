import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/presentation/components/star_widget.dart';

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
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Text(
                    _movie.title,
                    style: TextStyle(
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Text(_movie.releaseDate),
                StarWidget(_movie.votesAverage),
              ],
            ),
          )
        ],
      ),
    );
  }
}
