package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.converter.BasketConverter;
import com.ebuozturk.ecommerce.dto.basket.BasketDto;
import com.ebuozturk.ecommerce.exception.BasketNotFoundException;
import com.ebuozturk.ecommerce.exception.NoProductsInStockException;
import com.ebuozturk.ecommerce.model.Basket;
import com.ebuozturk.ecommerce.model.BasketProduct;
import com.ebuozturk.ecommerce.model.Product;
import com.ebuozturk.ecommerce.model.User;
import com.ebuozturk.ecommerce.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BasketService {
    private final BasketRepository repository;
    private final BasketConverter converter;
    private final ProductService productService;
    private final BasketProductService basketProductService;

    public BasketService(BasketRepository repository, BasketConverter converter, ProductService productService, BasketProductService basketProductService) {
        this.repository = repository;
        this.converter = converter;
        this.productService = productService;
        this.basketProductService = basketProductService;
    }

    public List<BasketDto> getAllBaskets(){
        return converter.convert(repository.findAll());
    }
    public BasketDto getBasketById(Long id){
        return converter.convert(findById(id));
    }
    public BasketDto getBasketByUserId(Long id){
        return converter.convert(findByUserId(id));
    }
    public BasketDto createBasket(User user){

        return converter.convert(repository.save(new Basket(user, new HashSet<BasketProduct>())));
    }
    public BasketDto addProductToBasket(Long userId, Long productId){
        Basket basket = findByUserId(userId);

        Product product = productService.findById(productId);

        if(basketProductService.isBasketProductExistByProductIdAndBasketId(productId,basket.getId())){

            BasketProduct basketProduct = basketProductService.findByProductIdAndBasketId(productId, basket.getId());
            basketProductService.increaseQuantityByProductId(basketProduct.getId());
            return getBasketById(basket.getId());
        }

        else{

            if(product.getUnitsInStock() > 0) {
                basketProductService.createBasketProduct(new BasketProduct(product, basket));
                return converter.convert(basket);
            }
            else{
                throw new NoProductsInStockException("There are not enough products in stock!");
            }
        }

    }

    public BasketDto removeProductFromBasket(Long userId, Long productId){
        Basket basket = findByUserId(userId);
        BasketProduct basketProduct = basketProductService.findByProductIdAndBasketId(productId,basket.getId());
        basketProductService.deleteBasketProduct(basketProduct.getId());
        return getBasketById(basket.getId());
    }
    protected Double calculateTotalPrice(Basket basket){

        return basket.getProducts().stream().mapToDouble(i -> i.getProduct().getUnitPrice()*i.getQuantity()).sum();
    }
    protected Basket findByUserId(Long id) {
        return repository.findByUser_id(id).orElseThrow(()-> new BasketNotFoundException("Basket couldn't be found by following user id: "+id));
    }

    protected Basket findById(Long id){
        return repository.findById(id).orElseThrow(()-> new BasketNotFoundException("Basket couldn't be found by following id: "+id));
    }

}
