package br.com.fiap.gs.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GerenciarLocalizacao {
    public static void menu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Gerenciar Localização ===");
            System.out.println("1 - Inserir Localização");
            System.out.println("2 - Consultar Localizações");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> inserirLocalizacao(scanner);
                case 2 -> consultarLocalizacoes();
                case 0 -> running = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void inserirLocalizacao(Scanner scanner) {
        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();
        System.out.print("Digite o ID da cidade: ");
        int idCidade = scanner.nextInt();

        try {
            executeSpInserirLocalizacao(cep, idCidade);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Localização: " + e.getMessage());
        }
    }

    private static void consultarLocalizacoes() {
        String sql = "SELECT * FROM localizacao"; // Ajuste para a tabela correta

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Lista de Localizações ===");
            while (rs.next()) {
                String cep = rs.getString("cep");
                int idCidade = rs.getInt("id_cidade");
                System.out.printf("CEP: %s | ID Cidade: %d%n", cep, idCidade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar localizações: " + e.getMessage());
        }
    }

    private static void executeSpInserirLocalizacao(String cep, int idCidade) throws SQLException {
        String sql = "{CALL sp_inserir_localizacao(?, ?)}";

        try (var connection = DatabaseConnection.getConnection();
             var stmt = connection.prepareCall(sql)) {
            stmt.setString(1, cep);
            stmt.setInt(2, idCidade);
            stmt.execute();
            System.out.println("Localização inserida com sucesso!");
        }
    }
}
