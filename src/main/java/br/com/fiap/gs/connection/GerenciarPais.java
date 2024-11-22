package br.com.fiap.gs.connection;

import java.sql.*;
import java.util.Scanner;

public class GerenciarPais {
    public static void menu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Gerenciar País ===");
            System.out.println("1 - Inserir País");
            System.out.println("2 - Consultar Países");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> inserirPais(scanner);
                case 2 -> consultarPaises();
                case 0 -> running = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void inserirPais(Scanner scanner) {
        System.out.print("Digite o nome do país: ");
        String nomePais = scanner.nextLine();

        try {
            executeSpInserirPais(nomePais);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir País: " + e.getMessage());
        }
    }

    private static void consultarPaises() {
        String sql = "SELECT * FROM pais"; // Substitua pelo nome da tabela real

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Lista de Países ===");
            while (rs.next()) {
                int id = rs.getInt("id_pais");
                String nome = rs.getString("nome_pais");
                System.out.printf("ID: %d | Nome: %s%n", id, nome);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar Países: " + e.getMessage());
        }
    }

    private static void executeSpInserirPais(String nomePais) throws SQLException {
        String sql = "{CALL sp_inserir_pais(?)}";

        try (var connection = DatabaseConnection.getConnection();
             var stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nomePais);
            stmt.execute();
            System.out.println("País inserido com sucesso!");
        }
    }
}
