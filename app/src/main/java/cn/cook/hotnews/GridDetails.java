package cn.cook.hotnews;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by hasee on 2016/11/2.
 */

public class GridDetails extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final FourThreeImageView imageView= (FourThreeImageView) findViewById(R.id.image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementReturnTransition().addListener(shotReturnHomeListener);
        }
        imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startPostponedEnterTransition();
                }
                return true;
            }
        });
        floatingActionButton= (FloatingActionButton) findViewById(R.id.btn_fab);
        nestedScrollView= (NestedScrollView) findViewById(R.id.scrollview);
    }
FloatingActionButton floatingActionButton;
    NestedScrollView nestedScrollView;
    private Transition.TransitionListener shotReturnHomeListener =
            new TransitionUtils.TransitionListenerAdapter() {
                @Override
                public void onTransitionStart(Transition transition) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        super.onTransitionStart(transition);
                    }
                    // hide the fab as for some reason it jumps position??  TODO work out why
                    floatingActionButton.setVisibility(View.INVISIBLE);
                    // fade out the "toolbar" & list as we don't want them to be visible during return
                    // animation

                    nestedScrollView.animate()
                            .alpha(0f)
                            .setDuration(50)
                           .start();
                }
            };
}
