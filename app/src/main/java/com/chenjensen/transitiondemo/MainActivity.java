package com.chenjensen.transitiondemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import com.chenjensen.transitiondemo.transition.HTTransitionListener;
import com.chenjensen.transitiondemo.transition.TransitionHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HTTransitionListener{

    ImageView iv;
    ArrayList<TransitionHelper> helpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        iv = (ImageView)findViewById(R.id.iv);

        helpers = new ArrayList<>();
        TransitionHelper helper = new TransitionHelper();
        helper.setPreView(iv);
        helpers.add(helper);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);
                Main2Activity.setListener(MainActivity.this);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }


    @Override
    public void onViewChange() {

    }

    @Override
    public ArrayList<TransitionHelper> getTransitionHelpers() {
        return helpers;
    }

    @Override
    public void beforeDismiss() {

    }
}
