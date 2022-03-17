
package com.unobank.servicehub.platform.commonlib.log;

import com.unobank.servicehub.platform.commonlib.annotations.OrchTransactionLog;
import com.unobank.servicehub.platform.commonlib.annotations.TransactionLog;
import com.unobank.servicehub.platform.commonlib.dto.enums.ExternalID;
import com.unobank.servicehub.platform.commonlib.event.TransactionStateEvent;
import com.unobank.servicehub.platform.commonlib.exception.APIException;
import com.unobank.servicehub.platform.commonlib.producer.KafkaProducerService;
import com.unobank.servicehub.platform.commonlib.util.ApplicationUtil;
import com.unobank.servicehub.platform.commonlib.util.CommonParser;
import com.unobank.servicehub.platform.commonlib.util.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * @author tanay sen
 */


@Aspect
@Component
public class EventLogAspect {

    @Value("${spring.kafka.topicOne.name}")
    private String topicName;

    @Value("${application.transactionLog.enabled}")
    private boolean isLoggingEnabled;

    @Autowired
    KafkaProducerService kafkaProducerService;


    @Around("execution(* *.*(..)) && @annotation(transactionLog)")
    public Object logEvents(ProceedingJoinPoint proceedingJoinPoint, TransactionLog transactionLog) throws Throwable {
        CodeSignature codeSignature = (CodeSignature) proceedingJoinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        String methodName = transactionLog.methodName();
        String transactionId = proceedingJoinPoint.getArgs()[getIndexForProcessId(parameterNames)].toString();
        ExternalID externalId = ExternalID.valueOf(methodName);
        String externalIdString = ApplicationUtil.generateExternalId(externalId.getValue(), transactionId);
        System.out.println(methodName);
        try {
            Object result = proceedingJoinPoint.proceed();
            if (isLoggingEnabled)
                sendTransactionManagmentEvent(transactionId, "", externalIdString, methodName, "SUCCESS");
            return result;
        } catch (APIException exception) {
            if (isLoggingEnabled)
                sendTransactionManagmentEvent(transactionId, "", externalIdString, methodName, "FAILED");
            throw new APIException(exception.getStatus(), exception.getMessage());
        }

    }


    @Around("execution(* *.*(..)) && @annotation(orchTransactionLog)")
    public Object logEvents(ProceedingJoinPoint proceedingJoinPoint, OrchTransactionLog orchTransactionLog) throws Throwable {
        String orchestrationName = orchTransactionLog.orchestrationName();
        CodeSignature codeSignature = (CodeSignature) proceedingJoinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        String transactionId = proceedingJoinPoint.getArgs()[getIndexForProcessId(parameterNames)].toString();
        String externalIdString = ApplicationUtil.generateOrchestrationId("START", transactionId);
        if (isLoggingEnabled)
            sendTransactionManagmentEvent(transactionId, proceedingJoinPoint.getArgs()[0].toString(), externalIdString, orchestrationName, "START");
        Object result = proceedingJoinPoint.proceed();
        externalIdString = ApplicationUtil.generateOrchestrationId("END", transactionId);
        if (isLoggingEnabled)
            sendTransactionManagmentEvent(transactionId, result, externalIdString, orchestrationName, "END");
        return result;
    }

    private int getIndexForProcessId(String[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].equals("transactionId") || parameters[i].equals("processId")) {
                return i;
            }
        }
        return -1;
    }


    private void sendTransactionManagmentEvent(String transactionId, Object payLoad, String externalId, String serviceCall, String state) {
        TransactionStateEvent transactionStateEvent = getTransactionState(transactionId, CommonParser.parseToJsonString(payLoad), externalId, serviceCall, state);
        kafkaProducerService.produce(topicName, transactionStateEvent.getTransactionId(), transactionStateEvent);
    }


    private TransactionStateEvent getTransactionState(String transactionId, String payLoad, String externelId, String serviceCall, String state) {
        return TransactionStateEvent.builder()
                .transactionId(transactionId)
                .serviceName(serviceCall)
                .payload(payLoad)
                .externalId(externelId)
                .transactionState(state)
                .timestamp(DateUtil.convertDateToCustomFormatString(LocalDateTime.now()))
                .build();
    }

}

