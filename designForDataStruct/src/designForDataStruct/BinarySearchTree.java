package designForDataStruct;

public class BinarySearchTree {
		int order=1;//中序遍历时的编号
		int numOfNode=0;
		Node []Temp=new Node[256];
		int numOfTemp=0;
	    class Node {
	        int data;
	        int count;
	        Node left, right;
	 
	        public Node(int item) {
	            data= item;
	            count=1;
	            left = right = null;
	        }
	    }
	 
	    Node root;
	    
	    BinarySearchTree() {
	        root = null; 
	    }
	    BinarySearchTree(Node Root){
	    	root=Root;
	    }
	 
	    void insert(int key) {
	       root = insertRec(root, key);
	    }
	     
	    Node insertRec(Node root, int key) {
	 
	        if (root == null) {
	            root = new Node(key);
	            numOfNode++;
	            return root;
	        }
	 
	        if (key < root.data)
	            root.left = insertRec(root.left, key);
	        else if (key > root.data)
	            root.right = insertRec(root.right, key);
	        else
	        	root.count++;
	 
	        return root;
	    }
	    //建立数组存储哈夫曼树的节点，准备构造哈夫曼树，有效节点numOfNode个
	   // Node []node=new Node[2*numOfNode-1];
	 
	    void inorder()  {
	       inorderRec(root);
	    }
	    void inorderRec(Node root) {
	        if (root != null) {
	            inorderRec(root.left);
	            Temp[numOfTemp++]=root;
	            inorderRec(root.right);
	        }
	    }
	    /*中序遍历为每一个节点编号，通过比较节点编号的大小来确定哈夫曼编码是0还是1*/
	    void inorder2(int arr[]){
	    	inorderRec2(root,arr);
	    }
	    void inorderRec2(Node root,int arr[]){//正确了，哈哈
	    	Stack<Node> stack=new Stack<Node>();
	    	Node tempNode=root;
	    	while(tempNode!=null||!stack.empty()){
	    		while(tempNode!=null){
	    			stack.push(tempNode);
	    			//System.out.println("入栈的元素的字符为:"+tempNode.data);
	    			tempNode=tempNode.left;
	    		}
	    		tempNode=stack.topElem();
	    		if(tempNode.data!=-1){/*只有叶子节点才会被存进数组,非叶子结点data域为-1；*/
	    			arr[tempNode.data]=order;/*存储叶子结点的序号*/
	    		}
	    		//System.out.println(tempNode.data+"表示的字符对应的序号是"+order);
	    		tempNode.count=order;/*对所有的节点进行编号*/
	    		order++;
	    		stack.pop();
	    		tempNode=tempNode.right;
	    	}	
	    }

	    void coding(int arr1[],StringBuffer arr2[],int num){//进行哈夫曼编码，关键是怎么存储，便于以后调用
	    	Node tempNode=root;
	    	while(tempNode!=null){
	    		if(arr1[num]<tempNode.count){//在根节点的左子树上，编码加0
	    			arr2[num].append("0");
	    			tempNode=tempNode.left;
	    		}else if(arr1[num]>tempNode.count){//在根节点的右子树上，编码加1
	    			arr2[num].append("1");
	    			tempNode=tempNode.right;
	    		}else
	    			break;
	    	}
	    }
		public static void main(String[] args) {
			// TODO Auto-generated method stub

		}

}
