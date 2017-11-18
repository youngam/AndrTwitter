package misiulia.alex.dev.andrtwitter.network;

import static java.lang.String.format;
import static misiulia.alex.dev.andrtwitter.oauth.OAuthConstants.HEADER_AUTHORIZATION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.oauth.OAuth1aHeaders;

import misiulia.alex.dev.andrtwitter.entity.Tweet;
import misiulia.alex.dev.andrtwitter.entity.User;
import misiulia.alex.dev.andrtwitter.oauth.AuthPreference;

// TODO LM_ALL do it singleton
public class HttpClient {
    private static final String API_URL = "https://api.twitter.com/1.1";
    private static final String GET = "GET";
    private static final String EXTENDED_MODE_QUERY = "tweet_mode=extended";

    private Gson mGson = new Gson();

    public User readUserInfo(long userId) {
        String requestUrl = getUrl(format(Locale.ROOT, "%s/%s=%d", API_URL, "users/show.json?user_id", userId));
        String response = getResponseString(requestUrl);

        User user = mGson.fromJson(response, User.class);
        Log.d("LmTest", "response : " + response);
        return user;
    }

    public Collection<Tweet> readTweets(long userId) {
        String requestUrl = getUrl(format(Locale.ROOT, "%s/%s=%d", API_URL, "statuses/user_timeline.json?user_id", userId));
        String response = getResponseString(requestUrl);

        Type listType = new TypeToken<ArrayList<Tweet>>(){}.getType();
        Log.d("LmTest", "response : " + response);
        List<Tweet> tweets = mGson.fromJson(response, listType);
        return tweets;
    }

    public Collection<User> readUsers(String query) {
        String encodedQuery;

        try {
            encodedQuery = URLEncoder.encode(query, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Can't encode query: " + query, e);
        }
        String requestUrl = getUrl(format(Locale.ROOT, "%s/%s=%s", API_URL, "users/search.json?q", encodedQuery));
        String response = getResponseString(requestUrl);

        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        Log.d("LmTest", "response : " + response);
        List<User> users = mGson.fromJson(response, listType);
        return users;
    }

    private String getUrl(String url) {
        return url + "&" + EXTENDED_MODE_QUERY;
    }

    @NonNull
    private String getResponseString(String requestUrl) {
        String response = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty(HEADER_AUTHORIZATION, getAuthHeader(GET, requestUrl));
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
        return response;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
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
