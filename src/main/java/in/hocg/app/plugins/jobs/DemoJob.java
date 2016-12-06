package in.hocg.app.plugins.jobs;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by hocgin on 16-11-30.
 * cron.properties 添加任务类和执行时间
 */
@IocBean
public class DemoJob implements Job {
    private static final Log log = Logs.get();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(String.format("[--] %s ", getClass().getName()));
    }
}
