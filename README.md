# TouchVGPlay Demo for Android

This is a unit test and example project for [TouchVGPlay][vgplay], which is a lightweight vector shape playing and animation framework based on TouchVG for Android.

API (rhcad.vgplay.PlayingHelper):

```Java
// 图形播放管理类: 每个绘图视图对象(IGraphView)可有一个 PlayingHelper 对象.
public class PlayingHelper {
    public static final int FRAMEFLAGS_DYN = 8;

    // 播放结束的通知
    public static interface OnPlayEndedListener {
        // 播放完成，待用户结束播放: 返回false表示不拦截，true表示暂停在最后一帧，
        // 后续由应用来调用 stopPlay()
        public boolean onPlayWillEnd(IGraphView view);

        // 播放结束，应用可更新界面布局、销毁附加的 extra 对象
        public void onPlayEnded(IGraphView view, int tag, Object extra);
    }

    // 播放源接口，由应用提供显示内容
    public static interface PlayProvider {
        // 向动态图形列表(hShapes)提供当前帧的图形内容.
        // 返回: 0表示当前时刻还没有新帧，负数表示播放结束，正数表示已填充当前帧图形
        public int provideFrame(IGraphView view, int tag, Object extra,
                                int hShapes, int tick, int lastTick);

        // 播放结束，应用可更新界面布局、销毁附加的 extra 对象
        public void onPlayEnded(IGraphView view, int tag, Object extra);
    }

    // 指定视图的构造函数，每个绘图视图对象可有一个 PlayingHelper 对象
    public PlayingHelper(IGraphView view);

    // 得到本库的版本号
    public static String getVersion();

    // 添加播放结束的观察者，在 startPlay() 和 stopPlay() 之间回调
    public void setOnPlayEndedListener(OnPlayEndedListener listener);

    // 停止所有播放项，包含 startPlay/startSyncPlay/addPlayProvider 对应项
    public void stop();

    // 开始播放指定的目录下录制的图形
    public boolean startPlay(String path);

    // 停止播放
    public void stopPlay();

    // 暂停播放
    public boolean playPause();

    // 继续播放
    public boolean playResume();

    // 返回已播放的相对毫秒数
    public int getPlayTicks();

    // 开始同步播放，待播放的图形将缓存在指定的目录
    public boolean startSyncPlay(int cid, String path);

    // 同步播放一帧，此帧(*.vgr)已缓存到指定的目录
    public void applySyncPlayFrame(int cid, int index);

    // 停止同步播放
    public void stopSyncPlay(int cid);

    // 停止所有同步播放
    public void stopSyncPlayings();

    // 添加播放源，可指定应用所需的附加对象
    public boolean addPlayProvider(PlayProvider p, int tag, Object extra);

    // 返回播放项的个数
    public int getPlayProviderCount();

    // 停止所有播放项
    public void stopProviders();
}
```

## How to Compile

- Import and run the VGPlayTest project in eclipse.

  - Android SDK version of the projects may need to modify according to your installation.
  
  - Recommend using the newer [ADT Bundle](http://developer.android.com/sdk/index.html) to avoid complex configuration.

## How to Contribute

Contributors and sponsors are welcome. You may translate, commit issues or pull requests on this Github site.
To contribute, please follow the branching model outlined here: [A successful Git branching model](http://nvie.com/posts/a-successful-git-branching-model/).

## License

This is an open source BSD licensed project. It uses the following open source projects:

- [vgandroid](https://github.com/rhcad/vgandroid) (BSD): Vector drawing framework for Android.
- [vgcore](https://github.com/rhcad/vgcore) (BSD): Cross-platform vector drawing libraries using C++.
- [TouchVGPlay][vgplay] (GPL): Shape playing and animation framework based on TouchVG.

[vgplay]: https://github.com/rhcad/vgplay
