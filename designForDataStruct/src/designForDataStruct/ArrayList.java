package designForDataStruct;

public class ArrayList {
	class Node{
		int num;
		int Char;
		Node next;
		public Node(int length,int c){
			num=length;
			Char=c;
			next=null;
		}
	}
	Node head;
	ArrayList(){
		head=new Node(0,-1);
	}
	public void insert(String str,int ch){
		Node temp=head;
		while(temp.next!=null&&temp.next.num<str.length()){
			temp=temp.next;
		}
		Node newNode=new Node(str.length(),ch);
		if(temp.next==null){
			temp.next=newNode;
			
		}else{
			newNode.next=temp.next;
			temp.next=newNode;
		}
	}
	public int Search(int length){
		Node temp=head;
		while(temp.next.num<length){
			temp=temp.next;
		}
		return temp.next.Char;
	}
}
