package com.hy.game;


import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author John
 * GBK����_���깤��
 */
public class MouseInfo3_1 extends JFrame implements KeyListener{
	final String path = System.getProperty("user.dir") + "/ifYesXY.txt";
    private final static JPanel contentPanel = new JPanel();
    final static Button move=new Button("MOVE");//ע��
    JLabel value_x = null;
    JLabel value_y = null;
    JTextField text_x=new JTextField();//�ı�x
    JTextField text_y=new JTextField();//�ı�y
    
    JLabel value_r = null;
    JLabel value_g = null;
    JLabel value_b = null;

    JLabel value_RGB = null;
    
    
    final static JTextField textField1=new JTextField();//ע��
    
    public static void main(String[] args) {
        coordinate();
        
    }

	/**
	 * �������깤��
	 */
	public static void coordinate() {
		try {
//        	MouseInfo3 mouseInfo3 = new MouseInfo3();
//            mouseInfo3.addKeyListener(mouseInfo3);
            final MouseInfo3_1 info_frame = new MouseInfo3_1();
            textField1.addKeyListener(info_frame);
            info_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            info_frame.setVisible(true);
            info_frame.setAlwaysOnTop(true);
            
//            info_frame.setBounds(0,0,300, 216);
            
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                	
                	
                    Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
//                     System.out.println("Location:x=" + point.x + ", y=" +
//                     point.y);
                    info_frame.value_x.setText("" + point.x);
                    info_frame.value_y.setText("" + point.y);
                    try {
						Color c = getScreenPixel(point.x,point.y);
//						System.out.println("�죺"+c.getRed()+" �̣�"+c.getGreen()+" ����"+c.getBlue());
						info_frame.value_r.setText("" + c.getRed());
						info_frame.value_g.setText("" + c.getGreen());
						info_frame.value_b.setText("" + c.getBlue());
						info_frame.value_RGB.setText("" + c.getRGB());
					} catch (AWTException e) {
						e.printStackTrace();
					}
                }
            }, 100, 100);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    /**
     * Create the dialog.
     */
    public MouseInfo3_1() {
    	addKeyListener(this);
    	
        setTitle("\u9F20\u6807\u5750\u6807\u83B7\u53D6\u5668");
        setBounds(100, 100, 300, 221);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblx = new JLabel("\u5750\u6807:");
        lblx.setFont(new Font("����", Font.PLAIN, 15));
        lblx.setBounds(22, 27, 66, 31);
        contentPanel.add(lblx);

        JLabel lbly = new JLabel("RGBֵ:");
        lbly.setFont(new Font("����", Font.PLAIN, 15));
        lbly.setBounds(22, 68, 66, 31);
        contentPanel.add(lbly);
        
        JLabel rgb = new JLabel("��ɫ:");
        rgb.setFont(new Font("����", Font.PLAIN, 15));
        rgb.setBounds(22, 108, 66, 31);
        contentPanel.add(rgb);
        
        //��ע
        JLabel label1=new JLabel("��ע��");  
        label1.setFont(new Font("����", Font.PLAIN, 15));
        label1.setBounds(22, 142, 66, 31);
        
        textField1.setBounds(82, 142, 166, 31);
        contentPanel.add(label1);  
        contentPanel.add(textField1);  
        

        //����
        value_x = new JLabel("0");
        value_x.setForeground(Color.BLUE);
        value_x.setFont(new Font("����", Font.PLAIN, 20));
        value_x.setBounds(82, 27, 66, 31);
        contentPanel.add(value_x);

        value_y = new JLabel("0");
        value_y.setForeground(Color.BLUE);
        value_y.setFont(new Font("����", Font.PLAIN, 20));
        value_y.setBounds(132, 27, 66, 31);
        contentPanel.add(value_y);
        
        move.setBounds(196, 27, 66, 31);
        contentPanel.add(move);
        
        text_x.setBounds(188, 68, 40, 31);
		text_y.setBounds(188+50, 68, 40, 31);
		contentPanel.add(text_x);
		contentPanel.add(text_y);
        move.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Robot r = new Robot();
					int mx = Integer.parseInt(text_x.getText());
					int my = Integer.parseInt(text_y.getText());
					r.mouseMove(mx, my);
				} catch (Exception e1) {
				}
			}
		});
        
        //RGB
        value_RGB = new JLabel("0");
        value_RGB.setForeground(Color.BLUE);
        value_RGB.setFont(new Font("����", Font.PLAIN, 20));
        value_RGB.setBounds(82, 68, 660, 31);
        contentPanel.add(value_RGB);
        
        
        //��
        value_r = new JLabel("0");
        value_r.setForeground(Color.BLUE);
        value_r.setFont(new Font("����", Font.PLAIN, 20));
        value_r.setBounds(82, 108, 66, 31);
        contentPanel.add(value_r);
        
        //��
        value_g = new JLabel("0");
        value_g.setForeground(Color.BLUE);
        value_g.setFont(new Font("����", Font.PLAIN, 20));
        value_g.setBounds(132, 108, 66, 31);
        contentPanel.add(value_g);
        
        //��
        value_b = new JLabel("0");
        value_b.setForeground(Color.BLUE);
        value_b.setFont(new Font("����", Font.PLAIN, 20));
        value_b.setBounds(182, 108, 66, 31);
        contentPanel.add(value_b);
        
        
    }
    
    
    public static Color getScreenPixel(int x, int y) throws AWTException { // ��������ֵΪ��ɫ��RGBֵ��  
        Robot rb = null; // java.awt.image���е��࣬��������ץȡ��Ļ����������  
        rb = new Robot();  
        Toolkit tk = Toolkit.getDefaultToolkit(); // ��ȡȱʡ���߰�  
        Dimension di = tk.getScreenSize(); // ��Ļ�ߴ���  
//        System.out.println(di.width);  
//        System.out.println(di.height);  
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);  
        BufferedImage bi = rb.createScreenCapture(rec);  
        int pixelColor = bi.getRGB(x, y);  
        Color color=new Color(16777216 + pixelColor);
        return color; // pixelColor��ֵΪ��������ʵ���ó���������ɫ���ֵ����ʵ����ɫֵ��  
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_INSERT){

			System.out.println("start writing");
			String text = textField1.getText().replaceAll("[0-9,;]", "");
//		FileWriter writer;
	    try {
//	      writer = new FileWriter(path,true);
	    	File file1 = new File(path);  
	    	Writer writer = new BufferedWriter(  
	    	        new OutputStreamWriter(
	    	                new FileOutputStream(file1,true), "UTF-8")); 
	      writer.write(
//	    		  new String( text.getBytes("GBK") , "GBK")
	    		  text + "\r\n" +
	    		  ";"+value_x.getText() +","+ value_y.getText()+","
	      +value_r.getText()+","
	      +value_g.getText()+","
	      +value_b.getText()
	      +"\r\n");
	      writer.flush();
	      writer.close();
	    } catch (Exception e1) {
	      e1.printStackTrace();
	    }
	    
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
	}

    
}