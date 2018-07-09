package HITLLK;

import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

import HITLLK.enterGame.showTime;

import java.util.Timer;
//ready  go界面
class BjFrame3 extends JFrame implements ActionListener
{
	public BjPanel3 p3=new BjPanel3();
	public JButton button1_three;
	static int sign;
	//static MyTask order;
	static Timer timer1;
	static String Score[]=new String[51];
	static String Time[]=new String[501];
	Container contentPaneFrame3=getContentPane();
	public BjFrame3()
	{
		p3.setLayout(null);
	}
	public void draw3()
	{
		//只有一个开始按钮
		button1_three =new JButton("开始");
		 ImageIcon icon1=new ImageIcon("D:\\Game\\000.jpg");
		 //为按钮添加图片，设置位置
		 button1_three.setBounds(550, 358,140, 120);
		 button1_three.setIcon(icon1);
		 p3.add(button1_three); //添加按钮
		 contentPaneFrame3.add(p3);	
		 //为按钮添加事件响应函数
		 button1_three.addActionListener(this);
	}
	static class MyTask extends java.util.TimerTask{
		public void run(){
			if(enterGame.score!=enterGame.numOfRC*enterGame.numOfRC/2)
			{
				int numb[];
				numb=new int[enterGame.numOfRC*enterGame.numOfRC];
				for(int i=0;i<enterGame.numOfRC*enterGame.numOfRC;i++)
				{
					numb[i]=i;
				}
				int flag=1;
				int L = 0,k=0;
				for(int i=0;i<enterGame.numOfRC;++i){
					for(int j=0;j<enterGame.numOfRC;++j){
						enterGame.button[i][j].setVisible(false);
						enterGame.arr[i][j]='h';
					}
						
				}
				for(int i=enterGame.numOfRC*enterGame.numOfRC-1;i>=0;i--)
				{
					if(flag%2==1)
					{
						k=(int) ((Math.random()*24)%24);
					}
					L=(int)((Math.random()*1000)%i);
					int b=numb[L]%enterGame.numOfRC;
					int a=numb[L]/enterGame.numOfRC;
					enterGame.button[a][b].setIcon(enterGame.icon[k]);
					enterGame.button[a][b].setVisible(true);
					enterGame.arr[a][b]=(char)k;
					flag++;
					int temp=numb[L];
					numb[L]=numb[i];
					numb[i]=temp;
					if(i==2*enterGame.score)
						break;
				}
//				int already=0,a1,a2,b1,b2;				
//				for(int i=0;already<enterGame.score%(enterGame.numOfRC*enterGame.numOfRC/2)&&i<enterGame.numOfRC*enterGame.numOfRC-1;++i)
//				{
//					b1=i%enterGame.numOfRC;
//					a1=i/enterGame.numOfRC;
//				
//					if(enterGame.button[a1][b1].isVisible()==true)
//					{
//						for(int j=enterGame.numOfRC*enterGame.numOfRC-1;j>=i+1;--j)
//						{
//							b2=j%enterGame.numOfRC;
//							a2=j/enterGame.numOfRC;
//							if(enterGame.button[a2][b2].isVisible()==true&&enterGame.arr[a1][b1]==enterGame.arr[a2][b2])
//							{
//								enterGame.button[a1][b1].setVisible(false);
//								enterGame.button[a2][b2].setVisible(false);
//								enterGame.arr[a1][b1]='h';
//								enterGame.arr[a2][b2]='h';
//								already++;
//								break;
//							}
//						}
//					}
//							
//				}
			}	
		}
	}
	//go按钮的事件响应函数
	public void actionPerformed(ActionEvent e)
		{
			enterGame enter=new enterGame();
			for(int i=0;i<=500;i++){
				Time[i]=String.valueOf(i);
			}
			for(int i=0;i<=50;i++){
				Score[i]=String.valueOf(i);
			}
			enterGame.timer2=new Timer();
			enterGame.timer2.schedule(enter.new showTime(),1000,1000);
			
			if(sign==1)
			{//如果点击的是百变多样
				enterGame.score=0;//将分数重置为0
				enter.draw4(10);//进入第四个框架
				enter.frame4.setSize(1030,900);
				timer1 = new Timer();//在1秒后执行此任务,每次间隔5秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
				timer1.schedule(new MyTask(),1000,5000);
			}else if(sign==2)
			{//如果点击的是闯关模式
				enterGame.score=0;//将分数重置为0
				enter.draw4(10);//进入第四个框架
				enter.frame4.setSize(1030,900);
			}else if(sign==3)
			{//如果点击的是争分夺秒
				enterGame.score=0;//将分数重置为0
				enter.draw4(10);//进入第四个框架
				enter.frame4.setSize(1030,900);
				
			}
			//设置框架为可见，并将这个框架隐藏
			enter.frame4.setVisible(true);
			this.setVisible(false);
			enter.frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		}
}
//第二个框架界面的类
class BjFrame2 extends JFrame implements ActionListener
{
	//一进入这个框架就放音乐
	Music_1 m1=new Music_1();
	public BjPanel2 p2=new BjPanel2();
	public JButton button1_two;
	public JButton button2_two;
	public JButton button3_two;
	public JButton button4_two;
	Container contentPaneFrame2=getContentPane();
	public BjFrame2()
	{
		p2.setLayout(null);
	}
	//框架二的布局函数
	public  void draw2()
	{
		button1_two=new JButton("百变多样"); button2_two=new JButton("闯关模式");
		button3_two=new JButton("争分夺秒"); button4_two=new JButton("返回");
		
		//为四个按钮添加图片
		 ImageIcon icon_cfdm=new ImageIcon("D:\\Game\\55.jpg");
		 ImageIcon icon_cgms=new ImageIcon("D:\\Game\\66.jpg");
		 ImageIcon icon_bbdy=new ImageIcon("D:\\Game\\77.jpg");
		 ImageIcon icon_fh=new ImageIcon("D:\\Game\\88.jpg");
		 
		 button1_two.setIcon(icon_cfdm);
		 button2_two.setIcon(icon_cgms);
		 button3_two.setIcon(icon_bbdy);
		 button4_two.setIcon(icon_fh);
		//设置按钮位置
		button1_two.setBounds(480, 295,120, 60); button2_two.setBounds(712, 295,120, 60);
		button3_two.setBounds(480, 385,120, 60); button4_two.setBounds(712, 385,120, 60);
		p2.add(button1_two);p2.add(button2_two);p2.add(button3_two);p2.add(button4_two);
		contentPaneFrame2.add(p2);
		 //为按钮添加响应函数
		button1_two.addActionListener(this);
		button2_two.addActionListener(this);
		button3_two.addActionListener(this);
		button4_two.addActionListener(this);
	}
	//框架2按钮的消息响应函数
		public void actionPerformed(ActionEvent e)
		{
			//获取事件源
			JButton b=(JButton)e.getSource();
			m1.stop();//框架2的音乐停止
			if(b==button4_two)
			{
				//点击了返回按钮,重新回到界面一
				BjFrame1.frame1=new BjFrame1();
				BjFrame1.frame1.draw1();
				BjFrame1.frame1.setSize(1300, 800);
				BjFrame1.frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				BjFrame1.frame1.setVisible(true); 
				this.setVisible(false);
			}else if(b==button3_two){//点击了争分夺秒
				BjFrame3.sign=3;
				Select.go();
				Select.frame.setSize(300,200);
				this.setVisible(false);
			}
			else
			{
				//都将进入ready go界面
				enterGame.time=enterGame.record=500;
				BjFrame3 frame3=new BjFrame3();
				frame3.draw3();
				frame3.setSize(738, 572);
				frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				frame3.setVisible(true);
				this.setVisible(false);
				if(b==button1_two)
				{//记录下点击的是哪一种游戏模式，方便后边操作
					BjFrame3.sign=1;
				}else if(b==button2_two)
				{
					BjFrame3.sign=2;
				}
			}	
		}
}
class data{
	String name;
	String score;
	String time;
	data(){
		score="#";
	}
}
class card{
	String name;
	String count;
	card(){
		count="#";
	}
}
//框架一界面类
class BjFrame1 extends JFrame implements ActionListener
{
	static BjFrame1 frame1;
	Music m0=new Music();
//进入界面一就放音乐
	public BjPanel1 p1=new BjPanel1();
	public JButton button1_one;
	public JButton button2_one;
	public JButton button3_one;
	public JButton button4_one;
	Container contentPaneFrame1=getContentPane();
	
