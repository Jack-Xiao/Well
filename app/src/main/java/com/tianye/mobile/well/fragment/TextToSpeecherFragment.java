package com.tianye.mobile.well.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tianye.mobile.well.R;
import com.tianye.mobile.well.util.TextSpeaker;
import com.tianye.mobile.well.util.TextSpeaker.Type;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2015/3/19.
 */
public class TextToSpeecherFragment extends BaseFragment {

    private TextSpeaker mTextSpeaker1;
    private TextSpeaker mTextSpeaker2;
    @InjectView(R.id.et_content1) EditText mContent1;
    @InjectView(R.id.et_content2) EditText mContent2;

    private Button btnSpeaker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextSpeaker1 = new TextSpeaker(getActivity(), Type.EN);
        mTextSpeaker2 = new TextSpeaker(getActivity(), Type.CHINESE);
        //TextToSpeech
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.text_to_speech,null);
        ButterKnife.inject(this,contentView);
        return contentView;
    }

    @OnClick(R.id.btn_speaker1) void speak1(){
        mTextSpeaker1.speak(mContent1.getText().toString());
    }

    @OnClick(R.id.btn_speaker2) void speak2(){
        mTextSpeaker2.speak(mContent2.getText().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
