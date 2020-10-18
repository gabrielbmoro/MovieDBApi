import 'package:flutter/material.dart';
import 'package:movie_db_app/core/use_case_factory.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:movie_db_app/domain/model/movie_type.dart';
import 'file:///C:/Users/gabri/Documents/svn/personal/MovieDBApi/flutter-version/ProgrammingChallenge/movie_db_app/lib/presentation/components/movieList/movie_list.dart';

class HomePageScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _HomePageScreenState();
}

class _HomePageScreenState extends State<HomePageScreen> {
  List<Movie> _topRatedMovies = [];
  List<Movie> _favoriteMovies = [];
  List<Movie> _popularMovies = [];

  int _topRatedMoviesPageIndex = 1;
  int _popularMoviesPageIndex = 1;

  int _currentTabIndex;

  _HomePageScreenState() {
    _currentTabIndex = 0;
    UseCaseFactory.getTopRatedUseCase().execute(_topRatedMoviesPageIndex).then(
          (value) => this.updateTheState(
            () {
              _topRatedMovies.addAll(value.moviesList());
            },
          ),
        );

    UseCaseFactory.getPopularMoviesUseCase()
        .execute(_popularMoviesPageIndex)
        .then(
          (value) => this.updateTheState(
            () {
              _popularMovies.addAll(value.moviesList());
            },
          ),
        );
  }

  void updateTheState(VoidCallback action) {
    setState(() {
      action();
    });
  }

  void _onTapItem(int index) {
    updateTheState(() {
      _currentTabIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _getCurrentFragment(),
      bottomNavigationBar: BottomNavigationBar(
        items: _getMenuItems(),
        onTap: _onTapItem,
        currentIndex: _currentTabIndex,
      ),
    );
  }

  Widget _getCurrentFragment() {
    switch (_currentTabIndex) {
      case TOP_RATED_MOVIES_TAB_INDEX:
        return MovieList(_topRatedMovies);
      case POPULAR_MOVIES_TAB_INDEX:
        return MovieList(_popularMovies);
      case FAVORITE_MOVIES_TAB_INDEX:
        return MovieList(_favoriteMovies);
      default:
        return Text('aosdk');
    }
  }

  List<BottomNavigationBarItem> _getMenuItems() {
    final movieTypes = [
      MovieType.TOP_RATED_MOVIES,
      MovieType.POPULAR_MOVIES,
      MovieType.FAVORITE_MOVIES,
    ];

    return movieTypes
        .map(
          (e) => BottomNavigationBarItem(
            icon: Icon(e.icon()),
            label: e.name(),
          ),
        )
        .toList();
  }
}

const TOP_RATED_MOVIES_TAB_INDEX = 0;
const POPULAR_MOVIES_TAB_INDEX = 1;
const FAVORITE_MOVIES_TAB_INDEX = 2;
