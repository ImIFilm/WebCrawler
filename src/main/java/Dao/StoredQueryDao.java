package Dao;

import Model.Query;
import Model.Result;
import Model.StoredQuery;
import Utilities.SearchPattern;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StoredQueryDao extends GenericDao<StoredQuery> {

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
            return true;
        }catch (PersistenceException e){
            return false;
        }
    }

    public List<Result> downloadAllResults(StoredQuery query){
        try{
            return currentSession().createQuery(
                    "Select r FROM Result r " +
                            "WHERE r.storedQuery.url = :url and " +
                            "r.storedQuery.sentenceRegex = :sentenceRegex and " +
                            "r.storedQuery.forbiddenRegex = :forbiddenRegex and " +
                            "r.storedQuery.depth = :depth and " +
                            "r.storedQuery.subdomains = :subdomains", Result.class)
                    .setParameter("url",query.getUrl())
                    .setParameter("sentenceRegex",query.getSentencePattern().getPatternRegex())
                    .setParameter("forbiddenRegex",query.getForbiddenPattern().getPatternRegex())
                    .setParameter("depth",query.getDepth())
                    .setParameter("subdomains",query.getSubdomains())
                    .getResultList();
        }catch (PersistenceException e){
            return Collections.<Result>emptyList();
        }
    }

    public StoredQuery getQuery(Query query){
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
                return null;
            }
            StoredQuery result = results.get(0);
            result.setSentencePattern(new SearchPattern(result.getSentenceRegex()));
            result.setForbiddenPattern(new SearchPattern(result.getForbiddenRegex()));
            return result;
        }catch (PersistenceException e){
            return null;
        }
    }
}
