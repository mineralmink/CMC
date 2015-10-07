package donuseiei.test.com.authen;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Plan_page fragment_plan = new Plan_page();
        FragmentTransaction transaction_plan = getSupportFragmentManager().beginTransaction();
        transaction_plan.replace(R.id.container_ac, fragment_plan);
        transaction_plan.commit();
    }

}
