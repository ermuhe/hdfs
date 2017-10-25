import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * Created by dell on 2017/4/24.
 */
public class HDFSutil {
    private static Configuration con=new Configuration();
    private static FileSystem fs;

    public static void  initEnv() throws IOException {
        fs=FileSystem.get(URI.create("hdfs://master:9000"),con);
    }

    public boolean exitsFile(String destFile)  {
        boolean flag=false;
        Path destPath = new Path(destFile);

        try {
            if(fs.exists(destPath))
                flag=true;
            else
                flag=false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean delFile(String destFile){
        boolean flag=false;
        Path destPath=new Path(destFile);
        if(exitsFile(destFile)){
            try {
                fs.delete(destPath,true);
                flag=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {

        }
        return false;
    }

//    public boolean creatFile(String destPath){
//        fs.
//    }

    public String upload(String src,String dest){
        String message=null;
        Path srcPath=new Path(src);
        Path destPath=new Path(dest);
        if(exitsFile(dest)){
            try {
                fs.copyFromLocalFile(false,true,srcPath,destPath);
                message="上传成功！";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                fs.copyFromLocalFile(srcPath,destPath);
                message="上传成功！";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }
}
