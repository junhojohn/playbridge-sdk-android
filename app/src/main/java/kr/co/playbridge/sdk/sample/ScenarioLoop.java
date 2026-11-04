package kr.co.playbridge.sdk.sample;


import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.IntFunction;

public final class ScenarioLoop {

    private ScenarioLoop() {}

    @FunctionalInterface
    public interface TrackFunction {
        boolean track(String name, Map<String, String> properties);
    }

    public static void run(
            Consumer<Map<String, String>> init,
            TrackFunction track,
            String endpoint,
            String candidateKey,
            String sessionId,
            int count,
            int intervalMs,
            int observeSeconds,
            IntFunction<String> valueFactory
    ) throws InterruptedException {

        if (count < 1) {
            throw new IllegalArgumentException("count must be >= 1");
        }
        if (intervalMs < 0) {
            throw new IllegalArgumentException("intervalMs must be >= 0");
        }
        if (observeSeconds < 0) {
            throw new IllegalArgumentException("observeSeconds must be >= 0");
        }

        if (sessionId == null) {
            sessionId = UUID.randomUUID().toString();
        }

        System.out.println(
                "{\"sessionId\":\"" + sessionId +
                        "\",\"expectedCount\":" + count + "}"
        );

        // SDK 초기화
        Map<String, String> config = new HashMap<>();
        config.put("endpoint", endpoint);
        config.put("candidateKey", candidateKey);
        config.put("sessionId", sessionId);

        init.accept(config);

        // 이벤트 전송
        for (int i = 1; i <= count; i++) {

            String name = String.format(
                    java.util.Locale.ROOT,
                    "testevent_%03d", i
            );

            // Track 호출마다 값을 한 번만 생성
            String value = valueFactory != null
                    ? valueFactory.apply(i)
                    : UUID.randomUUID().toString().replace("-", "");

            Map<String, String> properties = new HashMap<>();
            properties.put("value", value);

            long started = System.nanoTime();

            boolean accepted = track.track(name, properties);

            double elapsedMs =
                    (System.nanoTime() - started) / 1_000_000.0;

            System.out.println(
                    "{\"sessionId\":\"" + sessionId +
                            "\",\"call\":" + i +
                            ",\"name\":\"" + name +
                            "\",\"accepted\":" + accepted +
                            ",\"elapsedMs\":" + elapsedMs + "}"
            );

            if (intervalMs > 0 && i < count) {
                Thread.sleep(intervalMs);
            }
        }

        // SDK 백그라운드 전송 및 재시도 관찰
        Thread.sleep(observeSeconds * 1000L);
    }

    // 기본값을 적용하는 오버로드
    public static void run(
            Consumer<Map<String, String>> init,
            TrackFunction track,
            String endpoint,
            String candidateKey
    ) throws InterruptedException {

        run(
                init,
                track,
                endpoint,
                candidateKey,
                null,   // sessionId 자동 생성
                100,    // count
                0,      // intervalMs
                60,     // observeSeconds
                null    // valueFactory
        );
    }
}
