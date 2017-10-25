package TicketCount;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by dell on 2017/5/3.
 */
public class sortByFilmNameMapper extends Mapper<LongWritable,Text,Text,LongWritable>{
    LongWritable list=new LongWritable();
    Text filename=new Text();

//    @Override
//    protected void setup(Context context) throws IOException, InterruptedException {
//        super.setup(context);
//        FileSplit fileSplit= (FileSplit) context.getInputSplit();
//        Path path=fileSplit.getPath();
//        String filename=path.getName();
//    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] values=value.toString().split(" ");
//        if(values[0].startsWith("\uFEFF")){
//            values[0]=values[0].substring(1);
//        }
        filename.set(values[0]);
        list.set(Long.parseLong(values[2]));
        context.write(filename,list);
    }
}
