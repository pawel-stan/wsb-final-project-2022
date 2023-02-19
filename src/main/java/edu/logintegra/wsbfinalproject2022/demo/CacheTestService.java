package edu.logintegra.wsbfinalproject2022.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheTestService {

    @Cacheable(value = "square-cache", key = "#x")
    public String calcSquare(int x) throws InterruptedException {
        int square = x * x;

        Thread.sleep(2 * 1000);

        System.out.println("Kwadrat z " + x + " to: " + square);

        return String.valueOf(square);
    }
}
