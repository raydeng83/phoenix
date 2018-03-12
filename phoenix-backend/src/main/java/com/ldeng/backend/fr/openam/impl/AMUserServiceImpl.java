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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AMUserServiceImpl implements AMUserService {

    @Value("${openam-server.address}")
    private String openamUrl;

    @Override
    public void createUser(User user) {
        String token = "";
        String createUserUrl = openamUrl+"/json/realms/phoenix-dev/selfservice/userRegistration?_action=submitRequirements";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler responseHandler = (ResponseHandler) new MyJSONResponseHandler();

        try {
            HttpPost httpPost = new HttpPost(createUserUrl);
            StringEntity entity = new StringEntity("{ \"input\": { \"user\": { \"username\": \""+user.getUsername()+"\", \"givenName\": \"Demo User\", \"sn\": \"User\", \"mail\":\"demo@example.com\", \"userPassword\": \"password\", \"inetUserStatus\": \"Active\" } } }");
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(entity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            System.out.println(httpResponse.getEntity().getContent());
            System.out.println(httpResponse);
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
    public void authenticateUser(User user) {

    }
}
