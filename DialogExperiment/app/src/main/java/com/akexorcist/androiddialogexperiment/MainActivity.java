package com.akexorcist.androiddialogexperiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AwesomeDialogFragment.OnDialogListener {
    private static String TAG_DIALOG = "dialog";

    private Button btnCallDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCallDialog = (Button) findViewById(R.id.btn_show_dialog);
        btnCallDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowDialogButtonClick();
            }
        });

        if (savedInstanceState == null) {
            addFragment();
        }
    }

    private void onShowDialogButtonClick() {
        AwesomeDialogFragment fragment = new AwesomeDialogFragment.Builder()
                .setMessage(R.string.sample_dialog_message)
                .setPosition(R.string.ok)
                .setNegative(R.string.cancel)
                .build();
        fragment.show(getSupportFragmentManager(), TAG_DIALOG);
    }

    private void addFragment() {
        String tag = SimpleFragment.class.getSimpleName();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_fragment_container, SimpleFragment.newInstance(), tag)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void onPositiveButtonClick() {
        Toast.makeText(this, R.string.sample_dialog_confirm, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeButtonClick() {
        Toast.makeText(this, R.string.sample_dialog_cancel, Toast.LENGTH_SHORT).show();
    }
}
