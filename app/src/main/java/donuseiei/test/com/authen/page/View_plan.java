package donuseiei.test.com.authen.page;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import donuseiei.test.com.authen.Cloud;
import donuseiei.test.com.authen.Plan;
import donuseiei.test.com.authen.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link View_plan.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link View_plan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class View_plan extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "password";

    // TODO: Rename and change types of parameters
    private String id;
    private String password;
    private ArrayList<Plan> listCloud;
    private TextView cpu;
    private TextView mem;
    private TextView sto;
    private TextView net;
    private Spinner dropdown;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment View_plan.
     */
    // TODO: Rename and change types and number of parameters
    public static View_plan newInstance(String param1, String param2) {
        View_plan fragment = new View_plan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public View_plan() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listCloud = new ArrayList<>();
        if (getArguments() != null) {
            id = getArguments().getString(ARG_PARAM1);
            password = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view_plan =  inflater.inflate(R.layout.fragment_view_plan, container, false);
        //dropdown
        dropdown = (Spinner) view_plan.findViewById(R.id.spinner_vp);
        // detail user
        cpu = (TextView)view_plan.findViewById(R.id.sCPU);
        mem  = (TextView)view_plan.findViewById(R.id.sMem);
        sto  = (TextView)view_plan.findViewById(R.id.sStr);
        net = (TextView)view_plan.findViewById(R.id.sNet);
        RequestParams p = new RequestParams();
        p.put("password", password);
        getVM(p);
        /*dropdown.*/
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plan c = listCloud.get(position);
                cpu.setText(c.getCpu());
                mem.setText(c.getMemory());
                sto.setText(c.getStorage());
                net.setText(c.getNetwork());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view_plan;
    }

    /*send http to get plan that available*/
    public void getVM(RequestParams params) {
        ProgressDialog dialog = ProgressDialog.show(getActivity(), "Loading", "Please wait...", true);
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://161.246.5.203:3000/plan/"+id+"/", params, new AsyncHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                if (statusCode == 404)
                    Toast.makeText(getActivity(), "Page Not Found", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String response = "";
                for (int index = 0; index < bytes.length; index++) {
                    response += (char) bytes[index];
                }

                try {
                    JSONArray jsonarr = new JSONArray(response);
                    if(jsonarr.length()!=0) {
                        for(int index = 0; index < jsonarr.length();index++){
                            JSONObject json = new JSONObject(jsonarr.get(index).toString());
                            Plan c = new Plan(
                                    json.getString("cloudProv"),
                                    json.getString("ip"),
                                    json.getString("monthlyRate"),
                                    json.getString("cpu"),
                                    json.getString("mem"),
                                    json.getString("network"),
                                    json.getString("storage"));
                            listCloud.add(c);
                        }
                        final ArrayList<String> items = new ArrayList<String>();
                        for(int j = 0 ; j < listCloud.size() ; j++){
                            items.add(listCloud.get(j).getIp());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
                        dropdown.setAdapter(adapter);
                    }
                    else{
                        Toast.makeText(getActivity(),"No Any Fucking Plan , Go to ur school",Toast.LENGTH_LONG);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.dismiss();
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