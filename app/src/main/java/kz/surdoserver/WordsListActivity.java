package kz.surdoserver;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubeInitializationResult;

import java.util.List;

import kz.surdoserver.pojo.SurdoWord;
import kz.surdoserver.config.YoutubeConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import kz.surdoserver.adapters.WordsBaseAdapter;

public class WordsListActivity extends YouTubeBaseActivity {

    private static final int RECOVERY_REQUEST = 1;

    private APIInterface apiInterface;
    private YouTubePlayerView youTubeView;
    private YouTubePlayer youTubePlayer;

    private List<SurdoWord> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);

        // get category
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(YoutubeConfig.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayerr, boolean b) {
                youTubePlayer = youTubePlayerr;
            }

            @Override
            public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(WordsListActivity.this, "Youtube Failed!", Toast.LENGTH_SHORT).show();
            }
        });

        final ListView wordsListView = (ListView) findViewById(R.id.words_list);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<SurdoWord>> call = apiInterface.getWordsByCategory(category);
        call.enqueue(new Callback<List<SurdoWord>>() {
            @Override
            public void onResponse(Call<List<SurdoWord>> call, Response<List<SurdoWord>> response) {
                wordList = response.body();
                wordsListView.setAdapter(new WordsBaseAdapter(getApplicationContext(), wordList));
            }

            @Override
            public void onFailure(Call<List<SurdoWord>> call, Throwable t) { call.cancel(); }
        });

        wordsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (youTubePlayer != null) {

                    String lang = getLang();

                    SurdoWord word = wordList.get(position);

                    String link = (word.getVideoLinkRU() != null && !word.getVideoLinkRU().isEmpty())
                            ? word.getVideoLinkRU() : word.getVideoLinkKZ();

                    if ("kz".equals(lang) && word.getVideoLinkKZ() != null && !word.getVideoLinkKZ().isEmpty()) {
                        link = word.getVideoLinkKZ();
                    }

                    if (link == null || link.isEmpty()) {
                        Toast.makeText(getBaseContext(), haveNotVideoLink(lang), Toast.LENGTH_SHORT).show();
                    } else {
                        youTubePlayer.loadVideo(link);
                    }
                }
            }
        });
    }

    private String haveNotVideoLink(String lang) {
        return "ru".equals(lang) ? "Cсылка на видео пустая" : "Бейне сілтемесі бос";
    }

    private String getLang() {
        Resources res = this.getApplicationContext().getResources();
        Configuration conf = res.getConfiguration();
        return conf.locale.getLanguage();
    }
}
