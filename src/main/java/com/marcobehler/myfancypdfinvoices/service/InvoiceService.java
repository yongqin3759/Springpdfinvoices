package com.marcobehler.myfancypdfinvoices.service;

import com.marcobehler.myfancypdfinvoices.model.Invoice;
import com.marcobehler.myfancypdfinvoices.model.User;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {
    private final UserService userService;
    private final String cdnUrl;
    private JdbcTemplate jdbcTemplate;

    private List<Invoice> invoices = new CopyOnWriteArrayList<>();

    public InvoiceService(UserService userService, JdbcTemplate jdbcTemplate, @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Invoice> findAll(){
        return jdbcTemplate.query("SELECT id, user_id, pdf_url, amount from invoices", (resultSet,rowNum)-> {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getObject("id").toString());
            invoice.setPdfUrl(resultSet.getString("pdf_url"));
            invoice.setUserId(resultSet.getString("user_id"));
            invoice.setAmount(resultSet.getInt("amount"));
            return invoice;
        });
    }

    public Invoice create(String userId, Integer amount){
        throw new IllegalStateException("not implemented yet");
        /*User user = userService.findById(userId);

        if(user == null){
            throw new IllegalStateException();
        }

        Invoice invoice = new Invoice(userId, amount, cdnUrl + "/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;*/
    }
}
