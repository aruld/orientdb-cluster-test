package de.jotschi.orientdb.test;

import java.io.File;

import org.junit.Test;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

public class OrientDBClusterTest2 extends AbstractClusterTest {

	private final String nodeName = "nodeB";

	@Test
	public void testCluster() throws Exception {
		start(nodeName);
		System.in.read();
		OrientGraphFactory factory = new OrientGraphFactory("plocal:" + new File("databases/GratefulDeadConcerts").getAbsolutePath());
		while (true) {
			OrientGraphNoTx graph = factory.getNoTx();
			System.out.println("Count: " + graph.countVertices());
			Thread.sleep(1500);
			graph.shutdown();
		}

	}
}
