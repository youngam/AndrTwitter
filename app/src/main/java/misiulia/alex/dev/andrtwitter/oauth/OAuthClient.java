package misiulia.alex.dev.andrtwitter.oauth;

import static misiulia.alex.dev.andrtwitter.oauth.OAuthConstants.HEADER_AUTHORIZATION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

import okio.ByteString;

public class OAuthClient {
    public static final String AUTH_URL = "api.twitter.com/oauth2/token";
    public static final String BASE_HOST_URL = "https://" + AUTH_URL;
    private static final String CONSUMER_KEY = "yHP7WOGyapkv7hlAsjO6zOURt";
    private static final String CONSUMER_SECRET = "YZHL3AP029AO2ycdb0kTJ9Iz8Lb0v4cFsfKZRKdQaD2Cnckgtj";

    public void connect() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String response = null;
                try {
                    URL url = new URL(BASE_HOST_URL);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty(HEADER_AUTHORIZATION, getAuthHeader());
                    connection.setRequestMethod("POST");
                    connection.connect();
                    InputStream in;
                    int status = connection.getResponseCode();
                    if(status != HttpURLConnection.HTTP_OK)
                        in = connection.getErrorStream();
                    else
                        in = connection.getInputStream();

                    response = convertStreamToString(in);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Log.d("LmTest", "response: " + response);
                return null;
            }
        }.execute();
    }

    private String getAuthHeader() {
        final ByteString string = ByteString.encodeUtf8(
                UrlUtils.percentEncode(CONSUMER_KEY)
                        + ":"
                        + UrlUtils.percentEncode(CONSUMER_SECRET));

        return OAuthConstants.AUTHORIZATION_BASIC + " " + string.base64();
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
}
