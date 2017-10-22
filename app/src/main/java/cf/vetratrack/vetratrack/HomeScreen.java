package cf.vetratrack.vetratrack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {


    //menu
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_list:
                    mTextMessage.setText(R.string.title_list);
                    return true;
                case R.id.navigation_report:
                    mTextMessage.setText(R.string.title_report);
                    return true;
                case R.id.navigation_activity:
                    mTextMessage.setText(R.string.title_activity);
                    return true;
                case R.id.navigation_about:
                    mTextMessage.setText(R.string.title_about);
                    return true;
            }
            return false;
        }

    };
    //menu

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


        //menu
        mTextMessage = (TextView) findViewById(R.id.tv_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //menu

    }


}
