import 'package:flutter/material.dart';

// ignore: must_be_immutable
class FavoriteButton extends StatelessWidget {
  bool isFilled;

  FavoriteButton(this.isFilled);

  @override
  Widget build(BuildContext context) {
    return Icon(
      isFilled ? Icons.favorite : Icons.favorite_border_outlined,
      size: 40.0,
      color: Colors.red,
    );
  }
}
