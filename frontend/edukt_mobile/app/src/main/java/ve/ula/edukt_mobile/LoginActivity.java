
package ve.ula.edukt_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.sf.oval.Validator;

import ve.ula.edukt_mobile.form.LoginForm;
import ve.ula.edukt_mobile.library.DatabaseHandler;
import ve.ula.edukt_mobile.library.TextValidator;
import ve.ula.edukt_mobile.library.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {
	// Button btnLogin;
    TextView btnLogin;
	TextView btnLinkToRegister;
    EditText inputEmail;
    TextInputLayout email_text_input_layout;
    TextInputLayout password_text_input_layout;
	EditText inputPassword;
    // Flag to avoid the setError message blinking
    LoginForm loginForm = new LoginForm();

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

    private Validator validator;

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
        email_text_input_layout = (TextInputLayout) findViewById(R.id.email_text_input_layout);
        email_text_input_layout.setErrorEnabled(true);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
        password_text_input_layout = (TextInputLayout) findViewById(R.id.password_text_input_layout);
        password_text_input_layout.setErrorEnabled(true);
        //Define the ime action
        inputPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnLogin.performClick();
                    return true;
                }
                return false;
            }
        });

        btnLogin = (TextView) findViewById(R.id.btnLogin);
		btnLinkToRegister = (TextView) findViewById(R.id.btnLinkToRegisterScreen);

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				Log.d("Button", "Login");

                //if the completed form is valid
                if(validateForm()){

                    //Validate if there is communication to api2 server
                    try {
                        JSONObject json = userFunction.loginUser(email, password);
                        // check for login response
                        try {
                            if (json.getString(KEY_SUCCESS) != "success") {
                                String res = json.getString(KEY_SUCCESS);
                                if (Integer.parseInt(res) == 1) {
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
                                } else {
                                    // Error in login
                                    Toast.makeText(getApplicationContext(), getString(R.string.error_bad_login), Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (JSONException e) {

                            e.printStackTrace();

                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "API2. server unreachable", Toast.LENGTH_LONG).show();
                    }
                }
			}
		});

		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                //overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
                //finish();
            }
        });

        //for field validation after text changed
        inputEmail.addTextChangedListener(new TextValidator(inputEmail, loginForm.getClass().getName(),"setEmail", "email", email_text_input_layout) {
            @Override public void validate(TextView textView, String text) {}
        });

        //for field validation after text changed
        inputPassword.addTextChangedListener(new TextValidator(inputPassword, loginForm.getClass().getName(),"setPassword", "password", password_text_input_layout) {
            @Override public void validate(TextView textView, String text) {}
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

        return super.onOptionsItemSelected(item);
    }


    //For validate the completed form
    public boolean validateForm(){

        //verify email field validation
        TextValidator textValidatorEmail = new TextValidator(inputEmail, loginForm.getClass().getName(),"setEmail", "email", email_text_input_layout, true){
            @Override public void validate(TextView textView, String text) {}
        };

        //verify password field validation
        TextValidator textValidatorPassword = new TextValidator(inputPassword, loginForm.getClass().getName(),"setPassword", "password", password_text_input_layout, true){
            @Override public void validate(TextView textView, String text) {}
        };

        if((textValidatorEmail.getFlag_submit() > 0 ) || (textValidatorPassword.getFlag_submit() > 0)){
            return false;
        }

        return true;
    }


}
