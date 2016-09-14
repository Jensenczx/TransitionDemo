package com.chenjensen.transitiondemo.transition;

import android.view.animation.Interpolator;

/**
 * Created by chenjensen on 16/8/24.
 */
public class DefaultInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float v) {
        return (float)(Math.cos((v + 1) * Math.PI) / 2.0f) + 0.5f;
    }
}
