package donuseiei.test.com.authen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import donuseiei.test.com.authen.ListItemPlan;
import donuseiei.test.com.authen.R;

/**
 * Created by Pongpayak on 10/25/2015.
 */
public class DashApdater {//extends ArrayAdapter<String>{
/*    private final LayoutInflater mInflater;

    public DashApdater(Context context, int resource, List<String> itemsVM) {
        super(context, resource);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setData(itemsVM);
    }

    public void setData(List<ListItemPlan> data) {
        clear();
        if (data != null) {
            for (ListItemPlan appEntry : data) {
                add(appEntry);
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.planviewlist, parent, false);
        } else {
            view = convertView;
        }

        String item = getItem(position);
        ((TextView)view.findViewById(R.id.elementTitle)).setText(item.getTitle());
        ((TextView)view.findViewById(R.id.elementDetail)).setText(item.getDetail());

        return view;
    }*/
}
