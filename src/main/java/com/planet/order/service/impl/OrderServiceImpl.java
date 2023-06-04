package com.planet.order.service.impl;

import com.planet.order.dao.OrderDao;
import com.planet.order.dao.repository.OrderRepository;
import com.planet.order.service.CsvFileService;
import com.planet.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CsvFileService csvFileService;

    OrderServiceImpl(OrderRepository repository, CsvFileService fileService) {
        this.orderRepository = repository;
        this.csvFileService = fileService;

    }

    /**
     * Process CSV file
     *
     * @param file MultipartFile
     */
    @Override
    public String processFile(MultipartFile file) {
        try {
            CSVParser csvParser = new CSVParser(new InputStreamReader(file.getInputStream()), CSVFormat.DEFAULT.withFirstRecordAsHeader());

            List<CompletableFuture<OrderDao>> orderFuture = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                orderFuture.add(csvFileService.processOrder(csvRecord));
            }

            List<OrderDao> orderDaos = orderFuture.stream().map(CompletableFuture::join).toList();
            orderRepository.saveAll(orderDaos);
            csvParser.close();
            return "Successful...";
        } catch (Exception e) {
            log.info("ERROR :: ", e);
            return "Failed :: " + e.getMessage();
        }
    }
}
