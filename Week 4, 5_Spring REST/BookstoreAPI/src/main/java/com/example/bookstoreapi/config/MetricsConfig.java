package com.example.bookstoreapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MetricsConfig {

    @Bean
    CustomMetrics customMetrics(MeterRegistry meterRegistry) {
        return new CustomMetrics(meterRegistry);
    }

    public static class CustomMetrics {

        private final MeterRegistry meterRegistry;

        public CustomMetrics(MeterRegistry meterRegistry) {
            this.meterRegistry = meterRegistry;
            initializeMetrics();
        }

        private void initializeMetrics() {
            meterRegistry.gauge("custom.book.count", this, CustomMetrics::getBookCount);
            // Add more custom metrics here
        }

        // Example method for custom metric
        public int getBookCount() {
            // Implement logic to return the count of books, e.g., from a repository
            return 0; // Replace with actual count
        }
    }
}
