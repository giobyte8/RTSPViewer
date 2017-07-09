package org.rtspviewer.streams.list;

import org.rtspviewer.db.Stream;

import io.realm.RealmResults;

/**
 *
 * Created by giovanni on 8/07/17.
 */
interface StreamsContract {

    interface Presenter {
        void destroy();

        void loadStreams();
    }

    interface View {
        void setPresenter(Presenter mPresenter);

        void goToStream(Stream stream);

        void setupAddStreamButton();

        void updateStreamsList(RealmResults<Stream> streams);
    }
}
