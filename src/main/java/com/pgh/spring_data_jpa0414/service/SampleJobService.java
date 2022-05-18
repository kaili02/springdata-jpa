package com.pgh.spring_data_jpa0414.service;

import lombok.Getter;
import lombok.Setter;
import org.jobrunr.jobs.annotations.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

@Service
@Setter
@Getter
public class SampleJobService {

    @Value("${server.port}")
    private String port;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Job(name = "The sample job without variable")
    public void execute() {

        execute("Hello world!");
    }

    @Job(name = "The sample job with variable %0")
    public void execute(String input) {
        logger.info("haha port: " + port);
        logger.info("The sample job has begun. The variable you passed is {}", input);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            logger.info("Sample job has finished...");
        }
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();

        // We want this for failover on the replicas
        props.put("autoReconnect", "true");

        // We want to load balance between the replicas
        props.put("roundRobinLoadBalance", "true");

        props.put("user", "root");
        props.put("password", "123456");

        //
        // Looks like a normal MySQL JDBC url, with a
        // comma-separated list of hosts, the first
        // being the 'source', the rest being any number
        // of replicas that the driver will load balance against
        //

        Connection conn =
                DriverManager.getConnection("jdbc:mysql:replication://source,replica1,replica2,replica3/test",
                        props);

        //
        // Perform read/write work on the source
        // by setting the read-only flag to "false"
        //

        conn.setReadOnly(false);
        conn.setAutoCommit(false);
        conn.createStatement().executeUpdate("UPDATE some_table ....");
        conn.commit();

        //
        // Now, do a query from a replica, the driver automatically picks one
        // from the list
        //

        conn.setReadOnly(true);

        ResultSet rs =
                conn.createStatement().executeQuery("SELECT a,b FROM alt_table");

    }

}