package net.twilightdevelopment.plugin.autohub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class ArrayUtil {

  public static <B, A> Collection<A> applyModification(Collection<B> before, Function<B, A> function) {
    final List<A> result = new ArrayList<>();
    for (B b : before) {
      result.add(function.apply(b));
    }

    return result;
  }
}
