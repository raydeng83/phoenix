package com.phoenix.external.ssoapp.utility;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;

public class MyJSONResponseHandler implements ResponseHandler<JSONObject> {
    @Override
    public JSONObject handleResponse(final HttpResponse response) {
        int status = response.getStatusLine().getStatusCode();
        JSONObject returnData = new JSONObject();
        JSONParser parser = new JSONParser();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            try {

                if(null == entity){
                    returnData.put("status_code", "1");
                    returnData.put("error_message", "null Data Found");
                } else {
                    returnData = (JSONObject) parser.parse(EntityUtils.toString(entity));
                }
            } catch (ParseException | IOException | org.json.simple.parser.ParseException e) {
                returnData.put("status_code", "1");
                returnData.put("error_message", e.getMessage());
            }
        } else {
            returnData.put("status_code", "1");
            returnData.put("error_message", "Unexpected response status: " + status);
        }

        return returnData;
    }
}
