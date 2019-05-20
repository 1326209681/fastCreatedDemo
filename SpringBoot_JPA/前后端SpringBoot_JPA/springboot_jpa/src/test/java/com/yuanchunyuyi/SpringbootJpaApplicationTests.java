package com.yuanchunyuyi;

import com.yuanchunyuyi.domain.YcBookContent;
import com.yuanchunyuyi.domain.ycBookList;
import com.yuanchunyuyi.service.com.yuanchun.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Service;
import java.util.List;

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

