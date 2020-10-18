import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:movie_db_app/domain/model/page.dart';
import 'package:movie_db_app/repository/api/mapper_json_to_objects.dart';

const String BASE_URL = 'api.themoviedb.org';
const String TOKEN = '755e0c67ac2fa886e775fb9057f0a32f';
const String BASE_IMAGE_ADDRESS = 'https://image.tmdb.org/t/p/w780';
const String PARAMETER_POPULAR_MOVIES = 'popularity.desc';
const String PARAMETER_TOP_RATED_MOVIES = 'vote_average.desc';

class ApiRepositoryImpl {
  PageMapper _mapper;

  ApiRepositoryImpl(PageMapper mapper) {
    _mapper = mapper;
  }

  Future<http.Response> _getMovie({int pageNumber, String sortBy}) {
    var queryParameters = {
      'api_key': TOKEN,
      'sort_by': sortBy,
      'page': pageNumber.toString()
    };
    var uri = Uri.https(BASE_URL, '/3/discover/movie', queryParameters);
    return http.get(uri);
  }

  Future<Page> getPopularMovies({int pageNumber}) async {
    final response = await _getMovie(
      pageNumber: pageNumber,
      sortBy: PARAMETER_POPULAR_MOVIES,
    );

    if (response.statusCode == 200) {
      String jsonBody = response.body;
      Map<String, dynamic> bodyMapped = jsonDecode(jsonBody);
      return _mapper.fromJson(bodyMapped);
    } else {
      throw Exception('Failed to get movies');
    }
  }

  Future<Page> getTopRatedMovies({int pageNumber}) async {
    final response = await _getMovie(
      pageNumber: pageNumber,
      sortBy: PARAMETER_TOP_RATED_MOVIES,
    );

    if (response.statusCode == 200) {
      return _mapper.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to get movies');
    }
  }
}
