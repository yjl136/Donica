package cn.donica.slcd.dmanager.agnet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by liangmingjie on 2015/12/7.
 */
public class LoopTask {
    /**
     * 调用服务执行循环任务
     *
     * @param context
     * @param seconds
     * @param cls
     */
    public static void startLoopTaskService(Context context, int seconds,
                                            Class<?> cls) {
        // 获取AlarmManager系统服务
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // 包装需要执行Service的Intent
        Intent intent = new Intent(context, cls);
        //        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 触发服务的起始时间
        long triggerAtTime = SystemClock.elapsedRealtime();
        // 使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
                seconds * 1000, pendingIntent);
    }

    /**
     * 停止循环任务
     *
     * @param context
     * @param cls
     */
    public static void stopLoopTaskService(Context context, Class<?> cls) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        //   intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 取消正在执行的服务
        manager.cancel(pendingIntent);
    }
}
