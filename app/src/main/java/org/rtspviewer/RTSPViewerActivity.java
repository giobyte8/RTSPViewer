package org.rtspviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.rtspviewer.player.ViewerFragment;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class RTSPViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rtsp_viewer);

        // Show player fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ViewerFragment())
                .commit();
    }
}
