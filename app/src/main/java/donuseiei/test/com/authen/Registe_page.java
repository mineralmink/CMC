package donuseiei.test.com.authen;

import android.app.Activity;
import android.app.ProgressDialog;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registe_page.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registe_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registe_page extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ProgressDialog prgDialog;
    private EditText name_edit;
    private EditText email_edit;
    private EditText pass_edit;
    private Button submit;
    private Button cancel;
    private String name;
    private String email;
    private String password;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static Registe_page newInstance(String param1, String param2) {
        Registe_page fragment = new Registe_page();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Registe_page() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          //  mParam1 = getArguments().getString(ARG_PARAM1);
          //  mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registe_page, container, false);
        prgDialog = new ProgressDialog(getContext());
        name_edit = (EditText)v.findViewById(R.id.regis_fullname_edit);
        email_edit = (EditText)v.findViewById(R.id.regis_email_edit);
        pass_edit = (EditText)v.findViewById(R.id.regis_pass_edit);
        submit = (Button)v.findViewById(R.id.regis_submit_button);
        cancel = (Button)v.findViewById(R.id.regis_cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getFragmentManager().beginTransaction().remove(Registe_page.this).commit();
                getFragmentManager().beginTransaction().replace(R.id.container, new Login_page()).commit();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(v);
            }
        });
        return v;
    }

    public void register(View view){
        name = name_edit.getText().toString();
        // Get Email Edit View Value
        email = email_edit.getText().toString();
        // Get Password Edit View Value
        password = pass_edit.getText().toString();
        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();

        // Put Http parameter username with value of Email Edit View control
        params.put("e", email);
        // Put Http parameter password with value of Password Edit Value control
        params.put("p", password);

        params.put("i","");
        // Invoke RESTful Web Service with Http parameters
        post(params);
    }
    public void post(RequestParams params){
        //prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://203.151.92.185:8080/addUser/"+email+"/"+name+"/", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = "";
                for (int index = 0; index < responseBody.length; index++) {
                    response += (char) responseBody[index];
                }
                Log.i("res",response);
                    Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction().replace(R.id.container, new Login_page()).commit();

               /* try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    //if (obj.getBoolean("1")) {
                    //    getFragmentManager().beginTransaction().replace(R.id.container,new Login_page()).commit();
                   // }

                } catch (JSONException e) {
                    //TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("number", "" + statusCode);
                if(statusCode == 404)
                    Toast.makeText(getActivity(), "Page Not Found", Toast.LENGTH_LONG).show();
            }
        });
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
