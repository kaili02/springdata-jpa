package org.jobrunr.scheduling;

import com.pgh.spring_data_jpa0414.entity.User;
import com.pgh.spring_data_jpa0414.repository.UserDao;
import org.jobrunr.jobs.JobDetails;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.annotations.Recurring;
import org.jobrunr.scheduling.cron.CronExpression;
import org.jobrunr.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/5/18
 */
public class JobPostProcessor implements BeanPostProcessor, EmbeddedValueResolverAware, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(org.jobrunr.scheduling.RecurringJobPostProcessor.class);
    private final JobScheduler jobScheduler;
    private StringValueResolver embeddedValueResolver;
    private UserDao userDao;
    private JobPostProcessor.RecurringJobFinderMethodCallback recurringJobFinderMethodCallback;

    public JobPostProcessor(JobScheduler jobScheduler, UserDao userDao) {
        this.jobScheduler = jobScheduler;
        this.userDao = userDao;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithMethods(bean.getClass(), this.recurringJobFinderMethodCallback);
        return bean;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.embeddedValueResolver = resolver;
    }

    public void afterPropertiesSet() {
        this.recurringJobFinderMethodCallback = new JobPostProcessor.RecurringJobFinderMethodCallback(this.jobScheduler, this.embeddedValueResolver, this.userDao);
    }

    private static class RecurringJobFinderMethodCallback implements ReflectionUtils.MethodCallback {
        private final JobScheduler jobScheduler;
        private final StringValueResolver embeddedValueResolver;
        private final UserDao userDao;

        public RecurringJobFinderMethodCallback(JobScheduler jobScheduler, StringValueResolver resolver, UserDao userDao) {
            this.jobScheduler = jobScheduler;
            this.embeddedValueResolver = resolver;
            this.userDao = userDao;
        }

        public void doWith(Method method) throws IllegalArgumentException {
            if (method.isAnnotationPresent(Recurring.class)) {
                if (this.hasParametersOutsideOfJobContext(method)) {
                    throw new IllegalStateException("Methods annotated with " + Recurring.class.getName() + " can only have zero parameters or a single parameter of type JobContext.");
                } else {
                    Recurring recurringAnnotation = (Recurring)method.getAnnotation(Recurring.class);
                    String id = this.getId(recurringAnnotation);
                    Optional<User> byId = userDao.findById(1L);
                    System.out.println(byId.get());
                    String cron = this.resolveStringValue(recurringAnnotation.cron());
                    if ("-".equals(cron)) {
                        if (id == null) {
                            JobPostProcessor.LOGGER.warn("You are trying to disable a recurring job using placeholders but did not define an id.");
                        } else {
                            this.jobScheduler.delete(id);
                        }
                    } else {
                        JobDetails jobDetails = this.getJobDetails(method);
                        CronExpression cronExpression = CronExpression.create(cron);
                        ZoneId zoneId = this.getZoneId(recurringAnnotation);
                        this.jobScheduler.scheduleRecurrently(id, jobDetails, cronExpression, zoneId);
                    }

                }
            }
        }

        private boolean hasParametersOutsideOfJobContext(Method method) {
            if (method.getParameterCount() == 0) {
                return false;
            } else if (method.getParameterCount() > 1) {
                return true;
            } else {
                return !method.getParameterTypes()[0].equals(JobContext.class);
            }
        }

        private String getId(Recurring recurringAnnotation) {
            String id = this.resolveStringValue(recurringAnnotation.id());
            return StringUtils.isNullOrEmpty(id) ? null : id;
        }

        private JobDetails getJobDetails(Method method) {
            JobDetails jobDetails = new JobDetails(method.getDeclaringClass().getName(), (String)null, method.getName(), new ArrayList());
            jobDetails.setCacheable(true);
            return jobDetails;
        }

        private ZoneId getZoneId(Recurring recurringAnnotation) {
            String zoneId = this.resolveStringValue(recurringAnnotation.zoneId());
            return StringUtils.isNullOrEmpty(zoneId) ? ZoneId.systemDefault() : ZoneId.of(zoneId);
        }

        private String resolveStringValue(String value) {
            return this.embeddedValueResolver != null && value != null ? this.embeddedValueResolver.resolveStringValue(value) : value;
        }
    }
}
