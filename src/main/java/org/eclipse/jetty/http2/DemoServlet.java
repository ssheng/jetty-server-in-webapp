package org.eclipse.jetty.http2;

import java.io.IOException;

import java.lang.management.ManagementFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;


public class DemoServlet extends javax.servlet.http.HttpServlet {
    private final Logger logger = Logger.getLogger(DemoServlet.class);


    @Override
    public void init() throws ServletException {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(new HttpConfiguration()));
        server.setConnectors(new ServerConnector[] { connector });

        MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        server.addEventListener(mbContainer);
        server.addBean(mbContainer);

        logger.info("DemoServlet initialization complete");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
