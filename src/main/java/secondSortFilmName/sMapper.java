package secondSortFilmName;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dell on 2017/5/4.
 */
public class sMapper extends Mapper<LongWritable ,Text,Text,LongWritable> {
    Text v1=new Text();
    LongWritable v2=new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] values=value.toString().split("\\t");
        v1.set(values[0]);
        v2.set(Long.valueOf(values[1]));
        context.write(v1,v2);

    }


}
