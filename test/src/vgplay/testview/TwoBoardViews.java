// Copyright (c) 2014 Zhang Yungui, https://github.com/touchvg/touchvgplay

package vgplay.testview;

import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class TwoBoardViews extends LinearLayout {

    public TwoBoardViews(Context context) {
        super(context);
        this.setOrientation(HORIZONTAL);

        final BoardView view1 = createView(context);
        final IViewHelper helper1 = ViewFactory.createHelper(view1);
        view1.setId(1);
        helper1.setLineColor(Color.GREEN);
        helper1.setCommand("splines");

        final BoardView view2 = createView(context);
        final IViewHelper helper2 = ViewFactory.createHelper(view2);
        view2.setId(2);
        helper2.setLineColor(Color.RED);
        helper2.setCommand("ellipse");
        view2.setBackgroundColor(Color.LTGRAY);

        view1.setReceiver(view2);
        view2.setReceiver(view1);
    }

    private BoardView createView(Context context) {
        final FrameLayout layout = new FrameLayout(context);
        final BoardView view = new BoardView(context);
        final LayoutParams param = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        layout.addView(view, param);
        layout.addView(view.createDynamicShapeView(context), param);
        param.weight = 1;
        addView(layout, param);

        return view;
    }
}
