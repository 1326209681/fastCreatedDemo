package com.migu;

import com.migu.service.com.yuanchun.service.BookService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

	@Autowired
	private BookService bookService;
/*	@Test
	public void test1() {
		List<YcBookContent> book=bookService.findBookContentById(1 + "");
		System.out.println(book);
	}*/

}

