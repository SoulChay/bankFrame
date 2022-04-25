package util;
import javax.swing.*;
import java.awt.*;
public abstract class BaseFrame extends JFrame {
    public BaseFrame(){}
    public BaseFrame(String title){
        super(title);
    }

    //设计一个具体方法 规定加载窗体时的执行顺序
    protected void init(){
        this.setFontAndSoOn();
        this.addElements();
        this.addListener();
        this.setFrameSelf();
    }

    //设计一个通用的画图方法
    protected ImageIcon drawImage(String path,int width,int height){
        ImageIcon imageIcon = new ImageIcon(path);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT));
        return imageIcon;
    }
    //用来设置 字体颜色布局管理 等等
    protected abstract void setFontAndSoOn();
    //用来添加组件
    protected abstract void addElements();
    //添加事件监听器
    protected abstract void addListener();
    //设置窗体自身的属性
    protected abstract void setFrameSelf();

}

