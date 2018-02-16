package chisholm.lance.circleclock;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Calendar;


public class Clock extends AppCompatActivity {
    private class CanvasView extends View {
        int height = Resources.getSystem().getDisplayMetrics().widthPixels;
        int width = Resources.getSystem().getDisplayMetrics().heightPixels;


        public CanvasView(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            Calendar currentTime = Calendar.getInstance();
            float hour = currentTime.get(Calendar.HOUR_OF_DAY);
            float minute = currentTime.get(Calendar.MINUTE);
            float seconds = currentTime.get(Calendar.SECOND);
            Paint paint = new Paint();

            paint.setStrokeWidth(1.0f);
            //draw big circle
            paint.setColor(Color.GRAY);
            canvas.drawCircle(300,300,200,paint);
            //draw current seconds
            paint.setColor(Color.RED);
            float radius = (seconds/60.0f)*200.0f;
            //float radius = 100.0f;
            canvas.drawCircle(300,300,radius,paint);
            Log.d("Dev", "Seconds = " + seconds);
            canvas.drawRect(30,30,80,80,paint);

            invalidate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        //need a CanvasView object.
        CanvasView cv = new CanvasView(this);

        setContentView(cv);
    }
}
