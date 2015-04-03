package com.tianye.mobile.well.util;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by lenovo on 2015/3/26.
 */
public class TextSpeaker {
    private Context context;
    private TextToSpeech tts;
    public static final int  CHINESE = 0;
    public static final int EN = 1;
    private final Locale mType;

    public TextSpeaker(final Context context,Type type){
        this.context = context;
        if(type == Type.CHINESE){
            this.mType  = Locale.CANADA;
        }else{
            this.mType = Locale.UK;
        }

        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(mType);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        //Toast.makeText(context, "Language is not available.",Toast.LENGTH_SHORT).show();
                        ToastUtil.showLong("Language is not available.");
                    }
                }
            }
        });
    }
    public void speak(String text){
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    public enum Type{
        CHINESE,EN
    }
}
