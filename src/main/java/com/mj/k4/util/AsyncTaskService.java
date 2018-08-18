package com.mj.k4.util;

import com.mj.common.config.ModbusConfig;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import javax.inject.Inject;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/1/9
 * 线程执行任务类 这个类作废 但是后期可以参考
 */
//@Service
//@Component
public class AsyncTaskService {
    Random random = new Random();//默认构造方法

    ModbusConfig modbusConfig = new ModbusConfig();

    @Inject
    private TaskExecutor taskExecutor;

//    启动异步线程
//    @PostConstruct
//    public void doThing(){
//        taskExecutor.execute(() -> {
//            executeAsyncTask(1);
//        });
//    }

    /**
     * 表明异步方法 没有返回值
     *
     * @param
     */
//    @Async("taskExecutor")
    public Number executeAsyncTask(Integer num) throws ModbusTransportException, ErrorResponseException, ModbusInitException, InterruptedException {
        Number i = modbusConfig.readHoldingRegister(2, num, DataType.FOUR_BYTE_INT_SIGNED);
        System.out.println("这是第" + num + "个数:" + i);
        Thread.sleep(20000);
        System.out.println("我休眠结束了");
        return i;
    }

//    @Async("taskExecutor")
    public void executeAsyncTask1() throws ModbusTransportException, ErrorResponseException, ModbusInitException {


    }

    /**
     * 异步调用返回future
     *
     * @param i
     * @return
     * @throws InterruptedException
     */
    @Async
    public Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException {
        System.out.println("input is " + i);
        Thread.sleep(1000 * random.nextInt(i));
        Future<String> future = new AsyncResult<>("success:" + i);//Future接收返回值，这里是String类型，可以指明其他类型
        return future;
    }


}
