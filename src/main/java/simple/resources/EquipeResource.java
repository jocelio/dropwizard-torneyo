package simple.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import simple.api.Equipe;
import simple.api.Player;
import simple.db.EquipesDAO;
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
@Path("equipe")
@Produces(MediaType.APPLICATION_JSON)
public class EquipeResource {

    private EquipesDAO dao;

    public EquipeResource(EquipesDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Equipe> findAll() {
        return dao.findAll();
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Equipe findPerson(@PathParam("id") LongParam id) {
        return dao.findById(id.get());
    }
}
