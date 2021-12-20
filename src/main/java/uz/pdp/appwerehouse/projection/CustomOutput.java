package uz.pdp.appwerehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwerehouse.entity.Client;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.entity.Output;
import uz.pdp.appwerehouse.entity.Warehouse;

import java.sql.Timestamp;

@Projection(types = Output.class)
public interface CustomOutput {
    Integer getId();

    Timestamp getDate();

    Warehouse getWarehouse();

    Client getClient();

    Currency getCurrency();

    String getFactureNumber();


}
