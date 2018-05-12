package res.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class myAsyncThread extends AsyncTask<Void, Integer, String>
{
    Button btnCreate, btnStart, btnDelete;
    TextView textView;

    public myAsyncThread(Context context, Button btnCreate, Button btnStart, Button btnDelete, TextView textView)
    {
        this.btnCreate = btnCreate;
        this.btnStart = btnStart;
        this.btnDelete = btnDelete;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        return null;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
    }
}
