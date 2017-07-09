package org.rtspviewer.db;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 *
 * Created by giovanni on 8/07/17.
 */
public class StreamService {

    private Realm realm;

    public StreamService() {
        realm = Realm.getDefaultInstance();
    }

    public void close() {
        realm.close();
    }

    public void save(Stream stream) {
        realm.beginTransaction();
        realm.copyToRealm(stream);
        realm.commitTransaction();
    }

    public Stream find(String id) {
        return realm.where(Stream.class).equalTo("id", id).findFirst();
    }

    public RealmResults<Stream> findAll() {
        return realm.where(Stream.class).findAll();
    }
}
