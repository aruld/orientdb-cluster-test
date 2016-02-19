package de.jotschi.orientdb.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.exception.OStorageException;
import org.apache.commons.io.IOUtils;

import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.OServerMain;
import com.orientechnologies.orient.server.plugin.OServerPluginManager;

public class Database {

	private String nodeName;

	public Database(String nodeName) {
		this.nodeName = nodeName;
	}

	private InputStream getOrientServerConfig() throws IOException {
		InputStream configIns = getClass().getResourceAsStream("/config/orientdb-server-config.xml");
		StringWriter writer = new StringWriter();
		IOUtils.copy(configIns, writer, StandardCharsets.UTF_8);
		String configString = writer.toString();
		configString = configString.replaceAll("%PLUGIN_DIRECTORY%", "orient-plugins");
		configString = configString.replaceAll("%CONSOLE_LOG_LEVEL%", "finest");
		configString = configString.replaceAll("%FILE_LOG_LEVEL%", "fine");
		configString = configString.replaceAll("%NODENAME%", nodeName);
		InputStream stream = new ByteArrayInputStream(configString.getBytes(StandardCharsets.UTF_8));
		return stream;
	}

	public void startOrientServer() throws Exception {
		String orientdbHome = new File("").getAbsolutePath();
		System.setProperty("ORIENTDB_HOME", orientdbHome);
//		createDatabase();
		OServer server = OServerMain.create();

		server.startup(getOrientServerConfig());
		OServerPluginManager manager = new OServerPluginManager();
		manager.config(server);
		server.activate();
		manager.startup();
	}

	private void createDatabase() {
		ODatabaseDocumentTx database = null;
		try {
			database = new ODatabaseDocumentTx("plocal:" + new File("databases/db_testdb").getAbsolutePath()).open("admin", "admin");
		} catch (OStorageException e) {
			// database does not exist, create one
			try {
				database = new ODatabaseDocumentTx("plocal:/databases/db_testdb").create();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if (database != null) database.close();

	}

}
