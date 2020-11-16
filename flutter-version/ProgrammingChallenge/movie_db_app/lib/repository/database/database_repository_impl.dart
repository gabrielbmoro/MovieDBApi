import 'package:movie_db_app/repository/database/database_helper.dart';
import 'package:movie_db_app/repository/database/favorite_movies_dao.dart';

class DatabaseRepositoryImpl {
  FavoriteMoviesDAO favoriteMoviesDAO = FavoriteMoviesDAO(
    new DatabaseHelper(),
  );
}
