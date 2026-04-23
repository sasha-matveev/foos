package org.am.a;

import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Raif1 {

    public interface LoadBalancer<T> {
        T next();
        boolean add(T resource);
        boolean remove(T resource);
    }


    public class StrategyBasedLoadBalancer<T> implements LoadBalancer<T> {
        private final ILoadBalancerStrategy<T> strategy;

        private final List<T> resources = new ArrayList<>();

        public StrategyBasedLoadBalancer(ILoadBalancerStrategy<T> strategy) {
            this.strategy = strategy;
        }

        @Override
        @Nullable
        public T next() {
            if (resources.isEmpty()) {
                return null;
            }
            return strategy.next(Collections.unmodifiableList(resources));
        }

        @Override
        public boolean add(T resource) {
            if (resources.contains(resource)) {
                return false;
            }
            return resources.add(resource);
        }

        @Override
        public boolean remove(T resource) {
            return resources.remove(resource);
        }
    }

    public interface ILoadBalancerStrategy<T> {
        T next(List<T> resources);
    }

    public class RoundRobinStrategy<T> implements ILoadBalancerStrategy<T> {
        private final AtomicLong counter = new AtomicLong();

        @Override
        public T next(List<T> resources) {
            return resources.get(Math.abs(Math.toIntExact(counter.incrementAndGet() % resources.size())));
        }
    }

}
