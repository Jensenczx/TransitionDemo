package com.chenjensen.transitiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import com.chenjensen.transitiondemo.transition.HTTransitionListener;
import com.chenjensen.transitiondemo.transition.TransitionHelper;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ImageView linearLayout;
    private static HTTransitionListener mListener;
    private List<TransitionHelper> helpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        if(mListener != null){
            helpers = mListener.getTransitionHelpers();
        }
        startAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static void setListener(HTTransitionListener listener){
        mListener = listener;
    }

    private void initView(){
        linearLayout = (ImageView) findViewById(R.id.iv_main2);
    }

    private void startAnimation() {
        if(helpers == null)
            return;
        for(int i = 0; i < helpers.size(); i++){
            TransitionHelper helper = helpers.get(i);
            helper.setActivity(this);
            helper.setNextView(linearLayout);
            helper.startEnterAnimation();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(helpers == null)
                return true;
            for(int i = 0; i < helpers.size(); i++){
                TransitionHelper helper = helpers.get(i);
                helper.exitAnimation();
            }
        }
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }

}
