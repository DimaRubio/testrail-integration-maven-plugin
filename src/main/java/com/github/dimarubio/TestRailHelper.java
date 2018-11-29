package com.github.dimarubio;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * @numberOfTestRailRun = 0 - test result won't send to TestRail. This value is got from system variable "numberOfTestRailRun"
 *                              or from plugin configuration and should contains number of existing test Run in TestRail
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
    }

    public APIClient getClient() {
        return client;
    }

    public Integer getNumberOfTestRailRun() {
        return numberOfTestRailRun;
    }

    public ArrayList<Long> getTestCasesIDFromRun() throws IOException, APIException {
        ArrayList<Long> listTestsId = new ArrayList<Long>();
        if ( numberOfTestRailRun > 0) {
            JSONArray testsArray = (JSONArray) client.sendGet("get_tests/" + getNumberOfTestRailRun());
            for (Object testDetails : testsArray) {
                JSONObject test = (JSONObject) testDetails;
                Long id = (Long) test.get("case_id");
                listTestsId.add(id);
            }
        }
        return listTestsId;
    }

}
