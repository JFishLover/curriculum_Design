package HITLLK;

import java.awt.*;
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

import javax.swing.*; 
import java.util.Timer;
public class enterGame implements ActionListener
{
	Music_2 m2=new Music_2();
	JFrame frame4=new JFrame();
	JOptionPane dialog;
	static JButton b1,b2,b3,b4,b5,b6;
	static int numOfRC=0,r1,c1,r2,c2,flag1=0,score=0,time,R1,R2,C1,C2,record,recive_number,card1=0,card2=0,recordForHelp=0;
	static JButton button[][];
	static char arr[][];
	static ImageIcon icon[]=new ImageIcon[24];
	static Timer timer2;
	//static showTime show;
	enterGame()
	{
		icon[0]=new ImageIcon("D:\\Game\\初始化图片\\0.jpg");
		icon[1]=new ImageIcon("D:\\Game\\初始化图片\\1.jpg");
		icon[2]=new ImageIcon("D:\\Game\\初始化图片\\2.jpg");
		icon[3]=new ImageIcon("D:\\Game\\初始化图片\\3.jpg");
		icon[4]=new ImageIcon("D:\\Game\\初始化图片\\4.jpg");
		icon[5]=new ImageIcon("D:\\Game\\初始化图片\\5.jpg");
		icon[6]=new ImageIcon("D:\\Game\\初始化图片\\6.jpg");
		icon[7]=new ImageIcon("D:\\Game\\初始化图片\\7.jpg");
		icon[8]=new ImageIcon("D:\\Game\\初始化图片\\8.jpg");
		icon[9]=new ImageIcon("D:\\Game\\初始化图片\\9.jpg");
		icon[10]=new ImageIcon("D:\\Game\\初始化图片\\10.jpg");
		icon[11]=new ImageIcon("D:\\Game\\初始化图片\\11.jpg");
		icon[12]=new ImageIcon("D:\\Game\\初始化图片\\12.jpg");
		icon[13]=new ImageIcon("D:\\Game\\初始化图片\\13.jpg");
		icon[14]=new ImageIcon("D:\\Game\\初始化图片\\14.jpg");
		icon[15]=new ImageIcon("D:\\Game\\初始化图片\\15.jpg");
		icon[16]=new ImageIcon("D:\\Game\\初始化图片\\16.jpg");
		icon[17]=new ImageIcon("D:\\Game\\初始化图片\\17.jpg");
		icon[18]=new ImageIcon("D:\\Game\\初始化图片\\18.jpg");
		icon[19]=new ImageIcon("D:\\Game\\初始化图片\\19.jpg");
		icon[20]=new ImageIcon("D:\\Game\\初始化图片\\20.jpg");
    	icon[21]=new ImageIcon("D:\\Game\\初始化图片\\21.jpg");
    	icon[22]=new ImageIcon("D:\\Game\\初始化图片\\22.jpg");
    	icon[23]=new ImageIcon("D:\\Game\\初始化图片\\23.jpg");
	}

