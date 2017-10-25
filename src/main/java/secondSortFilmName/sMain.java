package secondSortFilmName;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by dell on 2017/5/4.
 */
public class sMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration con=new Configuration();
        con.addResource(new Path("/opt/hadoop/etc/hadoop/core-site.xml"));
        con.addResource(new Path("/opt/hadoop/etc/hadoop/hdfs-site.xml"));
        Job job=Job.getInstance(con,"TOP K");
        job.setJarByClass(sMain.class);
        job.setMapperClass(sMapper.class);
        job.setReducerClass(sReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
