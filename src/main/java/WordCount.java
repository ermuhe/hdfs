import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by dell on 2017/4/25.
 */
public class WordCount {


    public static void main(String []args){
        Configuration con=new Configuration();
        if(args.length<2){
            System.out.print("参数小于2");
            System.exit(1);
        }
        try {
            Job job=Job.getInstance(con,"first");
            job.setJarByClass(WordCount.class);
            job.setMapperClass(mapNew.class);
            job.setReducerClass(reudceNew.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);
            FileInputFormat.setInputPaths(job,new Path(args[0]));
            FileOutputFormat.setOutputPath(job,new Path(args[1]));
            System.exit(job.waitForCompletion(true) ? 0 :1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  class mapNew extends Mapper<LongWritable,Text,Text,IntWritable>{
        IntWritable time=new IntWritable();
        private Text wordtext=new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] words=value.toString().split(" ");
//            for(String word:words) {
//                wordtext.set(word);
//                context.write(wordtext,time);
//            }
            wordtext.set(words[0]);
            time.set(Integer.parseInt(words[1]));
            context.write(wordtext,time);
        }
    }

    public static class reudceNew extends Reducer<Text,IntWritable,Text,IntWritable>{

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum=0;
            for(IntWritable i:values){
                sum+=i.get();
            }
            context.write(key,new IntWritable(sum));
        }
    }
}
