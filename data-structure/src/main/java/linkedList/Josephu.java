package linkedList;

public class Josephu {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addBoy(5);
        circleLinkedList.list();
        circleLinkedList.countBoy(1,2,5);
    }
}

//单向环形链表
class CircleLinkedList {
    private Boy first;//第一个节点

    //添加nums个节点
    public void addBoy(int nums) {
        Boy cur = null; //辅助指针
        if (nums < 1) {
            System.out.println("数据输入有误");
            return;
        }
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                cur = first;
            } else {
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }

    //遍历环形链表
    public void list() {
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        Boy cur = first;
        while (true) {
            System.out.printf("小孩的编号为%d \n",cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }

    /**展示小孩出圈顺序
     *
     * @param startNum 从第几个小孩开始数
     * @param countNum 每次数几个小孩
     * @param nums 总共有几个小孩
     */
    public void countBoy(int startNum,int countNum,int nums) {
        //数据校验
        if (startNum < 1 || startNum > nums || first == null) {
            System.out.println("数据输入有误");
            return;
        }
        //定义辅助指针，让辅助指针在first前一个节点
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //先让helper和first移动startNum - 1次
        for (int i = 0; i < startNum -1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始报数，让helper和first同时移动countNum -1 次然后出圈
        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNum -1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时first指向的小孩就是要出圈的小孩
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩是" + first.getNo());
    }

}

//节点
class Boy {
    private int no; //编号
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}
