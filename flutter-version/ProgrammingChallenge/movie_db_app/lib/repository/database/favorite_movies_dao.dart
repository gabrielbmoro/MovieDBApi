import 'package:flutter/material.dart';
import 'package:movie_db_app/domain/model/movie.dart';
import 'package:sqflite/sqflite.dart';

import 'database_helper.dart';

class FavoriteMoviesDAO {
  static const TABLE_NAME = 'favorite_movies';

  static const FIELD_ID = 'id';
  static const FIELD_VOTES_AVERAGE = 'votes_average';
  static const FIELD_TITLE = 'title';
  static const FIELD_IS_VIDEO = 'is_video';
  static const FIELD_VOTES = 'votes';
  static const FIELD_POPULARITY = 'popularity';
  static const FIELD_POST_PATH = 'post_path';
  static const FIELD_ORIGINAL_LANGUAGE = 'original_language';
  static const FIELD_ORIGINAL_TITLE = 'original_title';
  static const FIELD_BACKDROP_PATH = 'backdrop_path';
  static const FIELD_IS_ADULT = 'is_adult';
  static const FIELD_OVERVIEW = 'overview';
  static const FIELD_RELEASE_DATE = 'release_date';

  DatabaseHelper _databaseHelper;

  FavoriteMoviesDAO(this._databaseHelper);

  insert({
    @required String title,
    @required bool isVideo,
    @required int votes,
    @required num votesAverage,
    @required num popularity,
    @required String posterPath,
    @required String originalLanguage,
    @required String originalTitle,
    @required String backdropPath,
    @required bool isAdult,
    @required String overview,
    @required String releaseDate,
    @required VoidCallback onSuccess,
    @required VoidCallback onFail,
  }) {
    Map<String, dynamic> values = Map();
    values[FIELD_TITLE] = title;
    values[FIELD_IS_VIDEO] = isVideo ? 1 : 0;
    values[FIELD_VOTES] = votes;
    values[FIELD_VOTES_AVERAGE] = votesAverage;
    values[FIELD_POPULARITY] = popularity;
    values[FIELD_POST_PATH] = posterPath;
    values[FIELD_ORIGINAL_LANGUAGE] = originalLanguage == null ? '' : originalLanguage;
    values[FIELD_ORIGINAL_TITLE] = originalTitle;
    values[FIELD_BACKDROP_PATH] = backdropPath;
    values[FIELD_IS_ADULT] = isAdult ? 1 : 0;
    values[FIELD_OVERVIEW] = overview;
    values[FIELD_RELEASE_DATE] = releaseDate;

    _databaseHelper.database().then(
          (db) => {
            db.transaction(
              (txn) async {
                int newId = await txn.insert(
                  TABLE_NAME,
                  values,
                  conflictAlgorithm: ConflictAlgorithm.replace,
                );

                if (newId > 0) {
                  onSuccess();
                } else {
                  onFail();
                }
              },
            )
          },
        );
  }

  remove({
    @required int movieId,
    @required VoidCallback onSuccess,
    @required VoidCallback onFail,
  }) {
    _databaseHelper.database().then(
          (db) => {
            db.transaction(
              (txn) async {
                int affectedRows = await txn.delete(
                  TABLE_NAME,
                  where: "$FIELD_ID=?",
                  whereArgs: [movieId],
                );

                if (affectedRows > 0) {
                  onSuccess();
                } else {
                  onFail();
                }
              },
            )
          },
        );
  }

  select(Function(List<Movie>) onResult) async {
    List<String> columns = [
      FIELD_ID,
      FIELD_VOTES_AVERAGE,
      FIELD_TITLE,
      FIELD_IS_VIDEO,
      FIELD_VOTES,
      FIELD_POPULARITY,
      FIELD_POST_PATH,
      FIELD_ORIGINAL_LANGUAGE,
      FIELD_ORIGINAL_TITLE,
      FIELD_BACKDROP_PATH,
      FIELD_IS_ADULT,
      FIELD_OVERVIEW,
      FIELD_RELEASE_DATE,
    ];

    Database db = await _databaseHelper.database();
    db.transaction(
      (txn) => txn.query(TABLE_NAME, columns: columns).then(
            (rows) => onResult(
              rows.map(
                (e) => Movie(
                  id: e[FIELD_ID],
                  votesAverage: e[FIELD_VOTES_AVERAGE],
                  title: e[FIELD_TITLE],
                  isVideo: e[FIELD_IS_VIDEO] == 1,
                  votes: e[FIELD_VOTES],
                  popularity: e[FIELD_POPULARITY],
                  postPath: e[FIELD_POST_PATH],
                  originalLanguage: e[FIELD_ORIGINAL_LANGUAGE],
                  originalTitle: e[FIELD_ORIGINAL_TITLE],
                  backdropPath: e[FIELD_BACKDROP_PATH],
                  isAdult: e[FIELD_IS_ADULT] == 1,
                  overview: e[FIELD_OVERVIEW],
                  releaseDate: e[FIELD_RELEASE_DATE],
                ),
              ).toList()
            ),
          ),
    );
  }
}
