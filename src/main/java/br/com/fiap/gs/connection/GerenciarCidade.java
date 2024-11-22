package br.com.fiap.gs.connection;

import java.sql.*;
import java.util.Scanner;

public class GerenciarCidade {
    public static void menu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Gerenciar Cidade ===");
            System.out.println("1 - Inserir Cidade");
            System.out.println("2 - Consultar Cidades");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> inserirCidade(scanner);
                case 2 -> consultarCidades();
                case 0 -> running = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void inserirCidade(Scanner scanner) {
        System.out.print("Digite o nome da cidade: ");
        String nomeCidade = scanner.nextLine();
        System.out.print("Digite o ID do estado: ");
        int idEstado = scanner.nextInt();

        try {
            executeSpInserirCidade(nomeCidade, idEstado);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Cidade: " + e.getMessage());
        }
    }

    private static void consultarCidades() {
        String sql = "SELECT * FROM cidade"; // Ajuste para a tabela correta

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Lista de Cidades ===");
            while (rs.next()) {
                int id = rs.getInt("id_cidade");
                String nome = rs.getString("nome_cidade");
                int idEstado = rs.getInt("id_estado");
                System.out.printf("ID: %d | Nome: %s | Estado ID: %d%n", id, nome, idEstado);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar cidades: " + e.getMessage());
        }
    }

    private static void executeSpInserirCidade(String nomeCidade, int idEstado) throws SQLException {
        String sql = "{CALL sp_inserir_cidade(?, ?)}";

        try (var connection = DatabaseConnection.getConnection();
             var stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nomeCidade);
            stmt.setInt(2, idEstado);
            stmt.execute();
            System.out.println("Cidade inserida com sucesso!");
        }
    }
}
