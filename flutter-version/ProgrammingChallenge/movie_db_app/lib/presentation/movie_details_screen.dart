import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/presentation/common/strings.dart';
import 'package:movie_db_app/presentation/components/text_section_title_widget.dart';

import 'components/text_section_label_widget.dart';

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
      body: SingleChildScrollView(
        child: Container(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              FittedBox(
                child: Image.network(
                  _movie.imageAddress(),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(
                  top: 20,
                  left: 20,
                  right: 20,
                ),
                child: TextSectionLabel(
                  ORIGINAL_TITLE,
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(
                  left: 20,
                  right: 20,
                ),
                child: TextSectionTitle(_movie.title),
              ),
              Padding(
                padding: const EdgeInsets.only(
                  top: 20,
                  left: 20,
                  right: 20,
                ),
                child: TextSectionLabel(
                  OVERVIEW_TITLE,
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(
                  left: 20,
                  right: 20,
                ),
                child: Text(_movie.overview),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
