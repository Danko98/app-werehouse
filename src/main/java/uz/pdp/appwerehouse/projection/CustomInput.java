package uz.pdp.appwerehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.entity.Input;
import uz.pdp.appwerehouse.entity.Supplier;
import uz.pdp.appwerehouse.entity.Warehouse;

import java.sql.Timestamp;

@Projection(types = Input.class)
public interface CustomInput {
    Integer getId();

    Timestamp getDate();

    Warehouse getWarehouse();

    Supplier getSupplier();

    Currency getCurrency();

    String getFactureNumber();


}
