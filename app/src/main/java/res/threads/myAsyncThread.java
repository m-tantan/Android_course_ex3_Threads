package res.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageButton;
import android.widget.TextView;

public class myAsyncThread extends AsyncTask<Void, Integer, String>
{
    ImageButton btnCreate, btnStart, btnDelete;
    TextView textView;
    Context context;
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
    {   int counter = 0;
        synchronized (this)
        {
            while (counter < 10)
            {
                try
                {
                    wait(1000);
                    counter++;
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return "DONE";
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s)
    {
        textView.setText(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
        int progress = values[0];
        textView.setText(progress);


    }
}
