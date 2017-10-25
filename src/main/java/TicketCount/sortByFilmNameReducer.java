package TicketCount;

import org.apache.hadoop.hdfs.util.EnumCounters;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dell on 2017/5/3.
 */
public class sortByFilmNameReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    LongWritable total =new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Long count=0L;
        for (LongWritable i:values) {
            count+=i.get();
        }
        total.set(count);
        context.write(key,total);
    }

}
