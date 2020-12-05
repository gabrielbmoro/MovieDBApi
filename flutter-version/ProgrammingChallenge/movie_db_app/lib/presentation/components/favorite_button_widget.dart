import 'package:flutter/material.dart';

// ignore: must_be_immutable
class FavoriteButtonWidget extends StatelessWidget {
  bool isFilled;
  VoidCallback onPressed;

  FavoriteButtonWidget(this.isFilled, this.onPressed);

  @override
  Widget build(BuildContext context) {
    return FlatButton(
      child: Icon(
        isFilled ? Icons.favorite : Icons.favorite_border_outlined,
        size: 40.0,
        color: Colors.red,
      ),
      onPressed: onPressed,
    );
  }
}
