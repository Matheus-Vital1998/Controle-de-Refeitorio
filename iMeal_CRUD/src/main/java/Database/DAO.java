package Database;

public interface DAO<Model> {
    public void create(Model model) throws Exception;
    public Model read(Integer id) throws Exception;
    public void update(Model newModel) throws Exception;
    public void delete(Integer id) throws Exception;
}
