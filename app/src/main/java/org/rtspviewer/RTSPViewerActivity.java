package org.rtspviewer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.rtspviewer.databinding.ActRtspViewerBinding;
import org.rtspviewer.streams.list.StreamsFragment;
import org.rtspviewer.streams.list.StreamsPresenter;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class RTSPViewerActivity extends AppCompatActivity {

    private ActRtspViewerBinding viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(this, R.layout.act_rtsp_viewer);
        setupToolbar();

        // Prepare streams fragment and presenter
        StreamsFragment streamsFragment = new StreamsFragment();
        new StreamsPresenter(streamsFragment);

        // Show player fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, streamsFragment)
                .commit();
    }

    private void setupToolbar() {
        setSupportActionBar(viewBinding.toolbar);
    }
}
