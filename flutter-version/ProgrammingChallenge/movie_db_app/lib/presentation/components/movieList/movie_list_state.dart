import 'dart:async';
import 'package:flutter/material.dart';
import 'package:movie_db_app/core/use_case_factory.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/domain/model/movie_type.dart';
import 'package:movie_db_app/presentation/screens/movieDetailsScreen/movie_details_screen_widget.dart';
import 'movie_list_widget.dart';
import 'package:movie_db_app/domain/model/page.dart' as page;
import 'movie_list_item_widget.dart';

class MovieListState extends State<MovieListWidget> {
  List<Movie> _movies = [];
  MovieType _type;
  int _currentPageSelected = 1;
  bool hasMoreMovies = true;
  ScrollController _controller;
  Future<Null> _requestLocker;

  MovieListState(MovieType movieType) {
    _type = movieType;
  }

  @override
  void initState() {
    super.initState();

    _controller = ScrollController();
    _controller.addListener(_scrollListener);

    getMovies(_currentPageSelected);
  }

  _scrollListener() {
    if (_controller.position.extentAfter <= 0 && _requestLocker == null) {
      _currentPageSelected++;
      getMovies(_currentPageSelected);
    }
  }

  void getMovies(int pageNumber) async {
    if (_requestLocker == null) {
      // lock
      var completer = new Completer<Null>();
      _requestLocker = completer.future;

      Future<page.Page> futurePage;
      switch (_type) {
        case MovieType.TOP_RATED_MOVIES:
          futurePage = UseCaseFactory.getTopRatedUseCase().execute(
            pageNumber,
          );
          break;
        case MovieType.POPULAR_MOVIES:
          futurePage = UseCaseFactory.getPopularMoviesUseCase().execute(
            pageNumber,
          );
          break;
        case MovieType.FAVORITE_MOVIES:
          futurePage = UseCaseFactory.getFavoriteMoviesUseCase().execute();
          break;
        default:
          futurePage = null;
          break;
      }

      if (futurePage != null) {
        var page = await futurePage;
        setState(() {
          _movies.addAll(page.movieList);
          hasMoreMovies = page.hasMorePages;
          _currentPageSelected++;
        });
      }

      //unlock
      completer.complete();
      _requestLocker = null;
    }
  }

  _resetData() {
    _movies.clear();
    _currentPageSelected = 1;
    getMovies(_currentPageSelected);
  }

  _gestureDetector({BuildContext context, Movie movie}) {
    return GestureDetector(
      child: Container(
        padding: EdgeInsets.all(8),
        child: MovieListCellWidget(movie),
      ),
      onTap: () => {
        MovieDetailsScreenWidget.launch(context, movie).then((value) => _resetData())
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: _movies.length,
      scrollDirection: Axis.vertical,
      controller: _controller,
      itemBuilder: (context, position) => _gestureDetector(
        context: context,
        movie: _movies[position],
      ),
    );
  }
}
