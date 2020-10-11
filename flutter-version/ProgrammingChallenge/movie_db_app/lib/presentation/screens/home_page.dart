import 'package:flutter/material.dart';
import 'package:movie_db_app/presentation/components/bottom_navigation_bar.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  int _selectedIndex = 0;

  void _onTapItem(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Text("Eita"),
      bottomNavigationBar: BottomNavigationBarBuilder.build(
        onTap: _onTapItem,
        currentIndex: _selectedIndex,
      ),
    );
  }
}
