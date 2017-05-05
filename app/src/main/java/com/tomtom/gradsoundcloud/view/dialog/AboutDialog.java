package com.tomtom.gradsoundcloud.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.tomtom.gradsoundcloud.R;


/**
 * An activity dialog that will handle the about section
 */
public class AboutDialog extends Activity implements View.OnClickListener {
    private static String TAG = AboutDialog.class.getSimpleName();

    /**
     * Helper class to get a starting  intent to launch this activity
     *
     * @param context the context
     * @return the intent
     */
    public static Intent getStartIntent(@NonNull Context context) {
        return new Intent(context, AboutDialog.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_about);
        buildDialogUI();
    }

    /**
     * Helper class to build the dialog UI
     */
    private void buildDialogUI() {
        TextView ok = (TextView) findViewById(R.id.ok);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

}
