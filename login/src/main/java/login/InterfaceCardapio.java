package login;

import java.time.LocalDate;
import java.util.Scanner;
import Database.CardapioDAO;
import Domain.Cardapio;

public class InterfaceCardapio implements Interface {

    private Scanner reader;
    private CardapioDAO persister;

    public InterfaceCardapio() {
        reader = new Scanner(System.in);
        persister = new CardapioDAO();
    }

    @Override
    public void start() {
        System.out.println(
            "Select a method to test:\n\n"
            + "1. Create\n"
            + "2. Read\n"
            + "3. ReadAll\n"
            + "4. Update\n"
            + "5. Delete\n"
            + "6. Exit\n"
        );
        System.out.print("Type your option: ");

        Integer option = reader.nextInt();
        reader.nextLine();

        ProcessOption(option);

        reader.close();
    }

    private void ProcessOption(Integer option) {
        switch (option) {
            case 1:
                promptCreate();
                start();
            break;
            case 2:
                promptRead();
                start();
            break;
            case 3:
                promptReadAll();
                start();
            break;
            case 4:
                promptUpdate();
                start();
            break;
            case 5:
                promptDelete();
                start();
            break;
            case 6:
                System.out.println("Bye!");
            break;
            default:
                System.out.println("Try again");
                start();
            break;
        }
    }

    private void promptCreate() {
        System.out.print("\tRefeicaoID: ");
        Integer refeicaoID = Integer.parseInt(reader.nextLine());

        System.out.print("\tData: ");
        LocalDate data = LocalDate.parse(reader.nextLine());

        System.out.print("\tDescricao: ");
        String descricao = reader.nextLine();

        Cardapio cardapio = new Cardapio();
        cardapio.refeicaoID = refeicaoID;
        cardapio.data = data;
        cardapio.descricao = descricao;

        persister.create(cardapio);
    }

    private void promptRead() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        Cardapio cardapio = persister.read(id);
        
        printCardapio(cardapio);
    }

    private void promptReadAll() {
        System.out.println("Not implemented");
    }

    private void promptUpdate() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        Cardapio cardapio = persister.read(id);

        System.out.print("\tNovo RefeicaoID: ");
        Integer refeicaoID = reader.nextInt();
        reader.nextLine();

        System.out.print("\tNovo Data: ");
        LocalDate data = LocalDate.parse(reader.nextLine());

        System.out.print("\tNovo Descricao: ");
        String descricao = reader.nextLine();

        cardapio.refeicaoID = refeicaoID;
        cardapio.data = data;
        cardapio.descricao = descricao;
        
        persister.update(cardapio);
    }

    private void promptDelete() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        persister.delete(id);
    }

    private void printCardapio(Cardapio cardapio) {
        System.out.println(
            "This is Cardapio["+cardapio.id+"]:\n\n"
            + "Refeicao ID: "+cardapio.refeicaoID+"\n"
            + "Data: "+cardapio.data+"\n"
            + "Descrição: "+cardapio.descricao+"\n"
        );
    }
}
