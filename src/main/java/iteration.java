import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;

/**
 * Created by dell on 2017/4/24.
 */
public class iteration {

    public static void main(String [] args) throws IOException {
        iterator("/a/jdk");
    }
    public static void iterator(String path) throws IOException {
        Configuration con=new Configuration();
        FileSystem fs=FileSystem.get(URI.create("hdfs://192.168.126.128:9000"),con);
        Path path1=new Path(path);
        if(fs.exists(path1)){
//           FileStatus[] fileStatus= fs.listStatus(path1);
//            for(FileStatus fsp: fileStatus){
//                RemoteIterator ri=
//                System.out.println(fsp);
//            }
        RemoteIterator<LocatedFileStatus> ri =fs.listFiles(path1,true);

            while(ri.hasNext()){
                System.out.println(ri.next());
                LocatedFileStatus lfs=ri.next();
                System.out.println(lfs.getPath()+"  "+lfs.isDirectory());
            }

        }else{
            System.out.println("目录不存在！");
        }
    }
}
