package TicketCount;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by dell on 2017/5/3.
 */
public class FilmSortByNameWritable implements WritableComparable<FilmSortByNameWritable> {
    long count;
    String filmname;
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getFilmname() {
        return filmname;
    }

    public void setFilmname(String filmname) {
        this.filmname = filmname;
    }
    public FilmSortByNameWritable(){

    }
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(filmname);
        dataOutput.writeLong(count);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.filmname=dataInput.readUTF();
        this.count=dataInput.readLong();
    }

    @Override
    public int compareTo(FilmSortByNameWritable o) {
        return this.count>o.count?1:(this.count==o.count?0:-1);
    }
}
