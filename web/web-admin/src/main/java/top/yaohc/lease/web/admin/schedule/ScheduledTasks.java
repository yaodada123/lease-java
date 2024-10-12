package top.yaohc.lease.web.admin.schedule;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.yaohc.lease.model.entity.LeaseAgreement;
import top.yaohc.lease.model.enums.LeaseStatus;
import top.yaohc.lease.web.admin.service.LeaseAgreementService;

import java.util.Date;

/**
 * ClassName: ScheduledTasks
 * Description:
 *
 * @Author 所谓独醉
 * @Create 2024/10/10 下午6:57
 * @Version 1.0
 */
@Component
public class ScheduledTasks {

    @Autowired
    private LeaseAgreementService leaseAgreementService;

    @Scheduled(cron = "0 0 0 * * *")
    public void checkLeaseStatus() {

        LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<>();
        Date now = new Date();
        updateWrapper.le(LeaseAgreement::getLeaseEndDate, now);
        updateWrapper.eq(LeaseAgreement::getStatus, LeaseStatus.SIGNED);
        updateWrapper.in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING);

        leaseAgreementService.update(updateWrapper);
    }
}
