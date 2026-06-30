package com.camel.stock.placeholder;

import java.util.List;

import com.camel.common.object.BaseObject;
import com.camel.person.valueholders.Person;
import com.camel.product.valueholders.Product;
import com.camel.product.valueholders.Shop;

public class StockBought extends BaseObject{
    private int id;
    private Product product;
    private Person seller;
    private  List<Shop> shops;
    
}
