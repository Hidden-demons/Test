package 扫雷;

import java.util.Scanner;

/**
 * @Auther: H_ui
 * @Date:2019/11/25 10:37
 * @Description: 测试类
 */
public class Test {
    public static void main(String[] args) {
        //获得游戏核心类的对象
        Game game = new Game();
        //调用添加格子对象的方法
        game.addGrid();
        //布雷方法调用
        game.setMine();
        //添加雷数的调用
        game.setNumber();

        Scanner s=new Scanner(System.in);

        game.paint();
        Boolean stamp = true;
        while (stamp){
            System.out.println("请输入X坐标");
            int x = s.nextInt();
            System.out.println("请输入Y坐标");
            int y = s.nextInt();
            //判断游戏是否结束
            stamp = game.stamp(y - 1, x - 1);
            //调用绘制界面的方法
            game.paint();
        }
    }
}
