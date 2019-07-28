package com.retail.com.retail.service;

import com.retail.BillingTestDataBuilder;
import com.retail.model.bill.Invoice;
import com.retail.service.BillCalculationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBillCalculationService {

    BillCalculationService billCalculationService;
    Invoice simpleInvoice, employeeInvoice, employeeLoyaltyInvoice, affiliateLoyalityInvoice, customerInvoiceNoLoyalty, customerInvoiceWithLoyality;

    @Before
    public void setUp(){
        billCalculationService = new BillCalculationService();
        simpleInvoice = BillingTestDataBuilder.getInvoice();
        employeeInvoice = BillingTestDataBuilder.getEployeeDiscountTestData();
        employeeLoyaltyInvoice = BillingTestDataBuilder.getEployeeDiscountTestDataWithLoyalty();
        affiliateLoyalityInvoice = BillingTestDataBuilder.getAffiliateDiscountTestDataWithLoyalty();
        customerInvoiceNoLoyalty = BillingTestDataBuilder.getCustomerDiscountTestDataNoLoyalty();
        customerInvoiceWithLoyality = BillingTestDataBuilder.getCustomerDiscountTestDataWithLoyalty();
    }
    @Test
    public void testInvoice(){
        simpleInvoice = billCalculationService.calculateBill(simpleInvoice);
        Assert.assertNotNull(simpleInvoice);
    }

    @Test
    public void testEmployeeDiscount(){
        employeeInvoice = billCalculationService.calculateBill(employeeInvoice);
        Assert.assertNotNull(employeeInvoice);
        Assert.assertTrue(employeeInvoice.getTotalAmount() == 271);
    }

    @Test
    public void testEmployeeCustomerWithLoyalty() {
        employeeLoyaltyInvoice = billCalculationService.calculateBill(employeeLoyaltyInvoice);
        Assert.assertNotNull(employeeLoyaltyInvoice);
        Assert.assertTrue(employeeLoyaltyInvoice.getTotalAmount() == 122);
    }

    @Test
    public void testAffiliateCustomerNoLoyalty() {
        customerInvoiceNoLoyalty = billCalculationService.calculateBill(customerInvoiceNoLoyalty);
        Assert.assertNotNull(customerInvoiceNoLoyalty);
        Assert.assertTrue(customerInvoiceNoLoyalty.getTotalAmount() == 155);
    }

    @Test
    public void testAffiliateCustomerWithLoyalty() {
        customerInvoiceWithLoyality = billCalculationService.calculateBill(customerInvoiceWithLoyality);
        Assert.assertNotNull(customerInvoiceWithLoyality);
        Assert.assertTrue(customerInvoiceWithLoyality.getTotalAmount() == 149.5);
    }



}
