package cn.qc.config;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dinghao
 * @version 1.0.0
 * @ClassName AppProperties.java
 * @Description TODO
 * @createTime 2020年04月22日 10:44:00
 */
public class AppProperties {

    private AppProperties.Page page = new AppProperties.Page();

    @Data
    public static class AppPackage {
        private static final String BASE_PACKAGE = "cn.qc";
        public static final String[] BASE_PACKAGES = {BASE_PACKAGE};
    }

    public AppProperties.Page getPage() {
        return this.page;
    }

    @Data
    @NoArgsConstructor
    public static class Page {
        private String pageNum = "pagenum";
        private Integer pageNumStartIndex = 0;
        private String pageSize = "pagesize";
        private String pageRealSize = "pagerealsize";
        private String totalElements = "totalelements";
        private String totalPages = "totalpages";

    }
}
