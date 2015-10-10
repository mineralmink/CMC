package donuseiei.test.com.authen;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements Plan_page.OnFragmentInteractionListener{
    RadioButton dash;
    RadioButton plan;
    RadioButton bill;
    RadioButton report;
    RadioButton mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Plan_page fragment_plan = new Plan_page();
        FragmentTransaction transaction_plan = getSupportFragmentManager().beginTransaction();
        transaction_plan.replace(R.id.container_ac, fragment_plan);
        transaction_plan.commit();
        //onClick();
    }
    private void onClick(){
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Dash_page fragment_dash = new Dash_page();
                FragmentTransaction transaction_plan = getSupportFragmentManager().beginTransaction();
                transaction_plan.replace(R.id.container_ac, fragment_dash);
                transaction_plan.commit();*/
            }
        });
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
