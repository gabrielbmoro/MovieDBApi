import 'package:flutter/cupertino.dart';

// ignore: must_be_immutable
class TextSectionTitleWidget extends StatelessWidget {
  String _title;

  TextSectionTitleWidget(this._title);

  @override
  Widget build(BuildContext context) {
    return Text(
      _title,
      textAlign:TextAlign.start,
      style: TextStyle(
        fontSize: 18,
      ),
    );
  }
}