	public BjFrame1()
	{
		
		p1.setLayout(null);
	}
	public void draw1()
	{//界面一的布局函数
		button1_one=new JButton("进入游戏"); button2_one=new JButton("排行榜");
		button3_one=new JButton("帮助");   button4_one=new JButton("退出");
		
		//为按钮添加图片
		 ImageIcon icon_00=new ImageIcon("D:\\Game\\11.jpg");
		 ImageIcon icon_11=new ImageIcon("D:\\Game\\22.jpg");
		 ImageIcon icon_22=new ImageIcon("D:\\Game\\33.jpg");
		 ImageIcon icon_33=new ImageIcon("D:\\Game\\44.jpg");
		 
		 button1_one.setIcon(icon_00);
		 button2_one.setIcon(icon_11);
		 button3_one.setIcon(icon_22);
		 button4_one.setIcon(icon_33);
		
		//为按钮添加事件响应函数
		button1_one.addActionListener(this);
		button2_one.addActionListener(this);
		button3_one.addActionListener(this);
		button4_one.addActionListener(this);
		//设置按钮的布局
		button1_one.setBounds(480, 295,120, 60); button2_one.setBounds(712, 295,120, 60);
		button3_one.setBounds(480, 385,120, 60); button4_one.setBounds(712, 385,120, 60);
		p1.add(button1_one);p1.add(button2_one);p1.add(button3_one);p1.add(button4_one);
		contentPaneFrame1.add(p1); 
		
	}
	//按钮的事件响应函数
		public void actionPerformed(ActionEvent e)
		{
			JButton b=(JButton)e.getSource();
			//获取事件源
			if(b==button1_one){
				m0.stop();
				//一点击进入游戏按钮，界面一消失，音乐停止
				BjFrame2 frame2=new BjFrame2();
				//进入第二个界面
				frame2.draw2();
				frame2.setSize(1300, 800);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				frame2.setVisible(true);
				this.setVisible(false);	
				//界面一隐藏 ，显示界面二
			}
			else if(b==button2_one)
			{	
				//排行榜的显示
				Rank.go();
				Rank.frame.setSize(300,200);
				
			}else if(b==button3_one)
			{//帮助
				JDialog dialog=new JDialog(this,"帮助",true);
				String s;
				String text=" ";
				try
				{
					FileInputStream fis=new  FileInputStream("D:\\Game\\帮助.txt");
					InputStreamReader dis=new InputStreamReader(fis,"GB2312");
					BufferedReader reader=new BufferedReader(dis);
					//Font f=new Font("宋体",Font.PLAIN,50);
					while((  s=reader.readLine() )!=null)
					{
						text=text+"\n"+s;
					}
					dis.close();
				}catch(IOException e1)
				{
					e1.printStackTrace();
				}	
				
				JTextArea textArea=new JTextArea(text);
				Font f=new Font("宋体",Font.PLAIN,30);
				textArea.setFont(f);
				dialog.getContentPane().add(textArea);
				dialog.setBackground(Color.green);
				dialog.setSize(1850, 850);
				dialog.setVisible(true);

			}
			else
			{//退出
				System.exit(0);
			}
		}
}
class RankOfZFDM extends BjFrame1{//争分夺秒选择时间的框架
	static JFrame frame=new JFrame("争分夺秒排行榜");
	static void go(){
		JRadioButton rb1=new JRadioButton("30秒");
		JRadioButton rb2=new JRadioButton("60秒");
		JRadioButton rb3=new JRadioButton("90秒");
		JButton button=new JButton("确定");
		
		JPanel p1=new JPanel();
		
		p1.add(rb1);
		p1.add(rb2);
		p1.add(rb3);
		Border etched=BorderFactory.createEtchedBorder();
		Border border=BorderFactory.createTitledBorder(etched,"请选择时间模式");
		p1.setBorder(border);
		
		//为按钮分组
		ButtonGroup group1=new ButtonGroup();
		group1.add(rb1);
		group1.add(rb2);
		group1.add(rb3);
		
		frame.getContentPane().add(p1, BorderLayout.CENTER);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		
		ActionListener al=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()||rb2.isSelected()||rb3.isSelected()){
					JDialog dialog=new JDialog();
					JTextArea textArea=new JTextArea();
					Font f=new Font("宋体",Font.PLAIN,50);
					textArea.setFont(f);
					int i=0;
					data d[]=new data[100];
					for(i=0;i<100;i++){
						d[i]=new data();
					}
					try{//从排行榜.txt中读取并排序完成，存储在d[]结构体数组中
						FileInputStream fis;
						if(rb1.isSelected()){//选择了三十秒的模式
							dialog=new JDialog(BjFrame1.frame1,"争分夺秒30秒模式排行榜",true);
							fis=new  FileInputStream("D:\\Game\\争分夺秒30秒排行榜.txt");
						}else if(rb2.isSelected()){//选择了六十秒的模式
							dialog=new JDialog(BjFrame1.frame1,"争分夺秒60秒模式排行榜",true);
							fis=new  FileInputStream("D:\\Game\\争分夺秒60秒排行榜.txt");
						}else{//选择了九十秒的模式
							dialog=new JDialog(BjFrame1.frame1,"争分夺秒90秒模式排行榜",true);
							fis=new  FileInputStream("D:\\Game\\争分夺秒90秒排行榜.txt");
						}
						InputStreamReader dis=new InputStreamReader(fis,"GB2312");
						BufferedReader reader=new BufferedReader(dis);
						String s;
						i=0;
						int L=1;
						while((  s=reader.readLine() )!=null)
						{
							
							if((L++)!=1)
							{
								
								String a=s.substring(2, 3);
								String aa=" ";
								if(!a.equals(aa))
								{
									d[i].name=s.substring(0, 3);
									d[i].score=s.substring(5, 7);
									d[i].time=s.substring(9);
								}
								else
								{
									d[i].name=s.substring(0, 2);
									d[i].score=s.substring(6,8);
									d[i].time=s.substring(10);
								}
								i++;
							}
						}
						
						dis.close();
					}catch(IOException e1)
					{
						e1.printStackTrace();
					}
					int k=0;
					String ss1="#";
					int L=1;
					while(!d[k].score.equals(ss1))
					{
						if(L++==1)
							textArea.append(" 姓名 分数 时间 \n");
						if(d[k].name.length()==2)
						textArea.append(d[k].name+"    "+d[k].score+"  "+d[k].time+"\n");
						else if(d[k].name.length()==3)
							textArea.append(d[k].name+"  "+d[k].score+"  "+d[k].time+"\n");
						k++;
					}
					//在窗口中显示完成
			
					dialog.getContentPane().add(textArea);
					dialog.setBackground(Color.green);
					dialog.setSize(1000, 800);
					dialog.setVisible(true);
					frame.setVisible(false);
				}
			}
		};
		button.addActionListener(al);
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
class Rank{
	static JFrame frame=new JFrame("排行榜");
	static void go(){
		JRadioButton rb1=new JRadioButton("百变多样");
		JRadioButton rb2=new JRadioButton("闯关模式");
		JRadioButton rb3=new JRadioButton("争分夺秒");
		JButton button=new JButton("确定");
		
		JPanel p1=new JPanel();
		
		p1.add(rb1);
		p1.add(rb2);
		p1.add(rb3);
		Border etched=BorderFactory.createEtchedBorder();
		Border border=BorderFactory.createTitledBorder(etched,"请选择游戏模式");
		p1.setBorder(border);
		
		//为按钮分组
		ButtonGroup group1=new ButtonGroup();
		group1.add(rb1);
		group1.add(rb2);
		group1.add(rb3);
		
		frame.getContentPane().add(p1, BorderLayout.CENTER);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		
		ActionListener al=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()||rb2.isSelected()||rb3.isSelected()){
					if(rb3.isSelected()){//选择了争分夺秒的模式
						RankOfZFDM.go();
						RankOfZFDM.frame.setSize(300,200);
						frame.setVisible(false);
					}
					else{
						JDialog dialog=new JDialog();
						JTextArea textArea=new JTextArea();
						Font f=new Font("宋体",Font.PLAIN,50);
						textArea.setFont(f);
						int i=0;
						card d[]=new card[100];
						for(i=0;i<100;i++){
							d[i]=new card();
						}
						try{//从排行榜.txt中读取并排序完成，存储在d[]结构体数组中
							
							FileInputStream fis;
							if(rb1.isSelected()){//选择了百变多样模式
								dialog=new JDialog(BjFrame1.frame1,"百变多样排行榜",true);
								fis=new  FileInputStream("D:\\Game\\百变多样排行榜.txt");
							}else{//选择了闯关模式
								dialog=new JDialog(BjFrame1.frame1,"闯关模式排行榜",true);
								fis=new  FileInputStream("D:\\Game\\闯关模式排行榜.txt");
							}
							InputStreamReader dis=new InputStreamReader(fis,"GB2312");
							BufferedReader reader=new BufferedReader(dis);
							String s;
							i=0;
							int L=1;
							while((  s=reader.readLine() )!=null)
							{
								//System.out.println(s.substring(2,3));
								if((L++)!=1)
								{
								
									String a=s.substring(2, 3);
									String aa=" ";
									if(!a.equals(aa))
									{
										d[i].name=s.substring(0, 3);
										d[i].count=s.substring(5);
									}
									else
									{
										d[i].name=s.substring(0, 2);
										d[i].count=s.substring(6);
									}
									i++;
								}
							}
							dis.close();
						}catch(IOException e1)
						{
							e1.printStackTrace();
						}
						int k=0;
						String ss1="#";
						int L=1;
						while(!d[k].count.equals(ss1))
						{
							if(L++==1)
								textArea.append(" 姓名  关数\n");
							if(d[k].name.length()==3)
							textArea.append(d[k].name+"  "+d[k].count+"\n");
							else if(d[k].name.length()==2)
								textArea.append(d[k].name+"    "+d[k].count+"\n");
							k++;
						}
						//在窗口中显示完成
			
						dialog.getContentPane().add(textArea);
						dialog.setBackground(Color.green);
						dialog.setSize(1000, 800);
						dialog.setVisible(true);
						frame.setVisible(false);
					}
					
				}
				
			}
		};
		button.addActionListener(al);
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

