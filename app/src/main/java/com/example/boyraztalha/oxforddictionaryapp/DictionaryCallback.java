package com.example.boyraztalha.oxforddictionaryapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DictionaryCallback extends AsyncTask<String,Integer,String> {

    String app_id = "8e915110";
    String app_key = "747a5e1ae90752eb66f8b124cd8a79ef";
    Context context;
    TextView textView;

    DictionaryCallback(Context context, TextView textView){
        this.context = context;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray results =  jsonObject.getJSONArray("results");

            JSONObject resultObject = results.getJSONObject(0);
            JSONArray lexialarray = resultObject.getJSONArray("lexicalEntries");

            JSONObject entriesObject = lexialarray.getJSONObject(0);
            JSONArray entriesarray = entriesObject.getJSONArray("entries");

            JSONObject sensesObject = entriesarray.getJSONObject(0);
            JSONArray sensesarray = sensesObject.getJSONArray("senses");

            JSONObject definitionObject = sensesarray.getJSONObject(0);
            JSONArray definitionarray = definitionObject.getJSONArray("definitions");

            String definition = definitionarray.getString(0);
            textView.setText(definition);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
