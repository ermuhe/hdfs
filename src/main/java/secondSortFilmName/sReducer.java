package secondSortFilmName;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.*;


/**
 * Created by dell on 2017/5/4.
 */
public class sReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    Map<String,Long> map1=new TreeMap<>();
    Text key1=new Text();
    LongWritable value1=new LongWritable();
    List<Map.Entry< String,Long>> list = new ArrayList<>();
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Long count=0L;
        for (LongWritable i:values) {
            count+=i.get();
        }

        map1.put(key.toString(),count);
//        Map<String, Long> rsMap = new LinkedHashMap<>();
//        for (Map.Entry<String, Long> entry : list) {
////            rsMap.put(entry.getKey(), entry.getValue());
//            key1.set(entry.getKey());
//            value1.set(entry.getValue());
//            context.write(key1,value1);
//        }
//        for(int i=0;i<list.size();i++){
//            Map.Entry<String,Long> entry=list.get(i);
//            key1.set(entry.getKey());
//            value1.set(entry.getValue());
//            context.write(key1,value1);
//            if(i==3){
//                break;
//            }
//        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        super.cleanup(context);
        for (Map.Entry<String,Long> entry : map1.entrySet()) {
            list.add(entry);
        }
        java.util.Collections.sort(list, new Comparator<Map.Entry<String,Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Collections.reverse(list);
        for(int i=0;i<list.size();i++){
            Map.Entry<String,Long> entry=list.get(i);
            key1.set(entry.getKey());
            value1.set(entry.getValue());
            context.write(key1,value1);
            if(i==2){
                break;
            }
        }
    }
}
