
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 3, 9, 5, 7, 3, 1, 456, 1));
        Stream<Integer> stream = list.stream();
        BiConsumer<Optional<Integer>, Optional<Integer>> biConsumer = (n1, n2) -> System.out.println(n1 + "\n" + n2);
        findMinMax(stream, Comparator.naturalOrder(), biConsumer);
        stream.close();
        System.out.println(countHonest(list));
    }

    public static <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> order, BiConsumer<Optional<T>, Optional<T>> minMaxConsumer) {
        List<T> list = stream.sorted(order).collect(Collectors.toList());
        if (!list.isEmpty()) {
            Optional<T> min = Optional.ofNullable(Collections.min(list, order));
            Optional<T> max = Optional.ofNullable(Collections.max(list, order));
            minMaxConsumer.accept(min, max);
        } else {
            minMaxConsumer.accept(null, null);
        }
    }

    public static long countHonest(List<Integer> list) {
        return list.stream()
                .filter(v -> v % 2 == 0)
                .peek(System.out::println)
                .count();

    }
}