class Select{//争分夺秒选择时间的框架
	static JFrame frame=new JFrame("争分夺秒");
	static void go(){
		JRadioButton rb1=new JRadioButton("30秒");
		JRadioButton rb2=new JRadioButton("60秒");
		JRadioButton rb3=new JRadioButton("90秒");
		JButton button=new JButton("确定");
		
		JPanel p1=new JPanel();
		
		p1.add(rb1);
		p1.add(rb2);
		p1.add(rb3);
		Border etched=BorderFactory.createEtchedBorder();
		Border border=BorderFactory.createTitledBorder(etched,"请选择时间模式");
		p1.setBorder(border);
		
		//为按钮分组
		ButtonGroup group1=new ButtonGroup();
		group1.add(rb1);
		group1.add(rb2);
		group1.add(rb3);
		
		frame.getContentPane().add(p1, BorderLayout.CENTER);
		frame.getContentPane().add(button, BorderLayout.SOUTH);
		
		ActionListener al=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rb1.isSelected()||rb2.isSelected()||rb3.isSelected()){
					if(rb1.isSelected()){//选择了三十秒的模式
						enterGame.record=enterGame.time=30;
					}else if(rb2.isSelected()){//选择了六十秒的模式
						enterGame.record=enterGame.time=60;
					}else{//选择了九十秒的模式
						enterGame.record=enterGame.time=90;
					}
					BjFrame3 frame3=new BjFrame3();
					frame3.draw3();
					frame3.setSize(738, 572);
					frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					frame3.setVisible(true);
					Select.frame.setVisible(false);
				}	
			}
		};
		button.addActionListener(al);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
