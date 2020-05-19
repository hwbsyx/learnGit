package tree.threadedbinarytree;


/**
 * 线索化二叉树
 * n个结点的二叉链表中含有n+1  【公式 2n-(n-1)=n+1】 个空指针域。利用二叉链表中的空指针域，存放指向该结点在 某种遍历次序 下的前驱和后继结点的指针（这种附加的指针称为"线索"）
 *
 * 这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
 *
 * 一个结点的前一个结点，称为前驱结点
 * 一个结点的后一个结点，称为后继结点
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "张三");
        HeroNode heroNode2 = new HeroNode(3, "李四");
        HeroNode heroNode3 = new HeroNode(6, "王五");
        HeroNode heroNode4 = new HeroNode(8, "小陈");
        HeroNode heroNode5 = new HeroNode(10, "小王");
        HeroNode heroNode6 = new HeroNode(14, "小何");
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(heroNode1);
        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);

        //中序遍历添加线索
        threadedBinaryTree.threadedNode();
        //测试10号节点的前序节点和后继节点
        HeroNode left = heroNode5.getLeft();
        HeroNode right = heroNode5.getRight();
        System.out.println("10号节点的前驱节点为" + left); //3
        System.out.println("10号节点的后继节点为" + right); //1

        HeroNode left1 = heroNode4.getLeft();
        HeroNode right1 = heroNode4.getRight();
        System.out.println("8号节点的前驱节点为" + left1);
        System.out.println("8号节点的后继节点为" + right1);

        threadedBinaryTree.threadedList();  //遍历中序线索化后的二叉树 8 3 10 1 14 6

    }
}
class ThreadedBinaryTree {
    private HeroNode root; //根结点
    private HeroNode pre=null; //表示前序节点

    //线索化情况下遍历二叉树
    public void threadedList() {
        //定义一个指针节点
        HeroNode node = root;
        while (node != null) {
            //由于是中序遍历线索化的二叉树，第一个输出的是最左边的子节点，该节点满足leftType = 1
            //其它左子节点的leftType = 0
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //循环结束后当前节点就是最左边的子节点，直接输出
            System.out.println(node);
            //然后输出当前节点的后继节点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    //重载默认从根节点开始线索化二叉树
    public void threadedNode() {
        this.threadedNode(root);
    }

    //中序遍历情况下的线索二叉树
    public void threadedNode(HeroNode node) {
        if (node == null) {
            return;
        }
        //向左递归添加线索
        threadedNode(node.getLeft());
        //当前节点添加线索
        if (node.getLeft() == null) {
            //添加前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //添加后继节点，此时右子节点为null，为了找到后继节点，必须把当前节点当前驱节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //添加完后，将当前节点赋值给前驱节点
        pre = node;
        //向右递归添加线索
        threadedNode(node.getRight());
    }

    //删除节点
    public void delNode(int no) {
        //先判断root节点是否为空
        if (this.root != null) {
            if (this.root.getNo() == no) {
                this.root = null;
            } else {
                this.root.delNode(no);
            }
        } else {
            System.out.println("空树，无法删除");
        }
    }

    //前序
    public void preOrder() {
        if (root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序
    public void infixOrder() {
        if (root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序
    public void postOrder() {
        if (root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return this.root.preOrederSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public HeroNode getPre() {
        return pre;
    }

    public void setPre(HeroNode pre) {
        this.pre = pre;
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;  //左结点
    private HeroNode right; //右结点
    private int leftType;  //表示左子节点状态，0表示左子节点，1表示前序节点
    private int rightType; //表示右子节点状态，0表示右子节点，1表示后继节点

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    //删除节点,1.如果删除的节点是叶子节点，则直接删除.2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {
        //判断左子节点是否为空，不为空，判断是非为要删除节点，如果是直接置空并返回
        if (this.left != null && this.left.getNo() == no) {
            this.left = null;
            return;
        }
        //判断右子节点是否为空，不为空，判断是非为要删除节点，如果是直接置空并返回
        if (this.right != null && this.right.getNo() == no) {
            this.right = null;
            return;
        }
        //判断左子节点是否为空，不为空则向左子节点递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //前序查找
    public HeroNode preOrederSearch(int no) {
        //看比较了多少次，这个语句只能写在比较前，
        System.out.println("前序查找开始比较！");
        if (no == this.no) {
            return this;
        }
        HeroNode resHeroNode = null;
        if (this.left != null) {
            resHeroNode = this.left.preOrederSearch(no);
        }
        if (resHeroNode != null) {
            return resHeroNode;
        }
        if (this.right != null) {
            resHeroNode = this.right.preOrederSearch(no);
        }
        return resHeroNode;
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resHeroNode = null;
        if (this.left != null) {
            resHeroNode = this.left.infixOrderSearch(no);
        }
        if (resHeroNode != null) {
            return resHeroNode;
        }
        System.out.println("中序查找开始比较！");
        if (no == this.no) {
            return this;
        }
        if (this.right != null) {
            resHeroNode = this.right.infixOrderSearch(no);
        }
        return resHeroNode;
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resHeroNode = null;
        if (this.left != null) {
            resHeroNode = this.left.postOrderSearch(no);
        }
        if (resHeroNode != null) {
            return resHeroNode;
        }
        if (this.right != null) {
            resHeroNode = this.right.postOrderSearch(no);
        }
        if (resHeroNode != null) {
            return resHeroNode;
        }
        System.out.println("后序查找开始比较！");
        if (no == this.no) {
            return this;
        }
        return resHeroNode;
    }

    //前序遍历
    public void preOrder() {
        //直接输出根结点
        System.out.println(this);
        //若左结点存在，递归
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}