package org.rtspviewer.streams.form;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.rtspviewer.R;
import org.rtspviewer.databinding.FragStreamFormBinding;
import org.rtspviewer.db.Stream;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class StreamFormFragment extends DialogFragment implements StreamFormContract.StreamFormView {

    private StreamFormContract.StreamFormPresenter mPresenter;
    private FragStreamFormBinding viewBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_stream_form, container, false);
        viewBinding = FragStreamFormBinding.bind(rootView);

        setupActionButtons();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mPresenter.destroy();
    }

    @Override
    public void setPresenter(StreamFormContract.StreamFormPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public boolean validateForm() {
        boolean formOk = true;

        // Remove previous errors
        viewBinding.tilName.setError(null);
        viewBinding.tilUrl.setError(null);

        // Validate name
        if (viewBinding.tietName.getText().length() < 3) {
            formOk = false;
            viewBinding.tilName.setError(getString(R.string.error_stream_name));
        }

        // Validate url
        if (viewBinding.tietUrl.getText().length() < 7) {
            formOk = false;
            viewBinding.tilUrl.setError(getString(R.string.error_stream_url));
        }

        return formOk;
    }

    @Override
    public Stream createFromForm() {
        Stream stream = new Stream();
        stream.setName(viewBinding.tietName.getText().toString());
        stream.setUrl(viewBinding.tietUrl.getText().toString());

        return stream;
    }

    @Override
    public void setupActionButtons() {
        viewBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        viewBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    Stream stream = createFromForm();
                    mPresenter.save(stream);

                    getTargetFragment().onActivityResult(
                            getTargetRequestCode(),
                            Activity.RESULT_OK,
                            null
                    );
                    dismiss();
                }
            }
        });
    }
}