class BjPanel1 extends JPanel
{//框架一的面板类，为插入图片而设
	Image  image1;
	public BjPanel1()
	{
		image1=Toolkit.getDefaultToolkit().getImage("D:\\Game\\界面一.jpg"); 
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
{
	Image  image;
	public BjPanel2() 
	{
		image=Toolkit.getDefaultToolkit().getImage("D:\\Game\\界面二.jpg"); 
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
		int imWidth=image.getWidth(this); 
		int imHeight=image.getHeight(this); //定义图片的宽度、高度 
		int FWidth=getWidth(); 
		int FHeight=getHeight();//定义窗口的宽度、高度 
		int x=(FWidth-imWidth)/2; 
		int y=(FHeight-imHeight)/2;//计算图片的坐标,使图片显示在窗口正中间 
		g.drawImage(image,0,0,null);//绘制图片 
 	}
 }
class BjPanel3 extends JPanel 
{
	Image  image;
	public BjPanel3()
	{
		image=Toolkit.getDefaultToolkit().getImage("D:\\Game\\背景二.jpg"); 
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
		int imWidth=image.getWidth(this); 
		int imHeight=image.getHeight(this); //定义图片的宽度、高度 
		int FWidth=getWidth(); 
		int FHeight=getHeight();//定义窗口的宽度、高度 
		int x=(FWidth-imWidth)/2; 
		int y=(FHeight-imHeight)/2;//计算图片的坐标,使图片显示在窗口正中间 
		g.drawImage(image,0,0,null);//绘制图片 
 	} 
 }
public class bkground
{
	
	public static void main(String[] args)
	{
		//JButton buttonFlag=new JButton();
		BjFrame1 frame1=new BjFrame1();
		//程序运行进入界面一
		frame1.draw1();
		frame1.setSize(1300, 800);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setVisible(true); 
	}
}
