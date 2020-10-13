import 'package:flutter/material.dart';
import 'file:///C:/Users/gabri/Documents/svn/personal/MovieDBApi/flutter-version/ProgrammingChallenge/movie_db_app/lib/presentation/screens/home_page.dart';
import 'package:movie_db_app/presentation/colors.dart';

const String BASE_URL = 'https://api.themoviedb.org/3/';
const String TOKEN = '755e0c67ac2fa886e775fb9057f0a32f';
const String DATA_BASE_NAME = 'MovieDBAppDataBase';
const String BASE_IMAGE_ADDRESS = 'https://image.tmdb.org/t/p/w780';
const String PARAMETER_POPULAR_MOVIES = 'popularity.desc';
const String PARAMETER_TOP_RATED_MOVIES = 'vote_average.desc';

void main() => runApp(MovieDBApi());

class MovieDBApi extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
        primaryColor: colorPrimary,
        primaryColorDark: colorPrimaryDark,
        // This makes the visual density adapt to the platform that you run
        // the app on. For desktop platforms, the controls will be smaller and
        // closer together (more dense) than on mobile platforms.
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: HomePage(),
    );
  }
}
