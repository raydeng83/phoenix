package com.ldeng.backend.fr.openam.impl;

import com.ldeng.backend.fr.openam.AMUserService;
import com.ldeng.backend.model.User;
import com.ldeng.backend.utility.MyJSONResponseHandler;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
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
            StringEntity entity = new StringEntity("{ \"input\": { \"user\": { \"username\": \""+user.getUsername()+"\", \"givenName\": \"Demo User\", \"sn\": \"User\", \"mail\":\"demo@example.com\", \"userPassword\": \"password\", \"inetUserStatus\": \"Active\" } } }");
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

        String tokenId = "";

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

            tokenId = (String) o.getString("tokenId");

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
}
