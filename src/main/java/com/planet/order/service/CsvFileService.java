package com.planet.order.service;

import com.planet.order.dao.OrderDao;
import org.apache.commons.csv.CSVRecord;

import java.util.concurrent.CompletableFuture;

public interface CsvFileService {
    CompletableFuture<OrderDao> processOrder(CSVRecord csvRecord);
}
