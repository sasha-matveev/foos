package org.am.a;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
Нам дана функция которая оценивает релевантность документа пользователю

interface Scorer<Document, User> {
    double getScore(Document doc, User user);
}

Необходимо реализовать сервис который может сохранять документ и получать топ К (limit) документов для пользователя по скору этой функции

interface RecommenderService<Document, User> {
List<Document> getTop(User user, int limit);

void addDocument(Document document);
}
 */
public class Ok {
    public interface RecommenderService<Document, User> {
        List<Document> getTop(User user, int limit);

        void addDocument(Document document);
    }

    public interface Scorer<Document, User> {
        double getScore(Document doc, User user);
    }

    public static class InMemoryRecommenderService<Document, User> implements RecommenderService<Document, User> {
        private final List<Document> documents = new ArrayList<>();
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        private final Scorer<Document, User> scorer;

        public InMemoryRecommenderService(Scorer<Document, User> scorer) {
            this.scorer = scorer;
        }

        @Override
        public List<Document> getTop(User user, int limit) {
            lock.readLock().lock();
            try {
                return documents.stream()
                        .map(d -> new ScoredDocument<>(d, scorer.getScore(d, user)))
                        .sorted(Comparator.comparing((ScoredDocument<Document> sd) -> sd.score()).reversed())
                        .map(sd -> sd.document())
                        .limit(limit)
                        .toList();
            } finally {
                lock.readLock().unlock();
            }
        }

        @Override
        public void addDocument(Document document) {
            lock.writeLock().lock();
            try {
                this.documents.add(document);
            } finally {
                lock.writeLock().unlock();
            }
        }

        private record ScoredDocument<D>(D document, Double score) {
        }
    }
}
