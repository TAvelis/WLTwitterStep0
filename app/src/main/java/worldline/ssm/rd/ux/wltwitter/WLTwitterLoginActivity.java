package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;


public class WLTwitterLoginActivity extends Activity implements View.OnClickListener {

    private EditText mLoginEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // load our WML layout to display GUI
        setContentView(R.layout.activity_login);

        //Now add a listener when we click on the Login button
        mLoginEditText = (EditText)findViewById(R.id.loginEditText);
        mPasswordEditText = (EditText)findViewById(R.id.passwordEditText);

        final String storedLogin = PreferenceUtils.getLogin();
        final String storedPassword = PreferenceUtils.getPassword();

        if ((!TextUtils.isEmpty(storedLogin))&& (!TextUtils.isEmpty(storedPassword)))
        {
            final Intent homeIntent =  getHomeActivityIntent(storedLogin);
            startActivity(homeIntent);
        }
    }

    @Override
    public void onClick (View view){
        //handle click on button

        if (TextUtils.isEmpty(mLoginEditText.getText()))
        {
            Toast.makeText(this,R.string.error_no_login, Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(mPasswordEditText.getText()))
        {
            Toast.makeText(this,R.string.error_no_password,Toast.LENGTH_LONG).show();
            return;
        }

        final Intent homeIntent = getHomeActivityIntent(mLoginEditText.getText().toString());
        PreferenceUtils.setLogin(mLoginEditText.getText().toString());
        PreferenceUtils.setPassword(mPasswordEditText.getText().toString());
        startActivity(homeIntent);
    }

    private Intent getHomeActivityIntent(String userName)
    {
        Intent intent = new Intent(this,WLTwitterActivity.class);
        final Bundle extras = new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);
        intent.putExtras(extras);
        return intent;
    }
}
