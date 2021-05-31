package jpa1;
import javax.persistence.*;

@Entity
@Table (name = "RestaurantMenu")
public class RestaurantMenu {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")

    private long id;

    @Column (nullable = false)
    private String dishes;
    private int cost;
    private int weigth;
    private Boolean discount;


    public RestaurantMenu(String dishes, int cost, int weigth, Boolean discount) {
        this.dishes = dishes;
        this.cost = cost;
        this.weigth = weigth;
        this.discount = discount;
    }

    public RestaurantMenu() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "RestaurantMenu{" +
                "id=" + id +
                ", dishes='" + dishes + '\'' +
                ", cost=" + cost +
                ", weigth=" + weigth +
                ", discount='" + discount + '\'' +
                '}';
    }
}
