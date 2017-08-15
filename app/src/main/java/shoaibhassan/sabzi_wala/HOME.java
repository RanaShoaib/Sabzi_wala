package shoaibhassan.sabzi_wala;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HOME extends Activity {
    Context ctx = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void A(View v) {
        Intent i = new Intent(ctx, ADD.class);
        startActivity(i);
    }


    public void D(View v) {
        Intent z = new Intent(ctx, DELETE.class);
        startActivity(z);
    }

    public void U(View v) {
        Intent z = new Intent(ctx, UPDATE.class);
        startActivity(z);
    }

    public void p(View v) {
        Intent z = new Intent(ctx, VIEW.class);
        startActivity(z);
    }

}
