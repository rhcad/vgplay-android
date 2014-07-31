// Copyright (c) 2014, https://github.com/rhcad/touchvg

package vgtest.testview.view;

import rhcad.touchvg.IGraphView;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import rhcad.touchvg.core.GiCoreView;
import rhcad.touchvg.core.Matrix2d;
import rhcad.touchvg.core.MgShapes;
import rhcad.touchvg.core.Point2d;
import rhcad.touchvg.core.Vector2d;
import rhcad.touchvg.view.SFGraphView;
import rhcad.vgplay.PlayingHelper;
import vgtest.testview.TestFlags;
import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Toast;

public class SFGraphView1 extends SFGraphView {
    protected static final String PATH = "mnt/sdcard/TouchVG/";
    protected PlayingHelper mPlayHelper;

    public SFGraphView1(Context context) {
        this(context, null);
    }

    public SFGraphView1(Context context, Bundle savedInstanceState) {
        super(context, savedInstanceState);

        final int flags = ((Activity) context).getIntent().getExtras().getInt("flags");
        final IViewHelper helper = ViewFactory.createHelper(this);

        if (savedInstanceState == null
                && (flags & (TestFlags.RECORD | TestFlags.PROVIDER | TestFlags.RAND_SHAPES)) != 0) {
            setOnFirstRegenListener(new IGraphView.OnFirstRegenListener() {

                public void onFirstRegen(IGraphView view) {
                    if ((flags & TestFlags.RAND_SHAPES) != 0) {
                        helper.addShapesForTest();
                    }
                    if ((flags & TestFlags.RECORD) != 0) {
                        if ((flags & TestFlags.CMD_MASK) != 0) {
                            helper.startRecord(PATH + "record");
                        } else {
                            mPlayHelper.startPlay(PATH + "record");
                        }
                    }
                    if ((flags & TestFlags.PROVIDER) != 0) {
                        mPlayHelper.addPlayProvider(new MyPlayProvider(), 1, new Point2d());
                    }
                }
            });
        }
        if ((flags & TestFlags.PROVIDER) != 0) {
            setOnGestureListener(new IGraphView.OnDrawGestureListener() {

                public boolean onPreGesture(int gestureType, float x, float y) {
                    if (mPlayHelper.getPlayProviderCount() < 3) {
                        return mPlayHelper.addPlayProvider(new MyPlayProvider(),
                                mPlayHelper.getPlayProviderCount() + 1, new Point2d(x, y));
                    }
                    return false;
                }

                public void onPostGesture(int gestureType, float x, float y) {
                }
            });
        }
        if ((flags & (TestFlags.PLAY_SHAPES | TestFlags.PROVIDER)) != 0) {
            mPlayHelper = new PlayingHelper(this);
            mPlayHelper.setOnPlayEndedListener(new PlayingHelper.OnPlayEndedListener() {
                public boolean onPlayWillEnd(IGraphView view) {
                    return false;
                }

                public void onPlayEnded(IGraphView view, int tag, Object extra) {
                    Toast.makeText(getContext(), "Play has ended.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        switch (flags & TestFlags.CMD_MASK) {
        case TestFlags.SELECT_CMD:
            helper.setCommand("select");
            break;
        case TestFlags.SPLINES_CMD:
            helper.setCommand("splines");
            break;
        case TestFlags.LINE_CMD:
            helper.setCommand("line");
            break;
        case TestFlags.LINES_CMD:
            helper.setCommand("lines");
            break;
        }
    }

    private static class MyPlayProvider implements PlayingHelper.PlayProvider {
        public void onPlayEnded(IGraphView view, int tag, Object extra) {
        }

        public int provideFrame(IGraphView view, int tag, Object extra, int hShapes, int tick,
                int lastTick) {
            MgShapes shapes = MgShapes.fromHandle(hShapes);
            Point2d center = (Point2d) extra;
            int ret = 0;

            if (shapes.getShapeCount() == 0) {
                final GiCoreView cv = GiCoreView.createView(null, 0);
                ret = cv.loadFromFile(PATH + "resume.vg") ? 1 : -1;
                shapes.copyShapes(MgShapes.fromHandle(cv.backShapes()), false);
                cv.delete();
                if (!center.isEqualTo(Point2d.kOrigin())) {
                    final Point2d oldpt = shapes.getExtent().center();
                    final PointF newpt = ViewFactory.createHelper(view).displayToModel(
                            center.getX(), center.getY());
                    shapes.transform(Matrix2d.translation(new Vector2d(newpt.x - oldpt.getX(),
                            newpt.y - oldpt.getY())));
                }
                center.set(shapes.getExtent().center());
            } else if (tick > lastTick + 60 - tag * 20) {
                float angle = (float) Math.toRadians(1);
                shapes.transform(Matrix2d.rotation(angle, center));
                ret = 1;
            }

            return ret;
        }
    }
}
