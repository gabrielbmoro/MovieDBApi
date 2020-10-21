import 'package:flutter/material.dart';
import 'package:movie_db_app/presentation/common/assets.dart';

// ignore: must_be_immutable
class ImageLoader extends StatelessWidget {
  String _imageAddress;

  ImageLoader(this._imageAddress);

  @override
  Widget build(BuildContext context) {
    return FadeInImage.assetNetwork(
      placeholder: IMAGE_LOADER,
      image: _imageAddress,
    );
  }
}
