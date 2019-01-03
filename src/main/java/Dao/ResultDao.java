package Dao;

import Model.GivenQuery;
import Model.Query;
import Model.Result;
import Model.StoredQuery;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;


public class ResultDao extends GenericDao<Result> {

    //powinno dzialac
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

    //powinno dzialac
    public Boolean exists(Result result){
        try{
            List<Result> results = currentSession().createQuery(
                    "Select r FROM Result r " +
                            "WHERE r.storedQuery.url = :url and r.sentence = :sentence and " +
                            "r.storedQuery.sentenceRegex = :sentenceRegex and " +
                            "r.storedQuery.forbiddenRegex = :forbiddenRegex and " +
                            "r.storedQuery.depth = :depth and " +
                            "r.storedQuery.subdomains = :subdomains", Result.class)
                    .setParameter("sentence",result.getSentence())
                    .setParameter("url",result.getStoredQuery().getUrl())
                    .setParameter("sentenceRegex",result.getStoredQuery().getSentenceRegex())
                    .setParameter("forbiddenRegex",result.getStoredQuery().getForbiddenRegex())
                    .setParameter("depth",result.getStoredQuery().getDepth())
                    .setParameter("subdomains",result.getStoredQuery().getSubdomains())
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
