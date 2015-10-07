package donuseiei.test.com.authen;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainManager extends AppCompatActivity implements  Login_page.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manager);
        Login_page fragment_login = new Login_page();
        FragmentTransaction transaction_plan = getSupportFragmentManager().beginTransaction();
        transaction_plan.replace(R.id.container, fragment_login);
        transaction_plan.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
