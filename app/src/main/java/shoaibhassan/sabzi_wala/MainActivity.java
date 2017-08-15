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

public class MainActivity extends Activity {

    EditText email, password;
    String Email, Password;
    Context ctx = this;
    String EMAIL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.main_email);
        password = (EditText) findViewById(R.id.main_password);

    }

    public void main_register(View v) {
        startActivity(new Intent(this, RegisterAdmin.class));
    }

    public void main_login(View v) {
        Email = email.getText().toString();
        Password = password.getText().toString();
        BackGround b = new BackGround();
        b.execute(Email, Password);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String email = params[0];
            String password = params[1];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://10.0.2.2/Sabzi_wala/login.php");
                String urlParams = "email=" + email + "&password=" + password;
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
                // NAME = user_data.getString("name");
                //Password = user_data.getString("password");
                EMAIL = user_data.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }
            if (EMAIL != null) {
                Intent i = new Intent(ctx, HOME.class);
                startActivity(i);
            } else {
                Toast.makeText(ctx, "USERNAME AND PASSWORD NOT MATCH ", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

