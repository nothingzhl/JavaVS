package zhl.study.pizza;


import org.junit.jupiter.api.Test;

class PizzaTest {

    @Test
    void testPizzaBuilder() {
        Pizza nyPizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.ONION)
                .build();
    }
}