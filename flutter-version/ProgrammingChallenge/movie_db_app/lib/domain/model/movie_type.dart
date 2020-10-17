import 'package:flutter/material.dart';
import 'package:movie_db_app/presentation/common/strings.dart';

const INVALID_INDEX = -1;

enum MovieType { TOP_RATED_MOVIES, FAVORITE_MOVIES, POPULAR_MOVIES }

extension MovieTypeExtension on MovieType {
  values() {
    return [
      MovieType.TOP_RATED_MOVIES,
      MovieType.POPULAR_MOVIES,
      MovieType.FAVORITE_MOVIES
    ];
  }

  String name() {
    switch (this) {
      case MovieType.TOP_RATED_MOVIES:
        return TOP_RATED;
      case MovieType.POPULAR_MOVIES:
        return POPULAR;
      case MovieType.FAVORITE_MOVIES:
        return FAVORITE;
      default:
        return '';
    }
  }

  IconData icon(){
    switch(this){
      case MovieType.TOP_RATED_MOVIES:
        return Icons.photo_album;
      case MovieType.POPULAR_MOVIES:
        return Icons.movie;
      case MovieType.FAVORITE_MOVIES:
        return Icons.star;
      default:
        return Icons.build;
    }
  }
}
