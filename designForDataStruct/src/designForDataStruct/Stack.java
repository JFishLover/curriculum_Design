package designForDataStruct;

public class Stack<T> {
	private class Node{
		public T data;
		public Node next;
		public Node(){}
		public Node(T data,Node next){
			this.data=data;
			this.next=next;
		}
	}
	private Node top;//栈顶元素
	private int size=0;//元素个数
	/*空栈构造函数*/
	public Stack(){//
		top=null;
	}
	/*插入元素*/
	public void push(T element){
		top=new Node(element,top);
		size++;
	}
	/*出栈操作*/
	public T pop(){
		Node oldTop=top;
		top=top.next;
		oldTop.next=null;//释放引用
		size--;
		return oldTop.data;
	}
	/*返回栈顶元素但不出栈*/
	public T topElem(){
		return top.data;
	}
	/*返回长度*/
	public int length(){
		return size;
	}
	/*是否空栈*/
	public boolean empty(){
		return size==0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
