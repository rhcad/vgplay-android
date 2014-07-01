package vgtest.testview;

public class TestFlags {
    public final static int SPLINES_CMD = 1;
    public final static int SELECT_CMD = 2;
    public final static int LINE_CMD = 4;
    public final static int LINES_CMD = 5;
    public final static int CMD_MASK = 7;
    public final static int RAND_SHAPES = 32;
    public final static int RAND_SPLINES = RAND_SHAPES | SPLINES_CMD;
    public final static int RAND_LINE = RAND_SHAPES | LINE_CMD;
    public final static int RAND_SELECT = RAND_SHAPES | SELECT_CMD;
    public final static int RECORD = 64;
    public final static int RECORD_SPLINES = RECORD | SPLINES_CMD;
    public final static int RECORD_LINE = RECORD | LINE_CMD;
    public final static int RECORD_SPLINES_RAND = RECORD | SPLINES_CMD | RAND_SHAPES;
    public final static int RECORD_LINE_RAND = RECORD | LINE_CMD | RAND_SHAPES;
    public final static int PLAY_SHAPES = 64;
    public final static int PROVIDER = 256;
}
