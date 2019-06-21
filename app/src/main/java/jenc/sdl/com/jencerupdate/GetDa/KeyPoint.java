package jenc.sdl.com.jencerupdate.GetDa;

/**
 * jenc.sdl.com.jencerupdate.GetDa
 * 作者：she on 13:43
 * 邮箱：610184089@qq.com
 **/
public class KeyPoint {

    public KeyPoint() {
    }

    public KeyPoint(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    /**
     * x : 1
     * y : 0
     * value : 1
     */

    private int x;
    private int y;
    private String value;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
