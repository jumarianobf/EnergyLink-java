package br.com.fiap.gs.connection;

import java.sql.*;
import java.util.Scanner;

public class GerenciarTipoUsuario {
    public static void menu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Gerenciar Tipo de Usuário ===");
            System.out.println("1 - Inserir Tipo de Usuário");
            System.out.println("2 - Consultar Tipos de Usuário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> inserirTipoUsuario(scanner);
                case 2 -> consultarTiposUsuario();
                case 0 -> running = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void inserirTipoUsuario(Scanner scanner) {
        System.out.print("Digite o nome do tipo de usuário: ");
        String nomeTipoUsuario = scanner.nextLine();

        try {
            executeSpInserirTipoUsuario(nomeTipoUsuario);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Tipo de Usuário: " + e.getMessage());
        }
    }

    private static void consultarTiposUsuario() {
        String sql = "SELECT * FROM tipo_usuario"; // Substitua pelo nome da tabela real

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Lista de Tipos de Usuário ===");
            while (rs.next()) {
                int id = rs.getInt("id_tipo_usuario");
                String descricao = rs.getString("descricao_tipo");
                System.out.printf("ID: %d | Descrição: %s%n", id, descricao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar Tipos de Usuário: " + e.getMessage());
        }
    }

    private static void executeSpInserirTipoUsuario(String nomeTipoUsuario) throws SQLException {
        String sql = "{CALL sp_inserir_tipo_usuario(?)}";

        try (var connection = DatabaseConnection.getConnection();
             var stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nomeTipoUsuario);
            stmt.execute();
            System.out.println("Tipo de Usuário inserido com sucesso!");
        }
    }
}
