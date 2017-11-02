package simple.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import simple.api.Equipe;
import simple.api.Player;

import java.util.List;

/**
 * Created by jocelio on 02/11/17.
 */
public class EquipesDAO extends AbstractDAO<Equipe> {

    public EquipesDAO(SessionFactory factory) {
        super(factory);
    }

    public Equipe findById(Long id) {
        return get(id);
    }

    public long create(Equipe person) {
        return persist(person).getId();
    }

    public List<Equipe> findAll() {
        return list(namedQuery("Equipe.findAll"));
    }
}