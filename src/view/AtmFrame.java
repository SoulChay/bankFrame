package view;

import service.AtmService;
import util.BaseFrame;
import util.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("all")
public class AtmFrame extends BaseFrame {

    private AtmFrame(String aname){
        super("操作窗口");
        this.aname = aname;
        this.init();
    }
    private static AtmFrame atmFrame;
    public synchronized static AtmFrame getAtmFrame(String aname){
        if(atmFrame==null){
            atmFrame = new AtmFrame(aname);
        }
        return atmFrame;
    }

    //管理当前用户的用户名
    private String aname;

    private AtmService service = MySpring.getBean("service.AtmService");

    private ImageIcon bg = new ImageIcon("src//img//b2.png");
    
    //组件
    private JPanel mainPanel = (JPanel) this.getContentPane();
    private JLabel bkLabel = new JLabel(bg);//背景
    private JLabel logoLabel = new JLabel();//logo
    private JLabel titleLabel = new JLabel("银行系统");
    private JLabel balanceLabelCN = new JLabel();
    private JLabel balanceLabelEN = new JLabel();
    private JLabel selectServerLabelCN = new JLabel("您好!请选择所需服务");
    private JLabel selectServerLabelEN = new JLabel("Please Select Service");
    private JButton messageButton = new JButton("销户");
    private JButton exitButton = new JButton("退出");
    private JButton depositButton = new JButton("存款");
    private JButton withdrawalButton = new JButton("取款");
    private JButton transferButton = new JButton("转账");

    protected void setFontAndSoOn() {
        bkLabel.setSize(bg.getIconWidth(),bg.getIconHeight());
        mainPanel.setOpaque(false);
        mainPanel.setLayout(null);
        logoLabel.setBounds(20,1,70,70);
        logoLabel.setIcon(this.drawImage("src//img//4.jpg",80,80));
        titleLabel.setBounds(95,10,160,60);
        titleLabel.setFont(new Font("微软雅黑",Font.ITALIC,32));

        balanceLabelCN.setBounds(250,200,300,40);
        balanceLabelCN.setFont(new Font("微软雅黑",Font.BOLD,20));
        balanceLabelCN.setHorizontalAlignment(JTextField.CENTER);
        balanceLabelCN.setText("账户余额:￥"+service.inquire(aname));
        balanceLabelCN.setForeground(Color.RED);

        balanceLabelEN.setBounds(240,240,320,40);
        balanceLabelEN.setFont(new Font("微软雅黑",Font.BOLD,20));
        balanceLabelEN.setHorizontalAlignment(JTextField.CENTER);
        balanceLabelEN.setText("Account Balance:￥"+service.inquire(aname));
        balanceLabelEN.setForeground(Color.RED);

        selectServerLabelCN.setBounds(250,370,300,40);
        selectServerLabelCN.setFont(new Font("微软雅黑",Font.BOLD,16));
        selectServerLabelCN.setHorizontalAlignment(JTextField.CENTER);
        selectServerLabelCN.setForeground(Color.RED);
        selectServerLabelEN.setBounds(250,400,300,40);
        selectServerLabelEN.setFont(new Font("微软雅黑",Font.BOLD,16));
        selectServerLabelEN.setHorizontalAlignment(JTextField.CENTER);
        selectServerLabelEN.setForeground(Color.RED);

        messageButton.setBounds(10,150,120,40);
        messageButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        messageButton.setBackground(Color.lightGray);
        messageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        exitButton.setBounds(10,390,120,40);
        exitButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        exitButton.setBackground(Color.lightGray);
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        depositButton.setBounds(670,150,120,40);
        depositButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        depositButton.setBackground(Color.lightGray);
        depositButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        withdrawalButton.setBounds(670,270,120,40);
        withdrawalButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        withdrawalButton.setBackground(Color.lightGray);
        withdrawalButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        transferButton.setBounds(670,390,120,40);
        transferButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        transferButton.setBackground(Color.lightGray);
        transferButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    protected void addElements() {
        mainPanel.add(logoLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(balanceLabelCN);
        mainPanel.add(balanceLabelEN);
        mainPanel.add(selectServerLabelCN);
        mainPanel.add(selectServerLabelEN);
        mainPanel.add(messageButton);
        mainPanel.add(exitButton);
        mainPanel.add(depositButton);
        mainPanel.add(withdrawalButton);
        mainPanel.add(transferButton);
        //this.add(mainPanel);
    }
    protected void addListener() {
        //退出按钮
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = JOptionPane.showConfirmDialog(AtmFrame.this,"确认退出系统么?");
                if(value==0){
                    System.exit(0);
                }
            }
        });

