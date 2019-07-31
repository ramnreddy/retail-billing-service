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
     * Products:
     *  1. MILK(Grocery) amount : 50
     *  2.CHOCOLATE amount - 110 X added 3 times
     *
     *   Customer is Employee
     *    rules  applied
     *     1. If the user is an employee of the store, he gets a 30% discount  : discount : 99, balance 281
     *     2.If the user is an affiliate of the store, he gets a 10% discount : not applied
     *     3.If the user has been a customer for over 2 years, he gets a 5% discount. :not applied
     *     4.For every $100 on the bill, there is $ 5 discount (e.g. for $ 990, you get $ 45 as a discount). :discount 5$ ,balance 100
     *
     * Expected output is 271
     */
    @Test
    public void testEmployeeDiscount(){
        employeeInvoice = billCalculationService.calculateBill(employeeInvoice);
        Assert.assertNotNull(employeeInvoice);
        Assert.assertTrue(employeeInvoice.getTotalAmount() == 271);
    }

    /**
     * Test Employee who is loyal customer for more than 2+ years
     * Products:
     *  1. MILK(Grocery) amount : 50
     *  2.CHOCOLATE amount - 110 X added 1 time
     *
     *   Customer is Employee
     *    rules  applied
     *     1. If the user is an employee of the store, he gets a 30% discount  : discount : 33, balance 127
     *     2.If the user is an affiliate of the store, he gets a 10% discount : not applied  thought enabled loyality flag
     *     3.If the user has been a customer for over 2 years, he gets a 5% discount. :not applied
     *     4.For every $100 on the bill, there is $ 5 discount ( discount of 5$ out of 127 is 127-5 = 122 (expected output)
     */

    @Test
    public void testEmployeeCustomerWithLoyalty() {
        employeeLoyaltyInvoice = billCalculationService.calculateBill(employeeLoyaltyInvoice);
        Assert.assertNotNull(employeeLoyaltyInvoice);
        Assert.assertTrue(employeeLoyaltyInvoice.getTotalAmount() == 122);
    }
    /**
     * Test Affiliate discount
     *  Customer is Affiliate and Noloyality
     *    Rules  applied
     *     1. If the user is an Affiliate of the store, he gets a 30% discount  : Not applied
     *     2.If the user is an affiliate of the store, he gets a 10% discount : discount : 11, balance 149
     *     3.If the user has been a customer for over 2 years, he gets a 5% discount. : Not applied
     *     4.For every $100 on the bill, there is $ 5 discount (149 - 5 = 144 is expected total amount)
     */
    @Test
    public void testAffiliateCustomerNoLoyalty() {
        affiliateInvoiceNoLoyalty = billCalculationService.calculateBill(affiliateInvoiceNoLoyalty);
        Assert.assertNotNull(affiliateInvoiceNoLoyalty);
        Assert.assertTrue(affiliateInvoiceNoLoyalty.getTotalAmount() == 144);
    }

    /**
     * Test Affiliate customer who is customer more than 2 years
     *  Customer is Affiliate
     *    Rules  applied
     *     1. If the user is an Affiliate of the store, he gets a 30% discount  : Not applied
     *     2.If the user is an affiliate of the store, he gets a 10% discount : discount : 11, balance 149
     *     3.If the user has been a customer for over 2 years, he gets a 5% discount. : Not applied
     *     4.For every $100 on the bill, there is $ 5 discount (149 - 5 = 144 is expected total amount)
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
     *  Customer is Affiliate
     *    Rules  applied
     *     1. If the user is an Affiliate of the store, he gets a 30% discount  : Not applied
     *     2.If the user is an affiliate of the store, he gets a 10% discount : Not Applied
     *     3.If the user has been a customer for over 2 years, he gets a 5% discount : 160 - 5.5 = 154.5
     *     4.For every $100 on the bill, there is $ 5 discount (154.5 - 5 = 149.5 is expected total amount)
     */
    @Test
    public void testNormalCustomerWithLoyalty() {
        customerInvoiceWithLoyality = billCalculationService.calculateBill(customerInvoiceWithLoyality);
        Assert.assertNotNull(customerInvoiceWithLoyality);
        Assert.assertTrue(customerInvoiceWithLoyality.getTotalAmount() == 149.5);
    }
    /**
     *  Test normal customer who is memeber for less than 2 years
     *    /**
     *      Test normal customer who is member for more than 2 years
     *      Gets loyality discount of 5 %
     *      Customer is Affiliate
     *      Rules  applied
     *      1. If the user is an Affiliate of the store, he gets a 30% discount  : Not applied
     *      2.If the user is an affiliate of the store, he gets a 10% discount : Not Applied
     *      3.If the user has been a customer for over 2 years, he gets a 5% discount : Not Applied
     *      4.For every $100 on the bill, there is $ 5 discount : (160 - 5 =  155 is expected total amount)
     */
    @Test
    public void testNormalCustomerNoLoyalty() {
        customerInvoiceNoLoyalty = billCalculationService.calculateBill(customerInvoiceNoLoyalty);
        Assert.assertNotNull(customerInvoiceNoLoyalty);
        Assert.assertTrue(customerInvoiceNoLoyalty.getTotalAmount() == 155);
    }


}
