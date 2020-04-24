package cn.qc.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linjs
 * @version 1.0.0
 * @ClassName UtilString.java
 * @Description TODO
 * @createTime 2020年04月22日
 */
public class UtilString extends StringUtils {
    public static final Pattern PACKAGE_PATTERN = Pattern.compile("^([a-zA-Z]+[.][a-zA-Z]+)[.]*.*");
    public static final Pattern PARAM$_PATTERN = Pattern.compile("\\$\\{([\\w]*)\\}");

    public UtilString() {
    }

    public static boolean matcherPackageName(String packageName) {
        Assert.hasText(packageName, "包名不能为空");
        Matcher matcher = PACKAGE_PATTERN.matcher(packageName);
        return matcher.find();
    }


    public static List<String> getBetween(String s) {
        List<String> results = new ArrayList();
        Matcher m = PARAM$_PATTERN.matcher(s);

        while (m.find()) {
            results.add(m.group(1));
        }

        return results;
    }

    public static String joinByComma(Iterable<String> items) {
        return items == null ? "" : Joiner.on(",").skipNulls().join(items);
    }

    public static List<String> splitByComma(String item) {
        return (List) (isBlank(item) ? Lists.newArrayListWithExpectedSize(1) : Splitter.on(",").omitEmptyStrings().trimResults().splitToList(item));
    }

    public static String formatUrl(String url) {
        url = url.trim().replaceAll("(\\w+)/+", "$1/");
        return url;
    }
}
