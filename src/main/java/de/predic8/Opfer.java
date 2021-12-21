package de.predic8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import spark.Spark;

public class Opfer {

    static Logger log = LogManager.getLogger(Opfer.class.getName());

    public static void main(String[] args) {

        log.info("Opfer started!");

        Spark.port(8000);

        Spark.get("/hallo", (req, res) -> {
            String name = req.queryParams("name");
            log.info("Name: " + name);
            return "Hallo " + name;
        });
    }
}
