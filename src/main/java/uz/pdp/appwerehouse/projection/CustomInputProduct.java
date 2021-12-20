package uz.pdp.appwerehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwerehouse.entity.Input;
import uz.pdp.appwerehouse.entity.InputProduct;
import uz.pdp.appwerehouse.entity.Product;

import java.util.Date;

@Projection(types = InputProduct.class)
public interface CustomInputProduct {

    Integer getId();

    Product getProduct();

    Double getAmount();

    Double getPrice();

    Input getInput();

    Date getExpireDate();

}
