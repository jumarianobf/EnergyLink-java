package br.com.fiap.gs.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GerenciarEntidadeBase {
    public static void menu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Gerenciar Entidade Base ===");
            System.out.println("1 - Inserir Entidade Base");
            System.out.println("2 - Consultar Entidades Base");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> inserirEntidadeBase(scanner);
                case 2 -> consultarEntidadesBase();
                case 0 -> running = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void inserirEntidadeBase(Scanner scanner) {
        System.out.print("Digite o nome da entidade base: ");
        String nomeEntidade = scanner.nextLine();

        try {
            executeSpInserirEntidadeBase(nomeEntidade);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Entidade Base: " + e.getMessage());
        }
    }

    private static void consultarEntidadesBase() {
        String sql = "SELECT * FROM entidade_base"; // Ajuste para a tabela correta

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Lista de Entidades Base ===");
            while (rs.next()) {
                String nomeEntidade = rs.getString("nome_entidade");
                System.out.printf("Nome: %s%n", nomeEntidade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar entidades base: " + e.getMessage());
        }
    }

    private static void executeSpInserirEntidadeBase(String nomeEntidade) throws SQLException {
        String sql = "{CALL sp_inserir_entidade_base(?)}";

        try (var connection = DatabaseConnection.getConnection();
             var stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nomeEntidade);
            stmt.execute();
            System.out.println("Entidade Base inserida com sucesso!");
        }
    }
}
