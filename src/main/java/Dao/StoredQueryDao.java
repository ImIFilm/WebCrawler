package Dao;

import Model.GivenQuery;
import Model.Query;
import Model.Result;
import Model.StoredQuery;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class StoredQueryDao extends GenericDao<StoredQuery>{

    //powinno dzialac
    public StoredQuery create(Query query) {
        try {
            StoredQuery storedQuery = new StoredQuery(query);
            save(storedQuery);
            return storedQuery;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean exists(Query query){
        try{
            List<StoredQuery> results = currentSession().createQuery(
                    "Select sq FROM StoredQuery sq " +
                            "WHERE sq.url = :url and " +
                            "sq.sentenceRegex = :sentenceRegex and " +
                            "sq.forbiddenRegex = :forbiddenRegex and " +
                            "sq.depth = :depth and " +
                            "sq.subdomains = :subdomains", StoredQuery.class)
                    .setParameter("url",query.getUrl())
                    .setParameter("sentenceRegex",query.getSentencePattern().getPatternRegex())
                    .setParameter("forbiddenRegex",query.getForbiddenPattern().getPatternRegex())
                    .setParameter("depth",query.getDepth())
                    .setParameter("subdomains",query.getSubdomains())
                    .getResultList();
            if(results.isEmpty()){
                return false;

            }
        }catch (PersistenceException e){
            return false;
        }
        return true;
    }
}
