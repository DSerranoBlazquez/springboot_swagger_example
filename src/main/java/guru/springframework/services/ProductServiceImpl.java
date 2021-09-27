package guru.springframework.services;

import guru.springframework.domain.Product;
import guru.springframework.exceptions.ElementoNoExistente;
import guru.springframework.exceptions.ElementoYaExistente;
import guru.springframework.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
   
    @Autowired
    private ProductRepository productRepository;
    
    //
    //public ProductServiceImpl (ProductRepository productRepository){
    //this.productRepository = productRepository;
    //

 //   @Autowired
   // public void setProductRepository(ProductRepository productRepository) {
  //      this.productRepository = productRepository;
   // }

    @Override
    public List<Product> getAll() {
        logger.debug("listAllProducts called");
        return productRepository.findAll();
    }
    
    public Page<Product>getByPage (Integer size, Integer page){
    	Pageable pageable = PageRequest.of(page, size);
    	return productRepository.findAll(pageable);
    }

    @Override
    public Product getById(Long id) {
    	logger.debug("getProductById called");
        Optional<Product> result = productRepository.findById(id);
        if(!result.isPresent()) {
        	logger.debug("No se encuentra el producto");
        }
        return result.orElse(null);
    }

    @Override
    public Product create(Product product) {
        if(product.getId() != null) {
        	throw new ElementoYaExistente();
        }
        product.setId(null);
        logger.debug("saveProduct called");
        return productRepository.save(product);
    }
    
    @Override
    public Product update(Long id ,Product product) {
        if(id == null) {
        	throw new ElementoNoExistente();
        }
        if(!productRepository.existsById(id)) {
        	throw new ElementoNoExistente();
        }
       product.setId(id);   
       logger.debug("saveProduct called");
       return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        if(id == null) {
        	throw new ElementoNoExistente();
        }
        if(!productRepository.existsById(id)) {
        	throw new ElementoNoExistente();
        }
    	logger.debug("deleteProduct called");
        productRepository.deleteById(id);
    }

	@Override
	public Product save(Product product) {
        logger.debug("saveProduct called");
		return productRepository.save(product);
	}

}
