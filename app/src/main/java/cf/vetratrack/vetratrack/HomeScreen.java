package cf.vetratrack.vetratrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        final TextView tvFullName = (TextView) findViewById(R.id.tv_FullName);
        final TextView tvToken = (TextView) findViewById(R.id.tv_Token);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra("fullName");
        String token = intent.getStringExtra("androidToken");

        tvFullName.setText(fullName);
        tvToken.setText(token);


    }
}
