package HiveJoinMR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by dell on 2017/5/10.
 */
public class mrJoinMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        if(args.length<3){
//            System.out.println("参数不够！！");
//            System.exit(1);
//        }
        Configuration con=new Configuration();
        Job job=Job.getInstance(con,"hive的join实现");
        job.setJarByClass(mrJoinMain.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setReducerClass(myReducer.class);
        MultipleInputs.addInputPath(job, new Path(args[0]),
                TextInputFormat.class, myMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]),
                TextInputFormat.class, myMapper2.class);
        FileOutputFormat.setOutputPath(job,new Path(args[2]));
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
