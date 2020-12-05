import 'package:flutter/cupertino.dart';

// ignore: must_be_immutable
class TextSectionLabelWidget extends StatelessWidget {
  String _title;

  TextSectionLabelWidget(this._title);

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
