package com.marcobehler.myfancypdfinvoices.service;

import com.marcobehler.myfancypdfinvoices.model.Invoice;
import com.marcobehler.myfancypdfinvoices.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {
    private final UserService userService;
    private List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    public List<Invoice> findAll(){
        return this.invoices;
    }

    public Invoice create(String userId, Integer amount){
        User user = userService.findById(userId);

        if(user == null){
            throw new IllegalStateException();
        }

        Invoice invoice = new Invoice(userId,amount,"http://www.africau.edu/images/default/sample.pdf");
        this.invoices.add(invoice);
        return invoice;
    }
}
