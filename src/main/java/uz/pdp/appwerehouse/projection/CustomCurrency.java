package uz.pdp.appwerehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwerehouse.entity.Currency;

@Projection(types = Currency.class)
public interface CustomCurrency {
    Integer getId();

    String getName();

    boolean getActive();
}
