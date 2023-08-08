package ru.netology.Exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepoTest {

    Product product1 = new Product(6, "бумага", 600);
    Product product2 = new Product(73, "мыло", 80);
    Product product3 = new Product(93, "масло", 200);


    @Test
    public void removeExistingElement() {

        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);

        shopRepository.removeById(93);

        Product[] expected = {product1, product2};
        Product[] actual = shopRepository.findAll();

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void notFoundExceptionTest() {

        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            shopRepository.removeById(400);
        });
    }

    @Test
    public void addProduct() {

        ShopRepository shopRepository = new ShopRepository();

        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = shopRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addProductWithDuplicateId() {

        ShopRepository shopRepository = new ShopRepository();

        Product product4 = new Product(93, "масло", 200);

        shopRepository.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            shopRepository.add(product4);
        });
    }

}
