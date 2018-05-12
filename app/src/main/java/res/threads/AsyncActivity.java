package res.threads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.support.constraint.Constraints.TAG;

public class AsyncActivity extends AppCompatActivity
{
    ImageButton btnCreate, btnStart, btnDelete;
    TextView textView;
    myAsyncThread asyncThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        btnCreate = findViewById(R.id.ivAcreate);
        btnDelete = findViewById(R.id.ivAdelete);
        btnStart = findViewById(R.id.ivAstart);
        textView = findViewById(R.id.txtATimer);
    }

    public void AsyncCreate(View view)
    {
        Log.e(TAG, "Creating AsyncThread");
        asyncThread = new myAsyncThread(AsyncActivity.this, btnCreate, btnStart, btnDelete, textView);
    }
    public void AsyncStart(View view)
    {
        Log.e(TAG, "Starting AsyncThread");
        asyncThread.execute();
    }
    public void AsyncDelete(View view)
    {
        asyncThread.terminate();
    }
}
