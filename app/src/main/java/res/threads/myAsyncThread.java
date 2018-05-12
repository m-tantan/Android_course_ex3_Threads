package res.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.support.constraint.Constraints.TAG;

public class myAsyncThread extends AsyncTask<Void, Integer, String>
{
    public static final int SLEEP = -1;
    ImageButton btnCreate, btnStart, btnDelete;
    TextView textView;
    Context context;


    private boolean isAlive = false;

    public myAsyncThread(Context context, ImageButton btnCreate, ImageButton btnStart, ImageButton btnDelete, TextView textView)
    {

        this.context = context;
        this.btnCreate = btnCreate;
        this.btnStart = btnStart;
        this.btnDelete = btnDelete;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        int counter = 0;
        synchronized (this)
        {
            while (counter < 10 && isAlive)
            {
                try
                {
                    wait(500);
                    counter++;
                    publishProgress(counter);
                    wait(500);
                    publishProgress(-1);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        terminate();
        return "DONE";
    }

    @Override
    protected void onPreExecute()
    {
        isAlive = true;
    }

    @Override
    protected void onPostExecute(String s)
    {
        textView.setText(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        int progress = values[0];
        if (progress == SLEEP)
        {
            textView.setText("");
        }
        else
        {

            textView.setText(String.valueOf(progress));
        }
    }

    protected boolean terminate()
    {

        Log.e(TAG, "terminated Async Thread");

        if (isAlive)
        {
            isAlive = false;
            return true;
        }
        return false;
    }
}
