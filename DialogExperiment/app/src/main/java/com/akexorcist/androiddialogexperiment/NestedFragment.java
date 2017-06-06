package com.akexorcist.androiddialogexperiment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Akexorcist on 5/25/2017 AD.
 */

public class NestedFragment extends DialogFragment implements AwesomeDialogFragment.OnDialogListener {
    private static String TAG_DIALOG = "dialog";

    private Button btnShowDialog;

    public static NestedFragment newInstance() {
        return new NestedFragment();
    }

    public NestedFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nested, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnShowDialog = (Button) view.findViewById(R.id.btn_show_dialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowDialogButtonClick();
            }
        });
    }

    private void onShowDialogButtonClick() {
        AwesomeDialogFragment fragment = new AwesomeDialogFragment.Builder()
                .setMessage(R.string.sample_dialog_message)
                .setPosition(R.string.ok)
                .setNegative(R.string.cancel)
                .build();
        fragment.show(getChildFragmentManager(), TAG_DIALOG);
    }

    @Override
    public void onPositiveButtonClick() {
        Toast.makeText(getContext(), R.string.sample_dialog_confirm, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeButtonClick() {
        Toast.makeText(getContext(), R.string.sample_dialog_cancel, Toast.LENGTH_SHORT).show();
    }
}
