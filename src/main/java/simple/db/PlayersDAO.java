package simple.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import simple.api.Player;

import java.util.List;

/**
 * Created by jocelio on 02/11/17.
 */
public class PlayersDAO extends AbstractDAO<Player> {
    public PlayersDAO(SessionFactory factory) {
        super(factory);
    }

    public Player findById(Long id) {
        return get(id);
    }

    public long create(Player person) {
        return persist(person).getId();
    }

    public List<Player> findAll() {
        return list(namedQuery("Player.findAll"));
    }
}