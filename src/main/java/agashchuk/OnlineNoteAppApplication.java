package agashchuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlineNoteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineNoteAppApplication.class, args);
	}
}
