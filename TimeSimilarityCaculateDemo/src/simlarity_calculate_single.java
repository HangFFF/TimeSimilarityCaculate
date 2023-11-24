/**
 * @author FengH
 * @date 2023/11/24 15:55
 * @author FengH
 * @date 2023/11/23 15:08
 */
/**
 * @author FengH
 * @date 2023/11/23 15:08
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Scanner;

class SwingLinster11 {
    public SwingLinster11() {
        double w1, w2, w3, w4;
        w1 = 0.05689d;
        w2 = 0.12187d;
        w3 = 0.26335d;
        w4 = 0.55789d;

        final String[] s1 = {""};
        final String[] s2 = {""};
        // 1. 创建一个顶层容器（窗口）
        JFrame jf = new JFrame("输入格式：时间点：2022-1-1 或：时间段：2022-1-1-2022-2-1");          // 创建窗口
        jf.setBackground(Color.white);
        //jf.setContentPane(panel);// 4. 把 面板容器 作为窗口的内容面板 设置到 窗口
        jf.setLayout(null); // 取消布局管理器
        //定义若干部件===========================================================================
        //3个文本框，两个输入，一个输出
        final JTextField textField1 = new JTextField(15);//框
        final JTextField textField2 = new JTextField(15);
        final JTextField result = new JTextField(15);
        final JTextField result_similarity = new JTextField(15);

        textField1.setFont(new Font(null, Font.PLAIN, 20));//框
        textField2.setFont(new Font(null, Font.PLAIN, 20));
        result.setFont(new Font(null, Font.PLAIN, 18));
        result_similarity.setFont(new Font(null, Font.PLAIN, 18));

        textField1.setBounds(85, 30, 300, 30);//框
        textField2.setBounds(465, 30, 300, 30);
        result.setBounds(20, 265, 750, 30);
        result_similarity.setBounds(105, 195, 240, 30);

        //文本介绍
        JTextArea textarea1 = new JTextArea("时间1：");//文本
        textarea1.setFont(new Font(null, Font.PLAIN, 20));
        textarea1.setBounds(20, 30, 60, 30);

        JTextArea textarea2 = new JTextArea("时间2：");//
        textarea2.setFont(new Font(null, Font.PLAIN, 20));
        textarea2.setBounds(400, 30, 60, 30);

        JTextArea textarea3 = new JTextArea("拓扑结果：");//
        textarea3.setFont(new Font(null, Font.PLAIN, 20));
        textarea3.setBounds(20, 230, 100, 30);

        JTextArea textarea4 = new JTextArea("相似度：");//
        textarea4.setFont(new Font(null, Font.PLAIN, 20));
        textarea4.setBounds(20, 195, 80, 30);

        // 创建一个按钮，点击后获取文本框中的文本
        JButton btn = new JButton("计算");
        btn.setFont(new Font(null, Font.PLAIN, 20));
        btn.setBounds(300, 100, 80, 30);
        //frame中添加部件
        //将按钮添加到容器中
        jf.add(textarea1);
        jf.add(textField1);
        jf.add(textarea2);
        jf.add(textField2);
        jf.add(btn);
        jf.add(textarea3);
        jf.add(result);
        jf.add(textarea4);
        jf.add(result_similarity);

        jf.setSize(900, 400);                       // 设置窗口大小
        jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）
        jf.setVisible(true);
        ////////////////////////////////////////////////////////////////////////
        //监听按钮
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                s1[0] = textField1.getText();
                s2[0] = textField2.getText();
                System.out.println("s1[0],s2[0]=" + s1[0] + " " + s2[0]);
                //result.setText(Transformation(textField.getText()));
                int flag1 = 0, flag2 = 0;//flag1代表第一个时间是时间点/时间段,flag默认是0，代表时间点;
                String str1 = s1[0];
                //String str1 = JOptionPane.showInputDialog("输入第一个时间(yy-mm-dd)","");//弹框输入
                //由于这个方法输入的格式为字符型，所以我们要转换成整型
                String[] str = str1.split("-");//好方法！！！
//第一个时间的输入与处理==============================================================
                Scanner sc = new Scanner(System.in);
                int y1 = 0, m1 = 0, d1 = 0, y2 = 0, m2 = 0, d2 = 0;//保存时间段
                int y0 = 0, m0 = 0, d0 = 0;//保存时间点
                LocalDate local_d_0 = LocalDate.of(2000, 1, 1);//初始化
                LocalDate local_d_1 = LocalDate.of(2000, 1, 1);
                LocalDate local_d_2 = LocalDate.of(2000, 1, 1);
                LocalDate local_dd_0 = LocalDate.of(2000, 1, 1);
                LocalDate local_dd_1 = LocalDate.of(2000, 1, 1);
                LocalDate local_dd_2 = LocalDate.of(2000, 1, 1);

                if (str.length == 6)
//默认正常输入，支持2020-1-1和2020-01-01，正常输入一个时间或者两个时间；
                {
                    flag1 = 1;//时间数据为时间段
                    y1 = Integer.valueOf(str[0]);//str转化为int类型;
                    m1 = Integer.valueOf(str[1]);
                    d1 = Integer.valueOf(str[2]);
                    y2 = Integer.valueOf(str[3]);
                    m2 = Integer.valueOf(str[4]);
                    d2 = Integer.valueOf(str[5]);
                    local_d_1 = LocalDate.of(y1, m1, d1);//获取时间
                    local_d_2 = LocalDate.of(y2, m2, d2);//获取时间

                } else if (str.length == 3) {
                    flag1 = 0;//时间数据为时间点
                    y0 = Integer.valueOf(str[0]);
                    m0 = Integer.valueOf(str[1]);
                    d0 = Integer.valueOf(str[2]);
                    local_d_0 = LocalDate.of(y0, m0, d0);//获取时间
                }

//同样操作用于第二个时间的输入==============================================================
                int yy1 = 0, mm1 = 0, dd1 = 0, yy2 = 0, mm2 = 0, dd2 = 0;//保存时间段
                int yy0 = 0, mm0 = 0, dd0 = 0;//保存时间点
                //String[] strr=sc.nextLine().split("-");//str[]字符串数组中保存该行中的以“-”为分割的若干字符串；
                String strr1 = s2[0];
                //strr1= JOptionPane.showInputDialog("输入第二个时间(yy-mm-dd)","");//弹框输入
                String[] strr = strr1.split("-");
                if (strr.length == 6)
                {
                    flag2 = 1;//时间数据为时间段
                    yy1 = Integer.valueOf(strr[0]);//str转化为int类型;
                    mm1 = Integer.valueOf(strr[1]);
                    dd1 = Integer.valueOf(strr[2]);
                    yy2 = Integer.valueOf(strr[3]);
                    mm2 = Integer.valueOf(strr[4]);
                    dd2 = Integer.valueOf(strr[5]);
                    local_dd_1 = LocalDate.of(yy1, mm1, dd1);//获取时间
                    local_dd_2 = LocalDate.of(yy2, mm2, dd2);//获取时间
                } else if (strr.length == 3) {
                    flag2 = 0;//时间数据为时间点
                    yy0 = Integer.valueOf(strr[0]);
                    mm0 = Integer.valueOf(strr[1]);
                    dd0 = Integer.valueOf(strr[2]);
                    local_dd_0 = LocalDate.of(yy0, mm0, dd0);//获取时间
                }
                int Flag = 0;//用来后序case来确定拓扑关系的str

                double similarity = 0.0D;///////=========================================================================
                if (flag1 == 0 && flag2 == 0)//两个时间点
                {
                    Flag = MyUtils.connect_calculate_1(local_d_0, local_dd_0);
                    //similarity= MyUtils.similarity_calculate_1(local_d_0,local_dd_0);
                    similarity = MyUtils.similarity_calculate_1(local_d_0, local_dd_0, w1, w2, w3, w4);
                }
                if (flag1 == 0 && flag2 == 1) {
                    Flag = MyUtils.connect_calculate_2(local_d_0, local_dd_1, local_dd_2);
                    similarity = MyUtils.similarity_calculate_2(local_d_0, local_dd_1, local_dd_2, w1, w2, w3, w4);
                }
                if (flag1 == 1 && flag2 == 0) {

                    Flag = MyUtils.connect_calculate_3(local_d_1, local_d_2, local_dd_0);
                    similarity = MyUtils.similarity_calculate_3(local_d_1, local_d_2, local_dd_0, w1, w2, w3, w4);
                }
                if (flag1 == 1 && flag2 == 1)//两个时间段
                {
                    Flag = MyUtils.connect_calculate_4(local_d_1, local_d_2, local_dd_1, local_dd_2);
                    similarity = MyUtils.similarity_calculate_4(local_d_1, local_d_2, local_dd_1, local_dd_2, w1, w2, w3, w4);
                }
                String str_message = "Null";
                String str_message_gather = "Null";
                String str_message_similarity = "Null";
                switch (Flag) {
                    case 0:
                        str_message = "BUG_unprocess";
                        break;
                    case 1:
                        str_message = "Before";
                        break;
                    case 2:
                        str_message = "Equals";
                        break;
                    case 3:
                        str_message = "After";
                        break;

                    case 11:
                        str_message = "Before";
                        break;
                    case 12:
                        str_message = "Starts";
                        break;
                    case 13:
                        str_message = "During";
                        break;
                    case 14:
                        str_message = "Ends";
                        break;
                    case 15:
                        str_message = "After";
                        break;

                    case 21:
                        str_message = "After";
                        break;
                    case 22:
                        str_message = "Started-By";
                        break;
                    case 23:
                        str_message = "Contains";
                        break;
                    case 24:
                        str_message = "Ended-By";
                        break;
                    case 25:
                        str_message = "Before";
                        break;

                    case 31:
                        str_message = "Before";
                        break;
                    case 32:
                        str_message = "After";
                        break;
                    case 33:
                        str_message = "Meets";
                        break;//meets迎接
                    case 34:
                        str_message = "Met-By";
                        break;
                    case 35:
                        str_message = "Overlaps";
                        break;
                    case 36:
                        str_message = "Overlapped-By";
                        break;
                    case 37:
                        str_message = "Starts";
                        break;
                    case 38:
                        str_message = "Started-By";
                        break;
                    case 39:
                        str_message = "Ends";
                        break;
                    case 40:
                        str_message = "Ended-By";
                        break;
                    case 41:
                        str_message = "During";
                        break;
                    case 42:
                        str_message = "Contains";
                        break;
                    case 43:
                        str_message = "Equals";
                        break;
                }

                str_message_gather = "两个时间的拓扑关系为: " + str1 + " " + str_message + " " + strr1;
                result.setText(str_message_gather);
                str_message_similarity = String.valueOf(similarity);
                result_similarity.setText(str_message_similarity);
            }
        });
    }
}

public class simlarity_calculate_single {
    public static void main(String[] args) {
        SwingLinster11 swingL = new SwingLinster11();
    }
}
