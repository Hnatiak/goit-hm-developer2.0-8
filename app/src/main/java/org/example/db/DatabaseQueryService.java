package org.example.db;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.model.LongestProject;
import org.example.model.MaxProjectCountClient;
import org.example.model.MaxSalaryWorker;
import org.example.model.ProjectPrice;
import org.example.model.YoungestEldestWorker;

public class DatabaseQueryService {

    private static String loadSql(String resource) throws Exception {
        try (InputStream is = DatabaseQueryService.class.getClassLoader().getResourceAsStream(resource)) {
                
            if (is == null) {
                throw new RuntimeException("SQL file not found: " + resource);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public List<LongestProject> findLongestProject() throws Exception {
        String sql = loadSql("sql/find_longest_project.sql");
        List<LongestProject> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                list.add(new LongestProject(
                        rs.getString("name"),
                        rs.getInt("month_count")
                ));
            }
        }
        return list;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() throws Exception {
        String sql = loadSql("sql/find_max_projects_client.sql");
        List<MaxProjectCountClient> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new MaxProjectCountClient(
                        rs.getString("name"),
                        rs.getInt("project_count")
                ));
            }
        }
        return list;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() throws Exception {
        String sql = loadSql("sql/find_max_salary_worker.sql");
        List<MaxSalaryWorker> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                list.add(new MaxSalaryWorker(
                        rs.getString("name"),
                        rs.getInt("salary")
                ));
            }
        }
        return list;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() throws Exception {
        String sql = loadSql("sql/find_youngest_eldest_workers.sql");
        List<YoungestEldestWorker> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                list.add(new YoungestEldestWorker(
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getDate("birthday").toLocalDate()
                ));
            }
        }
        return list;
    }

    public List<ProjectPrice> printProjectPrices() throws Exception {
        String sql = loadSql("sql/print_project_prices.sql");
        List<ProjectPrice> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                list.add(new ProjectPrice(
                        rs.getString("name"),
                        rs.getInt("price")
                ));
            }
        }
        return list;
    }
}