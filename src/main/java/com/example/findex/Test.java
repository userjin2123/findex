package com.example.findex;

import com.example.findex.common.openApi.service.IndexSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class Test implements CommandLineRunner {

    private final IndexSyncService indexSyncService;

    @Override
    public void run(String... args) throws Exception {
        LocalDate testDate = LocalDate.of(2025, 9, 14);

        for (int i = 1; i <= 1600; i++) {
            try {
                indexSyncService.syncDailyData(testDate);
            } catch (Exception e) {
                log.error("날짜 {} 처리 실패: {}", testDate, e.getMessage());
            }

            testDate = testDate.minusDays(1);

            try {
                Thread.sleep(200); // 200ms 대기
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
