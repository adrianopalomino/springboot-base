package com.spring.base;

import org.springframework.boot.SpringApplication;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);

		try {
			Path path = Paths.get("C:\\Users\\pc\\Desktop");
			WatchService watchService = path.getFileSystem().newWatchService();
			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY,
					StandardWatchEventKinds.ENTRY_DELETE);
			while (true) {
				WatchKey watchKey = watchService.take();
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					System.out.println(event);
					System.out.println("printou");
				}
				if (!watchKey.reset()) {
					watchKey.cancel();
					watchService.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
