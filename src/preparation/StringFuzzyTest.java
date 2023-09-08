package preparation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringFuzzyTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        list.add("liliu");

        String keyword = "li";

        List<String> answer1 = new ArrayList<>();

        answer1.addAll(list.stream().filter(one -> one.contains(keyword)).collect(Collectors.toList()));

        System.out.println("1. String的contains方法");
        answer1.forEach(System.out::println);

        List<String> answer2 = new ArrayList<>();

        Pattern regex = Pattern.compile(keyword);
        answer2.addAll(list.stream().filter(one -> regex.matcher(one).find()).collect(Collectors.toList()));

        System.out.println("2. 正则表达式");
        answer2.forEach(System.out::println);

        List<String> answer3 = new ArrayList<>();

        answer3.addAll(list.stream().filter(one -> one.indexOf(keyword) > -1).collect(Collectors.toList()));

        System.out.println("3. String的indexOf方法,大于-1说明找得到");
        answer3.forEach(System.out::println);

    }
}
