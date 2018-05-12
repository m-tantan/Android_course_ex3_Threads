package res.threads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void switchToHandler(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ThreadsActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }

    public void switchToAsync(View v)
    {
        Intent intent = new Intent(getApplicationContext(), AsyncActivity.class);
        startActivityForResult(intent, 0);
        finish();
    }
}
