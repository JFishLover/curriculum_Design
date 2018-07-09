package designForDataStruct;

public class LinkList {
	static int max=0;
	class Node{
		int order;
		Node Next;
		Node1 Head;
		public Node(int sum){
			order=sum;
			Next=null;
			Head=new Node1(0,-1);
		}
	}
	class Node1{
		int num;
		int Char;
		Node1 Next;
		public Node1(int length,int c){
			num=length;
			Char=c;
			Next=null;
		}
	}
	Node head;
	public LinkList(){
		head=null;
	}
	public LinkList(Node Head){
		head=Head;
	}
	/*将哈夫曼编码信息存入链表，插入节点*/
	public void insert(String str,int Char){//相同的插入不正确
		int c=0;
		for(int m=str.length()-1;m>=0;m--){//将字符串转换为数字
			if(str.charAt(str.length()-1-m)=='1'){
				c+=Math.pow(2, m);
			}
		}
		//System.out.println(str);
		//System.out.println("c="+c);
		if(head==null){
			head=new Node(-1);
			Node temp=new Node(c);
			head.Next=temp;
			
			Node1 newNode1=new Node1(str.length(),Char);
			temp.Head.Next=newNode1;
		}else{
			Node temp=head;
			
			while(temp.Next!=null&&temp.Next.order<c){
				temp=temp.Next;
			}
			if(temp.Next==null){
				Node newNode=new Node(c);
				temp.Next=newNode;
				
				Node1 newNode1=new Node1(str.length(),Char);
				newNode.Head.Next=newNode1;
			}else{
				if(temp.Next.order==c){
					Node1 tempNode1=temp.Next.Head;
					while(tempNode1.Next!=null&&tempNode1.Next.num<str.length()){
						tempNode1=tempNode1.Next;
					}
					if(tempNode1.Next==null){
						Node1 newNode1=new Node1(str.length(),Char);
						tempNode1.Next=newNode1;
					}else{
						Node1 newNode1=new Node1(str.length(),Char);
						newNode1.Next=tempNode1.Next;
						tempNode1.Next=newNode1;
					}
				}else{
					Node newNode=new Node(c);
					newNode.Next=temp.Next;
					temp.Next=newNode;
					
					Node1 newNode1=new Node1(str.length(),Char);
					newNode.Head.Next=newNode1;
				}
			}
		}
	}
	/*查找哈夫曼编码字符串对应的字符*/
	public int Search(String str){
		//System.out.println(str);
		Node temp=head;
		int c=0;
		for(int m=str.length()-1;m>=0;m--){//将字符串转换为数字
			if(str.charAt(str.length()-1-m)=='1'){
				c+=Math.pow(2, m);
			}
		}
		if(c>max){
			max=c;
		}
		while(temp.Next!=null&&temp.Next.order!=c){
			temp=temp.Next;
		}
		
		/*跳出循环的条件是temp.Next.order=c*/
		Node1 Temp=temp.Next.Head;
		while(Temp.Next!=null&&Temp.Next.num!=str.length()){
			Temp=Temp.Next;
		}
		return Temp.Next.Char;
	}
	
}