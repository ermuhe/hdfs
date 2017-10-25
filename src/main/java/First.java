import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;


/**
 * Created by dell on 2017/4/20.
 */
public class First {
   private FileSystem fs;

    @org.junit.Before
    public void init() throws IOException {
        Configuration con=new Configuration();
        fs=FileSystem.get(URI.create("hdfs://192.168.126.130"), con);
    }
    @org.junit.Test
    public void sendFile() throws IOException {
        fs.copyFromLocalFile(new Path("F:\\dd.txt"),new Path("/dd.txt"));
    }


}
