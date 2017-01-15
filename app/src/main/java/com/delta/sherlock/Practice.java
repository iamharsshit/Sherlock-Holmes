package com.delta.sherlock;

import android.renderscript.ScriptGroup;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Harshit Bansal on 1/9/2017.
 */

public class Practice {

    private static final String TAG=Practice.class.getSimpleName();
    public Practice(){

    }
    private String makeServiceCall(String reqUrl){
        String response=null;
        try{
            URL url=new URL(reqUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in=new BufferedInputStream(conn.getInputStream());
            response=convertStreamToString(in);
        }

        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String convertStreamToString(InputStream in) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        String line;
        StringBuilder sb=new StringBuilder();
        try{
           while((line=reader.readLine())!=null){
               sb.append(line).append("\n");
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try{
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
