package donuseiei.test.com.authen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


public class Login_page extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private  ProgressDialog prgDialog;
    private  Button submit;
    private  Button register;
    private  EditText edit_email;
    private  EditText edit_pass;
    private String email;
    // Get Password Edit View Value
    private String password;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static Login_page newInstance(String param1, String param2) {
        Login_page fragment = new Login_page();
        Bundle args = new Bundle();
        //args.putString(name_var1, param1);
        //args.putString(name_var2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Login_page() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(name_var1);
            //mParam2 = getArguments().getString(name_var2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login_page, container, false);
        prgDialog = new ProgressDialog(getContext());
        submit = (Button)v.findViewById(R.id.submit_login);
        register = (Button)v.findViewById(R.id.btn_register);
        edit_email = (EditText)v.findViewById(R.id.username_login);
        edit_pass = (EditText)v.findViewById(R.id.password_login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container, new Registe_page()).commit();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
        return v;
    }
    public void login(View view){
        // Get Email Edit View Value
        email = edit_email.getText().toString();
        // Get Password Edit View Value
        password = edit_pass.getText().toString();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();
        params.put("p", password);
        // Invoke RESTful Web Service with Http parameters
        get(params);
        Log.i("para", params.toString());
    }

    public void get(RequestParams params) {
        // Show Progress Dialog
        /*prgDialog.setMessage("checking");
        prgDialog.setCancelable(true);
        prgDialog.show();*/
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://203.151.92.185:8080/login/"+email+"/", params, new AsyncHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.i("number", "" + statusCode);
                if(statusCode == 404)
                    Toast.makeText(getActivity(),"Page Not Found",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String response = "";
                for (int index = 0; index < bytes.length; index++) {
                    response += (char) bytes[index];
                }
                Log.i("res", response);
                if(response.equals("true")) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getActivity(),"Email or Password may wrong",Toast.LENGTH_LONG).show();
               /* try {
                    JSONObject json = new JSONObject(response);
                    if(json.getBoolean("status")) {
                        //
                    }
                    else{
                        Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        });
        // PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        // client.setCookieStore(myCookieStore);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

