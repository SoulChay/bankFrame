package view;

import service.AtmService;
import util.BaseFrame;
import util.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("all")
public class LoginFrame extends BaseFrame {

    private LoginFrame(){
        super("登录窗口");
        this.init();
    }
    private static LoginFrame loginFrame;
    public synchronized static LoginFrame getLoginFrame(){
        if(loginFrame==null){
            loginFrame = new LoginFrame();
        }
        return loginFrame;
    }

    private ImageIcon bg = new ImageIcon("src//img//b1.png");

    //添加一些属性---登录窗口上的各种组件
    private JPanel mainPanel = (JPanel) this.getContentPane();
    private JLabel bkLabel = new JLabel(bg);//背景
    private JLabel logoLabel = new JLabel();//logo
    private JLabel titleLabel = new JLabel("欢迎进入银行系统");//title
    private JLabel accountLabel = new JLabel("请输入账号:");
    private JTextField accountField = new JTextField();//用来输入账号的文本框
    private JLabel passwordLabel = new JLabel("请输入密码:");
    private JPasswordField passwordField = new JPasswordField();//用来输入密码的密码框
    private JButton loginButton = new JButton("登 录");
    private JButton registButton = new JButton("注 册");


    //添加一个控制注册窗口的属性
    private RegistFrame registFrame = null;

    protected void setFontAndSoOn() {
        bkLabel.setSize(bg.getIconWidth(),bg.getIconHeight());
        mainPanel.setOpaque(false);
        mainPanel.setLayout(null);//设置panel布局为自定义
        logoLabel.setBounds(135,40,40,40);
        logoLabel.setIcon(this.drawImage("src//img//4.jpg",40,40));
        titleLabel.setBounds(185,40,200,40);
        titleLabel.setFont(new Font("微软雅黑",Font.BOLD,24));
        titleLabel.setForeground(Color.RED);
        accountLabel.setBounds(40,100,140,40);
        accountLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        accountLabel.setForeground(Color.RED);
        accountField.setBounds(160,105,260,32);
        accountField.setFont(new Font("微软雅黑",Font.BOLD,20));
        accountField.setOpaque(false); //将文本框设置为透明的
        //accountField.setForeground(Color.white);
        passwordLabel.setBounds(40,150,140,40);
        passwordLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        passwordLabel.setForeground(Color.RED);
        passwordField.setBounds(160,155,260,32);
        passwordField.setFont(new Font("微软雅黑",Font.BOLD,20));
        passwordField.setOpaque(false);
        //passwordField.setForeground(Color.white);
        loginButton.setBounds(140,210,100,32);
        loginButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        loginButton.setBackground(Color.LIGHT_GRAY);
        registButton.setBounds(320,210,100,32);
        registButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        registButton.setBackground(Color.LIGHT_GRAY);


    }
    protected void addElements() {
        mainPanel.add(logoLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(accountLabel);
        mainPanel.add(accountField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(registButton);
        //this.add(mainPanel);
    }
    protected void addListener() {
        //登录按钮
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String aname = accountField.getText();
                String apassword = new String(passwordField.getPassword());
                AtmService service = MySpring.getBean("service.AtmService");
                String result = service.login(aname,apassword);
                if(result.equals("登录成功")){
                    LoginFrame.this.setVisible(false);
                    AtmFrame.getAtmFrame(aname);
                }else{
                    JOptionPane.showMessageDialog(LoginFrame.this,"对不起,"+result);
                    LoginFrame.this.reset();
                }
            }
        });
        registButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginFrame.this.setVisible(false);
                if(registFrame==null) {
                    registFrame = RegistFrame.getRegistFrame();
                }else{
                    registFrame.setVisible(true);
                    registFrame.reset();
                }
            }
        });
    }

    //清空输入框
    void reset(){
        accountField.setText("");
        passwordField.setText("");
    }

    protected void setFrameSelf() {
        this.setBounds(400,200,bg.getIconWidth(),bg.getIconHeight());
        this.getLayeredPane().add(bkLabel,new Integer(Integer.MIN_VALUE));//将此标签放在分层面板的最底层
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

}
