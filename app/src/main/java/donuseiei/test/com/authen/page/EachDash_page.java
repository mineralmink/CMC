package donuseiei.test.com.authen.page;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

import donuseiei.test.com.authen.R;


public class EachDash_page extends Fragment {
/*    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    *//**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EachDash_page.
     *//*
    // TODO: Rename and change types and number of parameters
    public static EachDash_page newInstance(String param1, String param2) {
        EachDash_page fragment = new EachDash_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EachDash_page() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_each_dash_page, container, false);
        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        return v;
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

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_each_dash_page, container, false);

        v_cpu = (TextView)rootView.findViewById(R.id.text_cpu);
        v_mem = (TextView)rootView.findViewById(R.id.text_mem);
        v_str = (TextView)rootView.findViewById(R.id.text_storage);
        v_net = (TextView)rootView.findViewById(R.id.text_net);

        GraphView graph_cpu = (GraphView) rootView.findViewById(R.id.graph_cpu);
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

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTimer = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
                double cpu = getRandom();
                double mem = getRandom();
                double str = getRandom();
                double net = getRandom();
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

    private DataPoint[] generateData() {
        int count = 30;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;
            double f = mRand.nextDouble()*0.15+0.3;
            double y = Math.sin(i*f+2) + mRand.nextDouble()*0.3;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    double mLastRandom = 2;
    Random mRand = new Random();
    private double getRandom() {
        mLastRandom = mRand.nextInt()/100000000;
        if(mLastRandom<0)
            mLastRandom*=-1;
        return mLastRandom;
    }
}

