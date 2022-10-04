package pro.writers;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pro.dao.Book;

@Configuration
public class ConfigWriter {
	Logger log = LoggerFactory.getLogger(ConfigWriter.class);

	@Autowired
	private DataSource dataSource;


	@Bean
	public JdbcBatchItemWriter<Book> xml2dbjdbcWriter() {
		log.info("Writing to database from xml..");
		JdbcBatchItemWriter<Book> writer = new JdbcBatchItemWriter<Book>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Book>());
		writer.setSql(
				"INSERT INTO BOOK (ID, AUTHOR, DESCRIPTION, GENRE, PRICE, PUBLISH_DATE, TITLE) VALUES (:id, :author, :title, :genre, :price, :publish_date, :description)");
		writer.setDataSource(dataSource);

		return writer;
	}

}
