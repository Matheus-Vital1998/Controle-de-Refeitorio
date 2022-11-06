package login;

import java.time.LocalDateTime;
import java.util.Scanner;
import Database.ReservaDAO;
import Domain.Reserva;

public class InterfaceReserva implements Interface {

    private Scanner reader;
    private ReservaDAO persister;

    public InterfaceReserva() {
        reader = new Scanner(System.in);
        persister = new ReservaDAO();
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
        System.out.print("\tUsuarioID: ");
        Integer usuarioID = Integer.parseInt(reader.nextLine());

        System.out.print("\tCardapioID: ");
        Integer cardapioID = Integer.parseInt(reader.nextLine());

        System.out.print("\tHorarioChegada: ");
        LocalDateTime horario = LocalDateTime.parse(reader.nextLine());

        Reserva reserva = new Reserva();
        reserva.usuarioID = usuarioID;
        reserva.cardapioID = cardapioID;
        reserva.horario = horario;
        persister.create(reserva);
    }

    private void promptRead() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        Reserva reserva = persister.read(id);
        
        printRefeicao(reserva);
    }

    private void promptReadAll() {
        System.out.println("Not implemented");
    }

    private void promptUpdate() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        Reserva reserva = persister.read(id);

        System.out.print("\tNovo UsuarioID: ");
        Integer usuarioID = reader.nextInt();

        System.out.print("\tNovo CardapioID: ");
        Integer cardapioID = reader.nextInt();
        reader.nextLine();

        System.out.print("\tNovo Horario Reserva: ");
        LocalDateTime horarioReserva = LocalDateTime.parse(reader.nextLine());

        reserva.usuarioID = usuarioID;
        reserva.cardapioID = cardapioID;
        reserva.horario = horarioReserva;
        persister.update(reserva);
    }

    private void promptDelete() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        persister.delete(id);
    }

    private void printRefeicao(Reserva reserva) {
        System.out.println(
            "This is Reserva["+reserva.id+"]:\n\n"
            + "UsuarioID: "+reserva.usuarioID+"\n"
            + "CardapioID: "+reserva.cardapioID+"\n"
            + "Horario Reserva: "+reserva.horario+"\n"
        );
    }
}
