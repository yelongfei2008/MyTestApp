package com.mytestapp.weight;


import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;


/**
 * 短信监听
 * Created by james-ye on 16/4/19.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {

    private static MessageListener mMessageListener;
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    public SMSBroadcastReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.print("i hava rec msg");
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            for (Object pdu : pdus) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                String sender = smsMessage.getDisplayOriginatingAddress();
                //短信内容
                String content = smsMessage.getDisplayMessageBody();
                long date = smsMessage.getTimestampMillis();
                Date tiemDate = new Date(date);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = simpleDateFormat.format(tiemDate);

                //过滤不需要读取的短信的发送号码
                if ("+8613916948503".equals(sender) || "13916948503".equals(sender)) {
                    mMessageListener.onReceived(content);
                    abortBroadcast();
                }
            }
        }

    }

    //回调接口
    public interface MessageListener {
        public void onReceived(String message);
    }

    public void setOnReceivedMessageListener(MessageListener messageListener) {
        this.mMessageListener = messageListener;
    }
}
