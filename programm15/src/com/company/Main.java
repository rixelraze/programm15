package com.company;

class Main {

    public static void main(String[] args) {
        Clothes[] clothes = {
                new TeeShirt(Sizes.XS, "синий", 213),
                new TeeShirt(Sizes.L, "черный", 543),
                new Trousers(Sizes.M, "голубой", 1000),
                new Trousers(Sizes.XXS, "серый", 225),
                new Skirt(Sizes.S, "розовый", 400),
                new Skirt(Sizes.M, "красный", 423),
                new Tie(Sizes.M, "синий", 300),
                new Tie(Sizes.L, "красный", 990),};

        DressMakingStudio studio = new DressMakingStudio();
        studio.dressMale(clothes);
        System.out.println();
        studio.dressFemale(clothes);
    }

}

enum Sizes {
    XXS(36) {
        @Override
        public String getDescription() {
            return "детский размер";
        }
    }, XS(38), S(40), M(42), L(44);

    Sizes(int euroSize) {
        EuroSize = euroSize;
    }

    public String getDescription() {
        return "взрослый размер";
    }

    @Override
    public String toString() {
        return name() + "(" + EuroSize + ") " + getDescription();
    }

    private final int EuroSize;
}


abstract class Clothes {
    private final Sizes size;
    private final String color;
    private double cost;

    public Clothes(Sizes size, String color) {
        this.size = size;
        this.color = color;
    }

    public Clothes(Sizes size, String color, double cost) {
        this(size, color);
        this.cost = cost;
    }

    public Sizes getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clothes clothes)) return false;

        if (Double.compare(clothes.cost, cost) != 0) return false;
        if (size != clothes.size) return false;
        return color.equals(clothes.color);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = size.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + color.hashCode();
        return result;
    }
}

interface IFemaleClothes {
    default void dressFemale() {
        System.out.println("Одеваем женщину");
    }
}

interface IMaleClothes {
    default void dressMale() {
        System.out.println("Одеваем мужчину");
    }
}


class DressMakingStudio {

    public void dressMale(Clothes[] clothes) {
        System.out.println("Мужская Одежда:");
        for (Clothes clothe : clothes) {
            if (clothe instanceof IMaleClothes) {
                System.out.println(clothe);
            }
        }
    }

    public void dressFemale(Clothes[] clothes) {
        System.out.println("Женская Одежда:");
        for (Clothes clothe : clothes) {
            if (clothe instanceof IFemaleClothes) {
                System.out.println(clothe);
            }
        }
    }
}

class Tie extends Clothes implements IMaleClothes {
    public Tie(Sizes size, String color) {
        super(size, color);
    }

    public Tie(Sizes size, String color, double cost) {
        super(size, color, cost);
    }

    @Override
    public String toString() {
        return "Галстук - " + "размер = " + getSize() + ", цена = " + getCost() + " руб" + ", цвет = " + getColor() + "";
    }
}

class Skirt extends Clothes implements IFemaleClothes {
    public Skirt(Sizes size, String color) {
        super(size, color);
    }

    public Skirt(Sizes size, String color, double cost) {
        super(size, color, cost);
    }

    @Override
    public String toString() {
        return "Юбка - " + "размер = " + getSize() + ", цена = " + getCost() + " руб" + ", цвет = " + getColor() + "}";
    }
}

class Trousers extends Clothes implements IMaleClothes, IFemaleClothes {
    public Trousers(Sizes size, String color) {
        super(size, color);
    }

    public Trousers(Sizes size, String color, double cost) {
        super(size, color, cost);
    }

    @Override
    public String toString() {
        return "Штаны - " + "размер = " + getSize() + ", цена = " + getCost() + " руб" + ", цвет = " + getColor() + "";
    }
}

class TeeShirt extends Clothes implements IMaleClothes, IFemaleClothes {
    public TeeShirt(Sizes size, String color) {
        super(size, color);
    }

    public TeeShirt(Sizes size, String color, double cost) {
        super(size, color, cost);
    }

    @Override
    public String toString() {
        return "Футболка - " + "размер = " + getSize() + ", цена = " + getCost() + " руб" + ", цвет = " + getColor() + "}";
    }
}


