import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}





public class Solutions {
	
	
	public static TreeNode transform(TreeNode root){
		
		return transform(root,null,null);
		
	}
	
	
	




	public static TreeNode transform(TreeNode root, TreeNode prev, TreeNode next) {
		if(root==null) return null;
		TreeNode head = root;
		if(root.left==null && root.right==null){
			if(next!=null){
				root.right = next;
				next.left = root;
			}
			if(prev!=null){
				prev.right = root;
				root.left = prev;
				head = prev;
			}
			return head;
		}
		if(root.right!=null){
			transform(root.right, root, next);
			
		}
		if(root.left != null){
			head = transform(root.left, prev, root);
		}
		return head;
	}
	
	
	public static List<TreeNode> InOrderTraversal(TreeNode root){
		List<TreeNode> res = new ArrayList<TreeNode>();
		if(root==null){
			return res;
		}
		res.addAll(InOrderTraversal(root.left));
		res.add(root);
		res.addAll(InOrderTraversal(root.right));
		return res;
	}

	public static List<TreeNode> InOrderTraversalIterative(TreeNode root){
		
		List<TreeNode> res = new ArrayList<TreeNode>();
		if(root==null){
			return res;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		TreeNode node = root;
		while(!stack.isEmpty() || node!=null){  //this is OR relationship
			if(node!=null){
				stack.push(node);
				node = node.left;
			}else{
				node = stack.pop();
				res.add(node);
				stack.push(node.right);
			}
		}
		
		return res;
	}
	
	

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(4);
		TreeNode n = root;
		n.left = new TreeNode(2);
		n.right = new TreeNode(5);
		n=n.left;
		n.left = new TreeNode(1);
		n.right = new TreeNode(3);
		
		
		
		
		
		

//		System.out.println(-8%2);
//		System.out.println(-3%2);
//		System.out.println((-8)&1);
//		System.out.println((-3)&1);
//		System.out.println((8)&1);
//		System.out.println((3)&1);
//		
//		System.out.println(Integer.toBinaryString(-3));
//		System.out.println(Integer.toBinaryString(-1));
//		System.out.println(Integer.toBinaryString(-2));
//		System.out.println(Integer.toBinaryString(-8));
//		System.out.println(Integer.toBinaryString(-9));
//		System.out.println(Integer.toBinaryString(9));
		
		//		System.out.print(pow(2,-4));
		
//		ArrayList<ArrayList<Integer>> res = combine(4,3);
//		
//		for(List<Integer> r:res){
//			
//			for(Integer i:r){
//				System.out.print(i+",");
//			}
//			System.out.println();
//		}
		
		
		//CountAndSay(20);
		//int[] input = {4,2,3,4,1};
		
		//System.out.println(candy(input));
//		List<Integer> x = grayCode(2);
//		for(int v:x){
//			System.out.println(v);
//		}
	
//		System.out.println((int)'c');
//		System.out.println((char)-23);
		 
//		System.out.println(strStr("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));
//		System.out.println(strStr("dsf sd fwepfjsdl;vmxcvm sd'fkw[ fsd;fksd f'fkwef' sdfsad fsdfsd l; fksd'a f", "fwepf"));
	}
	
    public static int strStr(String haystack, String needle) {
        if ( needle == null || haystack == null ) return -1;  
         if(needle.length()==0 ) return 0;
        if(needle.length() > haystack.length() )    return -1;
                 
        
        int[] table = new int[needle.length()];
        table[0] = 0;
        for(int i = 1; i < needle.length();++i){
            int j = table[i-1];
            while(true){
                while(j>=0 && needle.charAt(j)!=needle.charAt(i)) j--;
                if(j<=0){
                    table[i] = j+1;            
                    break;  
                } 
                int k = 1;
                for(; k <=j; ++k){
                    if(needle.charAt(j-k)!=needle.charAt(i-k)) {
                        break;
                    }
                }
                if(k>j){
                    table[i] = k;
                    break;
                }else{
                    --j;
                }
            }
        }
        for(int i = 0; i <= haystack.length()-needle.length();){
            int j = 0;
            for(; j < needle.length(); ++j){
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    if(j>0){
                        i += (j-table[j-1]);
                    }else{
                        ++i;
                    }
                    break;        
                }
            }
            if(j>=needle.length()){
                return i;
            }
        }
        return -1;
    }

    public static List<Integer> grayCode(int n) {
        
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        if(n==0) return res;
        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(0);
        while(true){
            int prev = res.get(res.size()-1);
            boolean isLast = true;
            for(int i = 0; i < n; ++i){
                int v = prev ^ (0x1 << i);
                if(!visited.contains(v)){
                    isLast = false;
                    res.add(v);
                    visited.add(v);
                    break;
                }
            }
            if(isLast) break;
        }
        
        return res;
    }	

	    public static int candy(int[] ratings) {
	        if(ratings==null||ratings.length==0){
	            return 0;
	        }
	        int[] candy = new int[ratings.length];
	        candy[0] = 1;
	        for(int i = 1; i < ratings.length;++i){
	            candy[i] = ratings[i] > ratings[i-1] ? candy[i-1]+1 : 1;
	        }
	        for(int i = ratings.length-2; i >=0; --i){
	            candy[i] = (ratings[i] > ratings[i+1] && candy[i] <=candy[i+1]) ? candy[i+1]+1 : candy[i];
	        }
	        int sum = 0;
	        for(int i = 0; i < candy.length;++i){
	            sum+= candy[i];
	        }
	        return sum;
	    }
		
	
	public static void CountAndSay(int n ){
		
		String curRes = "1";
		System.out.println(curRes);
		for(int i = 2; i <=n; ++i){
			StringBuilder res = new StringBuilder();
			int count = 1;
			for(int j = 1; j < curRes.length(); ++j){
				if(curRes.charAt(j-1)==curRes.charAt(j)){
					count++;
				}else{
					res.append(count);
					res.append(curRes.charAt(j-1));
					count = 1;
				}
			}
			res.append(count);
			res.append(curRes.charAt(curRes.length()-1));
			curRes = res.toString();
			System.out.println(curRes);
		}
	}
	
	
    public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(n<=0 || n<k)
            return res;
        helper(n,k,1,new ArrayList<Integer>(), res);
        return res;
    }
    private static void helper(int n, int k, int start, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)
    {
        if(item.size()==k)
        {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=start;i<=n;i++)
        {
            item.add(i);
            helper(n,k,i+1,item,res);
            item.remove(item.size()-1);
        }
    }
    
    
    public static double pow(double x, int n) {
        if (n == 0) return 1.0;
        double half = pow(x, n/2);
        if (n%2 == 0)
        {
            return half*half;
        }
        else if (n>0)
        {
            return half*half*x;
        }
        else
        {
            return half/x*half;
        }
    }
	
	public int divide(int dividend, int divisor){
		
		if(divisor==0) return Integer.MAX_VALUE;
		int res = 0;
		if(dividend==Integer.MIN_VALUE){
			res = 1;
			dividend += Math.abs(divisor);
		}
		if(dividend==Integer.MIN_VALUE){
			return res;
		}
		
		boolean isNeg = ((dividend^divisor)>>>31==1);
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		
		int digit = 0;
		while(divisor<= (dividend>>1)){
			divisor <<= 1;
			++digit;
		}
		
		while(digit>=0){
			if(dividend>=divisor){
				dividend -= divisor;
				res +=  1<<digit;
			}
			divisor >>= 1;
			digit --;
		}
		return isNeg?-res:res;
		
	}	
	
}
