package org.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.model.Client;

public class ClientService {

    private void validateName(String name) {
        if (name == null || name.length() < 2 || name.length() > 1000) {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public long create(String name) {
        validateName(name);

        String sql = "INSERT INTO client (name) VALUES (?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, name);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }

            throw new RuntimeException("No ID obtained");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getById(long id) {
        String sql = "SELECT name FROM client WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return rs.getString("name");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setName(long id, String name) {
        validateName(name);

        String sql = "UPDATE client SET name = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setLong(2, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM client WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> listAll() {
        String sql = "SELECT id, name FROM client";

        List<Client> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                list.add(client);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
