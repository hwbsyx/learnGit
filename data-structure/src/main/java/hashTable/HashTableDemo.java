package hashTable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(8);
        EmpLinkedList[] linkedLists = hashTable.getLinkedLists();
        Scanner scanner = new Scanner(System.in);
        boolean loop = true; //控制程序退出
        String key;
        while (loop) {
            System.out.println("add 添加雇员");
            System.out.println("select 查找雇员");
            System.out.println("del 删除雇员");
            System.out.println("list 遍历雇员");
            System.out.println("exit 退出程序");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入姓名：");
                    String name = scanner.next();
                    int no = hashTable.hashFun(id);
                    linkedLists[no].add(new Emp(id,name));
                    break;
                case "select":
                    System.out.println("请输入要查找的ID:");
                    id = scanner.nextInt();
                    no = hashTable.hashFun(id);
                    linkedLists[no].findById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的ID：");
                    id = scanner.nextInt();
                    no = hashTable.hashFun(id);
                    linkedLists[no].del(id);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class HashTable {
    private EmpLinkedList[] linkedLists;

    public EmpLinkedList[] getLinkedLists() {
        return linkedLists;
    }

    public void setLinkedLists(EmpLinkedList[] linkedLists) {
        this.linkedLists = linkedLists;
    }

    public HashTable(int size) {
        linkedLists = new EmpLinkedList[size];
        //初始化所有的linkedList
        for (int i = 0; i < size; i++) {
            linkedLists[i] = new EmpLinkedList();
        }
    }

    //编写散列函数，通过ID决定放入哪个LinkedList中
    public int hashFun(int id) {
        return id % linkedLists.length;
    }

    //添加
    public void add(Emp emp) {
        //ID取模决定放入哪个linkedList中
        int no = hashFun(emp.getId());
        linkedLists[no].add(emp);
    }

    //查找
    public void findById(int id) {
        //先确认在哪个linkedList中
        int no = hashFun(id);
        Emp emp = linkedLists[no].findById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到%d雇员,name=%s\n", no, id, emp.getName());
        } else {
            System.out.printf("不存在id=%s的雇员", id);
        }
    }

    //删除
    public void del(int id) {
        int no = hashFun(id);
        linkedLists[no].del(id);
    }

    //遍历
    public void list() {
        for (int i = 0; i < linkedLists.length; i++) {
            linkedLists[i].list(i);
        }
    }
}

class EmpLinkedList {
    //头指针，直接指向第一个节点，默认为null
    private Emp head;

    //添加
    public void add(Emp emp) {
        //如果是第一个节点,直接指向head
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个节点，默认按顺序添加到末尾节点
        Emp cur = head;
        while (true) {
            if (cur.getNext() == null) { //到了最后的节点
                break;
            }
            cur = cur.getNext();
        }
        cur.setNext(emp);
    }

    //按照ID查找
    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp cur = head;
        while (true) {
            if (id == cur.getId()) {
                break;
            }
            if (cur.getNext() == null) {
                cur = null;
                break;
            }
        }
        return cur;
    }

    //按照ID删除
    public void del(int id) {
        if (head == null) {
            System.out.println("链表为空！");
            return;
        }
        if (id == head.getId()) {
            System.out.println("id为" + id + "的雇员已经删除");
            return;
        }
        Emp cur = head;
        while (true) {
            if (cur.getNext() == null) {
                System.out.println("没有找到该雇员！");
                break;
            }
            if (id == cur.getNext().getId()) {
                cur.setNext(cur.getNext().getNext());
                System.out.println("id为" + id + "的雇员已经删除");
                break;
            }
            cur = cur.getNext();
        }
    }

    //遍历
    public void list(int no) {
        if (head == null) {
            System.out.println("第"+(no+1)+"条链表为空！");
            return;
        }
        System.out.printf("第"+(no+1)+"条链表的信息为:");
        Emp cur = head;
        while (true) {
            System.out.printf("id=%d,name=%s\t", cur.getId(), cur.getName());
            if (cur.getNext() == null) {
                break;
            }
            cur = cur.getNext();
        }
        System.out.println();
    }

}

//表示雇员
class Emp {
    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }
}
