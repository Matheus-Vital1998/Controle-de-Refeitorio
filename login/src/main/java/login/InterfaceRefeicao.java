package login;

import java.time.LocalTime;
import java.util.Scanner;
import Database.RefeicaoDAO;
import Domain.Refeicao;

public class InterfaceRefeicao implements Interface {

    private Scanner reader;
    private RefeicaoDAO persister;

    public InterfaceRefeicao() {
        reader = new Scanner(System.in);
        persister = new RefeicaoDAO();
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
        System.out.print("\tNome: ");
        String nome = reader.nextLine();

        System.out.print("\tHorarioInicio: ");
        LocalTime horarioInicio = LocalTime.parse(reader.nextLine());

        System.out.print("\tHorarioFim: ");
        LocalTime horarioFim = LocalTime.parse(reader.nextLine());

        System.out.print("\tHorarioLimiteReserva: ");
        LocalTime horarioLimiteReserva = LocalTime.parse(reader.nextLine());

        Refeicao refeicao = new Refeicao();
        refeicao.nome = nome;
        refeicao.horarioInicio = horarioInicio;
        refeicao.horarioFim = horarioFim;
        refeicao.horarioLimiteReserva = horarioLimiteReserva;
        persister.create(refeicao);
    }

    private void promptRead() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        Refeicao refeicao = persister.read(id);
        
        printRefeicao(refeicao);
    }

    private void promptReadAll() {
        System.out.println("Not implemented");
    }

    private void promptUpdate() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        Refeicao refeicao = persister.read(id);

        System.out.print("\tNovo Nome: ");
        String nome = reader.nextLine();

        System.out.print("\tNovo Horario Inicio: ");
        LocalTime horarioInicio = LocalTime.parse(reader.nextLine());

        System.out.print("\tNovo Horario Fim: ");
        LocalTime horarioFim = LocalTime.parse(reader.nextLine());

        System.out.print("\tNova Horario Limite Reserva: ");
        LocalTime horarioLimiteReserva = LocalTime.parse(reader.nextLine());

        refeicao.nome = nome;
        refeicao.horarioInicio = horarioInicio;
        refeicao.horarioFim = horarioFim;
        refeicao.horarioLimiteReserva = horarioLimiteReserva;
        persister.update(refeicao);
    }

    private void promptDelete() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        persister.delete(id);
    }

    private void printRefeicao(Refeicao refeicao) {
        System.out.println(
            "This is Refeicao["+refeicao.id+"]:\n\n"
            + "Nome: "+refeicao.nome+"\n"
            + "Horario Inicio: "+refeicao.horarioInicio+"\n"
            + "Horario Fim: "+refeicao.horarioFim+"\n"
            + "Horario Limite Reserva: "+refeicao.horarioLimiteReserva+"\n"
        );
    }
}