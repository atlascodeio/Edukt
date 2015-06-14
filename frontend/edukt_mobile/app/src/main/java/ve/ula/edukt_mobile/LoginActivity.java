
package ve.ula.edukt_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ve.ula.edukt_mobile.library.DatabaseHandler;
import ve.ula.edukt_mobile.library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {
	//Button btnLogin;
    TextView btnLogin;
	TextView btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;


	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

        //Add to prevent the emulator localhost main threat error
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Borrar solo es para saltar el login
        //Intent dashboard = new Intent(getApplicationContext(), MainActivity.class);
        //startActivity(dashboard);

		// Importing all assets like buttons, text fields
		inputEmail = (EditText) findViewById(R.id.loginEmail);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		//btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin = (TextView) findViewById(R.id.btnLogin);
		btnLinkToRegister = (TextView) findViewById(R.id.btnLinkToRegisterScreen);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				Log.d("Button", "Login");

                //Validate if there is communication to api2 server
                try {
                    JSONObject json = userFunction.loginUser(email, password);
                    // check for login response
                    try {
                        if (json.getString(KEY_SUCCESS) != "success") {
                            loginErrorMsg.setText("");
                            String res = json.getString(KEY_SUCCESS);
                            if(Integer.parseInt(res) == 1){
                                // user successfully logged in
                                // Store user details in SQLite Database
                                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                                JSONObject json_user = json.getJSONObject("user");

                                // Clear all previous data in database
                                userFunction.logoutUser(getApplicationContext());
                                db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));

                                // Launch Dashboard Screen
                                //Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
                                Intent dashboard = new Intent(getApplicationContext(), MainActivity.class);

                                // Close all views before launching Dashboard
                                //dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(dashboard);

                                // Close Login Screen
                                finish();
                            }else{
                                // Error in login
                                loginErrorMsg.setText("Incorrect username/password");
                            }
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();

                    }


                }catch(Exception e){
                    Log.d("Button", "Loginooooo fail");
                    loginErrorMsg.setText("API2. server unreachable");
                }


			}
		});

		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
				//finish();
			}
		});
	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);}


}
