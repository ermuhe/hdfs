package HiveJoinMR;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by dell on 2017/5/10.
 */
public class myMapper2 extends Mapper<LongWritable,Text,LongWritable,Text>{
    int flag=2;
    LongWritable keyOut=new LongWritable();
    Text valueOut=new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String[] values = value.toString().split(",");
            keyOut.set(Long.parseLong(values[0]));
            valueOut.set(flag + "," + values[1]);
            context.write(keyOut, valueOut);

        }
    }