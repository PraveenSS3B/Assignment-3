package pro.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import pro.dao.Book;

@Configuration
public class ConfigReader {

	Logger log = LoggerFactory.getLogger(ConfigReader.class);

	@Bean
	public StaxEventItemReader<Book> readerXml() {
		log.info("Reading from books.xml");
		StaxEventItemReader<Book> xmlFileReader = new StaxEventItemReader<Book>();

		xmlFileReader.setResource(new ClassPathResource("/input/books.xml"));
		xmlFileReader.setFragmentRootElementName("book");

		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Book.class);
		xmlFileReader.setUnmarshaller(marshaller);

		return xmlFileReader;

	}

}
