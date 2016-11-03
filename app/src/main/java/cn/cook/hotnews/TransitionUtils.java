package cn.cook.hotnews;

import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.TransitionSet;

public class TransitionUtils {

    private TransitionUtils() { }

    public static @Nullable
    Transition findTransition(
            @NonNull TransitionSet set, @NonNull Class<? extends Transition> clazz) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (int i = 0; i < set.getTransitionCount(); i++) {
                Transition transition = set.getTransitionAt(i);
                if (transition.getClass() == clazz) {
                    return transition;
                }
                if (transition instanceof TransitionSet) {
                    Transition child = findTransition((TransitionSet) transition, clazz);
                    if (child != null) return child;
                }
            }
        }
        return null;
    }

    public static @Nullable Transition findTransition(
            @NonNull TransitionSet set,
            @NonNull Class<? extends Transition> clazz,
            @IdRes int targetId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (int i = 0; i < set.getTransitionCount(); i++) {
                Transition transition = set.getTransitionAt(i);
                if (transition.getClass() == clazz) {
                    if (transition.getTargetIds().contains(targetId)) {
                        return transition;
                    }
                }
                if (transition instanceof TransitionSet) {
                    Transition child = findTransition((TransitionSet) transition, clazz, targetId);
                    if (child != null) return child;
                }
            }
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static class TransitionListenerAdapter implements Transition.TransitionListener {

        @Override
        public void onTransitionStart(Transition transition) {

        }

        @Override
        public void onTransitionEnd(Transition transition) {

        }

        @Override
        public void onTransitionCancel(Transition transition) {

        }

        @Override
        public void onTransitionPause(Transition transition) {

        }

        @Override
        public void onTransitionResume(Transition transition) {

        }
    }
}