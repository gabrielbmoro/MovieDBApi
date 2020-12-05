
import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie_type.dart';

import 'movie_list_state.dart';

// ignore: must_be_immutable
class MovieListWidget extends StatefulWidget {
  MovieType _movieType;

  MovieListWidget(MovieType movieType) {
    _movieType = movieType;
  }

  @override
  State<StatefulWidget> createState() {
    return MovieListState(_movieType);
  }
}

