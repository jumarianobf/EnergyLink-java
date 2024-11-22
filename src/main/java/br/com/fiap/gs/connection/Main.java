package br.com.fiap.gs.connection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            printEnergyLink();

            while (running) {
                System.out.println("------------------------------------------------------");
                System.out.println("=== Menu Principal ===");
                System.out.println("1 - Gerenciar Usuário");
                System.out.println("2 - Gerenciar Tipo de Usuário");
                System.out.println("3 - Gerenciar País");
                System.out.println("4 - Gerenciar Estado");
                System.out.println("5 - Gerenciar Cidade");
                System.out.println("6 - Gerenciar Localização");
                System.out.println("7 - Gerenciar Entidade Base");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> GerenciarUsuario.menu(scanner);
                    case 2 -> GerenciarTipoUsuario.menu(scanner);
                    case 3 -> GerenciarPais.menu(scanner);
                    case 4 -> GerenciarEstado.menu(scanner);
                    case 5 -> GerenciarCidade.menu(scanner);
                    case 6 -> GerenciarLocalizacao.menu(scanner);
                    case 7 -> GerenciarEntidadeBase.menu(scanner);
                    case 0 -> {
                        System.out.println("Saindo do sistema... Até logo!");
                        running = false;
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }

    private static void printEnergyLink() {
        System.out.println("\n\n" +
                "  EEEEE  N   N  EEEEE  RRRR    GGGG  Y   Y    L       III  N   N  K   K \n" +
                "  E      NN  N  E      R   R  G       Y Y     L        I   NN  N  K  K  \n" +
                "  EEEE   N N N  EEEE   RRRR   G  GG    Y      L        I   N N N  KKK   \n" +
                "  E      N  NN  E      R  R   G   G    Y      L        I   N  NN  K  K  \n" +
                "  EEEEE  N   N  EEEEE  R   R   GGGG    Y      LLLLL   III  N   N  K   K \n"
        );
    }
}
