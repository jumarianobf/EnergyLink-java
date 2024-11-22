package br.com.fiap.gs.connection;

import java.sql.*;
import java.util.Scanner;

public class GerenciarUsuario {
    public static void menu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Gerenciar Usuário ===");
            System.out.println("1 - Inserir Usuário");
            System.out.println("2 - Consultar Usuário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> inserirUsuario(scanner);
                case 2 -> consultarUsuario();
                case 0 -> running = false;
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void inserirUsuario(Scanner scanner) {
        System.out.print("Digite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Digite o sobrenome do usuário: ");
        String sobrenomeUsuario = scanner.nextLine();
        System.out.print("Digite o CPF do usuário: ");
        String cpfUsuario = scanner.nextLine();
        System.out.print("Digite o e-mail do usuário: ");
        String emailUsuario = scanner.nextLine();
        System.out.print("Digite a senha do usuário: ");
        String senhaUsuario = scanner.nextLine();
        System.out.print("Digite o telefone do usuário: ");
        String telefoneUsuario = scanner.nextLine();
        System.out.print("Digite o ID do tipo de usuário: ");
        int idTipoUsuario = scanner.nextInt();

        try {
            executeSpInserirUsuario(nomeUsuario, sobrenomeUsuario, cpfUsuario, emailUsuario, senhaUsuario, telefoneUsuario, idTipoUsuario);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir Usuário: " + e.getMessage());
        }
    }

    private static void consultarUsuario() {
        String sql = "SELECT * FROM usuario_energylink"; // Substitua pelo nome da tabela real

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n=== Lista de Usuários ===");
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome_usuario");
                String sobrenome = rs.getString("sobrenome_usuario");
                String cpf = rs.getString("cpf_usuario");
                String email = rs.getString("email_usuario");
                String senha = rs.getString("senha_usuario");
                String telefone = rs.getString("telefone_usuario");
                String tipoUsuario = rs.getString("id_tipo_usuario");
                System.out.printf("ID: %d | Nome: %s | Sobrenome: %s | CPF: %s | E-mail: %s | Senha: %s | Telefone: %s | Tipo Usuario: %s%n", id, nome, sobrenome, cpf, email, senha, telefone, tipoUsuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar Usuários: " + e.getMessage());
        }
    }

    private static void executeSpInserirUsuario(String nomeUsuario, String sobrenomeUsuario, String cpfUsuario, String emailUsuario,
                                                String senhaUsuario, String telefoneUsuario, int idTipoUsuario) throws SQLException {
        String sql = "{CALL sp_inserir_usuario_energylink(?, ?, ?, ?, ?, ?, ?)}";

        try (var connection = DatabaseConnection.getConnection();
             var stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nomeUsuario);
            stmt.setString(2, sobrenomeUsuario);
            stmt.setString(3, cpfUsuario);
            stmt.setString(4, emailUsuario);
            stmt.setString(5, senhaUsuario);
            stmt.setString(6, telefoneUsuario);
            stmt.setInt(7, idTipoUsuario);
            stmt.execute();
            System.out.println("Usuário inserido com sucesso!");
        }
    }
}
