package com.app.gautam.reportintent;

import java.util.UUID;

/**
 * Created by gautam on 12-10-2016.
 */
public class ReportDBSchema {

    public static final class ReportTable{
        public static final String NAME = "reports";

        public static final class cols{
            public static final String UUID= "uuid";
            public static final String TITLE = "title";
            public static final String DATE= "date";
            public static final String RESOLVED= "resolved";
        }
    }
}
