package view;

import service.AtmService;
import util.BaseFrame;
import util.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("all")
public class RegistFrame extends BaseFrame {

    private RegistFrame(){
        super("注册窗口");
        this.init();
    }
    private static RegistFrame registFrame;
    public synchronized static RegistFrame getRegistFrame(){
        if(registFrame==null){
            registFrame = new RegistFrame();
        }
        return registFrame;
    }

    //控制登录页面
    private LoginFrame loginFrame = LoginFrame.getLoginFrame();

    private ImageIcon bg = new ImageIcon("src//img//b3.png");

    //添加一些组件的属性
    private JPanel mainPanel = (JPanel) this.getContentPane();
    private JLabel bkLabel = new JLabel(bg);//背景
    private JLabel logoLabel = new JLabel();
    private JLabel titleLabel = new JLabel("请 您 填 写 信 息");
    private JLabel accountLabel = new JLabel("请输入账号：");
    private JTextField accountField = new JTextField();
    private JLabel passwordLabel = new JLabel("请输入密码：");
    private JTextField passwordField = new JTextField();
    private JLabel balanceLabel = new JLabel("请输入金额：");
    private JTextField balanceField = new JTextField();
    private JButton registButton = new JButton("注 册");
    private JButton resetButton = new JButton("重 置");
    private JButton backButton = new JButton("返 回");

    protected void setFontAndSoOn() {
        bkLabel.setSize(bg.getIconWidth(),bg.getIconHeight());
        mainPanel.setOpaque(false);
        mainPanel.setLayout(null);
        logoLabel.setBounds(135,50,40,40);
        logoLabel.setIcon(this.drawImage("src//img//4.jpg",40,40));
        titleLabel.setBounds(185,50,200,40);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD,24));
        titleLabel.setForeground(Color.RED);

        accountLabel.setBounds(40,100,140,40);
        accountLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        accountLabel.setForeground(Color.RED);
        accountField.setBounds(160,105,260,32);
        accountField.setFont(new Font("微软雅黑",Font.BOLD,20));
        accountField.setOpaque(false); //将文本框设置为透明的

        passwordLabel.setBounds(40,150,140,40);
        passwordLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        passwordLabel.setForeground(Color.RED);
        passwordField.setBounds(160,155,260,32);
        passwordField.setFont(new Font("微软雅黑",Font.BOLD,20));
        passwordField.setOpaque(false); //将文本框设置为透明的

        balanceLabel.setBounds(40,200,140,40);
        balanceLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        balanceLabel.setForeground(Color.RED);
        balanceField.setBounds(160,205,260,32);
        balanceField.setFont(new Font("微软雅黑",Font.BOLD,20));
        balanceField.setOpaque(false); //将文本框设置为透明的

        registButton.setBounds(60,260,100,32);
        registButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        registButton.setBackground(Color.LIGHT_GRAY);

        resetButton.setBounds(200,260,100,32);
        resetButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        resetButton.setBackground(Color.LIGHT_GRAY);

        backButton.setBounds(340,260,100,32);
        backButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        backButton.setBackground(Color.LIGHT_GRAY);
    }
    protected void addElements() {
        mainPanel.add(logoLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(accountLabel);
        mainPanel.add(accountField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(balanceLabel);
        mainPanel.add(balanceField);
        mainPanel.add(registButton);
        mainPanel.add(resetButton);
        mainPanel.add(backButton);
       // this.add(mainPanel);
    }
    protected void addListener() {
        registButton.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                String aname = accountField.getText();
                String apassword = passwordField.getText();
                String abalance = balanceField.getText();
                //调用新增方法
                AtmService service = MySpring.getBean("service.AtmService");
                if(service.isExist(aname)){
                    JOptionPane.showMessageDialog(RegistFrame.this,"账号已存在");
                    RegistFrame.this.reset();
                }else {
                    try{
                        service.regist(aname,apassword,Float.parseFloat(abalance));
                        JOptionPane.showMessageDialog(RegistFrame.this, "注册成功,请登录系统");
                        RegistFrame.this.back();
                    }catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(RegistFrame.this, "对不起,您输入的金额有误");
                        RegistFrame.this.reset();
                    }
                }
            }
        });
        resetButton.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                RegistFrame.this.reset();
            }
        });
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistFrame.this.setVisible(false);
                loginFrame.setVisible(true);
                loginFrame.reset();
            }
        });
    }
    //清空输入框
    void reset(){
        accountField.setText("");
        passwordField.setText("");
        balanceField.setText("");
    }
    //返回登录页面
    private void back(){
        this.setVisible(false);
        loginFrame.setVisible(true);

    }
    protected void setFrameSelf() {
        this.setBounds(430,200,bg.getIconWidth(),bg.getIconHeight());
        this.getLayeredPane().add(bkLabel,new Integer(Integer.MIN_VALUE));//将此标签放在分层面板的最底层
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
