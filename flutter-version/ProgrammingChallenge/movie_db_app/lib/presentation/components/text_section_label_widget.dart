import 'package:flutter/cupertino.dart';

// ignore: must_be_immutable
class TextSectionLabel extends StatelessWidget {
  String _title;

  TextSectionLabel(this._title);

  @override
  Widget build(BuildContext context) {
    return Text(
      _title,
      textAlign:TextAlign.start,
      style: TextStyle(
        fontSize: 22,
        fontWeight: FontWeight.bold,
      ),
    );
  }
}
