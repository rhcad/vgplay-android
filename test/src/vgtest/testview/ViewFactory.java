//! \file ViewFactory.java
//! \brief 基于普通View类的绘图测试视图类
// Copyright (c) 2012-2013, https://github.com/rhcad/touchvg

package vgtest.testview;

import java.util.ArrayList;
import java.util.List;

//! 测试视图的构造列表类
public class ViewFactory {

    public static class DummyItem {

        public String id;
        public int flags;
        public String title;

        public DummyItem(String id, int flags, String title) {
            this.id = id;
            this.flags = flags;
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    static {
        addItem("vgplay.testview.TwoBoardViews", 0, "SharedBoard demo");
        addItem("vgtest.testview.view.SFGraphView1", TestFlags.RECORD_SPLINES, "SFGraphView record splines");
        addItem("vgtest.testview.view.SFGraphView1", TestFlags.RECORD_LINE, "SFGraphView record line");
        addItem("vgtest.testview.view.SFGraphView1", TestFlags.RECORD_SPLINES_RAND, "SFGraphView record randShapes splines");
        addItem("vgtest.testview.view.SFGraphView1", TestFlags.RECORD_LINE_RAND, "SFGraphView record randShapes line");
        addItem("vgtest.testview.view.SFGraphView1", TestFlags.PLAY_SHAPES, "SFGraphView play");
        addItem("vgtest.testview.view.SFGraphView1", TestFlags.PROVIDER | TestFlags.SPLINES_CMD, "SFGraphView provider");
        addItem("vgtest.testview.view.SFGraphView1", TestFlags.PROVIDER | TestFlags.RECORD_SPLINES, "SFGraphView provider record");
        addItem("vgtest.testview.view.SFGraphView1", TestFlags.PLAY_SHAPES | TestFlags.RAND_SHAPES, "SFGraphView randShapes play");

        addItem("vgtest.testview.view.GraphView1", TestFlags.RECORD_SPLINES, "StdGraphView record splines");
        addItem("vgtest.testview.view.GraphView1", TestFlags.RECORD_LINE, "StdGraphView record line");
        addItem("vgtest.testview.view.GraphView1", TestFlags.RECORD_SPLINES_RAND, "StdGraphView record randShapes splines");
        addItem("vgtest.testview.view.GraphView1", TestFlags.RECORD_LINE_RAND, "StdGraphView record randShapes line");
        addItem("vgtest.testview.view.GraphView1", TestFlags.PLAY_SHAPES, "StdGraphView play");
        addItem("vgtest.testview.view.GraphView1", TestFlags.PROVIDER | TestFlags.SPLINES_CMD, "StdGraphView provider");
        addItem("vgtest.testview.view.GraphView1", TestFlags.PROVIDER | TestFlags.RECORD_SPLINES, "StdGraphView provider record");
        addItem("vgtest.testview.view.GraphView1", TestFlags.PLAY_SHAPES | TestFlags.RAND_SHAPES, "StdGraphView randShapes play");
    }

    private static void addItem(String id, int flags, String title) {
        ITEMS.add(new DummyItem(id, flags, title));
    }
}
