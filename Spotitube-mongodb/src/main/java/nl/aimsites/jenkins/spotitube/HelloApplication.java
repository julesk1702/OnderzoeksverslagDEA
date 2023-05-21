package nl.aimsites.jenkins.spotitube;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
public class HelloApplication extends Application {
    public static class Main {
        public static void main(String[] args) {
            System.out.println("Hello world!");
        }
    }
}