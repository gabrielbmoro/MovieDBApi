import 'package:flutter/material.dart';
import 'package:movie_db_app/presentation/colors.dart';
import 'package:movie_db_app/presentation/strings.dart';

class BottomNavigationBarBuilder {
  static BottomNavigationBar build({
    ValueChanged<int> onTap,
    int currentIndex,
  }) {
    return BottomNavigationBar(
      items: const <BottomNavigationBarItem>[
        BottomNavigationBarItem(
          icon: Icon(Icons.photo_album),
          label: TOP_RATED,
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.movie),
          label: POPULAR,
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.star),
          label: FAVORITE,
        ),
      ],
      selectedItemColor: colorAccent,
      onTap: onTap,
      currentIndex: currentIndex,
    );
  }
}
