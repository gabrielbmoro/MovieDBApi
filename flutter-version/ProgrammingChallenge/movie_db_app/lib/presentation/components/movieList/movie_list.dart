import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:movie_db_app/core/use_case_factory.dart';
import 'package:movie_db_app/domain/model/page.dart' as page;
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/domain/model/movie_type.dart';
import 'movie_list_item.dart';

// ignore: must_be_immutable
class MovieList extends StatefulWidget {
  _MovieListState _state;

  MovieList(MovieType movieType) {
    _state = _MovieListState(movieType);
  }

  @override
  State<StatefulWidget> createState() {
    return _state;
  }
}

class _MovieListState extends State<MovieList> {
  List<Movie> _movies = [];
  MovieType _type;
  int _currentPageSelected = 1;
  bool hasMoreMovies = true;

  _MovieListState(MovieType movieType) {
    _type = movieType;
  }

  @override
  void initState() {
    super.initState();
    getMovies(_currentPageSelected);
  }

  void getMovies(int pageNumber) async {
    Future<page.Page> futurePage;
    switch (_type) {
      case MovieType.TOP_RATED_MOVIES:
        futurePage = UseCaseFactory.getTopRatedUseCase().execute(pageNumber);
        break;
      case MovieType.POPULAR_MOVIES:
        futurePage =
            UseCaseFactory.getPopularMoviesUseCase().execute(pageNumber);
        break;
      default:
        futurePage = null;
        break;
    }

    if (futurePage != null) {
      var page = await futurePage;
      setState(() {
        _movies.addAll(page.moviesList());
        hasMoreMovies = page.hasMorePages();
        _currentPageSelected++;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: _movies.length,
      scrollDirection: Axis.vertical,
      itemBuilder: (context, position) => Container(
        padding: EdgeInsets.all(8),
        child: MovieListCell(_movies[position]),
      ),
    );
  }
}
