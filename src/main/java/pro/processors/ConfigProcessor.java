package pro.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pro.dao.Book;

@Configuration
public class ConfigProcessor {
	Logger log = LoggerFactory.getLogger(ConfigProcessor.class);

	@Bean
	public ItemProcessor<Book, Book> processor3() {
		log.info("Considering the books having computer genre");
		return book -> {
			if (book.getGenre().equalsIgnoreCase("Computer"))
				return book;
			return null;
		};
	}
}
