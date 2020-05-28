package si.fri.core;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import si.fri.db.HistoryDAO;

public class AuditInterceptor extends EmptyInterceptor {

    private User user;
    private HistoryDAO hDao;
    private Primer primer = null;
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
        if (entity instanceof Primer) {
            primer = (Primer) entity;
            countDeleted++;
        }
    }

    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) {
        if (entity instanceof Primer) {
            primer = (Primer) entity;
            countFlushDirty++;
        }
        return false;
    }

    public boolean onLoad(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        if (entity instanceof Primer) {
            primer = (Primer) entity;
            countLoad++;
        }
        return false;
    }

    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        if (entity instanceof Primer) {
            primer = (Primer) entity;
            countSave++;
        }
        return false;
    }

    public void afterTransactionCompletion(Transaction tx) {
        if (primer != null) {
            if (countSave > 0) {
                hDao.create(new History(user, "add", primer));
            }
            else if (countDeleted > 0) {
                hDao.create(new History(user, "delete", primer));
            }
            else if (countFlushDirty > 0) {
                hDao.create(new History(user, "update", primer));
            }
        }
    }

}