
import Controller.AppController;
import Dao.ResultDao;
import Dao.StoredQueryDao;
import Model.GivenQuery;
import Model.Query;
import Model.Result;
import Model.StoredQuery;
import Session.SessionService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Run {

    private Stage primaryStage;

//    @Override
//    public void start(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("Web Crawler");
//        new AppController(primaryStage).initRootLayout();
//    }

    public static void main(String[] args) {
//        launch(args);
        System.out.println("siema");
        SessionService.openSession();
        Session session = SessionService.getSession();
        StoredQueryDao storedQueryDao = new StoredQueryDao();
        ResultDao resultDao = new ResultDao();
        Query tmp = new GivenQuery("http://onet.pl", "Polska", "", 0, true);
        Query tmp2 = new GivenQuery("http://onet.pl", "Polska", "", 2, true);
        String sentence = "ala";
        StoredQuery query = storedQueryDao.create(tmp);
        Result result = resultDao.create(query,sentence);

        if(resultDao.exists(result)){
            System.out.println("juz jest");
        }
        else{
            System.out.println("nie ma");
        }

        if(storedQueryDao.exists(tmp2)){
            System.out.println("juz jest");
        }
        else{
            System.out.println("nie ma");
        }



        SessionService.closeSession();

    }
}

//public class Run extends Application {
//
//    private Stage primaryStage;
//
//    @Override
//    public void start(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("Web Crawler");
//        new AppController(primaryStage).initRootLayout();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
