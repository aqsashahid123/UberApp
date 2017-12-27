package uberapp.itpvt.com.uberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btnRider,btnDeveloper,btnCustomer,btnDriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCustomer = (Button) findViewById(R.id.btnRider);
        btnDeveloper = (Button) findViewById(R.id.btnDeveloper);
        btnDriver = (Button) findViewById(R.id.btnDriver);


        btnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,CustomerActivity.class);
                startActivity(intent);
                finish();


            }



        });
        btnDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DeveloperActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DriverActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
