import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie_type.dart';
import '../../components/movieList/movie_list_widget.dart';
import 'home_page_screen.dart';

class HomePageScreenState extends State<HomePageScreenWidget> {
  int _currentTabIndex = 0;
  PageController _pageController;

  @override
  void initState() {
    _pageController = PageController(
      initialPage: _currentTabIndex,
    );
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: buildPageView(),
      bottomNavigationBar: buildBottomNavigationBar(),
    );
  }

  BottomNavigationBar buildBottomNavigationBar() {
    return BottomNavigationBar(
      items: [
        BottomNavigationBarItem(
          icon: Icon(MovieType.TOP_RATED_MOVIES.icon()),
          label: MovieType.TOP_RATED_MOVIES.name(),
        ),
        BottomNavigationBarItem(
          icon: Icon(MovieType.POPULAR_MOVIES.icon()),
          label: MovieType.POPULAR_MOVIES.name(),
        ),
        BottomNavigationBarItem(
          icon: Icon(MovieType.FAVORITE_MOVIES.icon()),
          label: MovieType.FAVORITE_MOVIES.name(),
        )
      ],
      onTap: (index) {
        this._pageController.animateToPage(index,
            duration: const Duration(milliseconds: 500),
            curve: Curves.easeInOut);
      },
      currentIndex: _currentTabIndex,
    );
  }

  PageView buildPageView() {
    return PageView(
      controller: _pageController,
      onPageChanged: (newPage) {
        setState(() {
          this._currentTabIndex = newPage;
        });
      },
      children: [
        MovieListWidget(MovieType.TOP_RATED_MOVIES),
        MovieListWidget(MovieType.POPULAR_MOVIES),
        MovieListWidget(MovieType.FAVORITE_MOVIES),
      ],
    );
  }
}