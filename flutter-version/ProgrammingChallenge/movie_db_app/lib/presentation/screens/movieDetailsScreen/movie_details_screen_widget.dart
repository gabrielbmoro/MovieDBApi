import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:movie_db_app/core/use_case_factory.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'movie_details_state.dart';

// ignore: must_be_immutable
class MovieDetailsScreenWidget extends StatefulWidget {
  Movie _movie;

  MovieDetailsScreenWidget(this._movie);

  @override
  State<StatefulWidget> createState() {
    return MovieDetailsState(
      _movie,
      UseCaseFactory.favoriteMoviesUseCase(),
      UseCaseFactory.unFavoriteMovieUseCase(),
      UseCaseFactory.checkMovieIsFavoriteUseCase(),
    );
  }

  static Future<dynamic> launch(BuildContext context, Movie movie) {
    return Navigator.of(context).push(
      MaterialPageRoute(builder: (context) => MovieDetailsScreenWidget(movie)),
    );
  }
}
