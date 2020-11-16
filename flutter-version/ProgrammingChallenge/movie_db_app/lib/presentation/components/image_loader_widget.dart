import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:movie_db_app/presentation/common/assets.dart';

// ignore: must_be_immutable
class ImageLoader extends StatelessWidget {
  String _imageAddress;
  double _height;

  ImageLoader(this._imageAddress, this._height);

  @override
  Widget build(BuildContext context) {
    print("${this._imageAddress} - ${this._height}");
    return FadeInImage.assetNetwork(
      width: double.infinity,
      height: _height,
      placeholder: IMAGE_LOADER,
      fit: BoxFit.fitWidth,
      image: _imageAddress,
    );
  }
}
