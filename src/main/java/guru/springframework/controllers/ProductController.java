package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.exceptions.ElementoNoExistente;
import guru.springframework.exceptions.ElementoYaExistente;
import guru.springframework.services.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class ProductController {

	@Autowired
    private IProductService productService;

	 // @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
	@GetMapping("/")
	public List<Product> getAll(){
	       List<Product> productList = productService.getAll();
	        return productList;
	    }
	
	@GetMapping("/{id}")
	public Product getById(Long id){
	        return productService.getById(id);
	    }
	
	@PostMapping("/")
	@ExceptionHandler({ElementoYaExistente.class})
	public void create(@RequestBody Product product)	{
		productService.create(product);
	}
	
	@PutMapping("/{id}")
	@ExceptionHandler({ElementoNoExistente.class})
	public void update(@PathVariable Long id, @RequestBody Product product)	{
		productService.update(id, product);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id)	{
		productService.delete(id);
	}
/*
	@ApiOperation(value = "View a list of available products",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Product> list(Model model){
        Iterable<Product> productList = productService.listAllProducts();
        return productList;
    }
    @ApiOperation(value = "Search a product with an ID",response = Product.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public Product showProduct(@PathVariable Integer id, Model model){
       Product product = productService.getProductById(id);
        return product;
    }

    @ApiOperation(value = "Add a product")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a product")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product){
        Product storedProduct = productService.getProductById(id);
        storedProduct.setDescription(product.getDescription());
        storedProduct.setImageUrl(product.getImageUrl());
        storedProduct.setPrice(product.getPrice());
        productService.saveProduct(storedProduct);
        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        productService.delete(id);
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);

    }
*/
}
