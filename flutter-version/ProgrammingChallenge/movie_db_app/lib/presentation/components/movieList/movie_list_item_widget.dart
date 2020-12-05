import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/presentation/components/stars_widget.dart';
import '../image_loader_widget.dart';
import '../text_section_title_widget.dart';

// ignore: must_be_immutable
class MovieListCellWidget extends StatelessWidget {
  Movie _movie;

  MovieListCellWidget(Movie movie) {
    _movie = movie;
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Row(
        children: [
          Expanded(
            flex: 2,
            child: ImageLoaderWidget(
              _movie.imageAddress(),
              400,
            ),
          ),
          Expanded(
            child: Column(
              children: [
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: TextSectionTitleWidget(
                    _movie.title,
                  ),
                ),
                Text(_movie.releaseDate),
                StarsWidget(_movie.votesAverage),
              ],
            ),
          )
        ],
      ),
    );
  }
}
