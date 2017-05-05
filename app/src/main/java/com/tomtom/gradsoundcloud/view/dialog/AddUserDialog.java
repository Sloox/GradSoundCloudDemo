package com.tomtom.gradsoundcloud.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.tomtom.gradsoundcloud.R;


/**
 * An activity dialog that will handle the addition of a new SoundCloud user
 */
public class AddUserDialog extends Activity implements View.OnClickListener {
    public static final String EXTRA_USER_ADD_ID = "USER_ADD_ID";
    private static final String TAG = AddUserDialog.class.getSimpleName();

    private EditText userIDtxt;

    /**
     * Helper class to get a starting  intent to launch this activity
     *
     * @param context the context
     * @return the intent
     */
    public static Intent getStartIntent(@NonNull Context context) {
        return new Intent(context, AddUserDialog.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_add_user);
        buildDialogUI();
    }

    /**
     * Helper class to build the dialog UI
     */
    private void buildDialogUI() {
        TextView addUser = (TextView) findViewById(R.id.addUser);
        userIDtxt = (EditText) findViewById(R.id.edit_user_id);
        TextView cancel = (TextView) findViewById(R.id.cancel);
        addUser.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addUser:
                addNewUserClicked();
                break;
            case R.id.cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    /**
     * Helper class to add a new user,
     * The error checking results in a seperate method
     * compared to the rest
     */
    private void addNewUserClicked() {
        int id = 0;
        if (userIDtxt.getText().length() > 0) {
            try {
                id = Integer.valueOf(userIDtxt.getText().toString());
                if (id > 0) {
                    Intent data = new Intent();
                    data.putExtra(EXTRA_USER_ADD_ID, id);
                    setResult(RESULT_OK, data);
                    finish();
                }
            } catch (NumberFormatException e) {
                Log.e(TAG, "Invalid user id entered,", e);
            }
        }
        userIDtxt.setHintTextColor(getResources().getColor(R.color.red));
        userIDtxt.requestFocus();
    }

}
