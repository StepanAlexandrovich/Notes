package java.android.notes.wrapper.animation

import android.view.animation.RotateAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator

// пригодится ещё
class Rotation(
    fromDegrees: Float,
    toDegrees: Float,
    pivotXType: Int,
    pivotXValue: Float,
    pivotYType: Int,
    pivotYValue: Float
) : RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue) {
    init {
        duration = 36000

        //setFillAfter(true);
        interpolator = DecelerateInterpolator()
        setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
        })
    }
}