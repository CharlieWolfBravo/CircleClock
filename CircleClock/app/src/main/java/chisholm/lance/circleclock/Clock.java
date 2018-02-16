package chisholm.lance.circleclock;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import java.util.Calendar;


public class Clock extends AppCompatActivity {
    private class CanvasView extends View {


        public CanvasView(Context context){
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas){
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;
            Calendar currentTime = Calendar.getInstance();
            float hour = currentTime.get(Calendar.HOUR_OF_DAY);
            float minute = currentTime.get(Calendar.MINUTE);
            float seconds = currentTime.get(Calendar.SECOND);
            Paint paint = new Paint();
            paint.setStrokeWidth(1.0f);

            //start drawing Back faces.
            //Second Hand
            float secondsX = 1.45f*(width/2);
            float secondsY = 1.15f*(height/2);
            //draw big circle
            paint.setColor(Color.DKGRAY);
            canvas.drawCircle(secondsX,secondsY,200,paint);
            //Three Quarter circle
            paint.setColor(Color.GRAY);
            canvas.drawCircle(secondsX,secondsY,(0.75f*200.0f),paint);
            //draw half circle
            paint.setColor(Color.LTGRAY);
            canvas.drawCircle(secondsX,secondsY,(0.5f*200.0f),paint);
            //Quarter Circle
            paint.setColor(0xffeeeeee);
            canvas.drawCircle(secondsX,secondsY,(0.25f*200.0f),paint);
            //draw current seconds
            paint.setColor(Color.RED);
            float radiusSec = (seconds/60.0f)*200.0f;
            //float radius = 100.0f;
            canvas.drawCircle(secondsX,secondsY,radiusSec,paint);
            Log.d("Dev", "Seconds = " + seconds);

            //minutes
            float minuteX = (width/2);
            float minuteY = 0.5f*(height/2);
            float fullMinuteRadius = 400.0f;
            //draw big circle
            paint.setColor(Color.DKGRAY);
            canvas.drawCircle(minuteX,minuteY,fullMinuteRadius,paint);
            //Three Quarter circle
            paint.setColor(Color.GRAY);
            canvas.drawCircle(minuteX,minuteY,(0.75f*fullMinuteRadius),paint);
            //draw half circle
            paint.setColor(Color.LTGRAY);
            canvas.drawCircle(minuteX,minuteY,(0.5f*fullMinuteRadius),paint);
            //Quarter Circle
            paint.setColor(0xffeeeeee);
            canvas.drawCircle(minuteX,minuteY,(0.25f*fullMinuteRadius),paint);
            //draw current seconds
            paint.setColor(0xffdb651c);
            float radiusMin = (minute/60.0f)*fullMinuteRadius;
            //float radius = 100.0f;
            canvas.drawCircle(minuteX,minuteY,radiusMin,paint);
            Log.d("Dev", "Minute = " + minute);

            //hours
            float hourX = 0.45f*(width/2);
            float hourY = 1.15f*(height/2);
            float fullHourRadius = 150.0f;
            //draw big circle
            paint.setColor(Color.DKGRAY);
            canvas.drawCircle(hourX,hourY,fullHourRadius,paint);
            //Three Quarter circle
            paint.setColor(Color.GRAY);
            canvas.drawCircle(hourX,hourY,(0.75f*fullHourRadius),paint);
            //draw half circle
            paint.setColor(Color.LTGRAY);
            canvas.drawCircle(hourX,hourY,(0.5f*fullHourRadius),paint);
            //Quarter Circle
            paint.setColor(0xffeeeeee);
            canvas.drawCircle(hourX,hourY,(0.25f*fullHourRadius),paint);
            //draw current seconds
            paint.setColor(0xff5d9ccc);
            float radiusHour;
            if(hour >= 12.0f){
                radiusHour = (hour/12.0f)*fullHourRadius;
            }else {
                radiusHour = ((hour%12.0f)/12.0f)*fullHourRadius;
            }
            //float radius = 100.0f;
            canvas.drawCircle(hourX,hourY,radiusHour,paint);
            Log.d("Dev", "Hour = " + hour);

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
