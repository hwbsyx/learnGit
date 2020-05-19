package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode heroNode1 = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义");
        HeroNode heroNode3 = new HeroNode(3, "无用");
        HeroNode heroNode4 = new HeroNode(4, "公孙胜");
        HeroNode heroNode5 = new HeroNode(5, "关胜");
        binaryTree.setRoot(heroNode1);
        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);

        System.out.println("前序遍历");
        binaryTree.preOrder(); //1 2 3 4

        System.out.println("中序遍历");
        binaryTree.infixOrder();// 2 1 3 4

        System.out.println("后序遍历");
        binaryTree.postOrder(); //2 4 3 1

        /*System.out.println("前序遍历");
        HeroNode resHeroNode = binaryTree.preOrderSearch(5);
        if (resHeroNode != null) {
            System.out.printf("找到id=%d的人物为%s", 5, resHeroNode.toString());
        } else {
            System.out.printf("没有找到id为%d的人物", 5);
        }*/

        /*System.out.println("中序遍历");
        HeroNode resHeroNode = binaryTree.infixOrderSearch(5);
        if (resHeroNode != null) {
            System.out.printf("找到id=%d的人物为%s", 5, resHeroNode.toString());
        } else {
            System.out.printf("没有找到id为%d的人物", 5);
        }*/

        System.out.println("后序查找");
        HeroNode resHeroNode = binaryTree.postOrderSearch(5);
        if (resHeroNode != null) {
            System.out.printf("找到id=%d的人物为%s", 5, resHeroNode.toString());
        } else {
            System.out.printf("没有找到id为%d的人物", 5);
        }

        System.out.println("删除前");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后");
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root; //根结点

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
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;  //左结点
    private HeroNode right; //右结点

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
