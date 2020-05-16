package 扫雷;

import java.awt.*;
import java.util.Random;


/**
 * @Auther: H_ui
 * @Date:2019/11/25 10:38
 * @Description: 游戏核心类 雷区(游戏界面)
 */
public class Game {
    //定义一个存放格子对象的二维数组
    Grid[][] grids = new Grid[9][9];

    //定义一个存放雷数的属性
    int count = 10;
    //定义一个随机数工具的属性
    Random random = new Random();

    //添加格子对象到二维数组中

    public  void  addGrid(){
        //通过循环的方式来为二维数组中添加格子对象
        for (int i = 0;i < grids.length; i++){
            for (int j = 0; j <grids[i].length;j++){
                //将二维数组中的每个格子上赋值格子对象
                grids[i][j] = new Grid();
                //设置内容
                grids[i][j].setContent(' ');
                //设置状态
                grids[i][j].setState(false);
                //设置坐标
                grids[i][j].setPoint(new Point(i,j));
            }
        }
    }

    //创建一个绘制游戏界面的方法
    public  void  paint(){
        for (int i = 0;i < grids.length ; i++){
            for (int j = 0; j < grids[i].length ;j++){
                if(grids[i][j].isState()){
                    System.out.print(grids[i][j].getContent() + " ");
                }else {
                    System.out.print("■ ");
                }
            }
            System.out.println();
        }
    }

    //添加一个布雷的方法
    public void setMine(){
        int i = 0;
        //通过循环的方式来获得10个格子对象
        do {
            //获得一个随机的行坐标
            int x = random.nextInt(9);
            //获得一个随机的列坐标
            int y = random.nextInt(9);
            //判断当前随机位置的格子内容是否是雷,如果不是就布雷
            if (grids[x][y].getContent() != '*'){
                //获得当前坐标位置的格子对象
                grids[x][y].setContent('*');
                i++;
            }
        }while(i<count);
    }

    //获得当前坐标位置的8个方向坐标对象
    public Point[] getPoint(int x, int y){
        //创建一个坐标数组
        Point[] point=new Point[8];
        //为左边的位置赋值坐标
        point[0]=new Point(x-1,y);
        //为左上的位置赋值坐标
        point[1]=new Point(x-1,y-1);
        //为上面的位置赋值坐标
        point[2]=new Point(x,y-1);
        //为右上的位置赋值坐标
        point[3]=new Point(x+1,y-1);
        //为右边的位置赋值坐标
        point[4]=new Point(x+1,y);
        //为右下的位置赋值坐标
        point[5]=new Point(x+1,y+1);
        //为下面的位置赋值坐标
        point[6]=new Point(x,y+1);
        //为左下的位置赋值坐标
        point[7]=new Point(x-1,y+1);
        return point;
    }

    //雷数字添加
    public void setNumber(){
        //遍历所有的格子对象
        for (int i = 0;i < grids.length; i++){
            for (int j = 0;j < grids[i].length ; j++){
                //定义统计当前位置方向中雷数的计数器
                int sum = 0;
                //判断是否当前位置是雷，如果是雷就进入下一个坐标
                if(grids[i][j].getContent() == '*'){
                    //结束当前的循环，进入下一次循环
                    continue;
                }else {
                    //进入当前位置的8个方向的扫描
                    Point[] points = getPoint(i, j);
                    //通过循环遍历来获得当前坐标的方向对象
                    for (int k = 0; k < points.length;k++){
                        //定义一个坐标对象来获得当前循环到的坐标
                        Point point = points[k];
                        if(point.x > 0 && point.y > 0 && point.x < 9 && point.y <9){
                            //判断当前有效范围内的格子是否是雷
                            if(grids[point.x][point.y].getContent() == '*'){
                                sum++;
                            }
                        }
                    }
                }
                //判断雷数的计数器是否发生改变，如果改变就将值填充到
                //当前坐标的格子当中
                if (sum > 0){
                    grids[i][j].setContent((char) (48+sum));
                }
            }
        }
    }

    //踩雷
    public Boolean  stamp(int x,int y){
        //判断当前位置是否存在雷
        if (grids[x][y].getContent() == '*'){
            grids[x][y].setState(true);
            System.out.println("游戏结束了");
            return false;
        }else {
            //将当前的格子内容状态改成已打开
            grids[x][y].setState(true);
            //判断当前的格子内容必须是空格的时候才执行辐射
            if (grids[x][y].getContent() == ' '){
                //获得当前格子的8个方向坐标对象
                Point[] points = getPoint(x, y);
                //循环遍历获得每个方向的对象
                for (int k = 0;k < points.length;k++){
                    Point p = points[k];
                    //判断当前坐标对象是否越界
                    if(p.x >= 0 && p.y >= 0 && p.x < 9 && p.y < 9){
                        //判断当前方向的格子内容是否为空格，如果为空格
                        //就执行辐射操作  --递归调用
                        if (grids[p.x][p.y].getContent() == ' ' && grids[p.x][p.y].isState() == false){
                            stamp(p.x, p.y);
                        }else if (grids[p.x][p.y].getContent() != ' '){
                            //如果当前位置为数字就将数字显示出来
                            grids[p.x][p.y].setState(true);
                            if( grids[p.x][p.y].getContent() == '*'){
                                grids[p.x][p.y].setState(false);
                            }
                        }
                    }
                }

            }
        }
        return true;
    }
}
