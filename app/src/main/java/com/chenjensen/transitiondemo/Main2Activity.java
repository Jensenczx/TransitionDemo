package com.chenjensen.transitiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import com.chenjensen.transitiondemo.transition.HTTransitionListener;
import com.chenjensen.transitiondemo.transition.TransitionHelper;

public class Main2Activity extends AppCompatActivity {

    private ImageView linearLayout;
    private static HTTransitionListener mListener;
    private TransitionHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        if(mListener != null){
            helper = mListener.getTransitionHelper();
            helper.setActivity(this);
        }
        startAnimation();
    }

    public static void setListener(HTTransitionListener listener){
        mListener = listener;
    }

    private void initView(){
        linearLayout = (ImageView) findViewById(R.id.iv_main2);
    }

    private void startAnimation() {
       helper.setNextView(linearLayout);
        helper.startEnterAnimation();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            helper.exitAnimation();
        }
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }

}
