import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by dell on 2017/4/24.
 */
public class pressOrUnpress {
    public  static  void main(String [] args) throws IOException, ClassNotFoundException {
        unPress("/a/jdk-8u111-linux-x64.tar.gz","/a/jdknew/");
    }
    public static void unPress(String source,String dest) throws IOException, ClassNotFoundException {
        Configuration con= new Configuration();
        FileSystem fs=FileSystem.get(URI.create("hdfs://master:9000"),con);
        Path sPath=new Path(source);
        Path dPath=new Path(dest);
        if(fs.isFile(sPath)){
            Class<?> codeclass=Class.forName("org.apache.hadoop.io.compress.GzipCodec");
            CompressionCodec codec= (CompressionCodec)ReflectionUtils.newInstance(codeclass,con);
            FSDataInputStream InputStream =fs.open(sPath);
            InputStream in=codec.createInputStream(InputStream);

            FSDataOutputStream OutputStream =fs.create(dPath);
            IOUtils.copyBytes(in,OutputStream,con);
            IOUtils.closeStream(InputStream);
            IOUtils.closeStream(OutputStream);
        }
    }
}
