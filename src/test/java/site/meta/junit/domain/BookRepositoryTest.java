package site.meta.junit.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.PrePersist;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest // DB와 관련된 컴포넌트만 테스트 - 메모리에 로딩
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

//    @BeforeAll // 테스트 시작전에 한번만 실행
    @BeforeEach // 각 테스트 시작전에 한번씩 실행
    void testData() {
        String title = "junit5";
        String author = "jm2";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        bookRepository.save(book);
    }

    @Test
    void 책등록_test() {
        //given(데이터 준비)
        String title = "junit5";
        String author = "jm";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        //when(테스트 실행)
        Book bookPS = bookRepository.save(book);

        //then(검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
        //트랜잭션 종료 -> 데이터 초기화
    }

    @Test
    void 책목록보기_test() {
        //given
        testData();
        //when
        List<Book> bookPS = bookRepository.findAll();
        //then
        System.out.println("books.size() = " + bookPS.size());
        assertEquals("junit5", bookPS.get(0).getTitle());
        assertEquals("jm2", bookPS.get(0).getAuthor());
    }

    @Test
    void 책한건보기_test() {
        //given
        testData();
        //when
        Book bookPS = bookRepository.findById(1L).get();
        //then
        assertEquals("junit5", bookPS.getTitle());
        assertEquals("jm2", bookPS.getAuthor());
    }
}