	static public void method()
	{//你要进行的操作 
		int numb[];
		numb=new int[numOfRC*numOfRC];
		for(int i=0;i<numOfRC*numOfRC;i++)
		{
			numb[i]=i;
		}
		int flag=1;
		int L = 0,k=0;
		for(int i=numOfRC*numOfRC-1;i>=0;i--)
		{
			if(flag%2==1)
			{
				k=(int) ((Math.random()*24)%24);
			}
			L=(int)((Math.random()*1000)%i);
			int b=numb[L]%numOfRC;
			int a=numb[L]/numOfRC;
			button[a][b].setIcon(icon[k]);
			button[a][b].setVisible(true);
			arr[a][b]=(char)k;
			flag++;
			int temp=numb[L];
			numb[L]=numb[i];
			numb[i]=temp;
		}		
		int already=0,a1,a2,b1,b2;					
		for(int i=0;already<score%(numOfRC*numOfRC/2)&&i<numOfRC*numOfRC-1;++i)
		{
			b1=i%enterGame.numOfRC;
			a1=i/enterGame.numOfRC;
			
			if(enterGame.button[a1][b1].isVisible()==true)
			{
				for(int j=numOfRC*numOfRC-1;j>=i+1;--j)
				{
					b2=j%numOfRC;
					a2=j/numOfRC;
					if(button[a2][b2].isVisible()==true&&arr[a1][b1]==arr[a2][b2])
					{
						button[a1][b1].setVisible(false);
						button[a2][b2].setVisible(false);
						arr[a1][b1]='h';
						arr[a2][b2]='h';
						already++;
						break;
					}
				}
			}
						
		}
	}
	public  void judgeOrderAgain()
	{//看能不能消除，以及消除之后是否需要重排
		if(HitLLk.ifClear(r1, c1, r2, c2, arr,numOfRC)||HitLLk.ifClear(r2, c2, r1, c1, arr,numOfRC))
		{
			button[r1][c1].setVisible(false);
			button[r2][c2].setVisible(false);
			arr[r1][c1]='h';
			arr[r2][c2]='h';
			score++;
			if(BjFrame3.sign==3)
			b2.setText(BjFrame3.Score[score]);
			//System.out.println(score);
			if(score%(numOfRC*numOfRC/2)!=0)
			{
				//System.out.println(score);
				while(again(button,arr,numOfRC))
				{
					int numb[];
					numb=new int[numOfRC*numOfRC];
					for(int i=0;i<numOfRC*numOfRC;i++)
					{
						numb[i]=i;
					}
					int flag=1;
					int L = 0,k=0;
					for(int i=numOfRC*numOfRC-1;i>=0;i--)
					{
						if(flag%2==1)
						{
							k=(int) ((Math.random()*24)%24);
						}
						L=(int)((Math.random()*1000)%i);
						int b=numb[L]%numOfRC;
						int a=numb[L]/numOfRC;
						button[a][b].setIcon(icon[k]);
						button[a][b].setVisible(true);
						arr[a][b]=(char)k;
						flag++;
						int temp=numb[L];
						numb[L]=numb[i];
						numb[i]=temp;
					}
					int already=0,a1,a2,b1,b2;

					for(int i=0;already<score&&i<numOfRC*numOfRC-1;++i)
					{
						b1=i%numOfRC;
						a1=i/numOfRC;
						if(button[a1][b1].isVisible()==true)
						{
							for(int j=numOfRC*numOfRC-1;j>=i+1;--j)
							{
								b2=j%numOfRC;
								a2=j/numOfRC;
								if(button[a2][b2].isVisible()==true&&arr[a1][b1]==arr[a2][b2])
								{
									button[a1][b1].setVisible(false);
									button[a2][b2].setVisible(false);
									arr[a1][b1]='h';
									arr[a2][b2]='h';
									already++;
									break;
								}
							}
						}
					}
				}
			}
			else
			{
				if(BjFrame3.sign==1)
					card1++;
				else if(BjFrame3.sign==2)
					card2++;
				String s="恭喜您闯关成功！\n"
						+ "您要进入下一关吗？";
				recive_number=JOptionPane.showOptionDialog(frame4, s, "游戏结束",
				JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null,null, null);
							
				if(recive_number==JOptionPane.YES_OPTION)   	//点击yes
				{
					//继续进行下一关，不进行分数的记录工作
					int card;
					if(BjFrame3.sign==1){
						card=card1;
					}
					else
						card=card2;
					b2.setText(Integer.toString(card+1));
					score=0;
					this.method();
					recive_number=100;
				}
				else if(recive_number==JOptionPane.NO_OPTION)    //点击NO
				{
					recive_number=100;
					timer2.cancel();
					if(BjFrame3.sign==1)
					BjFrame3.timer1.cancel();
					//先进行分数的记录工作
					String t = "本轮游戏结束\n"
							+ "请输入您的姓名：";
					String name;
					name=JOptionPane.showInputDialog(frame4,t);
					if(name!=null)
					{
						card d[]=new card[100];
						int i=0;
						for(i=0;i<100;i++)
							d[i]=new card();
						try{//从排行榜中读取并排序完成，存储在d[]结构体数组中
							FileInputStream fis;
							if(BjFrame3.sign==1)
								fis=new  FileInputStream("D:\\Game\\百变多样排行榜.txt");
							else
								fis=new  FileInputStream("D:\\Game\\闯关模式排行榜.txt");
							InputStreamReader dis=new InputStreamReader(fis,"GB2312");
							BufferedReader reader=new BufferedReader(dis);
						
							String s2;
							i=0;
							int L=1;
							while((  s2=reader.readLine() )!=null)
							{
							
								if((L++)!=1)
								{
									String a=s2.substring(2, 3);
									String aa=" ";
									if(!a.equals(aa))
									{
										d[i].name=s2.substring(0, 3);
										d[i].count=s2.substring(5);
									}
									else
									{
										d[i].name=s2.substring(0, 2);
										d[i].count=s2.substring(6);
									}
									i++;
								}
							}
							//传入参数================================
							int card;
							if(BjFrame3.sign==1){
								card=card1;
							}
							else
								card=card2;
							b2.setText(Integer.toString(card+1));
							for(int j=i-1;j>=0;j--)
							{
								int sum=0;
								int p=0;
							
								while(p<d[j].count.length())
								{
									sum=sum*10+d[j].count.charAt(p++)-48;
								}
								if(card>sum)
								{
									d[j+1].name=d[j].name;
									d[j+1].count=d[j].count;
								
								}
								else
								{
									d[j+1].name=name;
									d[j+1].count=Integer.toString(card);
									if(card<10)
										d[j+1].count="0"+d[j+1].count;
									break;
								}
								if(j==0)
								{
									d[0].name=name;
									d[0].count=Integer.toString(card);
									if(card<10)
										d[0].count="0"+d[j+1].count;
								}
							}
						
							dis.close();
						}catch(IOException e1)
						{
							e1.printStackTrace();
						}
						try {
								FileOutputStream fos;
								if(BjFrame3.sign==1)
									fos= new FileOutputStream("D:\\Game\\百变多样排行榜.txt");
								else
									fos= new FileOutputStream("D:\\Game\\闯关模式排行榜.txt");
								OutputStreamWriter dis=new OutputStreamWriter(fos,"GB2312");
								BufferedWriter Writer=new BufferedWriter(dis);
								int k=0;
								String ss1="#";
								int L=1;
								while(!d[k].count.equals(ss1))
								{
									if((L++) == 1)
									{
										Writer.write(" 姓名  关数");
										Writer.newLine();
									}
									if(d[k].name.length()==3)
										Writer.write(d[k].name+"  "+d[k].count);
									else if(d[k].name.length()==2)
										Writer.write(d[k].name+"    "+d[k].count);
									if(!d[k+1].count.equals(ss1))
										Writer.newLine();						
									k++;
								}
								Writer.close();
								
							} catch (FileNotFoundException e1) 
								{
									e1.printStackTrace();
								}
							catch (IOException e1) 
							{
								e1.printStackTrace();
							}				
						m2.stop();
						score=0;
						b2.setText("0");
						BjFrame2 frame2=new BjFrame2();
						frame2.draw2();
						frame2.setSize(1300, 800);
						frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
						frame2.setVisible(true);
						this.frame4.setVisible(false);
					}else
					{
						timer2.cancel();
						if(BjFrame3.sign==1)
							BjFrame3.timer1.cancel();

						m2.stop();
						//先关闭第三个音乐
						b2.setText("0");
						score=0;
						BjFrame2 frame2=new BjFrame2();
						frame2.draw2();
						frame2.setSize(1300, 800);
						frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
						frame2.setVisible(true);
						frame4.setVisible(false);	
					}
				}
			}
		}
	}
	class showTime extends java.util.TimerTask{//显示时间，如果时间到，弹出对话框
		public void run(){
			if(record>=0){
				if(score!=50)
				b3.setText(BjFrame3.Time[record--]);
			}else{
				//先进行分数的记录工作
				String t = "本轮游戏结束\n"
						+ "请输入您的姓名：";
				String name;
				name=JOptionPane.showInputDialog(frame4,t);
				if(name!=null){
					card d1[]=new card[100];
					data d2[]=new data[100];
					try{//从排行榜.txt中读取并排序完成，存储在d[]结构体数组中
						FileInputStream fis;
						if(BjFrame3.sign==1)
							fis=new  FileInputStream("D:\\Game\\百变多样排行榜.txt");
						else if(BjFrame3.sign==2)
							fis=new  FileInputStream("D:\\Game\\闯关模式排行榜.txt");
						else{
							if(time==30)
								fis=new  FileInputStream("D:\\Game\\争分夺秒30秒排行榜.txt");
							else if(time==60)
								fis=new  FileInputStream("D:\\Game\\争分夺秒60秒排行榜.txt");
							else
								fis=new  FileInputStream("D:\\Game\\争分夺秒90秒排行榜.txt");
						}
						InputStreamReader dis=new InputStreamReader(fis,"GB2312");
						BufferedReader reader=new BufferedReader(dis);

						int i=0;
						for(i=0;i<100;i++)
							d1[i]=new card();
						for(i=0;i<100;i++)
							d2[i]=new data();
						int L=1;
						String s2;
						if(BjFrame3.sign==1||BjFrame3.sign==2){
							i=0;
							while((  s2=reader.readLine() )!=null)
							{
								if((L++)!=1)
								{
									String a=s2.substring(2, 3);
									String aa=" ";
									if(!a.equals(aa))
									{
										d1[i].name=s2.substring(0, 3);
										d1[i].count=s2.substring(5);
									}
									else
									{
										d1[i].name=s2.substring(0, 2);
										d1[i].count=s2.substring(6);
									}
									i++;
								}
							}
							//传入参数================================
							int card;
							if(BjFrame3.sign==1){
								card=card1;
							}
							else
								card=card2;
							//System.out.println(card);
							for(int j=i-1;j>=0;j--)
							{
								int sum=0;
								int p=0;
								while(p<d1[j].count.length())
								{
									sum=sum*10+d1[j].count.charAt(p++)-48;
								}
								if(card>sum)
								{
									d1[j+1].name=d1[j].name;
									d1[j+1].count=d1[j].count;
							
								}
								else
								{
									d1[j+1].name=name;
									d1[j+1].count=Integer.toString(card);
									if(card<10)
										d1[j+1].count="0"+d1[j+1].count;
									break;
								}
								if(j==0)
								{
									d1[0].name=name;
									d1[0].count=Integer.toString(card);
									if(card<10)
										d1[0].count="0"+d1[j+1].count;
								}
							}
							dis.close();
						}
						else{
							i=0;
							while((  s2=reader.readLine() )!=null)
							{
								if((L++)!=1)
								{
							
									String a=s2.substring(2, 3);
									String aa=" ";
									if(!a.equals(aa))
									{
										d2[i].name=s2.substring(0, 3);
										d2[i].score=s2.substring(5,7);
										d2[i].time=s2.substring(9);
									}
									else
									{
										d2[i].name=s2.substring(0, 2);
										d2[i].score=s2.substring(6, 8);
										d2[i].time=s2.substring(10);
									}
									i++;
								}
							}
							//传入参数================================
							for(int j=i-1;j>=0;j--)
							{
								int sum=0;
								int p=0;
								//char a=d2[j].score.charAt(p);
								while(p<d2[j].score.length())
								{
									sum=sum*10+d2[j].score.charAt(p++)-48;
								}
								if(score>sum)
								{
									d2[j+1].name=d2[j].name;
									d2[j+1].score=d2[j].score;
									d2[j+1].time=d2[j].time;
								}
								else
								{
									d2[j+1].name=name;
									d2[j+1].score=Integer.toString(score);
									if(score<10)
										d2[j+1].score="0"+d2[j+1].score;
									d2[j+1].time=Integer.toString(time);
									break;
								}
								if(j==0)
								{
									d1[0].name=name;
									d2[0].score=Integer.toString(score);
									if(score<10)
										d2[0].score="0"+d2[0].score;
									d2[0].time=Integer.toString(time);
								}
							}
							dis.close();
						}
					}catch(IOException e1)
					{
						e1.printStackTrace();
					}
					try {
							FileOutputStream fos;
							if(BjFrame3.sign==1)
								fos= new FileOutputStream("D:\\Game\\百变多样排行榜.txt");
							else if(BjFrame3.sign==2)
								fos= new FileOutputStream("D:\\Game\\闯关模式排行榜.txt");
							else{
								if(time==30)
									fos= new FileOutputStream("D:\\Game\\争分夺秒30秒排行榜.txt");
								else if(time==60)
									fos= new FileOutputStream("D:\\Game\\争分夺秒60秒排行榜.txt");
								else
									fos= new FileOutputStream("D:\\Game\\争分夺秒90秒排行榜.txt");
							}
							OutputStreamWriter dis=new OutputStreamWriter(fos,"GB2312");
							BufferedWriter Writer=new BufferedWriter(dis);
							int k=0;
							String ss1="#";
							int L=1;
							if(BjFrame3.sign==1||BjFrame3.sign==2){
								while(!d1[k].count.equals(ss1))
								{
									if((L++) == 1)
									{
										Writer.write(" 姓名  关数");
										Writer.newLine();
									}
									if(d1[k].name.length()==3)
									Writer.write(d1[k].name+"  "+d1[k].count);
									else if(d1[k].name.length()==2)
										Writer.write(d1[k].name+"    "+d1[k].count);
									if(!d1[k+1].count.equals(ss1))
										Writer.newLine();					
									k++;
								}
								Writer.close();
							}
							else{
								while(!d2[k].score.equals(ss1))
								{
									if((L++) == 1)
									{
										Writer.write(" 姓名   分数  时间");
										Writer.newLine();
									}
									if(d2[k].name.length()==3)
									Writer.write(d2[k].name+"  "+d2[k].score+"  "+d2[k].time);
									else if(d2[k].name.length()==2)
										Writer.write(d2[k].name+"    "+d2[k].score+"  "+d2[k].time);
								
									if(!d2[k+1].score.equals(ss1))
										Writer.newLine();								
									k++;
								}
								Writer.close();
							}
						} catch (FileNotFoundException e1) 
							{
								e1.printStackTrace();
							}
						catch (IOException e1) 
						{
							e1.printStackTrace();
						}   
				   //写入完成
					//this.cancel();取消了计数器
					//时间到，弹出对话框
					//闯关失败
					if(time==500){
						String s="很遗憾闯关失败！\n"
								+ "您要继续挑战吗？";
						recive_number=JOptionPane.showOptionDialog(frame4, s, "游戏结束",
							JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null,null, null);
					}
					else{
						String s="时间到!\n"
								+ "您的得分是："+score
								+ "\n您的用时是："+time+"\n还要继续挑战吗？";
						recive_number=JOptionPane.showOptionDialog(frame4, s, "游戏结束",
								JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null,null, null);
					}
					if(recive_number==JOptionPane.YES_OPTION)   	//点击yes
					{
						b2.setText("0");
						score=0;
						enterGame.method();
						//对按钮的时间进行更改
						b3.setText(String.valueOf(time));
						record=time;
					}
					else if(recive_number==JOptionPane.NO_OPTION)   //点击NO
					{
						timer2.cancel();
						if(BjFrame3.sign==1)
							BjFrame3.timer1.cancel();

						m2.stop();
						//先关闭第三个音乐
						b2.setText("0");
						score=0;
						BjFrame2 frame2=new BjFrame2();
						frame2.draw2();
						frame2.setSize(1300, 800);
						frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
						frame2.setVisible(true);
						frame4.setVisible(false);
					}
				}else{
					timer2.cancel();
					if(BjFrame3.sign==1)
						BjFrame3.timer1.cancel();

					m2.stop();
					//先关闭第三个音乐
					b2.setText("0");
					score=0;
					BjFrame2 frame2=new BjFrame2();
					frame2.draw2();
					frame2.setSize(1300, 800);
					frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					frame2.setVisible(true);
					frame4.setVisible(false);
				}
			}
		}
	}
	
