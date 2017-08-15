package shoaibhassan.sabzi_wala;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class VIEW extends Activity {

    EditText editText;
    String Name;
    Context ctx = this;
    String NAME = null, CATEGORY = null, PRICE = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        editText = (EditText) findViewById(R.id.editTextId);

    }

    public void register_register(View v) {
        Name = editText.getText().toString();
        VIEW.BackGround b = new VIEW.BackGround();
        b.execute(Name);
    }

    class BackGround extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL(" http://10.0.2.2/Sabzi_wala/show.php");
                String urlParams = "name=" + name;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }


        @Override
        protected void onPostExecute(String s) {
            String err = null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                NAME = user_data.getString("name");
                CATEGORY = user_data.getString("category");
                PRICE = user_data.getString("price");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }
            Toast.makeText(ctx, NAME, Toast.LENGTH_SHORT).show();
            if (NAME != null) {
                Intent i = new Intent(ctx, ViewData.class);
                i.putExtra("name", NAME);
                i.putExtra("category", CATEGORY);
                i.putExtra("price", PRICE);
                i.putExtra("err", err);
                startActivity(i);
            } else {
                Toast.makeText(ctx, "NAME NOT FOUND", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
