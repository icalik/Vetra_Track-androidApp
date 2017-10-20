package cf.vetratrack.vetratrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
/*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
*/
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        //

        //
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject jsonResponseMessage = jsonResponse.getJSONObject("message");
                            String status = jsonResponseMessage.getString("status");


                            if (status.equals(String.valueOf("success"))) {
                                String fullName = jsonResponseMessage.getString("fullName");
                                String androidToken = jsonResponseMessage.getString("androidToken");
                                Intent intent = new Intent(LoginScreen.this, HomeScreen.class);
                                intent.putExtra("fullName",fullName);
                                intent.putExtra("androidToken",androidToken);
                                LoginScreen.this.startActivity(intent);

                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
                                builder.setMessage("Hatali bilgiler..")
                                        .setNegativeButton("Bi daha dene..", null)
                                        .create()
                                        .show();
                                etEmail.setText("");
                                etPassword.setText("");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginScreen.this);
                queue.add(loginRequest);
            }
        });


    }


}
