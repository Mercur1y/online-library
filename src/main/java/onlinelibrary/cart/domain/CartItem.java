package onlinelibrary.cart.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CartItem extends ListItem {
    private int quantity;
}
