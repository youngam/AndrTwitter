package misiulia.alex.dev.andrtwitter;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class UserInfoActivity extends BaseActivity {
    private static String CHUCK_NAME = "AndroidLearning";
    private static String CHUCK_NIK = "@it_pro_learning";
    private int mHoursCounter = 20;

    public static final Random RANDOM = new Random();


    private RecyclerView mRecyclerView;
    private FloatingActionButton mCreateTweetFab;
    private TweetAdapter mTweetAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity_layout);

        mRecyclerView = findViewById(R.id.tweets_recycler_view);
        mCreateTweetFab = findViewById(R.id.add_tweet_fab);
        mCreateTweetFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateTweetDialog();
            }
        });

        // for smooth scroll inside NestedScrollView
        ViewCompat.setNestedScrollingEnabled(mRecyclerView, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        mTweetAdapter = new TweetAdapter();
        mRecyclerView.setAdapter(mTweetAdapter);

        requestUserInfo();
    }

    private void showCreateTweetDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.create_tweet_dialog_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText contentEditText = dialogView.findViewById(R.id.tweet_content_edit_text);

        dialogBuilder.setTitle("Что происходит?");
        dialogBuilder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String content = contentEditText.getText().toString();
                Tweet newTweet = new Tweet(CHUCK_NAME, CHUCK_NIK, "5 sec", content, false);
                mTweetAdapter.addTweet(newTweet);
            }
        });
        dialogBuilder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private void requestUserInfo() {
        new AsyncTask<Void, Void, List<Tweet>>() {
            @Override
            protected List<Tweet> doInBackground(Void... voids) {
                List<Tweet> list = asList(
                        getTweet("Урок 1\n" +
                                "Установка Android Studio. Создание Hello world."),
                        getTweet("Урок 2\n" +
                                "Структура проекта. Activity, AndroidManifest."),
                        getTweet("Урок 3\n" +
                                "Типы Layout: Linear, Relative, Frame."),
                        getTweet("Урок 4\n" +
                                "Views: Button, TextView, EditText. Доступ к View из Java кода."),
                        getTweet("Урок 5\n" +
                                "Практика. Создание экрана информации о пользователе."),
                        getTweet("Урок 6\n" +
                                "Графика. Работа с PNG иконками. Знакомство со шрифтами. Создание иконок с помощью шрифта Font-Awesome."),
                        getTweet("Урок 7\n" +
                                "Практика. Создание макета для элемента списка твитов."),
                        getTweet("Урок 8\n" +
                                "RecyclerView. Принцип работы. Adapter паттерн."),
                        getTweet("Урок 9\n" +
                                "Практика. Добавление твитов в RecyclerView, используя сгенерированные объекты."),
                        getTweet("Урок 10\n" +
                                "Практика. Навигация между Активити, переход на информацию о пользователе."),
                        getTweet("Урок 11\n" +
                                "Asynctask. Что такое Thread? Почему нельзя всё сделать в одном Thread?"),
                        getTweet("Урок 12\n" +
                                "Практика. Добавление авторизации, используя twitter-kit библиотеку."),
                        getTweet("Урок 13\n" +
                                "HTTP. Как сделать GET, POST запросы. Считывание ответа, преобразование к строке."),
                        getTweet("Урок 14\n" +
                                "Формат обмена данными JSON. Преобразование строки к формату JsonObject, к обычному Java объекту."),
                        getTweet("Урок 15\n" +
                                "Практика. Добавление HTTP-запросов для считывания информации о пользователе."),
                        getTweet("Урок 16\n" +
                                "Практика. Добавление HTTP-запросов для считывания твитов пользователя."),
                        getTweet("Урок 17\n" +
                                "Обработка ошибок. Показ сообщения об ошибке пользователю.")
                );
                Collections.reverse(list);
                return list;
            }

            @Override
            protected void onPostExecute(List<Tweet> tweets) {
                super.onPostExecute(tweets);
                mTweetAdapter.setItems(tweets);
            }
        }.execute();
    }

    private Tweet getTweet(String content) {
        return new Tweet(CHUCK_NAME, CHUCK_NIK, mHoursCounter-- + " h", content);
    }
}
