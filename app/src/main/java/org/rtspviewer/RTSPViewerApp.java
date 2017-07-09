package org.rtspviewer;

import android.app.Application;

import io.realm.Realm;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class RTSPViewerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());
    }
}
