// Copyright (c) 2014 Zhang Yungui, https://github.com/rhcad/vgplay-android

package vgplay.testview;

import android.content.Context;
import android.os.Bundle;
import rhcad.touchvg.IGraphView;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import rhcad.touchvg.core.GiGestureState;
import rhcad.touchvg.view.SFGraphView;
import rhcad.vgplay.PlayingHelper;

public class BoardView extends SFGraphView implements IGraphView.OnFirstRegenListener,
        IGraphView.OnShapesRecordedListener {
    protected static final String PATH = "mnt/sdcard/TouchVG/";
    protected PlayingHelper mPlayHelper;
    private BoardView mReceiver;

    public BoardView(Context context) {
        this(context, null);
    }

    public BoardView(Context context, Bundle savedInstanceState) {
        super(context, savedInstanceState);
        mPlayHelper = new PlayingHelper(this);

        setOnFirstRegenListener(this);
        setOnShapesRecordedListener(this);
    }

    public void setReceiver(BoardView view) {
        mReceiver = view;
    }

    public String getRecordPath() {
        return PATH + "record" + getId();
    }

    @Override
    protected void onDetachedFromWindow() {
        mReceiver = null;
        mPlayHelper.stop();
        mPlayHelper = null;
        super.onDetachedFromWindow();
    }

    public void onFirstRegen(IGraphView view) {
        final IViewHelper helper = ViewFactory.createHelper(view);
        helper.startRecord(getRecordPath());
        mPlayHelper.startSyncPlay(getId(), mReceiver.getRecordPath());
    }

    public void onShapesRecorded(IGraphView view, Bundle info) {
        int index = info.getInt("index");

        if (index > 1 && view.coreView().getGestureState() != GiGestureState.kGiGestureMoved) {
            mReceiver.onSyncPlay(info);
        }
    }

    public void onSyncPlay(Bundle info) {
        int index = info.getInt("index") - 1;
        mPlayHelper.applySyncPlayFrame(getId(), index);
    }
}
