package org.rtspviewer.streams.form;

import org.rtspviewer.db.Stream;

/**
 *
 * Created by giovanni on 8/07/17.
 */
interface StreamFormContract {

    interface StreamFormPresenter {
        void destroy();

        void save(Stream stream);
    }

    interface StreamFormView {
        void setPresenter(StreamFormPresenter mPresenter);

        boolean validateForm();

        Stream createFromForm();

        void setupActionButtons();
    }
}
