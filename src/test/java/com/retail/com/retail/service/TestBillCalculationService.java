package com.retail.com.retail.service;

import com.retail.BillingTestDataBuilder;
import com.retail.model.bill.Invoice;
import com.retail.service.BillCalculationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBillCalculationService {

    BillCalculationService billCalculationService;
    Invoice simpleInvoice,employeeInvoice;

    @Before
    public void setUp(){
        billCalculationService = new BillCalculationService();
        simpleInvoice = BillingTestDataBuilder.getInvoice();
        employeeInvoice = BillingTestDataBuilder.getEployeeDiscountTestData();
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
        Assert.assertNotNull(employeeInvoice);
    }


}
