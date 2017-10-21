package cf.vetratrack.vetratrack;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iscalik on 10/21/17.
 */

public class RegisterRequest extends StringRequest {
    private Map<String, String> params;

    public RegisterRequest(String email, String password, String username, String fullName, Response.Listener<String> listener) {
        super(Method.POST, Config.REGISTER_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("userName", username);
        params.put("fullName", fullName);
        params.put("phoneNumber","");
        params.put("type","android");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}