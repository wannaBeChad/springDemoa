package com.example.springdemo.service;

import java.util.Map;

public class CosineSimilarityService {

    public static double cosineSimilarity(
            Map<String, Double> book1, Map<String, Double> book2) {
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;

        for (String key : book1.keySet()) {
            dotProduct += book1.get(key) * book2.get(key);
            magnitude1 += Math.pow(book1.get(key), 2);
            magnitude2 += Math.pow(book2.get(key), 2);
        }

        magnitude1 = Math.sqrt(magnitude1);
        magnitude2 = Math.sqrt(magnitude2);

        if (magnitude1 != 0.0 | magnitude2 != 0.0) {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } else {
            return 0.0;
        }
        return cosineSimilarity;
    }
}
