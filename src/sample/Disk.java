package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


/**
 * Created by keke on 2017/6/7.
 */
public class Disk {
    //每个磁盘块大小
    private int diskSize = 256;

    //磁盘块数量
    public static int diskBlockCount = 100;

    //全部存储
    private  ArrayList<String> blockList = new ArrayList<String>();


    //空块栈
    private static Stack<Integer> freeDiskBlock = new Stack<Integer>();
    //已使用块栈
    private static Stack<Integer> fullDiskBlock = new Stack<Integer>();

    public Disk() {
        this.initStack();
        for (int i = 0; i < diskBlockCount; i++) {
            blockList.add(new String());
        }
    }

    private static void initStack() {
        for (int i = diskBlockCount - 1; i >= 0; i--) {
            freeDiskBlock.push(new Integer(i));
        }
    }
    //获取一个空闲块
    public static int getFreeBlock() {
        if(!freeDiskBlock.empty()) {
            int index = freeDiskBlock.peek();
            fullDiskBlock.push(new Integer(index));
            freeDiskBlock.pop();
            return index;
        }
        return -1;
    }
    //释放掉一个块
    public void releaseBlock(int index) {
        freeDiskBlock.push(new Integer(index));
        //clear

        blockList.set(index, "");
    }
    //格式化
    public void format() {
        this.initStack();
        fullDiskBlock.clear();
    }

    public ArrayList<String> getBlockList() {
        return blockList;
    }

    public Stack<Integer> getFreeDiskBlock() {
        return freeDiskBlock;
    }

    public Stack<Integer> getFullDiskBlock() {
        return fullDiskBlock;
    }

//    public int getDiskSize() {
//        return diskSize;
//    }
    public int getDiskSize() {
        return diskSize;
    }

    public static void main(String[] args) {
        initStack();
//        while(!freeDiskBlock.empty()) {
//            System.out.println(freeDiskBlock.peek());
//            freeDiskBlock.pop();
//        }
        for (int i = 0; i < 10; i++) {
            System.out.println("index:"+getFreeBlock());
            System.out.println("free:"+freeDiskBlock.peek());
            System.out.println("full:"+fullDiskBlock.peek());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("index:"+getFreeBlock());
            System.out.println("free:"+freeDiskBlock.peek());
            System.out.println("full:"+fullDiskBlock.peek());
        }


    }
//    class DiskBlock {
//
//        //编号
//        private int number;
//        //存储
//        private String [] content = new String[getDiskSize()];
//
//        public DiskBlock(int n) {
//            this.number = n;
//        }
//    }
}





//    // 位示图
//    // 1 : 已占用
//    // 0 : 可用
//    private int [] bitmap = new int[diskCount];

//    //找到第一个空位
//    public int getFisrtEmptyBlock() {
//        return getNextEmptyBlock(0);
//    }
//
//    //找到下一个空位
//    public int getNextEmptyBlock(int index) {
//        for (int i = index; i < diskCount; i++) {
//            if(bitmap[i] == 0) {
//                return i;
//            }
//        }
//        return -1;
//    }