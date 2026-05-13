package org.am.a;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class OkTest {

    @Mock
    Ok.Scorer<TestDoc, TestUser> scorer;

    Ok.InMemoryRecommenderService<TestDoc, TestUser> service;

    @BeforeEach
    void setUp() {
        service = new Ok.InMemoryRecommenderService<>(scorer);
    }

    @Test
    void shouldReturnOneDocumentAfterAdded() {
        TestDoc doc = new TestDoc();
        service.addDocument(doc);
        List<TestDoc> top = service.getTop(new TestUser(), 1);
        Assertions.assertThat(top)
                .singleElement()
                .isSameAs(doc);
    }

    @Test
    void shouldReturnOneDocumentAfterTwoAdded() {
        TestDoc doc1 = new TestDoc();
        TestDoc doc2 = new TestDoc();

        Mockito.doReturn(1d).when(scorer).getScore(ArgumentMatchers.eq(doc1), ArgumentMatchers.any());
        Mockito.doReturn(2d).when(scorer).getScore(ArgumentMatchers.eq(doc2), ArgumentMatchers.any());

        service.addDocument(doc1);
        service.addDocument(doc2);

        List<TestDoc> top = service.getTop(new TestUser(), 1);
        Assertions.assertThat(top)
                .singleElement()
                .isSameAs(doc2);
    }

    @Test
    void shouldReturnTwoDocumentAfterThreeAdded() {
        TestDoc doc1 = new TestDoc();
        TestDoc doc2 = new TestDoc();
        TestDoc doc3 = new TestDoc();

        Mockito.doReturn(1d).when(scorer).getScore(ArgumentMatchers.eq(doc1), ArgumentMatchers.any());
        Mockito.doReturn(2d).when(scorer).getScore(ArgumentMatchers.eq(doc2), ArgumentMatchers.any());
        Mockito.doReturn(3d).when(scorer).getScore(ArgumentMatchers.eq(doc3), ArgumentMatchers.any());

        service.addDocument(doc2);
        service.addDocument(doc3);
        service.addDocument(doc1);

        List<TestDoc> top = service.getTop(new TestUser(), 2);
        Assertions.assertThat(top)
                .containsExactly(doc3, doc2);
    }

    private class TestDoc {
    }
    private class TestUser{
    }
}