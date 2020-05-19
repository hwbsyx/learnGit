package linkedList;

import java.util.Stack;

/*
* 以水浒英雄演示单向链表
* */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "公孙胜", "入云龙");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);
        /*singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);*/


        /*singleLinkedList.del(3);
        singleLinkedList.del(2);*/
       /* singleLinkedList.del(4);
        singleLinkedList.del(2);*/

//        singleLinkedList.update(new HeroNode(2,"老卢","玉麒麟！！"));

        singleLinkedList.list();
//        System.out.println(solution(heroNode1));
//        singleLinkedList.setHead(solution(heroNode1));
        singleLinkedList.list();

        //链表反转
       /* reverseLinked(singleLinkedList.getHead());
        System.out.println("反转后的链表为:");
        singleLinkedList.list();


        System.out.println("有效节点个数为：" + getLength(singleLinkedList.getHead()));

        System.out.println("查找倒数第K个节点" + findLastIndexNode(singleLinkedList.getHead(),2));*/

//        System.out.println("反向显示链表:");
//        reverseList(singleLinkedList.getHead());
    }

    //反向显示链表，使用栈

    public static void reverseList(HeroNode head) {
        //如果链表为空不打印
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //    计算链表节点个数
    public static int getLength(HeroNode head) {
        int count = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    //返回倒数第K个节点
    //index表示倒数第几个节点，先计算长度，length-index得到倒数第index个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        //计算节点个数
        int length = getLength(head);
        //判断index范围
        if (index < 0 || index > length) {
            return null;
        }
        HeroNode cur = head.next;
        //遍历到length-index个节点即为所求节点
        for (int i = 0; i < length - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //链表反转
    public static void reverseLinked(HeroNode head) {
        //如果没有节点或者只有一个节点
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode next = null; //保存下一个节点
        HeroNode cur = head.next;
        HeroNode reverseHead = new HeroNode(0, "", ""); //反转后的新的头节点
        //遍历节点，将每个节点放到新链表的最前端，最后将原始链表的头节点指向新链表的第一个节点，得到反转链表
        while (cur != null) {
            next = cur.next; //保存下一个节点
            cur.next = reverseHead.next; //让下一个节点指向新链表的第一个节点
            reverseHead.next = cur;
            cur = next; //遍历
        }
        head.next = reverseHead.next; //原来的头节点指向新链表的第一个节点，完成反转
    }

    /**
     * 反转链表，并返回反转后的头节点
     * @param head
     * @return
     */
    public static HeroNode solution(HeroNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //从当前链表第二个节点进行递归
        HeroNode reverseList = solution(head.next);
        //现在除了第一个节点其他节点已经完成反转，剩下只需要将第二个节点的next指向第一个节点，第一个节点的next指向null即可
        //利用临时变量保存第二个节点
        HeroNode temp = head.next;
        //将第二个节点的next指向第一个节点
        temp.next = head;
        head.next = null;
        return reverseList;
    }
}

class SingleLinkedList {
    //私有化一个头节点,头节点不会移动
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    //添加节点,不考虑顺序，直接添加到链表末尾
    public void add(HeroNode heroNode) {
        //因为头节点不能移动，需要一个临时变量来遍历
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {//末尾节点
                temp.next = heroNode;
                break;
            }
            temp = temp.next;//移动
        }
    }

    //添加节点，按英雄编号按顺序添加
    public void addByOrder(HeroNode heroNode) {
        //先要找到新节点插入的位置
        HeroNode temp = head;
        boolean flag = false;  //判断新节点是否已经存在
        while (true) {
            if (temp.next == null) { //已经在最后的节点
                break;
            }
            if (heroNode.no < temp.next.no) { //找到插入位置,就在temp后面插入
                break;
            } else if (heroNode.no == temp.next.no) {//节点已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("插入的英雄编号已经存在" + heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点
    public void update(HeroNode newheroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
        }
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) { //已经遍历完链表
                break;
            }
            if (temp.no == newheroNode.no) {  //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) { //找到
            temp.name = newheroNode.name;
            temp.nickName = newheroNode.nickName;
        } else {
            System.out.println("没有找到该英雄对应的编号" + newheroNode.no +"不能修改");
        }
    }

    //删除节点,head不能移动要找到需要删除的节点的前一个节点
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; //判断是否找到
        while (true) {
            if (temp.next == null) {//已经到末尾
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("需要删除的节点%d不存在\n", no);
        }
    }

    //显示链表
    public void list() {
        if (head == null) {
            System.out.println("链表为空!");
            return;
        }
        HeroNode temp = head.next;  //头节点不用输出
        while (true) {
            if (temp == null) {//遍历到了最后
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
class HeroNode {
    public int no;//英雄编号
    public String name;
    public String nickName;//昵称
    public HeroNode next;//下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

