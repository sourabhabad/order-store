package com.planet.order.service.impl;

import com.planet.order.dao.OrderDao;
import com.planet.order.model.Country;
import com.planet.order.service.CsvFileService;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.planet.order.constant.Constants.*;

@Service
public class CsvFileServiceImpl implements CsvFileService {

    private final List<Country> countries;

    public CsvFileServiceImpl() {
        countries = new LinkedList<>();
        countries.add(new Country(COUNTRY_CAMEROON, PATTERN_CAMEROON));
        countries.add(new Country(COUNTRY_ETHIOPIA, PATTERN_ETHIOPIA));
        countries.add(new Country(COUNTRY_MOROCCO, PATTERN_MOROCCO));
        countries.add(new Country(COUNTRY_MOZAMBIQUE, PATTERN_MOZAMBIQUE));
        countries.add(new Country(COUNTRY_UGANDA, PATTERN_UGANDA));
    }


    /**
     * Async method to prepare OrderDao and process logic to get Country name by mobile no
     *
     * @param csvRecord CSVRecord
     **/
    @Async
    @Override
    public CompletableFuture<OrderDao> processOrder(CSVRecord csvRecord) {
        OrderDao orderDao = new OrderDao();
        CompletableFuture<OrderDao> completableFuture = new CompletableFuture<>();
        orderDao.setOrderId(Long.valueOf(csvRecord.get(HEADER_ID).trim()));
        orderDao.setEmail(csvRecord.get(HEADER_EMAIL));
        orderDao.setCountryName(classifyCountry(csvRecord.get(HEADER_PHONE_NUMBER).trim()));
        orderDao.setPhoneNo(csvRecord.get(HEADER_PHONE_NUMBER).trim());
        orderDao.setParcelWeight(csvRecord.get(HEADER_PARCEL_WEIGHT).trim());
        completableFuture.complete(orderDao);
        return completableFuture;
    }


    /**
     * Logic to get Country name by phoneNo
     *
     * @param phoneNumber Phone number form csv file
     **/
    public String classifyCountry(String phoneNumber) {
        for (Country country : countries) {
            if (country.pattern().matcher(phoneNumber).matches()) {
                return country.name();
            }
        }
        return null;
    }

}
