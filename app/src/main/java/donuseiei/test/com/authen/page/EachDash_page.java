package donuseiei.test.com.authen.page;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import donuseiei.test.com.authen.Adapter.PlanViewAdapter;
import donuseiei.test.com.authen.HTTPConnector;
import donuseiei.test.com.authen.ListItemPlan;
import donuseiei.test.com.authen.Plan;
import donuseiei.test.com.authen.R;


public class EachDash_page extends Fragment {

    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    private LineGraphSeries<DataPoint> mSeries_cpu;
    private LineGraphSeries<DataPoint> mSeries_mem;
    private LineGraphSeries<DataPoint> mSeries_storage;
    private LineGraphSeries<DataPoint> mSeries_net;
    private double graph2LastXValue = 5d;
    private TextView v_cpu;
    private TextView v_mem;
    private TextView v_str;
    private TextView v_net;
    private String id;
    private String password;
    private Spinner dropdown;
    private RequestParams params;
    private List<String> list_name;
    private View rootView;
    private JSONObject json;
    private JSONArray json_arr;
    private JSONObject update_json;
    private JSONArray update_json_arr;
    private double cpu = 0;
    private double mem = 0;
    private double str = 0;
    private double net = 0;
    private String name;
    private String ip;
    private GraphView graph_cpu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString("id");
            password = getArguments().getString("password");
            params = new RequestParams();
            params.put("password",password);
        }
        list_name = new ArrayList<>();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_each_dash_page, container, false);

        v_cpu = (TextView)rootView.findViewById(R.id.text_cpu);
        v_mem = (TextView)rootView.findViewById(R.id.text_mem);
        v_str = (TextView)rootView.findViewById(R.id.text_storage);
        v_net = (TextView)rootView.findViewById(R.id.text_net);

        graph_cpu = (GraphView) rootView.findViewById(R.id.graph_cpu);
        mSeries_cpu = new LineGraphSeries<>();
        graph_cpu.addSeries(mSeries_cpu);
        graph_cpu.getViewport().setXAxisBoundsManual(true);
        graph_cpu.getViewport().setMinX(0);
        graph_cpu.getViewport().setMaxX(100);
        graph_cpu.getViewport().setMinY(0);
        graph_cpu.getViewport().setMaxY(100);
        graph_cpu.getViewport().setYAxisBoundsManual(true);

        GraphView graph_mem = (GraphView) rootView.findViewById(R.id.graph_mem);
        mSeries_mem = new LineGraphSeries<>();
        graph_mem.addSeries(mSeries_mem);
        graph_mem.getViewport().setXAxisBoundsManual(true);
        graph_mem.getViewport().setMinX(0);
        graph_mem.getViewport().setMaxX(100);
        graph_mem.getViewport().setMinY(0);
        graph_mem.getViewport().setMaxY(100);
        graph_mem.getViewport().setYAxisBoundsManual(true);

        GraphView graph_storage = (GraphView) rootView.findViewById(R.id.graph_storage);
        mSeries_storage = new LineGraphSeries<>();
        graph_storage.addSeries(mSeries_storage);
        graph_storage.getViewport().setXAxisBoundsManual(true);
        graph_storage.getViewport().setMinX(0);
        graph_storage.getViewport().setMaxX(100);
        graph_storage.getViewport().setMinY(0);
        graph_storage.getViewport().setMaxY(100);
        graph_storage.getViewport().setYAxisBoundsManual(true);

        GraphView graph_net = (GraphView) rootView.findViewById(R.id.graph_net);
        mSeries_net = new LineGraphSeries<>();
        graph_net.addSeries(mSeries_net);
        graph_net.getViewport().setXAxisBoundsManual(true);
        graph_net.getViewport().setMinX(0);
        graph_net.getViewport().setMaxX(100);
        graph_net.getViewport().setMinY(0);
        graph_net.getViewport().setMaxY(100);
        graph_net.getViewport().setYAxisBoundsManual(true);

        getData(params);

        return rootView;
    }
    public void CreateView() throws JSONException {

        dropdown = (Spinner) rootView.findViewById(R.id.spinner_dash);
        if(!list_name.isEmpty()){
            list_name.removeAll(list_name);
        }
        for (int i=0 ; i < json_arr.length();i++) {
            JSONArray j_arr = json_arr.getJSONObject(i).getJSONArray("vms");
            for(int j =0 ;j<j_arr.length();j++){
                list_name.add(json_arr.getJSONObject(i).getString("cloudName") +" : "+ j_arr.getJSONObject(j).getString("vmIP"));
            }
        }
        ArrayAdapter<String> adapter_spinner = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list_name);
        dropdown.setAdapter(adapter_spinner);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name = list_name.get(position).split(" : ")[0];
                ip = list_name.get(position).split(" : ")[1];
              /*  ListView lv = (ListView)rootView.findViewById(R.id.listPlanView);
                List<ListItemPlan> itemsVM = new ArrayList<>();;
                Plan p = listVM.get(position);
                if(!itemsVM.isEmpty()){
                    itemsVM.removeAll(itemsVM);
                }
                else {
                    itemsVM.add(new ListItemPlan("Cloud Provider", p.getProv()));
                    //itemsVM.add(new ListItemPlan("IP Address", p.getIp()));
                    itemsVM.add(new ListItemPlan("CPU", p.getCpu()));
                    itemsVM.add(new ListItemPlan("Memory", p.getMemory()));
                    itemsVM.add(new ListItemPlan("Network", p.getMemory()));
                    itemsVM.add(new ListItemPlan("Storage", p.getStorage()));
                    itemsVM.add(new ListItemPlan("Mountly Rate", p.getMounthlyrate()));
                }
                PlanViewAdapter adapter = new PlanViewAdapter(getContext(),android.R.layout.simple_expandable_list_item_2,itemsVM);
                lv.setAdapter(adapter);*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer = new Runnable() {
            @Override
            public void run() {
                updateData(params,name,ip);
                graph2LastXValue += 1d;
                mSeries_cpu.appendData(new DataPoint(graph2LastXValue, cpu), true, 100);
                mSeries_mem.appendData(new DataPoint(graph2LastXValue, mem), true, 100);
                mSeries_storage.appendData(new DataPoint(graph2LastXValue, str), true, 100);
                mSeries_net.appendData(new DataPoint(graph2LastXValue, net), true, 100);
                v_cpu.setText("CPU : " + cpu + "%");
                v_mem.setText("Memory : "+mem+"%");
                v_str.setText("Storage : "+str+"%");
                v_net.setText("Network : "+net+"%");

                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mTimer, 1000);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer);
        super.onPause();
    }

    public void getData(RequestParams params){
        HTTPConnector.get("/dashboard/" + id + "/", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = "";
                for (int index = 0; index < responseBody.length; index++) {
                    response += (char) responseBody[index];
                }
                try {
                    json = new JSONObject(response);
                    json_arr = json.getJSONArray("clouds");
                    System.out.println("json arr : " + json_arr.length());
                    CreateView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getActivity(), "Error Code " + statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }
    public void updateData(RequestParams params,final String name,final String ip){
        HTTPConnector.get("/dashboard/" + id + "/", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = "";
                for (int index = 0; index < responseBody.length; index++) {
                    response += (char) responseBody[index];
                }
                try {
                    update_json = new JSONObject(response);
                    update_json_arr = update_json.getJSONArray("clouds");
                    System.out.println("json arr : " + update_json_arr.toString());
                    for (int i = 0; i < update_json_arr.length(); i++) {
                        if(update_json_arr.getJSONObject(i).getString("cloudName").equals(name)) {
                            JSONArray j_arr = update_json_arr.getJSONObject(i).getJSONArray("vms");
                            for (int j = 0; j < j_arr.length(); j++) {
                                if(j_arr.getJSONObject(j).getString("vmIP").equals(ip)) {
                                    cpu = Double.parseDouble(j_arr.getJSONObject(j).getString("Cpu"));
                                    mem = Double.parseDouble(j_arr.getJSONObject(j).getString("Mem"));
                                    str = Double.parseDouble(j_arr.getJSONObject(j).getString("Storage"));
                                    net = Double.parseDouble(j_arr.getJSONObject(j).getString("Network"));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getActivity(), "Error Code " + statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }
}

