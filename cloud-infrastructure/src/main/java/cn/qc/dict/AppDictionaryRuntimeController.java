package cn.qc.dict;

import cn.qc.config.AppProperties;
import cn.qc.support.AppRuntimeDict;
import cn.qc.support.anno.AppDict;
import cn.qc.util.UtilString;
import cn.qc.web.http.ResponseParam;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.reflections.Reflections;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description 字典项
 * @Date 2020/4/22
 * @Author 林基山
 **/
@Slf4j
@RestController
@RequestMapping(AppDictionaryRuntimeController.BASE_PATH)
public class AppDictionaryRuntimeController {
    public static final String BASE_PATH = "/dictionary/runtime";

    private Set<String> dictRuntimeClassPackages = Sets.newHashSet();

    private static Map<String, Class<? extends AppRuntimeDict>> runtimeDictMap = Maps.newHashMap();

    public AppDictionaryRuntimeController() {
        // 添加运行时字典映射
        for (String basePackage : AppProperties.AppPackage.BASE_PACKAGES) {
            addDictRuntimeClassPackage(basePackage);
        }
    }

    private void addDictRuntimeClassPackage(String dictRuntimeClassPackage) {
        if (dictRuntimeClassPackages.contains(dictRuntimeClassPackage)) {
            return;
        }
        Reflections reflections = new Reflections(dictRuntimeClassPackage);
        Set<Class<? extends AppRuntimeDict>> appRuntimeDictClasses = reflections.getSubTypesOf(AppRuntimeDict.class);

        for (Class<? extends AppRuntimeDict> appRuntimeDictClass : appRuntimeDictClasses) {
            AppDict appDict = AnnotationUtils.findAnnotation(appRuntimeDictClass, AppDict.class);

            String dictParentCode = appDict != null ? appDict.value() : null;

            if (StringUtils.isBlank(dictParentCode)) {
                log.info("运行时字典 {} 的rest地址访问为空", appRuntimeDictClass.getName());
                continue;
            }

            log.info("运行时字典 {} 的rest地址访问地址: {}", appRuntimeDictClass.getName(), BASE_PATH + "/" + appDict.value());

            Assert.isTrue(!runtimeDictMap.containsKey(dictParentCode), "运行时字典 " + dictParentCode + " 已经存在");

            runtimeDictMap.put(dictParentCode, appRuntimeDictClass);

        }

    }

    @RequestMapping("{code}")
    public Object runtimeDictionary(@PathVariable String code,
                                    @RequestParam(required = false) String excludes,
                                    @RequestParam(required = false, defaultValue = "true") Boolean wrapped) {
        if (!runtimeDictMap.containsKey(code)) {
            return ResponseParam.fail().message("没有指定的字典项");
        }
        Class<?> runtimeDictClass = runtimeDictMap.get(code);

        if (!runtimeDictClass.isEnum()) {
            return ResponseParam.fail().message("运行时字典必须为枚举类型");
        }

        AppRuntimeDict[] appRuntimeDicts = null;

        // 通过反射机制获取所有的枚举类型
        try {
            Method valuesMethod = runtimeDictClass.getMethod("values");
            appRuntimeDicts = (AppRuntimeDict[]) valuesMethod.invoke(null, null);
        } catch (Exception e) {
            return ResponseParam.fail().message("运行时字典必须为枚举类型, " + e.getMessage());
        }

        ResponseParam responseParam = ResponseParam.success();

        // 构造字典列表
        if (appRuntimeDicts != null) {
            // 获取排除的编码
            List<String> excludeCodes = UtilString.splitByComma(excludes);

            List<Map<String, String>> dictList = Lists.newArrayListWithExpectedSize(appRuntimeDicts.length);
            Arrays.stream(appRuntimeDicts)
                    .filter(appRuntimeDict -> !excludeCodes.contains(appRuntimeDict.name()))
                    .forEach(appRuntimeDict -> {
                        Map<String, String> item = Maps.newHashMapWithExpectedSize(2);
                        item.put("code", appRuntimeDict.name());
                        item.put("name", appRuntimeDict.getRemark());
                        dictList.add(item);
                    });

            // 如果不需要进一步封装结构，直接返回list
            if (!wrapped) {
                return dictList;
            }

            responseParam.datalist(dictList);
        }

        return responseParam;
    }
}
