import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;

public class TestConnection {
  public static void main(String[] args) throws IOException, URISyntaxException 
  {
    Configuration conf = new Configuration();

     
//conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
//conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

// ec2-52-221-247-206.ap-southeast-1.compute.amazonaws.com



     conf.set("fs.defaultFS", "hdfs://"+args[0]+":8020");
     conf.set("hadoop.security.authentication", "kerberos");
     UserGroupInformation.setConfiguration(conf);
     UserGroupInformation.loginUserFromSubject(null);

    FileSystem hdfs = FileSystem.get(conf);

     FileStatus[] fileStatus = hdfs.listStatus(new Path("hdfs://"+args[0]+":8020"+args[1]));
    
        Path[] paths = FileUtil.stat2Paths(fileStatus);

    System.out.println("***** Contents of the Directory *****");
    for(Path path : paths)
    {
      System.out.println(path);
    }


   
  }
}



/*

export CLASSPATH=/opt/app/code/hadoop/hadoop-core-0.20.2-cdh3u6.jar:/opt/app/code/hadoop/log4j-1.2.17.jar:/opt/app/code/hadoop/commons-logging-1.0.4.jar:/opt/app/code/hadoop/guava-r09-jarjar.jar:/opt/app/code/hadoop/jackson-all-1.9.0.jar:/opt/app/code/hadoop/com.springsource.org.mortbay.jetty.server-6.1.9.jar:.


export CLASSPATH=/opt/app/code/hadoop/hadoop-core-0.20.2-cdh3u6.jar:/opt/app/code/hadoop/log4j-1.2.17.jar:/opt/app/code/hadoop/commons-logging-1.0.4.jar:/opt/app/code/hadoop/guava-r09-jarjar.jar:.



*/
