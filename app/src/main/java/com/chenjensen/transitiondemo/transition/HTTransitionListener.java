package com.chenjensen.transitiondemo.transition;

/**
 * Created by chenjensen on 16/8/24.
 */
public interface HTTransitionListener {

    TransitionHelper getTransitionHelper();

    void beforeDismiss();

    void onViewChange();
}
