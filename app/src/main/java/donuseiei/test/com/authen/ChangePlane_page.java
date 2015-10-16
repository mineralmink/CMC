package donuseiei.test.com.authen;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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

public class ChangePlane_page extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM2 = "password";
    // TODO: Rename and change types of parameters
    private String id;
    private String password;
    private Spinner dropdown;
    private final ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ImageButton btnNext;
    private ImageButton btnPre;
    private TextView plan;
    private TextView cpu_plan;
    private TextView mem_plan;
    private TextView sto_plan;
    private TextView net_plan;
    private TextView cpu;
    private TextView mem;
    private TextView sto;
    private TextView net;
    private int indexPlan = 0;
    private ArrayList<Plan> listPlan;
    private ArrayList<Plan> vm;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangePlane_page.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangePlane_page newInstance(String param1, String param2) {
        ChangePlane_page fragment = new ChangePlane_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ChangePlane_page() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listPlan = new ArrayList<>();
        vm = new ArrayList<>();
        if (getArguments() != null) {
            id = getArguments().getString(ARG_PARAM1);
            password = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_plane_page, container, false);
        //button
        dropdown = (Spinner) view.findViewById(R.id.spinner_vp_change);
        btnNext = (ImageButton)view.findViewById(R.id.btn_next);
        btnPre = (ImageButton)view.findViewById(R.id.btn_pre);
        //detail Plan
        cpu_plan = (TextView)view.findViewById(R.id.sCPU_change2);
        mem_plan  = (TextView)view.findViewById(R.id.sMem_change2);
        sto_plan  = (TextView)view.findViewById(R.id.sStr_change2);
        net_plan = (TextView)view.findViewById(R.id.sNet_change2);
        // detail user
        cpu = (TextView)view.findViewById(R.id.sCPU_change1);
        mem  = (TextView)view.findViewById(R.id.sMem_change1);
        sto  = (TextView)view.findViewById(R.id.sStr_change1);
        net = (TextView)view.findViewById(R.id.sNet_change1);
        RequestParams param = new RequestParams();
        param.put("password", password);
        getVM(param); //get list vm
        /*dropdown.*/
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                Plan c = vm.get(position);
                setTextPlanCurrent(c);
                //setTextPlan(listPlan.get(0));
                RequestParams params = new RequestParams();
                params.put("cloudProv", c.getProv());
                getPlan(params);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //setTextPlan(listPlan.get(0));
        onClickBtnNextOrPre();
        return view;
    }

    /*make list vm dropdown*/
    public void makeList(){
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    /*handler click button next or previous*/
    public void onClickBtnNextOrPre(){
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextPlan(listPlan.get(getIndexPlan(1)));
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextPlan(listPlan.get(getIndexPlan(0)));
            }
        });
    }

    /*set plan available to select*/
    public void setTextPlan(Plan p){
        cpu_plan.setText(p.getCpu());
        mem_plan.setText(p.getMemory());
        sto_plan.setText(p.getStorage());
        net_plan.setText(p.getStorage());
    }

    /*set plan Current*/
    public void setTextPlanCurrent(Plan c) {
        cpu.setText(c.getCpu());
        mem.setText(c.getMemory());
        sto.setText(c.getStorage());
        net.setText(c.getStorage());
    }

    /*return index list plan*/
    public int getIndexPlan(int mode){
        if(mode == 0){ //next
            if(indexPlan >= listPlan.size()){
                indexPlan = 0;
            }
            else indexPlan++;
        }
        else { //pre
            if(indexPlan <= 0){
                indexPlan = listPlan.size()-1;
            }
            else indexPlan--;
        }
        return indexPlan;
    }

    public void getPlanByName(String cloud){
        RequestParams param = new RequestParams();
        if(cloud.equals("GOOGLE"))
            param.put("cloudProv",0);
        else if(cloud.equals("Amazon"))
            param.put("cloudProv",1);
        else if(cloud.equals("Azure"))
            param.put("cloudProv", 2);
        else if(cloud.equals("Digital Ocean"))
            param.put("cloudProv", 3);
        else if(cloud.equals("VMWare"))
            param.put("cloudProv", 4);
        else if(cloud.equals("Unknown"))
            param.put("cloudProv", 5);
        getPlan(param); //make list plan
    }

    /*send http to get plan that available*/
    public void getPlan(RequestParams params) {
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://203.151.92.185:3000/plan/", params, new AsyncHttpResponseHandler() {
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
                    JSONObject json = new JSONObject(response);
                    if(!json.getString("cloudProv").isEmpty()) {
                        for(int index = 0; index < json.length();index++){
                            Plan p = new Plan(json.getString("cloudProv"),
                                    json.getString("ip"),
                                    json.getString("monthlyRate"),
                                    json.getString("cpu"),
                                    json.getString("mem"),
                                    json.getString("network"),
                                    json.getString("storage"));
                            listPlan.add(p);
                        }
                        Log.i("num",""+listPlan.size());
                        setTextPlan(listPlan.get(0));
                    }
                    else{
                        Toast.makeText(getActivity(),"No Any Fucking Plan , Go to ur school",Toast.LENGTH_LONG);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*send http to get plan that available*/
    public void getVM(RequestParams params) {
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://161.246.5.203:3000/plan/"+id+"/", params, new AsyncHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.i("number", "" + statusCode);
                if (statusCode == 404)
                    Toast.makeText(getActivity(), "Page Not Found", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onSuccess(int j, Header[] headers, byte[] bytes) {
                String response = "";
                for (int index = 0; index < bytes.length; index++) {
                    response += (char) bytes[index];
                }
                Log.i("ress",response);
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
                            vm.add(c);
                        }
                        for(int i = 0 ; i < vm.size() ; i++){
                            items.add(vm.get(i).getIp());
                        }
                        makeList();
                        RequestParams params = new RequestParams();
                        params.put("cloudProv",vm.get(0).getProv());
                    }
                    else{
                        Toast.makeText(getActivity(),"No Any Fucking Plan , Go to ur school",Toast.LENGTH_LONG);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
