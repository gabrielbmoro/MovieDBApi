import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:movie_db_app/domain/model/page.dart';

const String BASE_URL = 'api.themoviedb.org';
const String TOKEN = '755e0c67ac2fa886e775fb9057f0a32f';
const String BASE_IMAGE_ADDRESS = 'https://image.tmdb.org/t/p/w780';
const String PARAMETER_POPULAR_MOVIES = 'popularity.desc';
const String PARAMETER_TOP_RATED_MOVIES = 'vote_average.desc';

class ApiRepositoryImpl {
  Future<Page> getPopularMovies({int pageNumber}) async {
    var queryParameters = {
      'api_key': TOKEN,
      'sort_by': PARAMETER_POPULAR_MOVIES,
      'page': pageNumber.toString()
    };
    var uri = Uri.https(BASE_URL, '/3/discover/movie', queryParameters);
    final response = await http.get(uri);

    if (response.statusCode == 200) {
      return Page.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to get movies');
    }
  }

  Future<Page> getTopRatedMovies({int pageNumber}) async {
    var queryParameters = {
      'api_key': TOKEN,
      'sort_by': PARAMETER_TOP_RATED_MOVIES,
      'page': pageNumber.toString()
    };
    var uri = Uri.https(BASE_URL, '/3/discover/movie', queryParameters);
    final response = await http.get(uri);

    if (response.statusCode == 200) {
      return Page.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to get movies');
    }
  }
}
