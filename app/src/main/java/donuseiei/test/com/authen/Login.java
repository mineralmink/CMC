package donuseiei.test.com.authen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;

/**
 * Created by Donus Eiei on 9/12/2015.
 * Class for Login
 */
public class Login extends AppCompatActivity {
    Button submit;
    Button cancel;
    EditText edit_email;
    EditText edit_pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        submit = (Button)findViewById(R.id.submit_login);
        cancel = (Button)findViewById(R.id.cancel_login);
        edit_email = (EditText)findViewById(R.id.username_login);
        edit_pass = (EditText)findViewById(R.id.password_login);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }
    public void login(View view){
        // Get Email Edit View Value
        String email = edit_email.getText().toString();
        // Get Password Edit View Value
        String password = edit_pass.getText().toString();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        // Put Http parameter username with value of Email Edit View control
        params.put("username", email);
        // Put Http parameter password with value of Password Edit Value control
        params.put("password", password);
        // Invoke RESTful Web Service with Http parameters
        DataBase.get(params);
    }
}