        //销户按钮
        messageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = JOptionPane.showConfirmDialog(AtmFrame.this,"确认消除当前账号的所有信息么?");
                if(value==0){
                    if(service.closeAccount(aname)==1){
                        JOptionPane.showMessageDialog(AtmFrame.this,"销户成功");
                        System.exit(0);
                    }else{
                        JOptionPane.showMessageDialog(AtmFrame.this,"清除失败");
                    }
                }
            }
        });

        //存款按钮
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String value = JOptionPane.showInputDialog(AtmFrame.this,"请输入存款金额");
                    if(value!=null && !"".equals(value)){
                        Float depositMoney = Float.parseFloat(value);
                        if(depositMoney<=0){
                            throw new NumberFormatException();
                        }
                        int count = service.deposit(aname,depositMoney);
                        if(count==1){
                            JOptionPane.showMessageDialog(AtmFrame.this,"存款成功");
                            balanceLabelCN.setText("账户余额:￥" + service.inquire(aname));
                            balanceLabelEN.setText("Account Balance:￥"+service.inquire(aname));
                        }else{
                            JOptionPane.showMessageDialog(AtmFrame.this,"存款失败");
                        }
                    }
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(AtmFrame.this,"输入金额有误");
                }
            }
        });

        //取款按钮
        withdrawalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String value = JOptionPane.showInputDialog(AtmFrame.this,"请输入取款金额");
                    if(value!=null && !"".equals(value)){
                        Float withdrawalMoney = Float.parseFloat(value);
                        if(withdrawalMoney<=0){
                            throw new NumberFormatException();
                        }
                        int count = service.withdrawal(aname,withdrawalMoney);
                        if(count==1){
                            JOptionPane.showMessageDialog(AtmFrame.this,"取款成功");
                            balanceLabelCN.setText("账户余额:￥" + service.inquire(aname));
                            balanceLabelEN.setText("Account Balance:￥"+service.inquire(aname));
                        }else if(count==-1){
                            JOptionPane.showMessageDialog(AtmFrame.this,"对不起,余额不足");
                        }else{
                            JOptionPane.showMessageDialog(AtmFrame.this,"存款失败");
                        }
                    }
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(AtmFrame.this,"输入金额有误");
                }
            }
        });

        //转账按钮
        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String name = JOptionPane.showInputDialog(AtmFrame.this,"请输入转账账户");
                    if(name!=null && !"".equals(name) && service.isExist(name)){
                        String value = JOptionPane.showInputDialog(AtmFrame.this,"请输入转账金额");
                        if(value!=null && !"".equals(value)){
                            Float transferMoney = Float.parseFloat(value);
                            if(transferMoney<=0){
                                throw new NumberFormatException();
                            }
                            int count = service.transfer(aname,name,transferMoney);
                            if(count==2){
                                JOptionPane.showMessageDialog(AtmFrame.this,"转账成功");
                                balanceLabelCN.setText("账户余额:￥" + service.inquire(aname));
                                balanceLabelEN.setText("Account Balance:￥"+service.inquire(aname));
                            }else if(count==-1){
                                JOptionPane.showMessageDialog(AtmFrame.this,"对不起,余额不足");
                            }else{
                                JOptionPane.showMessageDialog(AtmFrame.this,"存款失败");
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(AtmFrame.this,"输入的账号不存在");
                    }
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(AtmFrame.this,"输入金额有误");
                }
            }
        });

    }
    protected void setFrameSelf() {
        this.setBounds(300,200,bg.getIconWidth(),bg.getIconHeight());
        this.getLayeredPane().add(bkLabel,new Integer(Integer.MIN_VALUE));//将此标签放在分层面板的最底层
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);//设置无边框
        this.setVisible(true);
    }
}
