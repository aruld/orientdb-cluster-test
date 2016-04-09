package de.jotschi.orientdb.test;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.junit.Test;

import java.io.File;

public class OrientDBClusterMasterTest extends AbstractClusterTest {

  private final String nodeName = "master";

  @Test
  public void testCluster() throws Exception {
    start(nodeName);

//		System.in.read();
    System.out.println("Sleeping for 30s, giving time for nodes to sync up");
    Thread.sleep(30000);
    ODatabaseDocumentTx database = new ODatabaseDocumentTx("plocal:" + new File("databases/userdb").getAbsolutePath()).open("admin", "admin");
    int i = 1;
    while (true) {
      ODocument user = new ODocument("User");
      user.field("FirstName", "John" + i);
      user.field("LastName", "Doe" + i);
      user.field("MailAddress", "john" + i + "@doe.com");
      user.save();
      i++;
      System.out.println("Count: " + database.countClass("User"));
      Thread.sleep(500);
    }
  }
}
