package huffumancode;

import java.util.*;

public class HuffumanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] strBytes = str.getBytes();
        byte[] huffumanZip = huffumanZip(strBytes);
        System.out.println(Arrays.toString(huffumanZip));
    }

    //完成数据的解压
    //思路
    //1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //   重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
    //2.  赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"


    //编写一个方法，完成对压缩数据的解码
    /**
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {

        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte>  map = new HashMap<String,Byte>();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for(int  i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while(flag) {
                //1010100010111...
                //递增的取出 key 1
                String key = stringBuilder.substring(i, i+count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if(b == null) {//说明没有匹配到
                    count++;
                }else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //字节转二进制字符串

    /**将一个byte 转成一个二进制的字符串, 如果看不懂，可以参考我讲的Java基础 二进制的原码，反码，补码
     *
     * @param flag  是否需要补高位，除了最后一个字节不需要补，其他正数都需要补高位，比如0001需要补成0000 0001，
     * @param b   传入的字节
     * @return  二进制字符串
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        //如果是正数我们还存在补高位
        if(flag) {
            temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if (flag || temp < 0) { //除了最后一个字节，或者最后一个字节是负数，都要截取后8位，最后一个字节如果是负数也要截取，不然前面全是1
            return str.substring(str.length() - 8);
        } else { //最后一个字节是正数的情况。
            return str;
        }
    }

    //编写方法，将前面方法汇总，输入字符串对应的byte数组得到赫夫曼编码后的byte数组
    public static byte[] huffumanZip(byte[] bytes) {
        //将字节存入结点中
        List<Node> nodes = getNodes(bytes);
        //利用nodes创建对应的赫夫曼树
        Node root = createHuffumanTree(nodes);
        //得到赫夫曼编码
        Map<Byte, String> huffumanCodes = getCodes(root);
        //将字符串对应的数组和赫夫曼编码传入得到编码后的字节数组
        byte[] huffumanCodeBytes = zip(bytes, huffumanCodes);
        return huffumanCodeBytes;
    }

    //编写一个方法，将传入字符串对应的byte[]转换为经过赫夫曼编码后的byte[]

    /**
     * bytes对着huffumanCodes得到0101010101010111字符串后，每8位取出转化为一个byte,最后得到huffumanCodeBytes
     * @param bytes 原始字符串对应的byte数组
     * @param huffumanCodes 赫夫曼编码表
     * @return 经过编码后的byte数组,
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffumanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        //遍历字节数组，按编码表得到对应的字符串01001010101
        for (byte b : bytes) {
            stringBuilder.append(huffumanCodes.get(b));
        }
        //每8位取出，得到对应的huffumanCodeBytes
        //转换后的数组长度
        int len = (stringBuilder.length() + 7) / 8;//最后可能不足8位，记为1个byte
        byte[] huffumanCodeBytes = new byte[len];
        //遍历0101010
        int index = 0; // 最后赫夫曼编码后的字节数组的索引
        String strByte;  //每8位取出
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            if (i + 8 > stringBuilder.length()) {//最后一个字节，
                strByte = stringBuilder.substring(i); //取出i之后的所有字符包括i
            } else { //前面的字节
                strByte = stringBuilder.substring(i, i + 8);//包前不包后比如i=0,取0-7不包括8
            }
            huffumanCodeBytes[index++] = (byte) Integer.parseInt(strByte,2);  //字符转int，radix为进制
        }
        return huffumanCodeBytes;
    }

    //利用赫夫曼树得到对应的赫夫曼编码表，利用Map<Byte,string>封装,key是每个字节,value是赫夫曼树到每个叶子结点的路径字符串，左为0右为1
    //形如 {32=01,97=100,100=1000。。。}
    private static Map<Byte, String> huffumanCodes = new HashMap<>(); //赫夫曼编码表
    private static StringBuilder stringBuilder = new StringBuilder(); //拼接路径字符

    /**
     * 重载getCodes方法，给定赫夫曼树根结点直接得到赫夫曼编码表
     * @param node
     * @return
     */
    public static Map<Byte,String> getCodes(Node node) {
        if (node == null) {
            return null;
        }
        //处理左子树
        getCodes(node.getLeft(),"0",stringBuilder);
        //处理右子树
        getCodes(node.getRight(),"1",stringBuilder);
        return huffumanCodes;
    }

    /**
     * 将传入的结点所有叶子结点的赫夫曼编码得到，然后添加到huffumanCodes中
     * @param node 传入的根结点
     * @param code 路径所代表的代码，左为0，右为1
     * @param stringBuilder 拼接路径字符
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);//直接在原来的基础上添加
        stringBuilder1.append(code);  //如果传入root.left 先添加一次路径
        if (node != null) { //根结点非空才进行拼接，递归结束条件
            //判断当前结点是叶子结点还是非叶子结点
            if (node.getData() == null) {//非叶子结点
                //向左右递归
                getCodes(node.getLeft(), "0", stringBuilder1);
                getCodes(node.getRight(), "1", stringBuilder1);
            } else { //叶子结点,存入huffumanCodes
                huffumanCodes.put(node.getData(), stringBuilder1.toString());
            }
        }
    }

    /**
     * 给定Node集合生成对应的赫夫曼树,返回最终的根节点
     * @param nodes
     */
    public static Node createHuffumanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //对集合进行排序，按字节出现次数从小到大
            Collections.sort(nodes);
            //取出最小的两个结点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node rootNode = new Node(null, leftNode.getValue() + rightNode.getValue());
            rootNode.setLeft(leftNode);
            rootNode.setRight(rightNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(rootNode);
        }
        //循环结束后只剩下一个根节点
        return nodes.get(0);
    }

    /**
     * 给定字节数组返回每个字符对应的节点数组
     * @param bytes
     * @return
     */
    public static List<Node> getNodes(byte[] bytes) {
        //利用map统计字节数组，key存放每个字节，vlaue存放该字节出现的次数
        Map<Byte, Integer> map = new HashMap<>();
        //遍历字节数组，统计每个字节出现的次数
        for (byte b : bytes) {
            map.merge(b, 1, (pre,one) -> pre + one);
        }
        //创建集合将每个map转换为Node加入集合
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        };
        return nodes;
    }
}

class Node implements Comparable<Node>{ //collections.sort从小到大排序
    private Byte data; //保存字节
    private int value; //保存当前字节个数
    private Node left;
    private Node right;

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(Byte data, int value) {
        this.data = data;
        this.value = value;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.getValue() - o.getValue();
    }
}
