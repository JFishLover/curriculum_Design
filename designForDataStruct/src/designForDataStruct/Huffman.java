package designForDataStruct;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
class BjFrame extends JFrame implements ActionListener{
	String PASSWORD;
	String NAME;
	public BjPanel1 panel1=new BjPanel1();
	public JButton enter;
	public JButton cancel;
	public JTextField tf;
	public JPasswordField pw;
	public JLabel name;
	public JLabel password;
	public JLabel title;
	Container contentpaneFrame1=getContentPane();
	public BjFrame(){
		panel1.setLayout(null);
	}
	public void draw(){
		enter=new JButton("确定");
		cancel=new JButton("清空");
		enter.setFont(new Font("宋体", Font.BOLD, 30));
		cancel.setFont(new Font("宋体", Font.BOLD, 30));
		enter.setText("确定");
		cancel.setText("清空");
		enter.setContentAreaFilled(false);
		cancel.setContentAreaFilled(false);
		title=new JLabel("基于哈夫曼树的文件加密压缩工具");
		title.setFont(new Font("宋体", Font.BOLD, 50));
		title.setForeground(Color.yellow);
		name=new JLabel("用户名:");
		
		name.setFont(new Font("宋体", Font.BOLD, 30));
		password=new JLabel("密  码:");
		password.setFont(new Font("宋体", Font.BOLD, 30));
		tf=new JTextField(12);
		tf.setOpaque(false);
		tf.setFont(new Font("宋体", Font.BOLD, 30));
		pw=new JPasswordField(20);
		pw.setOpaque(false);
		pw.setFont(new Font("宋体", Font.BOLD, 30));
		tf.setBackground(new Color(100,100,200));
		pw.setEchoChar('*');
		//enter.setIcon(icon_00);
		//cancel.setIcon(icon_11);
		
		enter.addActionListener(this);
		cancel.addActionListener(this);

		name.setBounds(480, 295,120, 60);
		tf.setBounds(601,295,300,60);
		password.setBounds(480, 350,120, 60);
		pw.setBounds(601,350,300,60);
		enter.setBounds(480,450,120,60);
		cancel.setBounds(750,450,120,60);
		title.setBounds(200,100,800,100);
		
		panel1.add(title);
		panel1.add(enter);
		panel1.add(cancel);
		panel1.add(password);
		panel1.add(name);
		panel1.add(tf);
		panel1.add(pw);
		contentpaneFrame1.add(panel1);
	}
	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();
		if(b==enter){
			char[] password = pw.getPassword();
			
			try{
				FileInputStream fis=new FileInputStream("D:\\Huffman\\NameAndPassword.txt");
				InputStreamReader dis=new InputStreamReader(fis,"GB2312");
				BufferedReader Read=new BufferedReader(dis);
				NAME=Read.readLine();
				PASSWORD=Read.readLine();
				Read.close();
			} catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
			}catch (IOException e1)
			{
				e1.printStackTrace();
			}
			
			if(tf.getText().equals(NAME)&&(new String(password)).equals(PASSWORD)){
				BjFrame1 frame1=new BjFrame1();
				frame1.draw1();
				frame1.setSize(1300,800);Toolkit kit = Toolkit.getDefaultToolkit();
				Dimension screenSize = kit.getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				  //置于屏幕中央
				frame1.setLocation(screenWidth/6, screenHeight/6-100);
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.setVisible(true);
				this.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(this, "用户名或密码错误！","error",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(b==cancel){
			tf.setText("");
			pw.setText("");
		}
	}
}
class BjFrame1 extends JFrame implements ActionListener{
	public BjPanel2 panel1=new BjPanel2();
	public JButton button1_1;
	public JButton button2_1;
	
