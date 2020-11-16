import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:movie_db_app/core/use_case_factory.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/presentation/common/strings.dart';
import 'package:movie_db_app/presentation/components/image_loader_widget.dart';
import 'package:movie_db_app/presentation/components/text_section_title_widget.dart';
import 'common/colors.dart';
import 'components/favorite_button.dart';
import 'components/stars_widget.dart';
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
  bool _isFavoriteMovie = false;

  _MovieDetailsScreen(this._movie);

  @override
  void initState() {
    super.initState();

    UseCaseFactory.getFavoriteMoviesUseCase()
        .execute()
        .then((value) => _updateFavoriteButton(
              value.movieList.any((element) =>
                  element.originalTitle == this._movie.originalTitle),
            ));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(_movie.title),
      ),
      body: SingleChildScrollView(
        child: Container(
          margin: const EdgeInsets.only(bottom: 40),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              _buildHeader(
                imageAddress: _movie.imageAddress(),
                numOfVotes: _movie.votes,
              ),
              _wrappingSectionLabel(TextSectionLabel(ORIGINAL_TITLE)),
              _wrappingSectionContent(TextSectionTitle(_movie.title)),
              _wrappingSectionLabel(TextSectionLabel(OVERVIEW_TITLE)),
              _wrappingSectionContent(Text(_movie.overview)),
              _wrappingSectionLabel(TextSectionLabel(LANGUAGE_TITLE)),
              _wrappingSectionContent(Text(_movie.originalLanguage)),
              _wrappingSectionLabel(TextSectionLabel(POPULARITY_TITLE)),
              _wrappingSectionContent(Text(_movie.popularity.toString()))
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildHeader({String imageAddress, int numOfVotes}) {
    return Stack(
      children: [
        ImageLoader(imageAddress, 400),
        Container(
          color: cardTransparentMask,
          width: double.infinity,
          height: 400,
        ),
        Positioned(
          bottom: 40,
          right: 16,
          height: 32,
          child: StarsWidget(numOfVotes),
        ),
        Positioned(
          bottom: 40,
          left: 16,
          child: FavoriteButton(
              _isFavoriteMovie,
              () => {
                    UseCaseFactory.favoriteMoviesUseCase()
                        .execute(
                          movie: _movie,
                          isToFavoriteOrNot: !_isFavoriteMovie,
                        )
                        .then((result) =>
                            _changeFavoriteValueJustIsSuccess(result))
                  }),
        )
      ],
    );
  }

  _changeFavoriteValueJustIsSuccess(bool wasSuccess) {
    if (wasSuccess) {
      _updateFavoriteButton(!_isFavoriteMovie);
    }
  }

  _updateFavoriteButton(bool newValue) {
    setState(() {
      _isFavoriteMovie = newValue;
    });
  }

  Widget _wrappingSectionLabel(Widget widget) {
    return Padding(
      padding: const EdgeInsets.only(
        top: 20,
        left: 20,
        right: 20,
      ),
      child: widget,
    );
  }

  Widget _wrappingSectionContent(Widget widget) {
    return Padding(
      padding: const EdgeInsets.only(
        left: 20,
        right: 20,
      ),
      child: widget,
    );
  }
}
