package com.app.gautam.myphotogallery;

import android.net.Uri;
import android.util.Log;
import android.widget.Gallery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gautam on 15-10-2016.
 */

public class FlickerFetcher {
    private static final String TAG = "FlickerFetcher";
    private static final String API_KEY = "562c7b2b56b0f57928a924781801f4c1";
    public byte[] getURLBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }
                int bytesRead = 0;
                byte[] buffer = new byte[1024];
                while ((bytesRead = in.read(buffer)) > 0 ){
                    out.write(buffer, 0, bytesRead);
                }
                out.close();;
                return  out.toByteArray();
            }
        finally {
            connection.disconnect();
        }
    }

    public String getURLString(String urlSpec) throws IOException{
        return new String(getURLBytes(urlSpec));
    }

    public List<GalleryItem> fetchItems(){
        List<GalleryItem> items = new ArrayList<>();

        try{
            String url = Uri.parse("https://api.flickr.com/services/rest/")
                    .buildUpon()
                    .appendQueryParameter("method", "flickr.photos.getRecent")
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", String.valueOf(1))
                    .appendQueryParameter("extras", "url_a")
                    .build().toString();

            String jsonString = getURLString(url);
            Log.d(TAG, jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);
        }catch (IOException ioe){
            Log.e(TAG, "FAiled to fetch",ioe);
        } catch (JSONException e) {
            Log.e(TAG,"failed to parse!!",e);
        }
        return items;
    }

    private void parseItems(List<GalleryItem> items, JSONObject jsonbody)
        throws IOException, JSONException{
        JSONObject photosJSONobject = jsonbody.getJSONObject("photos");
        JSONArray  photosJSONArray = photosJSONobject.getJSONArray("photo");

        for(int i=0;i<photosJSONArray.length();i++){
            JSONObject photoJSONObject = photosJSONArray.getJSONObject(i);

            GalleryItem item = new GalleryItem();
            item.setmID(photoJSONObject.getString("id"));
            item.setmCaption(photoJSONObject.getString("title"));

            if(!photoJSONObject.has("url_s")){
                continue;
            }
            item.setmURL(photoJSONObject.getString("url_s"));
            items.add(item);
        }
    }
}