	Container contentpaneFrame1=getContentPane();
	public BjFrame1(){
		panel1.setLayout(null);
	}
	public void draw1(){
		button1_1=new JButton();
		button2_1=new JButton();
		button1_1.setFont(new Font("宋体", Font.BOLD, 30));
		button2_1.setFont(new Font("宋体", Font.BOLD, 30));
		button1_1.setText("进入");
		button2_1.setText("改密");
		button2_1.setForeground(Color.white);
		button1_1.setForeground(Color.white);
		button1_1.setContentAreaFilled(false);
		button2_1.setContentAreaFilled(false);
		
		
		button1_1.addActionListener(this);
		button2_1.addActionListener(this);
		button1_1.setBounds(480, 295,120, 60); button2_1.setBounds(712, 295,120, 60);

		panel1.add(button1_1);
		panel1.add(button2_1);
		contentpaneFrame1.add(panel1);
	}
	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();
		if(b==button1_1){
			BjFrame2 frame2=new BjFrame2();
			frame2.draw2();
			frame2.setSize(1300,800);
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
			  //置于屏幕中央
			frame2.setLocation(screenWidth/6, screenHeight/6-100);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setVisible(true);
			this.setVisible(false);
		}else{//修改密码
			String name,password1,password2;
			name=JOptionPane.showInputDialog(this,"请输入新的用户名：");
			if(name!=null){
				password1=JOptionPane.showInputDialog(this,"请输入新的密码：");
				if(password1!=null){
					password2=JOptionPane.showInputDialog(this,"请再次确认新的密码：");
					if(password1.equals(password2)){
						//将新的用户名和密码写入文件
						try{
							FileOutputStream fos=new FileOutputStream("D:\\Huffman\\NameAndPassword.txt");
							OutputStreamWriter dis=new OutputStreamWriter(fos,"GB2312");
							BufferedWriter Writer=new BufferedWriter(dis);
							Writer.write(name);
							Writer.newLine();
							Writer.write(password1);
							Writer.close();
						} catch (FileNotFoundException e1) 
						{
							e1.printStackTrace();
						}catch (IOException e1) 
						{
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(this, "用户名和密码更新完成！","OK",JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(this, "两次密码不一致！","error",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this, "密码无效","error",JOptionPane.ERROR_MESSAGE);
				}
				
			}else{
				JOptionPane.showMessageDialog(this, "用户名无效！","error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
class BjFrame2 extends JFrame implements ActionListener{
	//static String str;
	public BjPanel3 panel2=new BjPanel3();
	public JButton button1_2;
	public JButton button2_2;
	public JButton button3_2;
	public JButton button4_2;
	public Container contentpaneFrame2=getContentPane();
	public BjFrame2(){
		panel2.setLayout(null);
	}
	public void draw2(){
		button1_2=new JButton("加密");
		button2_2=new JButton("解密");
		button3_2=new JButton("压缩");
		button4_2=new JButton("解压");
		button1_2.setFont(new Font("宋体", Font.BOLD, 30));
		button2_2.setFont(new Font("宋体", Font.BOLD, 30));
		button3_2.setFont(new Font("宋体",Font.BOLD,30));
		button4_2.setFont(new Font("宋体",Font.BOLD,30));
		button1_2.setText("加密");
		button2_2.setText("解密");
		button3_2.setText("压缩");
		button4_2.setText("解压");
		button1_2.setContentAreaFilled(false);
		button2_2.setContentAreaFilled(false);
		button3_2.setContentAreaFilled(false);
		button4_2.setContentAreaFilled(false);
		
		button1_2.addActionListener(this);
		button2_2.addActionListener(this);
		button3_2.addActionListener(this);
		button4_2.addActionListener(this);
		button3_2.setBounds(480, 385,120, 60); button4_2.setBounds(712, 385,120, 60);
		button1_2.setBounds(480, 295,120, 60); button2_2.setBounds(712, 295,120, 60);

		panel2.add(button1_2);
		panel2.add(button2_2);
		panel2.add(button3_2);
		panel2.add(button4_2);
		contentpaneFrame2.add(panel2);
	}
	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();
		if(b==button1_2){//加密
			Rank.go(1);
		}else if(b==button2_2||b==button4_2){//解密  解压
			int i;
			if(b==button2_2)
				i=1;
			else
				i=2;
			JFileChooser open=new JFileChooser();
			//检索.txt文件 
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
			open.setFileFilter(filter);
			int returnVal=open.showOpenDialog(open);
			if(returnVal == open.APPROVE_OPTION) {
				//str=open.getSelectedFile().getPath();
				FileOperate write=new FileOperate();
				write.inputstreamOfDecropt(open.getSelectedFile().getPath(),i);
			}
		}else if(b==button3_2){
			Rank.go(2);
		}
	}
}
class Rank{
	static JFrame frame=new JFrame("文件类型选择");
	static int sign;
	static void go(int i){
		sign=i;
		JRadioButton rb1=new JRadioButton("txt");
		JRadioButton rb2=new JRadioButton("jpg");
		JRadioButton rb3=new JRadioButton("mp3");
		JRadioButton rb4=new JRadioButton("mp4");
		JButton button=new JButton("确定");
		
		JPanel p1=new JPanel();
		
		p1.add(rb1);
		p1.add(rb2);
		p1.add(rb3);
		p1.add(rb4);
		Border etched=BorderFactory.createEtchedBorder();
		Border border=BorderFactory.createTitledBorder(etched,"请选择文件类型");
		p1.setBorder(border);
		
		//为按钮分组
		ButtonGroup group1=new ButtonGroup();
		group1.add(rb1);
		group1.add(rb2);
		group1.add(rb3);
		group1.add(rb4);
		
		frame.getContentPane().add(p1, BorderLayout.CENTER);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		
		ActionListener al=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()||rb2.isSelected()||rb3.isSelected()||rb4.isSelected()){
					JFileChooser open=new JFileChooser();
					
					//检索.txt文件
					FileNameExtensionFilter filter;
					/*filter= new FileNameExtensionFilter(".mp3","mp3");//".mp3","mp3"
					open.setFileFilter(filter);
					int returnVal=open.showOpenDialog(open);
					if(returnVal == open.APPROVE_OPTION) {
						FileOperate write=new FileOperate();
						write.inputstreamOfEncropt(open.getSelectedFile().getPath());
					}*/
					if(rb1.isSelected()){
						try{
							FileOutputStream fos;
							if(sign==1){
								fos=new FileOutputStream("D:\\Huffman\\encrypted.txt");
							}
							else{
								fos=new FileOutputStream("D:\\Huffman\\compressed.txt");
							}
								
							fos.write(1);
							fos.close();
						}catch(IOException e1){
							System.err.println("发生异常"+e1);
							e1.printStackTrace();
						}
						filter= new FileNameExtensionFilter(".txt","txt");//".mp3","mp3"
						open.setFileFilter(filter);
						int returnVal=open.showOpenDialog(open);
						if(returnVal == open.APPROVE_OPTION) {
							FileOperate write=new FileOperate();
							if(sign==1){
								write.inputstreamOfEncropt(open.getSelectedFile().getPath(),1);
								
							}
								
							else
							{
								write.inputstreamOfEncropt(open.getSelectedFile().getPath(),2);

							}
						}
					}
					else if(rb2.isSelected()){
						try{
							FileOutputStream fos;
							if(sign==1)
								fos=new FileOutputStream("D:\\Huffman\\encrypted.txt");
							else
								fos=new FileOutputStream("D:\\Huffman\\compressed.txt");							
							fos.write(2);
							fos.close();
						}catch(IOException e1){
							System.err.println("发生异常"+e1);
							e1.printStackTrace();
						}
						filter= new FileNameExtensionFilter(".jpg","jpg");//".mp3","mp3"
						open.setFileFilter(filter);
						int returnVal=open.showOpenDialog(open);
						if(returnVal == open.APPROVE_OPTION) {
							FileOperate write=new FileOperate();
							if(sign==1)
								write.inputstreamOfEncropt(open.getSelectedFile().getPath(),1);
							else
								write.inputstreamOfEncropt(open.getSelectedFile().getPath(),2);						
						}
					}
					else if(rb3.isSelected()){
						try{
							FileOutputStream fos;
							if(sign==1)
								fos=new FileOutputStream("D:\\Huffman\\encrypted.txt");
							else
								fos=new FileOutputStream("D:\\Huffman\\compressed.txt");							
							fos.write(3);
							fos.close();
						}catch(IOException e1){
							System.err.println("发生异常"+e1);
							e1.printStackTrace();
						}
						filter= new FileNameExtensionFilter(".mp3","mp3");//".mp3","mp3"
						open.setFileFilter(filter);
						int returnVal=open.showOpenDialog(open);
						if(returnVal == open.APPROVE_OPTION) {
							FileOperate write=new FileOperate();
							if(sign==1)
								write.inputstreamOfEncropt(open.getSelectedFile().getPath(),1);
							else
								write.inputstreamOfEncropt(open.getSelectedFile().getPath(),2);						
						}
					}
					else{
						try{
							FileOutputStream fos;
							if(sign==1)
								fos=new FileOutputStream("D:\\Huffman\\encrypted.txt");
							else
								fos=new FileOutputStream("D:\\Huffman\\compressed.txt");							
							fos.write(4);
							fos.close();
						}catch(IOException e1){
							System.err.println("发生异常"+e1);
							e1.printStackTrace();
						}
						filter= new FileNameExtensionFilter(".mp4","mp4");//".mp3","mp3"
						open.setFileFilter(filter);
						int returnVal=open.showOpenDialog(open);
						if(returnVal == open.APPROVE_OPTION) {
							FileOperate write=new FileOperate();
							if(sign==1)
								write.inputstreamOfEncropt(open.getSelectedFile().getPath(),1);
							else
								write.inputstreamOfEncropt(open.getSelectedFile().getPath(),2);						
						}
					}
				}
			}
		};
		button.addActionListener(al);
		frame.pack();
		frame.setSize(500,300);
		frame.setVisible(true);
	}
	
}
class BjPanel1 extends JPanel
{//框架一的面板类，为插入图片而设
	Image  image1;
	public BjPanel1()
	{
		image1=Toolkit.getDefaultToolkit().getImage("D:\\Game\\背景9.jpg"); 
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 
		int imWidth=image1.getWidth(this); 
		int imHeight=image1.getHeight(this); //定义图片的宽度、高度 
		int FWidth=getWidth(); 
		int FHeight=getHeight();//定义窗口的宽度、高度 
		int x=(FWidth-imWidth)/2; 
		int y=(FHeight-imHeight)/2;//计算图片的坐标,使图片显示在窗口正中间 
		g.drawImage(image1,0,0,null);//绘制图片 
 	}
 }
class BjPanel2 extends JPanel
{//框架一的面板类，为插入图片而设
	Image  image1;
	public BjPanel2()
	{
		image1=Toolkit.getDefaultToolkit().getImage("D:\\Game\\背景7.jpg"); 
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 
		int imWidth=image1.getWidth(this); 
		int imHeight=image1.getHeight(this); //定义图片的宽度、高度 
		int FWidth=getWidth(); 
		int FHeight=getHeight();//定义窗口的宽度、高度 
		int x=(FWidth-imWidth)/2; 
		int y=(FHeight-imHeight)/2;//计算图片的坐标,使图片显示在窗口正中间 
		g.drawImage(image1,0,0,null);//绘制图片 
 	}
 }
class BjPanel3 extends JPanel
{//框架一的面板类，为插入图片而设
	Image  image1;
	public BjPanel3()
	{
		image1=Toolkit.getDefaultToolkit().getImage("D:\\Game\\背景9.jpg"); 
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 
		int imWidth=image1.getWidth(this); 
		int imHeight=image1.getHeight(this); //定义图片的宽度、高度 
		int FWidth=getWidth(); 
		int FHeight=getHeight();//定义窗口的宽度、高度 
		int x=(FWidth-imWidth)/2; 
		int y=(FHeight-imHeight)/2;//计算图片的坐标,使图片显示在窗口正中间 
		g.drawImage(image1,0,0,null);//绘制图片 
 	}
 }
public class Huffman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BjFrame frame1=new BjFrame();
		frame1.draw();
		frame1.setSize(1300,800);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		  //置于屏幕中央
		frame1.setLocation(screenWidth/6, screenHeight/6-100);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setVisible(true);
	}

}
