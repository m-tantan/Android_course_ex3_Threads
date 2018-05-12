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
    public static final int UPDATE_COUNTER = 1;
    public static final int SLEEP = 2;
    public static final int PRINT_DONE = 3;
    long TIME_TO_WAIT = 10000; // 10 seconds
    ImageButton btnStart, btnCreate, btnDelete;
    TextView tvWindow;
    boolean tRunning = false;
    long currentTime;
    Thread mThread;
    long endTime;
    Handler mHandler;
    int seconds = 0, nextNum;

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
                int what = msg.arg1;
                switch (what)
                {
                    case SLEEP:
                        tvWindow.setText("");
                        break;
                    case UPDATE_COUNTER:
                        seconds++;
                        tvWindow.setText(String.valueOf(seconds));
                        break;
                    case PRINT_DONE:
                        tvWindow.setText("DONE");
                        break;
                    default:
                        break;
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
                if (currentTime >= endTime)
                {
                    tRunning = false;
                    break;
                }
                Log.e("Debug", "one second passed");
                try
                {
                    // Updates timer
                    Thread.sleep(500);
                    Message msg = new Message();
                    msg.arg1 = UPDATE_COUNTER;
                    mHandler.sendMessage(msg);
                    // Clears TextView
                    Thread.sleep(500);
                    Message msg2 = new Message();
                    msg2.arg1 = SLEEP;
                    mHandler.sendMessage(msg2);
                } catch (InterruptedException d)
                {
                    d.printStackTrace();
                }
            }
            Message msg3 = new Message();
            msg3.arg1 = PRINT_DONE;
            mHandler.sendMessage(msg3);
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
        if (!tRunning)
        {
            tRunning = true;
            mThread.start();
        }
        else
        {
            Log.e("Debug", "Started already running");

        }
    }

    public void terminateThread(View v)
    {
        tRunning = false;
        tvWindow.setText("Killed thread");
        seconds = 0;
        Log.e("Debug", "thread terminated");

    }


}
