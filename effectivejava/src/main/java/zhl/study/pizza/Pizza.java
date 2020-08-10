package zhl.study.pizza;

import java.util.EnumSet;
import java.util.Set;

/**
 * Builder 模式 构造类层次结构
 */
public abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    protected final Set<Topping> toppingSet;

    abstract static class Builder<T extends Builder<T>> {
        protected EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(topping);
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    public Pizza(Builder<?> builder) {
        this.toppingSet = builder.toppings.clone();
    }
}
