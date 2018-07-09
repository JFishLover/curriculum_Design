package designForDataStruct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import designForDataStruct.BinarySearchTree.Node;

public class FileOperate {
	public void inputstreamOfEncropt(String FileName,int i){
		/*第一棵二叉搜索树，统计每个字符的频率*/
		BinarySearchTree Bintree=new BinarySearchTree();
		try{
			FileInputStream fis=new FileInputStream(FileName);
			int c;//字符标号   
			
			while((c=fis.read())!=-1){
				Bintree.insert(c);
				//System.out.println(c);
			}
			fis.close();
		}catch(IOException e){
				System.err.println("发生异常"+e);
				e.printStackTrace();
			}//从源文件读入数组储存完成
		
		
		Bintree.inorder();//中序遍历二叉搜索树，为临时数组赋值
		Node []node=new Node[2*Bintree.numOfNode-1];//为建立哈夫曼树而建立的数组
//		System.out.println("总共有"+Bintree.numOfNode+"个节点");
		for(int j=0;j<2*Bintree.numOfNode-1;j++){
			node[j]=new BinarySearchTree().new Node(-1);
		}
//		System.out.println("数组大小为："+node.length);  //     调试语句
//		System.out.println(Bintree.numOfNode);
//		System.out.println();
		for(int j=0;j<Bintree.numOfNode;j++){//将临时数组中的值赋给哈夫曼数组
			node[j]=Bintree.Temp[j];
			node[j].left=null;//千万不要忘记了释放结点指针，否则遍历时就混乱了
			node[j].right=null;
			//i+=node[j].count;
		}
		Node tempNode;//临时节点，便于排序
		for(int j=0;j<Bintree.numOfNode-1;j++){//冒泡排序按照频率大小将哈夫曼树组进行排序，以建立哈夫曼树
			for(int k=0;k<Bintree.numOfNode-1-j;k++){
				if(node[k].count>node[k+1].count){
					tempNode=node[k];
					node[k]=node[k+1];
					node[k+1]=tempNode;
				}
			}
		}//排序正确
		/*for(int j=0;j<Bintree.numOfNode;j++){//调试排序是否正确
			System.out.println(node[j].data+"字符的个数是"+node[j].count);
		}*/
		/*接下来构造哈夫曼树*/
		int k,j,count;//j作为前面的指针，k作为后面待比较大小的元素的指针,count表示新生成的节点从后边开始向前应该放到第几个位置了
		node[node.length-1].count=node[0].count+node[1].count;//先找到两个频率最小的节点
		node[node.length-1].left=node[0];
		node[node.length-1].right=node[1];
		j=2;
		k=node.length-1; 
		count=node.length-2;
		//构造哈夫曼树，直到原始元素后边放上元素循环跳出，该位置存放的是哈弗曼树的根节点
		while(count>=Bintree.numOfNode){
			if(j+1<Bintree.numOfNode){
				if(node[j+1].count<node[k].count){
					node[count].count=node[j].count+node[j+1].count;
					node[count].left=node[j];
					node[count].right=node[j+1];
					j=j+2;
					count--;
				}else{
					if(k-count>=2&&node[j].count>node[k-1].count){//如果前边的小的比后边的大的还要大，说明后边两个的概率最小
						node[count].count=node[k].count+node[k-1].count;
						node[count].left=node[k];
						node[count].right=node[k-1];
						k=k-2;
						count--;
					}
					else{
						node[count].count=node[j].count+node[k].count;
						node[count].left=node[j];
						node[count].right=node[k];
						k--;
						count--;
						j++;
					}
				}
			}else if(j==Bintree.numOfNode-1){
				if(k-count>=2){
					if(node[j].count<=node[k].count){
						node[count].count=node[k].count+node[j].count;
						node[count].left=node[j];
						node[count].right=node[k];
						j++;
						count--;
						k--;
					}else{
						node[count].count=node[k].count+node[k-1].count;
						node[count].left=node[k];
						node[count].right=node[k-1];
						count--;
						k=k-2;
					}
				}else{
					node[count].count=node[j].count+node[k].count;
					node[count].left=node[j];
					node[count].right=node[k];
					k--;
					count--;
					j++;
				}
				
			}else{
				node[count].count=node[k].count+node[k-1].count;
				node[count].left=node[k];
				node[count].right=node[k-1];
				k=k-2;
				count--;
			}
		}
		/*System.out.println("测试语句");//===================
		for(int t=node.length-1;t>=Bintree.numOfNode;t--){
			System.out.println(node[t].count+"它的左孩子的字符是："+node[t].left.data+"左孩子的对应频率是"+node[t].left.count+"它的右孩子的字符是"+node[t].right.data+"右孩子的对应频率是"+node[t].right.count);
		}*/
		/*根据根节点，构造哈夫曼树*/
		BinarySearchTree Huffman=new BinarySearchTree(node[Bintree.numOfNode]);
		/*建立数组，专门用来存储每个节点对应的序号，以字符对应的数字为下标的元素存储的就是该节点的序号*/
		int inorderOfHuffman[]=new int[256];
		StringBuffer HuffmanCoding[]=new StringBuffer[256];
		for(int t=0;t<256;t++){
			inorderOfHuffman[t]=0;
			HuffmanCoding[t]=new StringBuffer("");
		}
		/*中序遍历哈夫曼树并将节点的count编号，以便进行哈夫曼编码,并将每个字符对应的编号存储在数组inorderOfHuffman中*/
		Huffman.inorder2(inorderOfHuffman);
		/*然后进行的是哈夫曼编码工作,可以将每个字符的哈夫曼编码存储在以字符对应的数字为下标的元素中*/
		for(int t=0;t<256;t++){
			if(inorderOfHuffman[t]!=0)
			Huffman.coding(inorderOfHuffman, HuffmanCoding, t);
		}
		/*接下来将每个字符的哈夫曼编码写入配置文件中，以便以后解密时使用*/
		
		try{
			FileOutputStream fos;
			fos=new FileOutputStream("D:\\Huffman\\HuffmanCoding.txt");
			OutputStreamWriter dis=new OutputStreamWriter(fos);
			BufferedWriter Writer=new BufferedWriter(dis);
			
			for(int t=0;t<256;t++){
				if(HuffmanCoding[t]!=null){
					Writer.write(HuffmanCoding[t].toString());
					//StringBuffer类型的不能写入，需要转换成String类型的
				}
				Writer.newLine();
			}
			Writer.close();
		} catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}catch (IOException e1) 
		{
			e1.printStackTrace();
		}
			
