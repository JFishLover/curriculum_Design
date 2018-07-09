package HITLLK;

public class HitLLk 
{
	//判断两个按钮是不是边缘可消得
		public static boolean ifEdge(int x1,int y1,int x2,int y2,char arrC[][],int numOfRC)
		{
			if(x1==x2&&y1==y2)//相同的不能消除
				return false;
		
			if(x1==x2)
				if(x1==0||x1==numOfRC-1)
				return true;
			if(y1==y2)
				if(y1==0||y1==numOfRC-1)
				return true;
			
			return false;
		}
		//判断两个按钮是不是直线相联通的
		public static boolean ifLine(int x1,int y1,int x2,int y2,char arrC[][])
		{
			//if(arrC[x1][y1]!=arrC[x2][y2])
				//return false;
			//如果不在一行或者一列直接return false
			if(x1==x2&&y1==y2)
				return false;
			if(x1!=x2&&y1!=y2)
				return false;
			//如果在一行上
			else if(x1==x2)
			{
				//确定横坐标的大小关系，以便进行循环，确定上下界
				int Maxx,Minx;
				if(y1<y2)
				{
					Maxx=y2;//2
					Minx=y1;//0
				}
				else
				{
					Maxx=y1;
					Minx=y2;
				}
				//如果两者差一，进入循环的话数组会越界，所以作讨论
				if(Maxx-Minx!=1)
				{
					//进入for循环
					for(int i=Minx+1;i<Maxx;++i)
					{	
						//中间有障碍，直接返回false
						if(arrC[x1][i]!='h')
							return false;
					}
				}
				//如果在一列上
			}
			else if(y1==y2)
			{
				//确定纵坐标大小关系，以便确定循环的上下界
				int Maxy,Miny;
				if(x1<x2)
				{
					Maxy=x2;
					Miny=x1;
				}
				else 
				{
					Maxy=x1;
					Miny=x2;
				}
				//两者差一的话，进入循环数组越界，另作分类
				if(Maxy-Miny!=1)
				{
					for(int i=Miny+1;i<Maxy;++i)
					{
						if(arrC[i][y1]!='h')
							return false;
					}
				}
			}
			//经过以上判断到达这里，可能是两个按钮紧邻，也可能是不紧邻，但是中间没有障碍，都是联通的
			return true;
		}
		//判断两个按钮是不是直角相连通的，调用ifLine（）函数
		public static boolean ifRightAngle(int x1,int y1,int x2,int y2,char arrC[][])
		{
			//如果直角拐点处没有障碍
			if(x1==x2&&y1==y2)
				return false;
			if(arrC[x1][y2]=='h'&&arrC[x2][y1]=='h')
			{
				//两个直角拐点只要有一个跟两个按钮是联通的，两个按钮就是联通的
				if((ifLine(x1,y2,x1,y1,arrC)&&ifLine(x1,y2,x2,y2,arrC))||(ifLine(x2,y1,x1,y1,arrC)&&ifLine(x2,y1,x2,y2,arrC)))
					return true;
				//两个拐点没有跟两个按钮都是联通的所以两个按钮不是联通的
				else
					return false;
			}
			//只有一个拐点处没有障碍
			else if(arrC[x1][y2]=='h')
			{
				//只有这个拐点跟两个按钮都是联通的，两个按钮才是联通的
				if(ifLine(x1,y2,x1,y1,arrC)&&ifLine(x1,y2,x2,y2,arrC))
					return true;
				else
					return false;
			}
			else if((arrC[x2][y1]=='h'))
			{
				if(ifLine(x2,y1,x1,y1,arrC)&&ifLine(x2,y1,x2,y2,arrC))
					return true;
				else
					return false;
			}	
			//两个拐点都有障碍，说明两个按钮不是联通的
			else
				return false;
		}
		//利用ifRightAngle（）函数判断两个按钮是不是双折线相连的
		public static boolean ifDoubleFold(int x1,int y1,int x2,int y2,char arrC[][],int numOfRC)
		{
			if(x1==x2&&y1==y2)
				return false;
			//创建过程坐标
			int x,y;
			//向上搜寻非障碍点，
			if(x1!=0)
			{
				if(x2!=0)
				{//新添加=====================
					if(arrC[0][y1]=='h'&&arrC[0][y2]=='h')
					if(ifLine(x1,y1,0,y1,arrC)&&ifLine(x2,y2,0,y2,arrC)/*&&ifEdge(0,y1,0,y2,arrC,numOfRC)*/)
						return true;
				}
				//在数组范围内看途经的按钮是不是没有障碍
				for(x=x1-1;x>=0;x--)
				{//向上
					//如果有障碍向上搜寻不联通
					if(arrC[x][y1]!='h')
						break;
					//如果该按钮处没有障碍判断该处是否跟第二个按钮直角相连通
					if(ifRightAngle(x,y1,x2,y2,arrC))
						return true;
				}
				//x=0的时候不能向上搜寻，需要作单独讨论
			}
			else
			{
				//x1=0时，判断按钮二同一列第一行的按钮是不是无障碍的
				if(arrC[0][y2]=='h')
					//如果无障碍的按钮二跟它同一列第一行的按钮是联通的，那么这两个按钮是联通的
				if(ifLine(0,y2,x2,y2,arrC))//新添加，修改===================
					 return true;
			}
			//向下搜寻非障碍点
			if(x1!=arrC.length-1)
			{
				if(x2!=arrC.length-1)
				{
					if(arrC[arrC.length-1][y1]=='h'&&arrC[arrC.length-1][y2]=='h'){
						if(ifLine(x1,y1,arrC.length-1,y1,arrC)&&ifLine(x2,y2,arrC.length-1,y2,arrC)/*&&ifEdge(arrC.length-1,y1,arrC.length-1,y2,arrC,numOfRC)*/)
						return true;
					}
				}
				for(x=x1+1;x<arrC.length;x++)
				{//向下
					if(arrC[x][y1]!='h')
						break;
					if(ifRightAngle(x,y1,x2,y2,arrC))
						return true;
				}
			}
			else
			{
				if(arrC[arrC.length-1][y2]=='h'){
					if(ifLine(arrC.length-1,y2,x2,y2,arrC))//改动=========================
						return true;
				}
			}
			//向左搜寻非障碍点
			if(y1!=0)
			{
				if(y2!=0)
				{
					if(arrC[x1][0]=='h'&&arrC[x2][0]=='h')
					{
						if(ifLine(x1,y1,x1,0,arrC)&&ifLine(x2,y2,x2,0,arrC)/*&&ifEdge(0,y1,0,y2,arrC,numOfRC)*/)
							return true;
					}
						
				}
				for(y=y1-1;y>=0;y--)
				{
					if(arrC[x1][y]!='h')
						break;
					if(ifRightAngle(x1,y,x2,y2,arrC))
						return true;
				}
			}
			else
			{
				if(arrC[x2][0]=='h'){
					if(ifLine(x2,0,x2,y2,arrC))
						return true;
				}
			}
			//向右搜寻非障碍点
			if(y1!=arrC[0].length-1)
			{
				if(y2!=arrC[0].length-1)
				{
					if(arrC[x1][arrC[0].length-1]=='h'&&arrC[x2][arrC[0].length-1]=='h')
					{
						if(ifLine(x1,y1,x1,arrC[0].length-1,arrC)&&ifLine(x2,y2,x2,arrC[0].length-1,arrC)/*&&ifEdge(arrC.length-1,y1,arrC.length-1,y2,arrC,numOfRC)*/)
							return true;
					}
				}
				for(y=y1+1;y<arrC[0].length;y++)
				{
					if(arrC[x1][y]!='h')
						break;
					if(ifRightAngle(x1,y,x2,y2,arrC))
						return true;
				}
			}
			else
			{
				if(arrC[x2][arrC[0].length-1]=='h'){
					if(ifLine(x2,arrC[0].length-1,x2,y2,arrC))
						return true;
				}
			}
			return false;
		}
		//调用判断是否联通函数判断是不是能进行消除
		public static boolean ifClear(int x1,int y1,int x2,int y2,char arrC[][],int numOfRC)
		{
			//两个按钮图片一致
			if(arrC[x1][y1]==arrC[x2][y2])
				//图片一样，两个按钮如果联通的话就可以消除
				return ifEdge(x1,y1,x2,y2,arrC,numOfRC)||ifLine(x1,y1,x2,y2,arrC)||ifRightAngle(x1,y1,x2,y2,arrC)||ifDoubleFold(x1,y1,x2,y2,arrC,numOfRC);
			//图片不同不可以消除
			else
				return false;
		}
		public static void main(String[] args)
		{
			// TODO Auto-generated method stub
			char arrC[][]={{'q','h','a','h'},{'a','h','q','q'},{'a','a','h','h'},{'q','h','a','q'}};
			if(ifClear(0,3,2,1,arrC,4)){
				System.out.println("可以消除");
			}
			else
				System.out.println("不可以消除");
			System.out.println(ifEdge(0,3,2,1,arrC,4));
			System.out.println(ifLine(1,0,1,2,arrC));
			System.out.println(ifRightAngle(0,3,2,1,arrC));
			System.out.println(ifDoubleFold(0,3,2,1,arrC,4));
		}
}
