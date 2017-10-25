import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



/**
 * Created by dell on 2017/4/25.
 */
public class countWord {
    public static void main(String [] args) throws IOException {
        FileReader in=new FileReader("G:\\file.txt");
        BufferedReader br=new BufferedReader(in);
        String str=null;
        int counta=0;
        int countb=0;
        int countc=0;
        int countd=0;
        Map<String  ,Long> map=new HashMap<String  ,Long>();
        while((str=br.readLine())!=null){
            String [] words=str.split(" ");
            if(map.containsKey(words[0])){
                Long i=map.get(words[0]);
                map.put(words[0],Long.parseLong(words[1])+i);
            }else{
                map.put(words[0], Long.parseLong(words[1]));
            }


//            if(words[0].equals("速度与激情8")){
//                count= count+Integer.parseInt(words[1]);
//
//            }
//            switch (words[0]) {
//                case "傲娇与偏见":
//                    counta=counta+Integer.parseInt(words[1]);
//                    break;
//                case "血狼犬":
//                    countb=countb+Integer.parseInt(words[1]);
//                    break;
//                case "大话西游之大圣娶亲":
//                    countc=countc+Integer.parseInt(words[1]);
//                    break;
//                case "蓝精灵：寻找神秘村":
//                    countd=countd+Integer.parseInt(words[1]);
//                    break;
//            }

        }
        Set set=map.keySet();
        Iterator iterator=set.iterator();
        while(iterator.hasNext()){
            String key= (String) iterator.next();
            Long count=map.get(key);
            System.out.println(key+":"+count);
        }
        if(in!=null){
            in.close();
        }
        if(br!=null){
            br.close();
        }
//        System.out.println(counta);
//        System.out.println(countb);
//        System.out.println(countc);
//        System.out.println(countd);


    }
}
