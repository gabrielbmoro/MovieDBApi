import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:movie_db_app/domain/model/page.dart';

import '../../main.dart';

class ApiRepositoryImpl {
  Future<Page> getMovies({String apiKey, String sortBy, int pageNumber}) async {
    var queryParameters = {
      'api_key': apiKey,
      'sort_by': sortBy,
      'page': pageNumber
    };
    var uri = Uri.https(BASE_URL, 'discover/movie', queryParameters);
    final response = await http.get(uri);

    if (response.statusCode == 200) {
      return Page.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to get movies');
    }
  }
}
