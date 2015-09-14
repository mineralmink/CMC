package donuseiei.test.com.authen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;

/**
 * Created by Donus Eiei on 9/12/2015.
 */
public class Register extends AppCompatActivity {
    EditText name_edit;
    EditText email_edit;
    EditText pass_edit;
    Button submit;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        name_edit = (EditText)findViewById(R.id.regis_fullname_edit);
        email_edit = (EditText)findViewById(R.id.regis_email_edit);
        pass_edit = (EditText)findViewById(R.id.regis_pass_edit);
        submit = (Button)findViewById(R.id.regis_submit_button);
        cancel = (Button)findViewById(R.id.regis_cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(v);
            }
        });
    }

    public void register(View view){
        String name = name_edit.getText().toString();
        // Get Email Edit View Value
        String email = email_edit.getText().toString();
        // Get Password Edit View Value
        String password = pass_edit.getText().toString();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();

        params.put("name",name);
        // Put Http parameter username with value of Email Edit View control
        params.put("username", email);
        // Put Http parameter password with value of Password Edit Value control
        params.put("password", password);
        // Invoke RESTful Web Service with Http parameters
        DataBase.post(params);
    }
}
