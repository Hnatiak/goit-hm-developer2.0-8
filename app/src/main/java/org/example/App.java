package org.example;
import org.example.db.DatabaseQueryService;

public class App {
    public static void main(String[] args) throws Exception {

        DatabaseQueryService service = new DatabaseQueryService();

        System.out.println(service.findMaxProjectsClient());
        System.out.println(service.findLongestProject());
        System.out.println(service.findMaxSalaryWorker());
        System.out.println(service.findYoungestEldestWorkers());
        System.out.println(service.printProjectPrices());
    }
}