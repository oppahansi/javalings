package de.oppa.jlings.exercise;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.util.concurrent.TimeUnit;

public class ExWatcher {
    private Thread watchThread;
    private volatile boolean running = true;

    public void watch(Exercise exercise, Runnable onFileChange) {
        watchThread = new Thread(() -> {
            try {
                var watchService = FileSystems.getDefault().newWatchService();

                for (var filePath : exercise.files()) {
                    Paths.get(filePath).getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                }

                while (running) {
                    if (!running) {
                        break;
                    }

                    var key = watchService.poll(1, TimeUnit.SECONDS);
                    if (key != null) {
                        for (var event : key.pollEvents()) {
                            if (event.kind() != StandardWatchEventKinds.OVERFLOW && onFileChange != null) {
                                onFileChange.run();
                            }
                        }

                        key.reset();
                    }
                }

                watchService.close();
            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
            }
        });

        watchThread.setDaemon(true);
        watchThread.start();
    }

    public void stop() {
        running = false;
    }
}