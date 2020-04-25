package com.gabrielbmoro.programmingchallenge.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.gabrielbmoro.programmingchallenge.R
import kotlinx.android.synthetic.main.component_five_stars.view.*
import kotlin.math.roundToInt

class FiveStarsComponent @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.component_five_stars, this, true)
        with(context.obtainStyledAttributes(attrs, R.styleable.FiveStarsComponent)) {
            val votes = getFloat(R.styleable.FiveStarsComponent_votes, INVALID_NUMBER)
            setVotesAvg(votes)
            recycle()
        }
    }

    @DrawableRes
    private fun getDrawableAccordingToStarPosition(votes: Float, position: Int): Int {
        return when {
            votes >= position -> R.drawable.ic_star
            votes < position -> {
                if (votes.roundToInt() == position)
                    R.drawable.ic_star_half
                else
                    R.drawable.ic_star_border
            }
            else -> R.drawable.ic_star_border
        }
    }

    fun setVotesAvg(votes: Float) {
        val numberOfStarts = (votes / AVERAGE_TOTAL) * STARS_AVAILABLE
        if (votes != INVALID_NUMBER) {
            ivFirstStar.background = ContextCompat.getDrawable(context, getDrawableAccordingToStarPosition(numberOfStarts, 1))
            ivSecondStar.background = ContextCompat.getDrawable(context, getDrawableAccordingToStarPosition(numberOfStarts, 2))
            ivThirdStar.background = ContextCompat.getDrawable(context, getDrawableAccordingToStarPosition(numberOfStarts, 3))
            ivFourthStar.background = ContextCompat.getDrawable(context, getDrawableAccordingToStarPosition(numberOfStarts, 4))
            ivFifthStar.background = ContextCompat.getDrawable(context, getDrawableAccordingToStarPosition(numberOfStarts, 5))
        }
    }

    companion object {
        private const val STARS_AVAILABLE = 5
        private const val AVERAGE_TOTAL = 10
        private const val INVALID_NUMBER = -1f
    }

}