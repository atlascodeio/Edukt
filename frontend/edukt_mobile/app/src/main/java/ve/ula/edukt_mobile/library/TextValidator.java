package ve.ula.edukt_mobile.library;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Created by realq on 24/06/15.
 * Helper Class to acomplish the textfileds validation with oval
 */
public abstract class TextValidator implements TextWatcher {
    private final TextView textView;

    private String method_name;
    private String oval_profile;
    private TextInputLayout error_layout;
    private String class_name;
    private HashMap<String, String> flags = new HashMap<String, String>();
    private Integer flag_submit;
    private String text;



    public TextValidator(TextView textView) {
        this.textView = textView;
    }

    public TextValidator(TextView textView, String class_name,String method_name, String oval_profile, TextInputLayout error_layout) {

        this.textView = textView;
        this.class_name = class_name;
        this.method_name = method_name;
        this.oval_profile = oval_profile;
        this.error_layout = error_layout;
        this.flags.put(oval_profile, "");
        this.flag_submit = 0;
        this.text = textView.getText().toString();
    }

    //Construct for invoke the validate field directly for the completed form
    public TextValidator(TextView textView, String class_name,String method_name, String oval_profile, TextInputLayout error_layout, Boolean valid_init) {

        this.textView = textView;
        this.class_name = class_name;
        this.method_name = method_name;
        this.oval_profile = oval_profile;
        this.error_layout = error_layout;
        this.flags.put(oval_profile, "");
        this.flag_submit = 0;
        this.text = textView.getText().toString();

        if(valid_init)
            try {
                validate(textView, text, class_name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
    }

    //abstract method for implement own validation
    public abstract void validate(TextView textView, String text);


    public void validate(TextView textView, String text, String class_name) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        //use reflection
        //find class
        Class cls = Class.forName(class_name);
        //instance an object
        Object clsInstance = (Object) cls.newInstance();
        //find the method needed
        Method method = cls.getMethod(method_name, String.class);
        //invoke method
        method.invoke(clsInstance, text);

        //call the oval validator
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(clsInstance, oval_profile);

        //if there is validation error
        if(violations.size()>0)
        {
            flag_submit++;
            if(flags.get(oval_profile)!=violations.get(0).getErrorCode()){
                error_layout.setError(violations.get(0).getMessage());
                this.flags.put(oval_profile, violations.get(0).getErrorCode());
            }
        }else{
            error_layout.setError(null);
            this.flags.put(oval_profile, "");
        }
    }

    @Override
    final public void afterTextChanged(Editable s) {
        String text = textView.getText().toString();
        if(class_name != null) {
            try {
                validate(textView, text, class_name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            validate(textView, text);
        }

    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }

    public Integer getFlag_submit() {
        return flag_submit;
    }
}
