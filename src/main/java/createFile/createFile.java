package createFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;


import java.io.*;
import java.net.URI;



/**
 * Created by dell on 2017/4/24.
 */
public class createFile {
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] filmNames = {"情圣","铁道飞虎","长城","酒国英雄之摆渡人","血战钢锯岭","那年夏天你去了哪","冰雪女皇之冬日魔","你好，疯子！","罗曼提克消亡史"};
        String[] tickets = {"20", "25", "30"};
        String[] areas = {"成都", "北京", "上海", "广州", "重庆", "福建", "深圳", "杭州", "天津"};
        StringBuffer sb = new StringBuffer();
        FileOutputStream bw = null;

        String filename=String.valueOf(System.currentTimeMillis());
//        Configuration con = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create("hdfs://192.168.126.128:9000"), con, "hadoop");
//        FSDataOutputStream out = fs.create(new Path("/a/b/"+filename+".txt"));
        File file=new File("/home/hadoop/collect/"+filename+".txt");
        if(file.createNewFile()){
            //Runtime.getRuntime().exec("chmod 777 /home/test3.txt");
            file.setExecutable(true);//设置可执行权限
            file.setReadable(true);//设置可读权限
            file.setWritable(true);//设置可写权限
            System.out.println("文件创建成功");
        }
        for (int i = 0; i < 100000; i++) {
            String film = filmNames[(int) (Math.random() * 9)];
            String ticket = tickets[(int) (Math.random() * 3)];
            String area = areas[(int) (Math.random() * 9)];
            sb.append(film + " " + area + " " + ticket + "\n");

//            out.write((film + " " + area+ " " + ticket + "\n").getBytes("UTF-8"));
//            out.write();
//            if((i%500)==0) {
//                Thread.sleep(2000);
//            }
        }
//            if (out != null) {
//                out.close();
//            }
            try {
                bw = new FileOutputStream(file,false);
                bw.write(sb.toString().getBytes("utf-8"));
                System.out.println("输出成功");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

