package client;

import _model.AuthToken;
import _result.AllPersonsResult;

public class DataCache {
    private String authToken;
    private String userName;
    private String userPersonId;
    private AllPersonsResult allPersonsResult;

    /************  Singleton  **************/
    private static DataCache instance;
    public static DataCache getInstance() {
        if (instance == null) {
            instance = new DataCache();
        }
        return instance;
    }
    private DataCache() {}
    /****************************************/

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserPersonId() {
        return userPersonId;
    }

    public void setUserPersonId(String userPersonId) {
        this.userPersonId = userPersonId;
    }

    public AllPersonsResult getAllPersonsResult() {
        return allPersonsResult;
    }

    public void setAllPersonsResult(AllPersonsResult allPersonsResult) {
        this.allPersonsResult = allPersonsResult;
    }

    public static void setInstance(DataCache instance) {
        DataCache.instance = instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
