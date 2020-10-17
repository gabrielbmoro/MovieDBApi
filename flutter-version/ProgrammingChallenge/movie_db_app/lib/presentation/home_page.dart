import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie_type.dart';
import 'package:movie_db_app/presentation/movie_list.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  List<MovieList> _movieFragments =
      MovieType.values.map((e) => MovieList(e)).toList();

  List<BottomNavigationBarItem> _barMenuItems = MovieType.values
      .map(
        (e) => BottomNavigationBarItem(
          icon: Icon(e.icon()),
          label: e.name(),
        ),
      )
      .toList();

  int _currentIndex;

  MovieList _currentFragment;

  _HomePageState() {
    _currentIndex = 0;
    _currentFragment = _movieFragments.first;
  }

  void _onTapItem(int index) {
    setState(() {
      _currentIndex = index;
      _currentFragment = _movieFragments[_currentIndex];
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _currentFragment,
      bottomNavigationBar: BottomNavigationBar(
        items: _barMenuItems,
        onTap: _onTapItem,
        currentIndex: _currentIndex,
      ),
    );
  }
}
