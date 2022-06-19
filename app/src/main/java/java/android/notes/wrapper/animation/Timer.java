package java.android.notes.wrapper.animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import java.android.notes.R;
import java.android.notes.listeners.INotesClickListener;

public class Timer extends CountDownTimer {
    private View view;
    private INotesClickListener listener;
    private int index;

    // drawing
    private int i = 0,direction = 1;
    private Spiral spiral = new Spiral();
    //
    private boolean finish = false;

    public Timer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    // set
    public void setParams(View view, INotesClickListener listener, int index){
        this.view = view;
        this.index = index;
        this.listener = listener;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void changeDirection() {
        if(direction == 1){ direction = -1; }
        else              { direction = +1; }
    }

    public void reset(){
        i = 0;
        direction = 1;
    }

    // get
    public boolean isFinish() { return finish; }

    @Override
    public void onTick(long l) {
        finish = false;

        ImageView imageView=(ImageView) view;
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        i+=direction;

        spiral.reset();
        for(int a = 0;a<i;a++) {
            spiral.process();
            paint.setColor(Color.RED);
            canvas.drawCircle(50+(float)spiral.getX(),50+(float)spiral.getY(),15,paint);
        }

        imageView.setImageBitmap(bitmap);

        if(i == 70){
            listener.removeNote(index);
        }

    }

    @Override
    public void onFinish() {
        ((ImageView)view).setImageResource(R.drawable.delete);
        finish = true;
    }

}
