// cc FileSystemCat Displays files from a Hadoop filesystem on standard output by using the FileSystem directly
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


// vv FileSystemCat
public class FileSystemCat {

  public static void main(String[] args) throws Exception {
    String uri = args[0];

    //隐藏得真深，在一个静态代码块中加载默认配置，cp路径加载
    Configuration conf = new Configuration();
    System.out.println(conf.get("fs.defaultFS"));

    //conf.set("fs.defaultFS","hdfs://192.168.52.100/");
    FileSystem fs = FileSystem.get( conf);
   // fs.listFiles(new Path("/"),true);

   boolean exist =  fs.exists(new Path(uri) );

   System.out.println(exist);

    InputStream in = null;
    try {
      in = fs.open(new Path(uri));
      IOUtils.copyBytes(in, System.out, 4096, false);
    } finally {
      IOUtils.closeStream(in);
    }
  }
}
// ^^ FileSystemCat
