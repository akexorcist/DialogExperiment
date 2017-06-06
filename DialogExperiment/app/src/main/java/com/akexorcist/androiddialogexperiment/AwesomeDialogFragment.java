package com.akexorcist.androiddialogexperiment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Akexorcist on 6/5/2017 AD.
 */

public class AwesomeDialogFragment extends DialogFragment {
    private static final String KEY_MESSAGE = "key_message";
    private static final String KEY_POSITIVE = "key_positive";
    private static final String KEY_NEGATIVE = "key_negative";

    private TextView tvMessage;
    private Button btnPositive;
    private Button btnNegative;

    private int message;
    private int positive;
    private int negative;

    public static AwesomeDialogFragment newInstance(@StringRes int message, @StringRes int positive, @StringRes int negative) {
        AwesomeDialogFragment fragment = new AwesomeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_MESSAGE, message);
        bundle.putInt(KEY_POSITIVE, positive);
        bundle.putInt(KEY_NEGATIVE, negative);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            restoreArguments(getArguments());
        } else {
            restoreInstanceState(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_awesome, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupView();
    }

    private void bindView(View view) {
        tvMessage = (TextView) view.findViewById(R.id.tv_message);
        btnPositive = (Button) view.findViewById(R.id.btn_positive);
        btnNegative = (Button) view.findViewById(R.id.btn_negative);
    }

    private void setupView() {
        tvMessage.setText(message);
        btnPositive.setText(positive);
        btnNegative.setText(negative);

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDialogListener listener = getOnDialogListener();
                if (listener != null) {
                    listener.onPositiveButtonClick();
                }
                dismiss();
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnDialogListener listener = getOnDialogListener();
                if (listener != null) {
                    listener.onNegativeButtonClick();
                }
                dismiss();
            }
        });
    }

    private OnDialogListener getOnDialogListener() {
        Fragment fragment = getParentFragment();
        try {
            if (fragment != null) {
                return (OnDialogListener) fragment;
            } else {
                return (OnDialogListener) getActivity();
            }
        } catch (ClassCastException ignored) {
        }
        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_MESSAGE, message);
        outState.putInt(KEY_POSITIVE, positive);
        outState.putInt(KEY_NEGATIVE, negative);
    }

    private void restoreInstanceState(Bundle bundle) {
        message = bundle.getInt(KEY_MESSAGE);
        positive = bundle.getInt(KEY_POSITIVE);
        negative = bundle.getInt(KEY_NEGATIVE);
    }

    private void restoreArguments(Bundle bundle) {
        message = bundle.getInt(KEY_MESSAGE);
        positive = bundle.getInt(KEY_POSITIVE);
        negative = bundle.getInt(KEY_NEGATIVE);
    }

    public interface OnDialogListener {
        void onPositiveButtonClick();

        void onNegativeButtonClick();
    }

    public static class Builder {
        private int message;
        private int positive;
        private int negative;

        public Builder() {
        }

        public Builder setMessage(@StringRes int message) {
            this.message = message;
            return this;
        }

        public Builder setPosition(@StringRes int positive) {
            this.positive = positive;
            return this;
        }

        public Builder setNegative(@StringRes int negative) {
            this.negative = negative;
            return this;
        }

        public AwesomeDialogFragment build() {
            AwesomeDialogFragment fragment = AwesomeDialogFragment.newInstance(message, positive, negative);
            return fragment;
        }
    }
}
