package com.retail.com.retail.service;

import com.retail.BillingTestDataBuilder;
import com.retail.model.bill.Invoice;
import com.retail.service.BillCalculationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBillCalculationService {

    BillCalculationService billCalculationService;
    Invoice simpleInvoice, employeeInvoice, employeeLoyaltyInvoice, affiliateLoyalityInvoice, affiliateInvoiceNoLoyalty, customerInvoiceNoLoyalty, customerInvoiceWithLoyality;

    @Before
    public void setUp(){
        billCalculationService = new BillCalculationService();
        simpleInvoice = BillingTestDataBuilder.getInvoice();
        employeeInvoice = BillingTestDataBuilder.getEployeeDiscountTestData();
        employeeLoyaltyInvoice = BillingTestDataBuilder.getEployeeDiscountTestDataWithLoyalty();
        affiliateLoyalityInvoice = BillingTestDataBuilder.getAffiliateDiscountTestDataWithLoyalty();
        affiliateInvoiceNoLoyalty = BillingTestDataBuilder.getAffiliateDiscountTestDataNoLoyalty();
        customerInvoiceNoLoyalty = BillingTestDataBuilder.getCustomerDiscountTestDataNoLoyalty();
        customerInvoiceWithLoyality = BillingTestDataBuilder.getCustomerDiscountTestDataWithLoyalty();
    }

    /**
     * Simple Test to check core billing logic execution
     */
    @Test
    public void testInvoice(){
        simpleInvoice = billCalculationService.calculateBill(simpleInvoice);
        Assert.assertNotNull(simpleInvoice);
    }

    /**
     * Test  Employee Discount
     */
    @Test
    public void testEmployeeDiscount(){
        employeeInvoice = billCalculationService.calculateBill(employeeInvoice);
        Assert.assertNotNull(employeeInvoice);
        Assert.assertTrue(employeeInvoice.getTotalAmount() == 271);
    }

    /**
     * Test Employee who is loyal customer for more than 2+ years
     */

    @Test
    public void testEmployeeCustomerWithLoyalty() {
        employeeLoyaltyInvoice = billCalculationService.calculateBill(employeeLoyaltyInvoice);
        Assert.assertNotNull(employeeLoyaltyInvoice);
        Assert.assertTrue(employeeLoyaltyInvoice.getTotalAmount() == 122);
    }
    /**
     * Test Affiliate discount
     */
    @Test
    public void testAffiliateCustomerNoLoyalty() {
        affiliateInvoiceNoLoyalty = billCalculationService.calculateBill(affiliateInvoiceNoLoyalty);
        Assert.assertNotNull(affiliateInvoiceNoLoyalty);
        Assert.assertTrue(affiliateInvoiceNoLoyalty.getTotalAmount() == 144);
    }

    /**
     * Test Affiliate customer who is customer more than 2 years
     * No loyality discount applied as affiliate got 10 % discount
     */
    @Test
    public void testAffiliateCustomerWithLoyalty() {
        affiliateLoyalityInvoice = billCalculationService.calculateBill(affiliateLoyalityInvoice);
        Assert.assertNotNull(affiliateLoyalityInvoice);
        Assert.assertTrue(affiliateLoyalityInvoice.getTotalAmount() == 144);
    }
    /**
     * Test normal customer who is member for more than 2 years
     * Gets loyality discount of 5 %
     */
    @Test
    public void testNormalCustomerWithLoyalty() {
        customerInvoiceWithLoyality = billCalculationService.calculateBill(customerInvoiceWithLoyality);
        Assert.assertNotNull(customerInvoiceWithLoyality);
        Assert.assertTrue(customerInvoiceWithLoyality.getTotalAmount() == 149.5);
    }
    /**
     *  Test normal customer who is memeber for less than 2 years
     *  Loyality discount (5%) not applied
     */
    @Test
    public void testNormalCustomerNoLoyalty() {
        customerInvoiceNoLoyalty = billCalculationService.calculateBill(customerInvoiceNoLoyalty);
        Assert.assertNotNull(customerInvoiceNoLoyalty);
        Assert.assertTrue(customerInvoiceNoLoyalty.getTotalAmount() == 155);
    }


}
