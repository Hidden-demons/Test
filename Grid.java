package 扫雷;

import java.awt.*;

/**
 * @Auther: H_ui
 * @Date:2019/11/25 10:39
 * @Description: 格子类 格子的内容、格子的状态、格子的坐标
 */
public class Grid {
    //格子内容
    char content;
    //格子状态
    boolean state;
    //格子的坐标
    Point point;
    public char getContent() {
        return content;
    }
    public void setContent(char content) {
        this.content = content;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }
}