		/*接下来就要再次访问源文件，对每一个字符，用他的编码进行表示，存储到临时文件*/
		StringBuffer str=new StringBuffer("");
		try{
			FileInputStream fis=new FileInputStream(FileName);
			int c;/*字符标号*/ 
			if(i==1){//加密处理
				try{
				FileOutputStream fos=new FileOutputStream("D:\\Huffman\\lengthOfHuffman.txt");
				while((c=fis.read())!=-1){/*一次读取一个字节，然后将对应的哈夫曼编码追加到字符串str上*/
					str.append(HuffmanCoding[c]);
					/*将字符的哈夫曼编码的长度写入文件*/
					fos.write(HuffmanCoding[c].length());
				}
				fos.close();
				}catch(IOException e){
					System.err.println("发生异常"+e);
					e.printStackTrace();
				}
			}else{//压缩操作
				while((c=fis.read())!=-1){/*一次读取一个字节，然后将对应的哈夫曼编码追加到字符串str上*/
					str.append(HuffmanCoding[c]);
				}
			}
			fis.close();
		}catch(IOException e){
			System.err.println("发生异常"+e);
			e.printStackTrace();
		}
		//System.out.println(str);
		/*对字符串进行操作，写入最终的加密文件中*/
		String substr;
		try{
			FileOutputStream fos;
			if(i==1)
				fos=new FileOutputStream("D:\\Huffman\\encrypted.txt",true);
			else
				fos=new FileOutputStream("D:\\Huffman\\compressed.txt",true);
			int c=0;//字符标号   
			int t;
			fos.write(str.length()%8);//第二个字符是最后不够八个的字符的个数所对应的字符，将来解密的时候用
			for(t=0;t+7<str.length();t=t+8){//将前面八的倍数个字符写入
				substr=str.substring(t, t+8);
				for(int m=7;m>=0;m--){//将字符串转换为数字
					if(substr.charAt(7-m)=='1'){
						c+=Math.pow(2, m);
					}
				}
				fos.write(c);
				c=0;//千万不要忘了，否则上次的c也给加起来了
			}
			for(;t<str.length();t++){//将最后不满八个的字符分别写入文件，无需进行转换
				fos.write(str.charAt(t));
			}
			fos.close();
		}catch(IOException e){
			System.err.println("发生异常"+e);
			e.printStackTrace();
		}
	}
	public void inputstreamOfDecropt(String FileName,int l){
		/*建立字符串数组，存储哈夫曼编码*/
		String HuffmanCoding[]=new String[256];
		/*访问存放哈夫曼编码的文件，将每个字符的哈夫曼编码存储在字符对应的数组上*/
		try{
			FileInputStream fis=new FileInputStream("D:\\Huffman\\HuffmanCoding.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader Read=new BufferedReader(dis);
			for(int t=0;t<256;t++){
				HuffmanCoding[t]=Read.readLine();
			}
			Read.close();
		} catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		
		/*从加密的文件中依次读取字节，进行操作*/
		StringBuffer str=new StringBuffer("");
		Stack<Integer> stack=new Stack<Integer>();
		int first=0;//记录第一个字节的值，标志源文件类型
		try{
			FileInputStream fis=new FileInputStream(FileName);
			int c;//字符标号
			first=fis.read();
			int sign=fis.read();
			//System.out.println("sign="+sign);
			/*按照加密的方式进行解密*/
			while(fis.available()!=sign/*&&(c=fis.read())!=-1*/){
				c=fis.read();
				//System.out.println("c="+c);
				while(c!=0){
					stack.push(c%2);
					c=c/2;
				}
				for(int j=stack.length();j<8;j++){//不够八个字符前面用0补齐
					str.append("0");
				}
				while(!stack.empty()){
					str.append(stack.pop().toString());
				}				
			}
			/*把最后剩下的那些附加到字符串上*/
			while((c=fis.read())!=-1){
				//System.out.println("最后剩下的部分"+c);
				if(c==48){
					str.append("0");
				}else
					str.append("1");
			}
			fis.close();
		}catch(IOException e){
				System.err.println("发生异常"+e);
				e.printStackTrace();
			}
		/*对该字符串进行操作，匹配哈夫曼编码*/
		if(l==1){//解密使用
			/*将哈夫曼编码存入链表，便于解密使用*/
			LinkList linklist=new LinkList();
			for(int t=0;t<256;t++){
				if(!HuffmanCoding[t].equals("")){
					linklist.insert(HuffmanCoding[t], t);
				}
			}
			try{
			FileInputStream fis=new FileInputStream("D:\\Huffman\\lengthOfHuffman.txt");
			int c;//字符标号 
			int i=0;//表示取子串参数中的起始位置
			String subStr;
			try{
				FileOutputStream fos;
				if(first==1)
					fos=new FileOutputStream("D:\\Huffman\\Decrypted.txt");
				else if(first==2)
					fos=new FileOutputStream("D:\\Huffman\\Decrypted.jpg");
				else if(first==3)
					fos=new FileOutputStream("D:\\Huffman\\Decrypted.mp3");
				else
					fos=new FileOutputStream("D:\\Huffman\\Decrypted.mp4");
				while((c=fis.read())!=-1){
					subStr=str.substring(i,i+c);
					//System.out.println(subStr);
					int cha=0;
					for(int m=subStr.length()-1;m>=0;m--){//将字符串转换为数字
						if(subStr.charAt(subStr.length()-1-m)=='1'){
							cha+=Math.pow(2, m);
						}
					}
					fos.write(linklist.Search(subStr));
					i=i+c;
				}
				fos.close();
			}catch(IOException e){
				System.err.println("发生异常"+e);
				e.printStackTrace();
			}
		
			fis.close();
			}catch(IOException e){
				System.err.println("发生异常"+e);
				e.printStackTrace();
			}
		}else{
			try{
				FileOutputStream fos;
				if(first==1)
					fos=new FileOutputStream("D:\\Huffman\\uncompressed.txt");
				else if(first==2)
					fos=new FileOutputStream("D:\\Huffman\\uncompressed.jpg");
				else if(first==3)
					fos=new FileOutputStream("D:\\Huffman\\uncompressed.mp3");
				else
					fos=new FileOutputStream("D:\\Huffman\\uncompressed.mp4");
		
				int i=0,j=1;
				for(i=0;i<str.length();i++){
					for(j=1;i+j<=str.length();j++){
						int k;
						if((k=ifInclude(HuffmanCoding,str.substring(i, i+j)))!=-1){
							//将该字符写入文件中
							fos.write(k);
							i=i+j-1;
							break;
						}
					}
				}
				fos.close();
			}catch(IOException e){
				System.err.println("发生异常"+e);
				e.printStackTrace();
			}
		}
		/*至此，文件加密解密工作全部完成*/

	}
	/*判断截取出来的子串是否在哈夫曼编码里面*/
	public int ifInclude(String str[],String string){
		for(int i=0;i<256;i++){
			if(string.equals(str[i])){
				return i;
			}
		}
		return -1;
	}
}
