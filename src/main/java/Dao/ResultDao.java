package Dao;

import Model.Query;
import Model.Result;
import Model.StoredQuery;

import javax.persistence.PersistenceException;
import java.util.List;


public class ResultDao extends GenericDao<Result> {

    public Result create(StoredQuery storedQuery, String sentence) {
        try {
            Result result = new Result(storedQuery,sentence);
            save(result);
            return result;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean exists(Query query, String sentence){
        try{
            List<Result> results = currentSession().createQuery(
                    "Select r FROM Result r " +
                            "WHERE r.storedQuery.url = :url and r.sentence = :sentence and " +
                            "r.storedQuery.sentenceRegex = :sentenceRegex and " +
                            "r.storedQuery.forbiddenRegex = :forbiddenRegex and " +
                            "r.storedQuery.depth = :depth and " +
                            "r.storedQuery.subdomains = :subdomains", Result.class)
                    .setParameter("sentence",sentence)
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

    public Boolean exists(Result result){
        return exists(result.getStoredQuery(),result.getSentence());
    }

}
