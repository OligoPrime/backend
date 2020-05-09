package si.fri.core;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class AuditInterceptor extends EmptyInterceptor {

    private String username;

    public AuditInterceptor(String username) {
        this.username = username;
    }

    public void onDelete(Object entity,
                         Serializable id,
                         Object[] state,
                         String[] propertyNames,
                         Type[] types) {
        // do nothing
        System.out.println("DELETED");
    }

    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types) {
        System.out.println("ONFLUSHDIRTY");
        return false;
    }

    public boolean onLoad(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        System.out.println("ONLOAD");
        return false;
    }

    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        System.out.println("ONSAVE");
        return false;
    }

    public void afterTransactionCompletion(Transaction tx) {
        System.out.println("AFTERTRANSACTIONCOMPLETED");
        System.out.printf("User: %s%n", username);
    }

}