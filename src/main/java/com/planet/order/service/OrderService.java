package com.planet.order.service;

import org.springframework.web.multipart.MultipartFile;

public interface OrderService {
    String processFile(MultipartFile file);
}
