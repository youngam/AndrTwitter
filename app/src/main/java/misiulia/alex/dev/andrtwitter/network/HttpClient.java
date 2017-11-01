package misiulia.alex.dev.andrtwitter.network;

import static java.lang.String.format;
import static misiulia.alex.dev.andrtwitter.oauth.OAuthConstants.HEADER_AUTHORIZATION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import android.util.Log;

import com.google.gson.Gson;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aHeaders;

import misiulia.alex.dev.andrtwitter.entity.Profile;
import misiulia.alex.dev.andrtwitter.oauth.AuthPreference;

public class HttpClient {
    private static final String API_URL = "https://api.twitter.com/1.1";
    private static final String GET = "GET";

    private Gson mGson = new Gson();

    public Profile readProfile(long userId) {
        String methodUrl = format(Locale.ROOT, "%s/%s=%d", API_URL, "users/show.json?user_id", userId);
        String response = null;
        try {
            URL url = new URL(methodUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty(HEADER_AUTHORIZATION, getAuthHeader(GET, methodUrl));
            connection.connect();
            InputStream in;
            int status = connection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                in = connection.getErrorStream();
            } else {
                in = connection.getInputStream();
            }

            response = convertStreamToString(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Profile profile = mGson.fromJson(response, Profile.class);
        Log.d("LmTest", "response : " + response);
        return profile;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    private String getAuthHeader(String method, String url) {
        TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();
        TwitterSession session = AuthPreference.getInstance().getTwitterSession();
        return new OAuth1aHeaders().getAuthorizationHeader(authConfig,
                session.getAuthToken(), null, GET, url, null);
    }
}
