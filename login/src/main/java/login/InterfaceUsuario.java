package login;

import java.util.Scanner;

import Database.UsuarioDAO;
import Domain.TipoUsuario;
import Domain.Usuario;

public class InterfaceUsuario implements Interface {

    private Scanner reader;
    private UsuarioDAO persister;

    public InterfaceUsuario() {
        reader = new Scanner(System.in);
        persister = new UsuarioDAO();
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

        System.out.print("\tRA: ");
        String ra = reader.nextLine();

        System.out.print("\tLogin: ");
        String login = reader.nextLine();

        System.out.print("\tSenha: ");
        String senha = reader.nextLine();

        System.out.print("\tTipo: ");
        String tipo = reader.nextLine();

        Usuario usuario = new Usuario();
        usuario.nome = nome;
        usuario.ra = ra;
        usuario.login = login;
        usuario.senha = senha;
        usuario.tipo = TipoUsuario.valueOf(tipo);
        persister.create(usuario);
    }

    private void promptRead() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        Usuario usuario = persister.read(id);
        
        printUsuario(usuario);
    }

    private void promptReadAll() {
        System.out.println("Not implemented");
    }

    private void promptUpdate() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        Usuario usuario = persister.read(id);

        System.out.print("\tNovo Nome: ");
        String nome = reader.nextLine();

        System.out.print("\tNovo RA: ");
        String ra = reader.nextLine();

        System.out.print("\tNovo Login: ");
        String login = reader.nextLine();

        System.out.print("\tNova Senha: ");
        String senha = reader.nextLine();

        System.out.print("\tNovo Tipo: ");
        String tipo = reader.nextLine();

        usuario.nome = nome;
        usuario.ra = ra;
        usuario.login = login;
        usuario.senha = senha;
        usuario.tipo = TipoUsuario.valueOf(tipo);
        persister.update(usuario);
    }

    private void promptDelete() {
        System.out.print("ID: ");
        Integer id = reader.nextInt();
        reader.nextLine();

        persister.delete(id);
    }

    private void printUsuario(Usuario usuario) {
        System.out.println(
            "This is Usuario["+usuario.id+"]:\n\n"
            + "Nome: "+usuario.nome+"\n"
            + "RA: "+usuario.ra+"\n"
            + "Login: "+usuario.login+"\n"
            + "Senha: "+usuario.senha+"\n"
            + "Tipo: "+usuario.tipo+"\n"
        );
    }
}