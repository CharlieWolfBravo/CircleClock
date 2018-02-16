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
import android.widget.TextView;

import java.util.Calendar;


public class Clock extends AppCompatActivity {
    private class CanvasView extends View {


        public CanvasView(Context context){
            super(context);
        }

        //after doing all this I realize I could probable write each of the seconds, minutes, and hours sections into their own sections,
        // but it's working so I'll leave it.
        @Override
        protected void onDraw(Canvas canvas){
            //get the size of the screen so we can hopefully run this on any device.
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;
            Calendar currentTime = Calendar.getInstance();
            float hour = currentTime.get(Calendar.HOUR_OF_DAY);
            float minute = currentTime.get(Calendar.MINUTE);
            float seconds = currentTime.get(Calendar.SECOND);
            Paint paint = new Paint(); //could have set multiple paints for various purposes, wanted to save on space.
            paint.setStrokeWidth(1.0f);
            paint.setTextSize(35.0f);

            //start by drawing Back faces.
            //Set transparency of current indicator and draw it.
            //Then indicate to user what the circle represents.

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
            paint.setColor(0xffe83232);
            float radiusSec = (seconds/60.0f)*200.0f;
            //make the color trasparent and draw current seconds
            //transparency is to help user be more accuratly determine the value being represented.
            paint.setAlpha(127);
            canvas.drawCircle(secondsX,secondsY,radiusSec,paint);
            paint.setAlpha(255);
            Log.d("Dev", "Seconds = " + seconds);
            paint.setColor(Color.BLACK);
            canvas.drawText("S",0,1,secondsX-15,secondsY+15,paint);

            /*
            *I decided to try and pick complementing colors for minutes and seconds as well as for AM and PM for hours.
            * I think it turned out a little "christmas-y" but it looks okay so I'm happy with it.
             */

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
            //find curent minutes. Set green color.
            paint.setColor(0xff7ee833);
            float radiusMin = (minute/60.0f)*fullMinuteRadius;
            //Draw current minutes and set transparency
            paint.setAlpha(127);
            canvas.drawCircle(minuteX,minuteY,radiusMin,paint);
            paint.setAlpha(255);
            Log.d("Dev", "Minute = " + minute);
            paint.setColor(Color.BLACK);
            canvas.drawText("M",0,1,minuteX-15,minuteY+15,paint);

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
            //draw current hour and set transparency

            paint.setAlpha(127);
            float radiusHour;
            if(hour <= 12.0f){//AM
                paint.setColor(0xff5d9ccc);
                radiusHour = (hour/12.0f)*fullHourRadius;
            }else {//PM
                paint.setColor(0xfff4b642);
                radiusHour = ((hour%12.0f)/12.0f)*fullHourRadius;
            }
            //float radius = 100.0f;
            canvas.drawCircle(hourX,hourY,radiusHour,paint);
            paint.setAlpha(255);
            Log.d("Dev", "Hour = " + hour);
            paint.setColor(Color.BLACK);
            canvas.drawText("H",0,1,hourX-15,hourY+15,paint);

            //indicate which color makes Hour AM and PM
            paint.setTextSize(25.0f);
            paint.setColor(0xff5d9ccc);
            canvas.drawRect(25,25,50,50,paint);
            paint.setColor(Color.BLACK);
            canvas.drawText("AM",0,2,75.0f,37.5f,paint);

            paint.setColor(0xfff4b642);
            canvas.drawRect(25,75,50,100,paint);
            paint.setColor(Color.BLACK);
            canvas.drawText("PM",0,2,75.0f,87.5f,paint);


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
