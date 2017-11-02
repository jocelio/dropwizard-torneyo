package simple.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import simple.api.Player;
import simple.api.Saying;
import simple.db.PlayersDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by jocelio on 02/11/17.
 */
@Path("player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    private PlayersDAO dao;

    public PlayerResource(PlayersDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Player> findAll() {
        return dao.findAll();
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Player findPerson(@PathParam("id") LongParam id) {
        return dao.findById(id.get());
    }
}
