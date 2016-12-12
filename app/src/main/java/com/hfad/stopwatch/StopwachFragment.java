package com.hfad.stopwatch;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchActivity extends Activity {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_stopwatch);
        runTimer();
        if(savedInstance!=null)
        {
            seconds=savedInstance.getInt("seconds");
            running=savedInstance.getBoolean("running");
            wasRunning=savedInstance.getBoolean("wasRunning");


        }
        runTimer();

    }
    @Override
    protected void onPause()
    {
        super.onPause();
        wasRunning=running;
        running=false;

    }
    @Override
    protected void onResume()
    {
        super.onResume();
        if(wasRunning)
            running=true;

    }

    public void onSaveInstanceState(Bundle savedInstance)
    {
        savedInstance.putInt("seconds", seconds);
        savedInstance.putBoolean("running", running);
        savedInstance.putBoolean("wasRunning", wasRunning);

    }

    //Start the stopwatch running when the Start button is clicked
    public void onClickStart(View view) {

        running = true;

    }

    public void onClickStop(View view) {
        running = false;

    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;

    }
    @Override
    protected void onStop(){
        super.onStop();
        wasRunning=running;
        running=false;
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(wasRunning)
        {
            running=true;

        }
    }

    private void runTimer(){
        final TextView timeView=(TextView)findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable(){

            @Override
            public void run(){
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time=String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running)
                {
                    ++seconds;

                }
                handler.postDelayed(this,1000);

            }

        });


    }
}
