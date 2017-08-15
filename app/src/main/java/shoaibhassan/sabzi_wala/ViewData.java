package shoaibhassan.sabzi_wala;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ViewData extends Activity {
    String Name, Category, Price, Err;
    TextView NAME, CATEGORY, PRICE, err;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        NAME = (TextView) findViewById(R.id.name);
        CATEGORY = (TextView) findViewById(R.id.category);
        PRICE = (TextView) findViewById(R.id.price);

        Name = getIntent().getStringExtra("name");
        Category = getIntent().getStringExtra("category");
        Price = getIntent().getStringExtra("price");

        NAME.setText("NAME :" + Name);
        CATEGORY.setText("CATEGORY :" + Category);
        PRICE.setText("PRICE :" + Price);


    }
}
