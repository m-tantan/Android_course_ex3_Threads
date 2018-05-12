package res.threads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
        asyncThread = new myAsyncThread(AsyncActivity.this, btnCreate, btnStart, btnDelete, textView);
    }
    public void AsyncStart(View view)
    {

    }
    public void AsyncDelete(View view)
    {

    }
}
