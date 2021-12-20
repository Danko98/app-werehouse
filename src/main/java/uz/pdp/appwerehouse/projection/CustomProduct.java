package uz.pdp.appwerehouse.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.appwerehouse.entity.*;

@Projection(types = Product.class)
public interface CustomProduct {

    Integer getId();

    String getName();

    boolean getActive();

    Category getCategory();

    Attachment getPhoto();

    Measurement getMeasurement();
}
