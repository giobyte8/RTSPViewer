package org.rtspviewer.player;

import org.rtspviewer.db.Stream;

/**
 *
 * Created by giovanni on 8/07/17.
 */
interface PlayerContract {

    interface Presenter {
        void destroy();

        void loadStream(String id);
    }

    interface View {
        void setPresenter(Presenter mPresenter);

        void playStream(Stream stream);
    }
}
