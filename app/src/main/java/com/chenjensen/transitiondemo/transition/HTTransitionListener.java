package com.chenjensen.transitiondemo.transition;

import java.util.ArrayList;

/**
 * Created by chenjensen on 16/8/24.
 */
public interface HTTransitionListener {

    ArrayList<TransitionHelper> getTransitionHelpers();

    void beforeDismiss();

    void onViewChange();
}