	static class help extends java.util.TimerTask{
		public void run(){
			button[R1][C1].setVisible(true);
			button[R2][C2].setVisible(true);
			this.cancel();
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b=(JButton)e.getSource();
		if(b==b1){}//得分
		else if(b==b2){}
		else if(b==b3){}
		else if(b==b4)
		{//帮帮忙
			if(BjFrame3.sign==3){
				if(score>=1)
				score--;
				b2.setText(Integer.toString(score));
			}
				for(int i=0;i<numOfRC*numOfRC-1;++i)
				{
					int b1=i%numOfRC;
					int a1=i/numOfRC;
					if(button[a1][b1].isVisible()==true)
					{
						for(int j=i+1;j<=numOfRC*numOfRC-1;j++)
						{
							int b2=j%numOfRC;
							int a2=j/numOfRC;
							if(button[a2][b2].isVisible()==true && HitLLk.ifClear(a1,b1,a2,b2,arr,numOfRC)==true)
							{//闪烁按钮
								R1=a1;
								R2=a2;
								C1=b1;
								C2=b2;
							}
						}
					}
				}
				button[R1][C1].setVisible(false);
				button[R2][C2].setVisible(false);
				Timer timer=new Timer();
				timer.schedule(new help(),200,1000);
			
		}else if(b==b5)
		{//剩余时间
		
		}
		else if(b==b6)
		{//返回
			card1=card2=0;
			b2.setText("0");
			score=0;
			timer2.cancel();
			if(BjFrame3.sign==1)
			BjFrame3.timer1.cancel();
			m2.stop();
			BjFrame2 frame2=new BjFrame2();
			frame2.draw2();
			frame2.setSize(1300, 800);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setVisible(true);
			this.frame4.setVisible(false);
		}
		else
		{//判断消除的按钮
			for(int i=0;i<numOfRC;i++)
			{
				for(int j=0;j<numOfRC;j++)
				{
					if(b==button[i][j])
					{
						if(flag1%2==0)
						{
							r1=i;
							c1=j;
						}
						else
						{
							r2=i;
							c2=j;
							
						}
						flag1++;
					}
				}
			}
			if(flag1%2==0)
			{
				judgeOrderAgain();
			}
		}
	}
	static boolean again(JButton button[][],char arr[][],int m)
	{
		for(int i=0;i<m*m-1;i++)
		{
			int b1=i%m;
			int a1=i/m;
			if(button[a1][b1].isVisible()==true)
			{
				for(int j=i+1;j<=m*m-1;j++)
				{
					int b2=j%m;
					int a2=j/m;
					if(button[a2][b2].isVisible()==true && HitLLk.ifClear(a1,b1,a2,b2,arr,numOfRC)==true)
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
	static void csh(JButton button[][],char arr[][],int m)
	{
		numOfRC=m;
		int numb[];
		numb=new int[100];
		do{
			for(int i=0;i<m*m;i++)
			{
				numb[i]=i;
			}
			int flag=1;
			int L = 0,k=0;
			for(int i=m*m-1;i>=0;i--)
			{
				if(flag%2==1)
				{
					k=(int) ((Math.random()*24)%24);
				}
				L=(int)((Math.random()*1000)%i);
				int b=numb[L]%m;
				int a=numb[L]/m;
				button[a][b].setIcon(icon[k]);
				button[a][b].setVisible(true);
				arr[a][b]=(char)k;
				flag++;
				int temp=numb[L];
				numb[L]=numb[i];
				numb[i]=temp;
			}
		}while(again(button,arr,m));
	}
	public void draw4(int m)
	{
		button=new JButton[m][m];
		arr=new char[m][m];
		
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<m;j++){
				button[i][j]=new JButton();
				button[i][j].addActionListener(this);
			}
		}
		b1=new JButton();
		if(BjFrame3.sign==3)
			b2=new JButton("0");
		else
			b2=new JButton("1");
		b4=new JButton();
		b3=new JButton(String.valueOf(time));
		b5=new JButton();
		b6=new JButton();
		b1.setBackground(new Color(0,124,195));
		b1.setFont(new Font("宋体", Font.BOLD, 45));
		if(BjFrame3.sign==3)
			b1.setText("得分");
		else
			b1.setText("关数");
		b2.setBackground(new Color(0,124,195));
		b2.setFont(new Font("宋体", Font.BOLD, 45));
		b3.setBackground(new Color(0,124,195));
		b3.setFont(new Font("宋体", Font.BOLD, 45));
		b4.setBackground(new Color(0,124,195));
		b4.setFont(new Font("宋体", Font.BOLD, 45));
		b4.setText("提示");
		b5.setBackground(new Color(0,124,195));
		b5.setFont(new Font("宋体", Font.BOLD, 45));
		b5.setText("时间");
		b6.setBackground(new Color(0,124,195));
		b6.setFont(new Font("宋体", Font.BOLD, 45));
		b6.setText("返回");
		
		frame4.getContentPane().setLayout(new BorderLayout());
		JPanel j_up=new JPanel();
		j_up.setLayout(new GridLayout(1,6));
		j_up.add(b1);
		j_up.add(b2);
		j_up.add(b5);
		j_up.add(b3);
		j_up.add(b4);
		j_up.add(b6);
		
		b1.setFocusPainted(false);
		b2.setFocusPainted(false);
		b3.setFocusPainted(false);
		b4.setFocusPainted(false);
		b5.setFocusPainted(false);
		b6.setFocusPainted(false);
		
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		
		
		j_up.setBackground(Color.gray);
		frame4.getContentPane().add(j_up,BorderLayout.NORTH);
		JPanel panel2=new JPanel();
		panel2.setLayout(new GridLayout(m,m));
		this.csh(button,arr, m);
		for(int i=0;i<m;i++)
			for(int j=0;j<m;j++)
				panel2.add(button[i][j]);
		frame4.getContentPane().add(panel2,BorderLayout.CENTER);
		

	}
}
