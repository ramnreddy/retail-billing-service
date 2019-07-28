package com.retail;


import com.retail.model.bill.Invoice;
import com.retail.model.bill.Product;
import com.retail.model.bill.ProductType;
import com.retail.model.customer.Affiliate;
import com.retail.model.customer.Employee;
import com.retail.model.customer.User;

import java.util.*;

public class BillingTestDataBuilder {

	private static Product getGroceryProduct() {
		Product product = new Product();
		product.setProductId("GR123");
		product.setProductName("MILK MEIJI");
		product.setProductPrice(50);
		product.setProductType(ProductType.GROCERIES);
		return product;
	}
	
	private static Product getNonGroceryProduct() {
		Product product = new Product();
		product.setProductName("CHOCLOTE KITKAT");
		product.setProductPrice(110);
		product.setProductType(ProductType.NON_GROCERIES);
		return product;
	}

	public static Invoice getInvoice() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(getGroceryProduct());
        productList.add(getNonGroceryProduct());
	    Invoice simpleInvoice = new Invoice();
        simpleInvoice.setProductList(productList);

	    return simpleInvoice;
    }

    public static Invoice getEployeeDiscountTestData() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(getGroceryProduct());
        productList.add(getNonGroceryProduct());
        productList.add(getNonGroceryProduct());
        productList.add(getNonGroceryProduct());
        User raj = new Employee();
        Invoice simpleInvoice = new Invoice();
        simpleInvoice = new Invoice(raj, productList,0);
        return simpleInvoice;
    }
	
	public static Affiliate getAffiliateCustomer() {
		Affiliate affliate1 = new Affiliate("AFT1", "ZEE-Affiliate", true);
		return affliate1;
	}
	

}
