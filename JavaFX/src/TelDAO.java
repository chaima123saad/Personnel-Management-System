import java.util.List;

public interface TelDAO {
    void add(NumTel numero);
    NumTel findById(int id);
    List<NumTel> findAll();
}
