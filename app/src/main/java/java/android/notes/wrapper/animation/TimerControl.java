package java.android.notes.wrapper.animation;

import android.view.View;
import java.android.notes.listeners.INotesClickListener;

public class TimerControl {
    private Timer timer;

    public void start(View view, int adapterPosition, INotesClickListener listener){
        if(timer == null){
            timer = new Timer(2000,10);
            timer.setParams(view,listener,adapterPosition);
            timer.start();
        }else{
            if(timer.isFinish()){
                timer.setParams(view,listener,adapterPosition);
                timer.reset();
                timer.start();
            }else{
                timer.changeDirection();
            }

        }
    }


}
