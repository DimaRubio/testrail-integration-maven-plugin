package com.github.dimarubio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * @numberOfTestRailRun = 0 - test result won't send to TestRail. This value is got from system variable "numberOfTestRailRun"
 *                              and should contains number of existing test Run
  */
public class TestRailHelper {
    private Map testResultData = new HashMap();
    private APIClient client = null;
    private Integer numberOfTestRailRun = 0;

    public TestRailHelper(String url, String userEmail, String password, String numberOfTestRailRun) {
        client = new APIClient(url);
        client.setUser(userEmail);
        client.setPassword(password);
        this.numberOfTestRailRun = Integer.parseInt(numberOfTestRailRun);
//        setNumberOfTestRailRun();
    }

    public APIClient getClient() {
        return client;
    }

    public Integer getNumberOfTestRailRun() {
        return numberOfTestRailRun;
    }

//    public void setNumberOfTestRailRun() {
//        String numberOfTestRailRun = System.getProperty("numberOfTestRailRun");
//        if (numberOfTestRailRun  != null ) {
//            this.numberOfTestRailRun = Integer.parseInt(System.getProperty("numberOfTestRailRun"));
//        }
//    }

    public ArrayList<Long> getTestCasesIDFromRun() throws IOException, APIException {
        ArrayList<Long> listTestsId = new ArrayList<Long>();
        JSONArray testsArray = (JSONArray) client.sendGet("get_tests/" + getNumberOfTestRailRun());
        for (Object testDetails : testsArray) {
            JSONObject test = (JSONObject) testDetails;
            Long id = (Long) test.get("case_id");
            listTestsId.add(id);
        }
        return listTestsId;
    }

}
