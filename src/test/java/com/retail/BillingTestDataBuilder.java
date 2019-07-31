package com.retail;


import com.retail.model.bill.Invoice;
import com.retail.model.bill.Product;
import com.retail.model.bill.ProductType;
import com.retail.model.customer.Affiliate;
import com.retail.model.customer.Customer;
import com.retail.model.customer.Employee;
import com.retail.model.customer.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		product.setProductName("CHOCOLATE KITKAT");
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
        User ram = new Employee();
        Invoice simpleInvoice = new Invoice();
        simpleInvoice = new Invoice(ram, productList, 0, false);
        return simpleInvoice;
    }

    public static Invoice getEployeeDiscountTestDataWithLoyalty() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(getGroceryProduct());
        productList.add(getNonGroceryProduct());
        Customer ram = new Employee(LocalDate.of(2016, 8, 23));
        Invoice simpleInvoiceLoyalty = new Invoice();
        simpleInvoiceLoyalty = new Invoice(ram, productList, 0, false);
        return simpleInvoiceLoyalty;
    }

    public static Invoice getAffiliateDiscountTestDataWithLoyalty() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(getGroceryProduct());
        productList.add(getNonGroceryProduct());
        Customer ram = new Affiliate(LocalDate.of(2014, 8, 23));
        Invoice affiliateInvoiceLoyalty = new Invoice();
        affiliateInvoiceLoyalty = new Invoice(ram, productList, 0, false);
        return affiliateInvoiceLoyalty;
    }

    public static Invoice getAffiliateDiscountTestDataNoLoyalty() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(getGroceryProduct());
        productList.add(getNonGroceryProduct());
        Customer ram = new Affiliate(LocalDate.of(2018, 8, 23));
        Invoice affiliateInvoiceNoLoyalty = new Invoice(ram, productList, 0, false);
        return affiliateInvoiceNoLoyalty;
    }

    public static Invoice getCustomerDiscountTestDataNoLoyalty() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(getGroceryProduct());
        productList.add(getNonGroceryProduct());
        Customer ram = new Customer(LocalDate.of(2018, 8, 23));
        Invoice customerInvoiceNoLoyalty = new Invoice();
        customerInvoiceNoLoyalty = new Invoice(ram, productList, 0, false);
        return customerInvoiceNoLoyalty;
    }

    public static Invoice getCustomerDiscountTestDataWithLoyalty() {
        List<Product> productList = new ArrayList<Product>();
        productList.add(getGroceryProduct());
        productList.add(getNonGroceryProduct());
        Customer ram = new Customer(LocalDate.of(2015, 8, 23));
        Invoice customerInvoiceWithLoyalty = new Invoice();
        customerInvoiceWithLoyalty = new Invoice(ram, productList, 0, false);
        return customerInvoiceWithLoyalty;
    }


}
