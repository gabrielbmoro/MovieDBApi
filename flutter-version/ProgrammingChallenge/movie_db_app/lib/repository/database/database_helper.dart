import 'dart:async';

import 'package:movie_db_app/repository/database/favorite_movies_dao.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

class DatabaseHelper {
  static const DATABASE_NAME = 'movie_app_database.db';
  static const DATABASE_VERSION = 1;

  static final DatabaseHelper _singleton = DatabaseHelper._internal();

  Future<Database> _database;

  factory DatabaseHelper() {
    return _singleton;
  }

  DatabaseHelper._internal() {
    _getDataBasePath().then(
      (databasePath) => {_database = _openDatabase(databasePath)},
    );
  }

  Future<String> _getDataBasePath() async {
    return join(await getDatabasesPath(), DATABASE_NAME);
  }

  Future<Database> _openDatabase(String databasePath) async {
    String databasePath = await _getDataBasePath();

    return openDatabase(
      // Set the path to the database. Note: Using the `join` function from the
      // `path` package is best practice to ensure the path is correctly
      // constructed for each platform.
      databasePath,
      // When the database is first created, create a table to store dogs.
      onCreate: (db, version) {
        // Run the CREATE TABLE statement on the database.
        return db.execute("CREATE TABLE ${FavoriteMoviesDAO.TABLE_NAME}("
            "${FavoriteMoviesDAO.FIELD_ID} INTEGER PRIMARY KEY, "
            "${FavoriteMoviesDAO.FIELD_VOTES_AVERAGE} REAL, "
            "${FavoriteMoviesDAO.FIELD_TITLE} TEXT, "
            "${FavoriteMoviesDAO.FIELD_IS_VIDEO} INTEGER, "
            "${FavoriteMoviesDAO.FIELD_VOTES} INTEGER, "
            "${FavoriteMoviesDAO.FIELD_POPULARITY} REAL, "
            "${FavoriteMoviesDAO.FIELD_POST_PATH} TEXT, "
            "${FavoriteMoviesDAO.FIELD_IS_ADULT} INTEGER, "
            "${FavoriteMoviesDAO.FIELD_OVERVIEW} TEXT, "
            "${FavoriteMoviesDAO.FIELD_RELEASE_DATE} TEXT ,"
            "${FavoriteMoviesDAO.FIELD_ORIGINAL_LANGUAGE} TEXT, "
            "${FavoriteMoviesDAO.FIELD_ORIGINAL_TITLE} TEXT, "
            "${FavoriteMoviesDAO.FIELD_BACKDROP_PATH} TEXT"
            ")");
      },
      // Set the version. This executes the onCreate function and provides a
      // path to perform database upgrades and downgrades.
      version: DATABASE_VERSION,
    );
  }

  Future<Database> database() {
    return _database;
  }
}
