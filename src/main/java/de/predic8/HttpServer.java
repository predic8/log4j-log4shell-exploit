package de.predic8;

import spark.Spark;

import java.io.File;
import java.nio.file.Files;

public class HttpServer {

    public static void main(String[] args) {
        Spark.port(8080);

        Spark.get("/BadCode.class", (req, res) -> {
            return Files.readAllBytes(new File("target/classes/BadCode.class").toPath());
        });
    }
}