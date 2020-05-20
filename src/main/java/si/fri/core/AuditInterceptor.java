package si.fri.core;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import si.fri.db.HistoryDAO;

public class AuditInterceptor extends EmptyInterceptor {

    private User user;
    private HistoryDAO hDao;
    private Primer primer;
    int countDeleted = 0;
    int countFlushDirty = 0;
    int countSave = 0;
    int countLoad = 0;

    public AuditInterceptor(User user, HistoryDAO hDao) {
        this.user = user;
        this.hDao = hDao;
    }

    public void onDelete(Object entity,
                         Serializable id,
                         Object[] state,
                         String[] propertyNames,
                         Type[] types) {
        primer = (Primer) entity;
        countDeleted++;
    }

    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) {
        primer = (Primer) entity;
        countFlushDirty++;
        return false;
    }

    public boolean onLoad(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        primer = (Primer) entity;
        countLoad++;
        return false;
    }

    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        countSave++;
        return false;
    }

    public void afterTransactionCompletion(Transaction tx) {
        if (countSave > 0) {
            History history = new History(user, "add", primer);
            hDao.create(history);
        }
    }

}