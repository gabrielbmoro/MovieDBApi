import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

// ignore: must_be_immutable
class StarWidget extends StatelessWidget {
  Icon _firstStar;
  Icon _secondStar;
  Icon _thirdStar;
  Icon _fourthStar;
  Icon _fifthStar;

  static const AVERAGE_TOTAL = 10.0;
  static const STARS_AVAILABLE = 5.0;

  StarWidget(num votes) {
    final numberOfStars = (votes / AVERAGE_TOTAL) * STARS_AVAILABLE;
    _firstStar = Icon(
      _getAccordingToStarPosition(votes: numberOfStars, position: 1),
    );
    _secondStar = Icon(
      _getAccordingToStarPosition(votes: numberOfStars, position: 2),
    );
    _thirdStar = Icon(
      _getAccordingToStarPosition(votes: numberOfStars, position: 3),
    );
    _fourthStar = Icon(
      _getAccordingToStarPosition(votes: numberOfStars, position: 4),
    );
    _fifthStar = Icon(
      _getAccordingToStarPosition(votes: numberOfStars, position: 5),
    );
  }

  IconData _getAccordingToStarPosition({double votes, int position}) {
    if (votes >= position) {
      return Icons.star;
    } else {
      if (votes.round() == position) {
        return Icons.star_half;
      } else {
        return Icons.star_border;
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return FittedBox(
      child: Row(
          children: [
            _firstStar,
            _secondStar,
            _thirdStar,
            _fourthStar,
            _fifthStar,
          ],
      ),
    );
  }
}
