package com.gabrielbmoro.programmingchallenge.presentation.components.loader

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.gabrielbmoro.programmingchallenge.R
import kotlin.math.roundToInt

class BubbleLoader @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val animatorSet = AnimatorSet()

    init {
        orientation = HORIZONTAL

        val animatorList = ArrayList<Animator>()

        with(context.obtainStyledAttributes(attrs, R.styleable.BubbleLoader)) {
            val dots = getInt(R.styleable.BubbleLoader_amountOfDots, DEFAULT_DOTS)
            val dotSize = getDimensionPixelSize(R.styleable.BubbleLoader_dotSize, 0)

            repeat(dots) { count ->
                val dotView = createDot(
                        color = getColor(
                                R.styleable.BubbleLoader_dotsColor,
                                Color.BLACK
                        )
                )
                val layoutSize = dotSize + (dotSize * DOT_EXPANDABLE_PROPORTION).roundToInt()
                addView(
                        dotView,
                        LayoutParams(
                                layoutSize,
                                layoutSize
                        )
                )
                animatorList.add(
                        getAnimator(
                                dotView
                        ).also { anim ->
                            anim.startDelay = ((count / dots.toFloat()) * DURATION).toLong()
                        }
                )
            }
            recycle()
        }

        animatorSet.playTogether(
                animatorList.toList()
        )
    }

    fun start() {
        animatorSet.start()
        visibility = View.VISIBLE
    }

    fun stop() {
        animatorSet.end()
        visibility = View.GONE
    }

    fun isRunning() = animatorSet.isRunning


    private fun getAnimator(view: View): Animator {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, DOT_EXPANDABLE_PROPORTION)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, DOT_EXPANDABLE_PROPORTION)
        return ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY).apply {
            repeatCount = INFINITE
            repeatMode = ObjectAnimator.REVERSE
            duration = DURATION
        }
    }

    private fun createDot(@ColorInt color: Int): View {
        return DotView(
                context = context,
                color = color
        )
    }

    companion object {
        private const val DOT_EXPANDABLE_PROPORTION = 1.8f
        private const val DURATION = 600L
        private const val INFINITE = -1
        private const val DEFAULT_DOTS = 3
    }
}