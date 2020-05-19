package linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "公孙胜", "入云龙");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode4);
        doubleLinkedList.add(heroNode3);

        doubleLinkedList.del(4);
        doubleLinkedList.del(3);
        doubleLinkedList.del(1);

        HeroNode2 heroNode5 = new HeroNode2(2, "老卢", "玉麒麟");
        doubleLinkedList.update(heroNode5);
        doubleLinkedList.del(2);

        doubleLinkedList.list();
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    HeroNode2 head = new HeroNode2(0, "", "");

    //得到头节点
    public HeroNode2 getHead() {
        return head;
    }

    //增加节点到最后
    public void add(HeroNode2 newHeroNode) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newHeroNode;
        newHeroNode.pre = temp;
    }

    //修改节点
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空！无法修改！");
            return;
        }
        HeroNode2 cur = head.next;
        boolean flag = false;
        while (cur != null) {
            if (cur.no == newHeroNode.no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            cur.name = newHeroNode.name;
            cur.nickName = newHeroNode.nickName;
        } else {
            System.out.println("没有找到要修改的节点");
        }
    }

    //删除节点
    public void del(int num) {
        if (head.next == null) {
            System.out.println("链表为空！无法删除");
            return;
        }
        HeroNode2 cur = head.next;
        boolean flag = false; //是否找到该节点
        while (cur != null) {
            //找到
            if (cur.no == num) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            //找到
            cur.pre.next = cur.next;
            //如果不是最后一个节点
            if (cur.next != null) {
                cur.next.pre = cur.pre;
            }
        } else {
            System.out.printf("没有找到%d节点\n", num);
        }
    }


    //遍历
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode2 {
    public int no;//英雄编号
    public String name;
    public String nickName;//昵称
    public HeroNode2 next;//下一个节点
    public HeroNode2 pre;//上一个节点

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode2(int no, String name, String nickName) {
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
