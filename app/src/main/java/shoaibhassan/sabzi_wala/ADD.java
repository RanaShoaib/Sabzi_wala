package shoaibhassan.sabzi_wala;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ADD extends Activity {

    EditText name, category, price;
    String Name, Category, Price;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        name = (EditText) findViewById(R.id.name);
        category = (EditText) findViewById(R.id.category);
        price = (EditText) findViewById(R.id.price);
    }

    public void register_register(View v) {
        Name = name.getText().toString();
        Category = category.getText().toString();
        Price = price.getText().toString();


        ADD.BackGround b = new ADD.BackGround();
        b.execute(Name, Category, Price);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String category = params[1];
            String price = params[2];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://10.0.2.2/Sabzi_wala/add.php");
                String urlParams = "name=" + name + "&category=" + category + "&price=" + price;

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
            if (s.equals("")) {
                s = "Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }

}
