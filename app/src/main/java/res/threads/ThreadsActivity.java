package res.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ThreadsActivity extends AppCompatActivity
{
    long TIME_TO_WAIT = 10000; // 10 seconds
    ImageButton btnStart, btnCreate, btnDelete;
    TextView tvWindow;
    boolean tRunning = false;
    long currentTime;
    Thread mThread;
    long endTime;
    Handler mHandler;
    int seconds = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);
        btnStart = findViewById(R.id.ivTstart);
        btnCreate = findViewById(R.id.ivTcreate);
        btnDelete = findViewById(R.id.ivTdelete);
        tvWindow = findViewById(R.id.txtTTimer);

        mHandler = new Handler(getMainLooper())
        {
            @Override
            public void handleMessage(Message msg)
            {
                if (seconds < 10)
                {
                    tvWindow.setText(String.valueOf(seconds));
                    seconds++;
                }
                else
                {
                    tvWindow.setText("DONE");
                }
            }
        };
    }


    Runnable r = new Runnable()
    {
        @Override
        public void run()
        {

            endTime = System.currentTimeMillis() + TIME_TO_WAIT;
            while (tRunning)
            {
                currentTime = System.currentTimeMillis();
                if (currentTime > endTime)
                {
                    tRunning = false;
                }
                Log.e("Debug", "one second passed");
                try
                {
                    Thread.sleep(1000);
                    mHandler.sendEmptyMessage(0);
                } catch (InterruptedException d)
                {
                    d.printStackTrace();
                }
            }
            if (seconds == 10)
            {
                mHandler.sendEmptyMessage(0);
            }
            Log.e("Debug", "Thread finished");
        }
    };


    public void createThread(View v)
    {
        mThread = new Thread(r);

        Log.e("Debug", "Created Thread");
    }

    public void startThread(View v)
    {
        Log.e("Debug", "Started Thread");
        seconds = 0;
        tRunning = true;
        mThread.start();
    }

    public void terminateThread(View v)
    {
        tRunning = false;
        tvWindow.setText("Killed thread");
        seconds = 0;
        Log.e("Debug", "thread terminated");

    }


}
