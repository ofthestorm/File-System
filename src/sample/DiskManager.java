package sample;

import java.util.ArrayList;

/**
 * Created by keke on 2017/6/7.
 */
public class DiskManager {

    private Disk disk;

    private DirectoryTree directoryTree;

    //系统打开文件表
    public static ArrayList<FCB> openFileTable = new ArrayList<>();

    //每个磁盘块大小
    private int diskSize = 256;

    public DiskManager(Disk d, DirectoryTree directoryTree) {
        this.disk = d;
        this.directoryTree = directoryTree;
    }

    //删除
    public void delete(FCB fcb) {
        if(fcb.getType() == FCB.Type.document) {
            for (int i = 0; i < blockCount(fcb); i++) {
                int index = fcb.getIndexTable()[i];
                disk.releaseBlock(index);
            }
        } else {
            while (fcb.getChild().size() != 0) {
                delete(fcb.getChild().get(0));
            }
        }
    }

    //读
    public String read(FCB fcb) {
        int i = 0;
        String str = "";
        int index = fcb.getIndexTable()[i];
        while(index != -1) {
            str += disk.getBlockList().get(index);
            i++;
            index = fcb.getIndexTable()[i];
        }
        return str;
    }

    //写 ~
    // TODO: 2017/6/20 追加写?
    public void write(FCB fcb, String str) {
        int occupiedBlockCount =  blockCount(fcb);

        //初始化
        for (int i = 0; i < occupiedBlockCount; i++) {
            disk.releaseBlock(fcb.getIndexTable()[i]);
            fcb.setIndexTableI(i, -1);
        }


        int needBlock = str.length() / diskSize + 1 ;
//        for (int i = occupiedBlockCount; i < occupiedBlockCount + needBlock; i++) {
//            int j = i - occupiedBlockCount;
        for (int i = 0; i < needBlock; i++) {
            int index = disk.getFreeBlock();
//            System.out.println(index);
            fcb.setIndexTableI(i, index);
            if(str.length() - i * diskSize >= diskSize) {
                disk.getBlockList().set(index, str.substring(i * diskSize, (i + 1) * diskSize - 1));
            } else {
                disk.getBlockList().set(index, str.substring(i * diskSize));
            }
//            fcb.setIndexTableI(i, 1);
        }
        if(str.length() <= 1024) {
            fcb.setSize(str.length() + "B");
        } else if (str.length() > 1024 && str.length() <= 1024 * 1024 ) {
            fcb.setSize(str.length() / 1024 + "K");
        }
    }

    //文件已占用块数
    private int blockCount(FCB fcb) {
        int count = 0;
        int i = 0;
        while(fcb.getIndexTable()[i] != -1) {
            count++;
            i++;
        }
        return count;
    }



    //文件详情
    public String displayDetails(FCB fcb) {
        String details = "";
        details += "Name : " + fcb.getName() + "\n";
        details += "Size : " + fcb.getSize() + "\n";
        details += "Type : " + fcb.getType() + "\n";
        details += "Authority : " + fcb.getAuthority() + "\n";
        details += "Create Time : " + fcb.getCreateTime() + "\n";
        details += "Modify Time : " + fcb.getModifyTime() + "\n";
        return details;
    }


}
