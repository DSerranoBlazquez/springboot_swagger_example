package guru.springframework.services;


import java.util.List;

import guru.springframework.domain.Product;

public interface IProductService {
    List<Product> getAll();

    Product getById(Long id);

    Product save(Product product);

    void delete(Long id);

	Product update(Long id, Product product);

	Product create(Product product);
}
