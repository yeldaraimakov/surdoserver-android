package kz.surdoserver;

import java.util.List;

import kz.surdoserver.pojo.SurdoWord;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface APIInterface {

    @GET("/api/words/{cat}")
    Call<List<SurdoWord>> getWordsByCategory(@Path("cat") String category);
}