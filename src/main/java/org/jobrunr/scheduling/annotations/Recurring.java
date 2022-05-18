package org.jobrunr.scheduling.annotations;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Recurring {
    String CRON_DISABLED = "-";

    String id() default "";

    String cron();

    String zoneId() default "";
}