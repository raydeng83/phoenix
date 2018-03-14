package com.phoenix.external.ssoapp.fr.openam.impl;

import com.phoenix.external.ssoapp.fr.openam.AMUserService;
import com.phoenix.external.ssoapp.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class AMUserServiceImpl implements AMUserService {

    @Value("${openam-server.address}")
    private String openamUrl;

    @Override
    public void createUser(User user) {
        String createUserUrl = openamUrl+"/json/realms/phoenix-dev/selfservice/userRegistration?_action=submitRequirements";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(createUserUrl);
            StringEntity entity = new StringEntity("{ \"input\": { \"user\": { \"username\": \""+user.getUsername()+"\", \"givenName\": \""+user.getFirstName()+"\", \"sn\": \""+user.getLastName()+"\", \"mail\":\""+user.getEmail()+"\", \"userPassword\": \""+user.getPassword()+"\", \"inetUserStatus\": \"Active\" } } }");
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(entity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            System.out.println(httpResponse.getEntity().getContent());
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String authenticateUser(String username, String password) {
        boolean status = false;

        String tokenId = null;

        String authenticateUserUrl = openamUrl+"/json/realms/phoenix-dev/authenticate";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(authenticateUserUrl);
            httpPost.setHeader("X-OpenAM-Username", username);
            httpPost.setHeader("X-OpenAM-Password", password);
            httpPost.setHeader("Content-Type", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpPost);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            JSONObject o = new JSONObject(result.toString());

            if (o.has("tokenId")) {
                tokenId = (String) o.getString("tokenId");
            } else {
                tokenId = null;
            }

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tokenId;
    }

    @Override
    public JSONObject invalidateSession(String tokenId) {
        JSONObject result = null;

        String authenticateUserUrl = openamUrl+"/json/sessions?_action=logout";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(authenticateUserUrl);
            httpPost.setHeader("iplanetDirectoryPro", tokenId);
            httpPost.setHeader("Cache-Control", "no-cache");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Accept-API-Version", "resource=2.0");

            HttpResponse httpResponse = httpClient.execute(httpPost);

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            StringBuffer rb = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                rb.append(line);
            }

           result = new JSONObject(rb.toString());



        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
