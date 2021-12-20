package uz.pdp.appwerehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwerehouse.entity.User;
import uz.pdp.appwerehouse.entity.Warehouse;

import java.util.Set;

@Projection(types = User.class)
public interface CustomUser {

    Integer getId();

    String getFirstName();

    String getLastName();

    String getPassword();

    Set<Warehouse> getWarehouses();

    boolean getActive();

}
