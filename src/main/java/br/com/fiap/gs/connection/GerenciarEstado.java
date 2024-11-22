package br.com.fiap.gs.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GerenciarEstado {
    public static void menu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Gerenciar Estado ===");
            System.out.println("1 - Inserir Estado");
            System.out.println("2 - Consultar Estados");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> inserirEstado(scanner);
                case 2 -> consultarEstados();
                case 0 -> running = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void inserirEstado(Scanner scanner) {
        System.out.print("Digite o nome do estado: ");
        String nomeEstado = scanner.nextLine();
        System.out.print("Digite o ID do país: ");
        int idPais = scanner.nextInt();

        try {
            executeSpInserirEstado(nomeEstado, idPais);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Estado: " + e.getMessage());
        }
    }

    private static void consultarEstados() {
        String sql = "SELECT * FROM estado"; // Substitua pelo nome da tabela real

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Lista de Estados ===");
            while (rs.next()) {
                int id = rs.getInt("id_estado");
                String nome = rs.getString("nome_estado");
                System.out.printf("ID: %d | Nome: %s%n", id, nome);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar Estados: " + e.getMessage());
        }
    }

    private static void executeSpInserirEstado(String nomeEstado, int idPais) throws SQLException {
        String sql = "{CALL sp_inserir_estado(?, ?)}";

        try (var connection = DatabaseConnection.getConnection();
             var stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nomeEstado);
            stmt.setInt(2, idPais);
            stmt.execute();
            System.out.println("Estado inserido com sucesso!");
        }
    }
}
