package com.mridul.managesmartbin.placepicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * For uploading data of a newly installed bin on server...
 * This class is used in PickerHome .
 */

public class BackgroundWorkerPicker extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;
    BackgroundWorkerPicker(Context context1){
        context = context1;
    }

    public BackgroundWorkerPicker() {

    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];

        String bin_register_url = "http://172.16.181.12/install_bin.php";

        if (type.equals("installBinData")){
            String placeId = params[1];
            String name = params[2];
            String address = params[3];
            String lat = params[4];
            String lng = params[5];

            URL url = null;
            try {
                url = new URL(bin_register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(placeId, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("mob_no", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                        + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&"
                        + URLEncoder.encode("lng", "UTF-8") + "=" + URLEncoder.encode(lng, "UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null ;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Bin Installation Status");
    }

    @Override
    protected void onPostExecute(String result) {

        alertDialog.setMessage(result);
        alertDialog.show();
        // AfterLoginLayout();
        //alertDialog.dismiss();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}


