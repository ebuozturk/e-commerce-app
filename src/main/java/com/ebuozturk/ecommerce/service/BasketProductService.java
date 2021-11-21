package com.ebuozturk.ecommerce.service;

import com.ebuozturk.ecommerce.converter.BasketProductConverter;
import com.ebuozturk.ecommerce.dto.basketproduct.BasketProductDto;
import com.ebuozturk.ecommerce.exception.BasketProductNotFoundException;
import com.ebuozturk.ecommerce.model.BasketProduct;
import com.ebuozturk.ecommerce.repository.BasketProductRepository;
import org.springframework.stereotype.Service;

@Service
public class BasketProductService {

    private final BasketProductRepository repository;
    private final BasketProductConverter converter;

    public BasketProductService(BasketProductRepository repository, BasketProductConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public BasketProductDto updateBasketProduct(BasketProduct product){
        return converter.convert(repository.save(product));
    }

    public BasketProductDto createBasketProduct(BasketProduct basketProduct) {
        return converter.convert(repository.save(basketProduct));
    }
    public void deleteBasketProduct(String id){
        findById(id);
        repository.deleteById(id);
    }
    public BasketProductDto increaseQuantityByProductId(String id){
        BasketProduct basketProduct = findById(id);
        basketProduct.increaseQuantity();
        return converter.convert(repository.save(basketProduct));
    }

    public BasketProductDto decreaseQuantityByProductId(String id){
        BasketProduct basketProduct = findById(id);
        basketProduct.decreaseQuantity();
        return converter.convert(repository.save(basketProduct));
    }
    protected BasketProduct findById(String id) {
        return repository.findById(id).orElseThrow(()-> new BasketProductNotFoundException("The product of basket couldn't be found by following id: "+id));
    }
    protected BasketProduct findByProductIdAndBasketId(String productId,String basketId){
        return repository.findByProduct_idAndBasket_id(productId,basketId).orElseThrow(()-> new BasketProductNotFoundException("The product of basket couldn't be found" +
                " by following product id: "+productId+" and basket id: "+basketId));

    }
    protected boolean isBasketProductExist(String id){

        return repository.existsBasketProductById(id);
    }
    protected boolean isBasketProductExistByProductIdAndBasketId(String productId,String basketId){
        return repository.existsBasketProductByProduct_IdAndBasket_Id(productId,basketId);
    }
}
