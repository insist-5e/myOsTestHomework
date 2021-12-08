package os.util;

import java.util.Map;
import java.util.Objects;

public class Pair<K,V> implements Map.Entry<K,V> {
    private K key;
    private V value;
    @Override
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }
    @Override
    public V getValue() {
        return value;
    }

    @Override
    public Object setValue(Object value) {
        return this.value= (V) value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(key, pair.key) &&
                Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
