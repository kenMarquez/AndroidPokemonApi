package com.bambu.mobile.requestexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bambu.mobile.requestexample.api.PokemonClient;
import com.bambu.mobile.requestexample.api.ServiceGenerator;
import com.bambu.mobile.requestexample.model.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://pokeapi.co/api/v2/pokemon/";
    private final String USER_AGENT = "Mozilla/5.0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getPokemonById(500);
        getPokemonByIdRetrofit(1);
        getPokemonByIdRetrofit2(1);
    }


    public void getPokemonById(int pokemonId) {

        new AsyncTask<Integer, Void, String>() {


            @Override
            protected String doInBackground(Integer... pokemonId) {

                StringBuilder result = new StringBuilder();
                try {
                    URL url = new URL(BASE_URL + pokemonId[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
//                    httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
                    int responseCode = httpURLConnection.getResponseCode();
                    System.out.println("code: " + responseCode);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    bufferedReader.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result.toString();
            }

            @Override
            protected void onPostExecute(String result) {
                System.out.println(result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String name = jsonObject.getString("name");
                    log(name);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onPostExecute(result);
            }
        }.execute(pokemonId);

    }

    public void getPokemonByIdRetrofit(int pokemonId) {
        PokemonClient pokemonClient = ServiceGenerator.createService(PokemonClient.class);

        pokemonClient.getPokemonById(pokemonId).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                log("code: " + response.code());
                log("body: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                log("fail1");
            }
        });

    }


    public void getPokemonByIdRetrofit2(int pokemonId) {
        PokemonClient pokemonClient = ServiceGenerator.createService(PokemonClient.class);

        pokemonClient.getPokemonById2(pokemonId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                log("fail2");
            }

        });

    }


    public void log(String content) {
        Log.d("myLog", content);
    }


}
