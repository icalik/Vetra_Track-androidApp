package cf.vetratrack.vetratrack;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        final Button login_button = (Button) findViewById(R.id.btn_R_login);
        final Button bRegister = (Button) findViewById(R.id.btn_R_register);
        final EditText etEmail = (EditText) findViewById(R.id.et_R_email);
        final EditText etPassword = (EditText) findViewById(R.id.et_R_password);
        final EditText etUsername = (EditText) findViewById(R.id.et_R_username);
        final EditText etFullName = (EditText) findViewById(R.id.et_R_nameSurname);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                final String username = etUsername.getText().toString();
                final String fullName = etFullName.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String status = jsonResponse.getString("result");


                            if (status.equals(String.valueOf("success"))) {
                                String androidToken = jsonResponse.getString("token");
                                String fullName = jsonResponse.getString("fullName");

                                Intent intent = new Intent(RegisterScreen.this, HomeScreen.class);
                                intent.putExtra("fullName", fullName);
                                intent.putExtra("androidToken", androidToken);
                                etEmail.setText("");
                                etPassword.setText("");
                                etFullName.setText("");
                                etUsername.setText("");
                                RegisterScreen.this.startActivity(intent);

                            } else if (status.equals(String.valueOf("error"))) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterScreen.this);
                                builder.setMessage("Böyle bir kullanıcı var!")
                                        .setNegativeButton("Bi daha dene..", null)
                                        .create()
                                        .show();
                                etEmail.setText("");
                                etPassword.setText("");
                                etFullName.setText("");
                                etUsername.setText("");
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterScreen.this);
                                builder.setMessage("Server kaynakli problem var!")
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

                RegisterRequest registerRequest = new RegisterRequest(email, password, username, fullName, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterScreen.this);
                queue.add(registerRequest);
            }
        });
    }
}
