package app;

import java.util.List;
import java.util.Scanner;
import dao.CarroDAO;
import model.Carro;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarroDAO carroDAO = new CarroDAO();
        int opcao;

        do {
            System.out.println("\nMENU");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Excluir");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    List<Carro> carros = carroDAO.getAll();
                    for (Carro c : carros) {
                        System.out.println(c);
                    }
                    break;
                case 2:
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    Carro novoCarro = new Carro(0, marca, modelo, ano);
                    carroDAO.insert(novoCarro);
                    break;
                case 3:
                    System.out.print("ID do carro a excluir: ");
                    int idExcluir = scanner.nextInt();
                    carroDAO.delete(idExcluir);
                    break;
                case 4:
                    System.out.print("ID do carro a atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nova marca: ");
                    String novaMarca = scanner.nextLine();
                    System.out.print("Novo modelo: ");
                    String novoModelo = scanner.nextLine();
                    System.out.print("Novo ano: ");
                    int novoAno = scanner.nextInt();
                    scanner.nextLine();
                    Carro carroAtualizado = new Carro(idAtualizar, novaMarca, novoModelo, novoAno);
                    carroDAO.update(carroAtualizado);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);

        scanner.close();
    }
}