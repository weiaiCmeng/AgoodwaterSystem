package contract;

import com.agoodwater.system.bean.QueryDetailsBean;

/**
 * Created by shiqiang on 2017/4/21.
 */

public class QueryDetailsContract {

    
    

public interface View{

    String getUserName();
    String getStartTime();
    String getEndTime();
    String getWork();
    String getCompany();
    int getPager();

    void loginSuceess(QueryDetailsBean queryDetailsBean);
    void loginError(String error) ;

}

public interface Presenter{

    void loginNet();


}




}