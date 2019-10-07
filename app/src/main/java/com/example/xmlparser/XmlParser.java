/*
Author: Anwar Abdalbari
Date: Sept. 30
Purpose: XML parser
 */

package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class XmlParser extends AppCompatActivity {
    LinearLayout linearLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parser);


       //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutParser);
        XmlResourceParser parser = getResources().getXml(R.xml.weather);
        //pass the parser to process it
        try {
            processData(parser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }

    private void processData(XmlResourceParser parser) throws IOException, XmlPullParserException {
        int eventType = -1;


        while (eventType != XmlResourceParser.END_DOCUMENT){//1
         if(eventType == XmlResourceParser.START_TAG){//2
          String localName =  parser.getName();
          if(localName.equals("location")){
              String city = parser.getAttributeValue(null,"city");
              String temperature = parser.getAttributeValue(null,"temperature");
              String weather = parser.getAttributeValue(null,"weather");
              Log.d("testParser",city+ " "+ temperature+ " "+ weather );
              printValues(city,temperature,weather);}
          }
            eventType=parser.next();
         }
        }

    private void printValues(String city, String temperature, String weather) {
        LinearLayout weatherData = (LinearLayout) new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT);

        params.weight=1;
        TextView cityText = new TextView(this);

        TextView TemperatureText = new TextView(this);
        TextView weatherText = new TextView(this);


        LinearLayout linearLayout =findViewById(R.id.linearLayoutParser);

        cityText.setLayoutParams(params);
        cityText.setText(city);
        cityText.setTextSize(24);
        cityText.setTextColor(Color.RED);
        cityText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        weatherData.addView(cityText);

        TemperatureText.setLayoutParams(params);
        TemperatureText.setText(temperature);
        TemperatureText.setTextSize(24);
        TemperatureText.setTextColor(Color.RED);
        TemperatureText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        weatherData.addView(TemperatureText);

        weatherText.setLayoutParams(params);
        weatherText.setText(weather);
        weatherText.setTextSize(24);
        weatherText.setTextColor(Color.RED);
        weatherText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        weatherData.addView(weatherText);

        linearLayout.addView(weatherData);

    }


}
