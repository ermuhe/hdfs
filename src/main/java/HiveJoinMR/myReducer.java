package HiveJoinMR;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/5/10.
 */
public class myReducer extends Reducer<LongWritable,Text,Text,Text> {
    Text key1=new Text();
    Text value1=new Text();
    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<String> aa= new ArrayList();
        for (Text i:values) {
            String[] splits=i.toString().split(",");
            if(splits[0].equals("1")){
                key1.set(splits[1]);
                continue;
            }
            aa.add(splits[1]);
        }
        for (String i:aa) {
            value1.set(i);
            context.write(key1,value1);
        }
    }
}
