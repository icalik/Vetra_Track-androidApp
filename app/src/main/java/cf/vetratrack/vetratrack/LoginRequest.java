package cf.vetratrack.vetratrack;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iscalik on 10/20/17.
 */

public class LoginRequest extends StringRequest {
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String>listener) {
        super(Method.POST, Config.LOGIN_URL, listener, null);
        params = new HashMap<>();
        params.put("password", password);
        params.put("email", email);
        params.put("type", "android");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
