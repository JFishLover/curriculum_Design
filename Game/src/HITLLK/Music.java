package HITLLK;
import java.applet.*; 
import java.io.*; 
import java.awt.Frame; 
import java.net.*; 
import javax.swing.JFrame;
public class Music extends JFrame{
	File f;
	URI uri;
    URL url; 
    AudioClip aau;
    Music()
    {
    	try
    	{
    		f = new File("D:\\Game\\背景音乐1.wav"); 
    		uri = f.toURI();
    		url = uri.toURL();  //解析地址
    		aau = Applet.newAudioClip(url);
    		aau.loop();  //循环播放
    		aau.play();
    	} 
    	catch (Exception e) 
    	{ 
    		e.printStackTrace();
        }
    }
    	void stop()
       	{
       		aau.stop();
       	}
}
class Music_1 extends JFrame
{ 
	File f;
	URI uri;
   URL url; 
   AudioClip aau; 
   Music_1()
   { 
   		try
   		{      
   			f = new File("D:\\Game\\背景音乐2.wav"); 
   			uri = f.toURI();
   			url = uri.toURL();  //解析地址
   			aau = Applet.newAudioClip(url);
   			aau.loop();  //循环播放
   			aau.play();
   		}
   		catch (Exception e) 
   		{ 
   			e.printStackTrace();
   		} 
   }
   	void stop()
   	{
   		aau.stop();
   	}
   
}
class Music_2 extends JFrame
{ 
	File f;
	URI uri;
	URL url; 
	AudioClip aau; 
	Music_2()
	{
		try
		{
   			f = new File("D:\\Game\\游戏音乐.wav"); 
   			uri = f.toURI();
   			url = uri.toURL();  //解析地址
   			aau = Applet.newAudioClip(url);
   			aau.loop();  //循环播放
   			aau.play();
   		}
		catch (Exception e)
   		{
   			e.printStackTrace();
   		} 
  }
 	void stop()
   	{
   		aau.stop();
   	}
}