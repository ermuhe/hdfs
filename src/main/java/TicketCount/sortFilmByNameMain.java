package TicketCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;


/**
 * Created by dell on 2017/5/3.
 */
public class sortFilmByNameMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration con=new Configuration();
        con.addResource(new Path("/opt/hadoop/etc/hadoop/core-site.xml"));
        con.addResource(new Path("/opt/hadoop/etc/hadoop/hdfs-site.xml"));
        FileSystem fileSystem=FileSystem.get(URI.create("hdfs://master:9000"),con);
        Path inputPath=new Path(args[0]);
        Path outputPath=new Path("/dd/filmResult/");
//        if(args.length!=2){
//            System.out.print("参数小于二");
//            System.exit(1);
//        }
        if(fileSystem.exists(outputPath)){
            fileSystem.delete(outputPath,true);
        }
        Job job= Job.getInstance(con,"根据电影名排序");
        job.setJarByClass(sortFilmByNameMain.class);
        job.setMapperClass(sortByFilmNameMapper.class);
        job.setReducerClass(sortByFilmNameReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        FileInputFormat.setInputPaths(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);
        if(job.waitForCompletion(true)){
            fileSystem.delete(inputPath,true);
            System.exit(0);
        }
//        try {
//            System.exit(job.waitForCompletion(true) ? 0:1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
