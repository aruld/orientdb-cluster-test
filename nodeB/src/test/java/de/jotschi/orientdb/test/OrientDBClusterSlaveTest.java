package de.jotschi.orientdb.test;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.junit.Test;

import java.io.File;

public class OrientDBClusterSlaveTest extends AbstractClusterTest {

  private final String nodeName = "slave";

  @Test
  public void testCluster() throws Exception {
    start(nodeName);
//    System.in.read();
    System.out.println("Sleeping for 30s, giving time for nodes to sync up");
    Thread.sleep(30000);
    ODatabaseDocumentTx database = new ODatabaseDocumentTx("plocal:" + new File("databases/userdb").getAbsolutePath()).open("admin", "admin");
    while (true) {
      long count = database.countClass("User");
      System.out.println("Count: " + count);
      Thread.sleep(1500);
    }

  }
}
