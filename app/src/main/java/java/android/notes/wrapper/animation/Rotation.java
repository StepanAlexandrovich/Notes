package java.android.notes.wrapper.animation;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;

// пригодится ещё
public class Rotation extends RotateAnimation {
    public Rotation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        super(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);

        setDuration(36000);

        //setFillAfter(true);
        setInterpolator(new DecelerateInterpolator());
        setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
