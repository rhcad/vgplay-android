// Copyright (c) 2012-2013, https://github.com/rhcad/touchvg

package vgtest.testview.view;

import rhcad.touchvg.IGraphView;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import rhcad.touchvg.view.StdGraphView;
import rhcad.vgplay.PlayingHelper;
import vgtest.testview.TestFlags;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class GraphView1 extends StdGraphView implements IGraphView.OnFirstRegenListener {
    protected static final String PATH = "mnt/sdcard/TouchVG/";
    protected PlayingHelper mPlayHelper;

    public GraphView1(Context context) {
        this(context, null);
    }

    public GraphView1(Context context, Bundle savedInstanceState) {
        super(context, savedInstanceState);

        int flags = ((Activity) context).getIntent().getExtras().getInt("flags");
        final IViewHelper helper = ViewFactory.createHelper(this);

        if ((flags & TestFlags.RAND_SHAPES) != 0) {
            helper.addShapesForTest();
        }
        if (savedInstanceState == null && (flags & TestFlags.RECORD) != 0) {
            setOnFirstRegenListener(this);
        }

        flags = flags & TestFlags.CMD_MASK;
        if (flags == TestFlags.SELECT_CMD) {
            helper.setCommand("select");
        } else if (flags == TestFlags.SPLINES_CMD) {
            helper.setCommand("splines");
        } else if (flags == TestFlags.LINE_CMD) {
            helper.setCommand("line");
        } else if (flags == TestFlags.LINES_CMD) {
            helper.setCommand("lines");
        }
    }

    public void onFirstRegen(IGraphView view) {
        int flags = ((Activity) getContext()).getIntent().getExtras().getInt("flags");
        final IViewHelper helper = ViewFactory.createHelper(view);

        if ((flags & TestFlags.RECORD) != 0) {
            if ((flags & TestFlags.CMD_MASK) != 0) {
                helper.startRecord(PATH + "record");
            } else {
                mPlayHelper = new PlayingHelper(this);
                mPlayHelper.startPlay(PATH + "record");
            }
        }
    }
}
