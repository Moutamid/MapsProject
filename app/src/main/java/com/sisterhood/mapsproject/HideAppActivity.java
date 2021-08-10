package com.sisterhood.mapsproject;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class HideAppActivity extends AppCompatActivity {

    SwitchMaterial switchMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_app);

        hideBtn();
        registerReceiver();

    }

//    private class MEdiaService extends Service implements Thread.UncaughtExceptionHandler {
//        public static final String CHANNEL_ID = "sala_rasoulallah_channel";
//        boolean isFirst = false;
//        boolean isRunning = false;
////        String[] listItem;
//        MediaPlayer mediaPlayer;
//        Random rand;
//        private BroadcastReceiver screenOnOffReceiver;
//
//        public IBinder onBind(Intent intent) {
//            return null;
//        }
//
//        public void uncaughtException(Thread thread, Throwable th) {
//            Intent intent = new Intent(getApplicationContext(), MEdiaService.class);
//            intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
//            startService(intent);
//        }
//
//        public void onCreate() {
//            super.onCreate();
//        }
//
//        public int onStartCommand(Intent intent, int i, int i2) {
//            this.rand = new Random();
////            this.listItem = getResources().getStringArray(C0917R.array.array_technology);
//            createNotificationChannel();
//            String string = getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_MULTI_PROCESS).getString("lang", "ar");
//            startForeground(1, new NotificationCompat.Builder((Context) this, CHANNEL_ID).setContentTitle("الشفيع").setContentText("SOME text here").setSmallIcon((int) R.drawable.location).setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.location), 128, 128, false)).setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, HideAppActivity.class), 0)).build());
//            new Thread(new Runnable() {
//                public void run() {
//                    MEdiaService.this.registerBroadcastReceiver();
//                }
//            }).start();
//            return Service.START_NOT_STICKY;
//        }
//
////        public static String getLocaleStringResource(Locale locale, int i, Context context) {
////            if (Build.VERSION.SDK_INT >= 17) {
////                Configuration configuration = new Configuration(context.getResources().getConfiguration());
////                configuration.setLocale(locale);
////                return context.createConfigurationContext(configuration).getText(i).toString();
////            }
////            Resources resources = context.getResources();
////            Configuration configuration2 = resources.getConfiguration();
////            Locale locale2 = configuration2.locale;
////            configuration2.locale = locale;
////            resources.updateConfiguration(configuration2, (DisplayMetrics) null);
////            String string = resources.getString(i);
////            configuration2.locale = locale2;
////            resources.updateConfiguration(configuration2, (DisplayMetrics) null);
////            return string;
////        }
//
//        private void createNotificationChannel() {
//            if (Build.VERSION.SDK_INT >= 26) {
//                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_LOW);
//                notificationChannel.setShowBadge(false);
//                notificationChannel.setLockscreenVisibility(1);
//                ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
//            }
//        }
//
//        /* access modifiers changed from: private */
//        public void registerBroadcastReceiver() {
//            IntentFilter intentFilter = new IntentFilter();
//            intentFilter.addAction("android.intent.action.USER_PRESENT");
//            this.screenOnOffReceiver = new BroadcastReceiver() {
//                public void onReceive(Context context, Intent intent) {
//                    MEdiaService.this.isRunning = true;
//                    String action = intent.getAction();
//                    KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//                    if (action.equals("android.intent.action.USER_PRESENT") && keyguardManager != null && !keyguardManager.isKeyguardLocked() && !MEdiaService.this.isFirst) {
//                        MEdiaService.this.isFirst = true;
////                        SharedPreferences sharedPreferences = MEdiaService.this.getSharedPreferences(Constants.MY_PREFS_NAME, Context.);
//  //                      boolean z = sharedPreferences.getBoolean("isonsilent", true);
//    //                    if (!sharedPreferences.getBoolean("isactive", true)) {
//      //                      MEdiaService.this.ShowText();
////                            MEdiaService.this.setStatistics(false);
//                            try {
//                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                                    public void run() {
//                                        MEdiaService.this.isFirst = false;
//                                    }
//                                }, 1000);
//                            } catch (Exception unused) {
//                                MEdiaService.this.isFirst = false;
//                            } catch (Error unused2) {
//                                MEdiaService.this.isFirst = false;
//                            }
////                        } else if (z) {
//                            MEdiaService.this.playAudio();
////                        } else if (((AudioManager) MEdiaService.this.getApplicationContext().getSystemService("audio")).getRingerMode() == 0) {
////                            MEdiaService.this.ShowText();
////                            MEdiaService.this.setStatistics(false);
////                            try {
////                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
////                                    public void run() {
////                                        MEdiaService.this.isFirst = false;
////                                    }
////                                }, 1000);
////                            } catch (Exception unused3) {
////                                MEdiaService.this.isFirst = false;
////                            } catch (Error unused4) {
////                                MEdiaService.this.isFirst = false;
////                            }
////                        } else {
////                            MEdiaService.this.playAudio();
////                        }
//                    }
//                }
//            };
//            getApplicationContext().registerReceiver(this.screenOnOffReceiver, intentFilter);
//        }
//
//        /* access modifiers changed from: private */
//        public void ShowText() {
//            int i;// = getSharedPreferences(Constants.MY_PREFS_NAME, 4).getInt("selectedSoundIndex", 0);
////            if (i == 5) {
//                i = new Random().nextInt(4) + 0;
////            }
////            Constants.ShowToast(getApplicationContext(), this.listItem[i], 1);
//        }
//
////        /* JADX WARNING: Removed duplicated region for block: B:45:0x0165  */
////        /* JADX WARNING: Removed duplicated region for block: B:48:0x0172  */
////        /* JADX WARNING: Removed duplicated region for block: B:51:0x0181  */
////        /* JADX WARNING: Removed duplicated region for block: B:52:0x0188  */
////        /* JADX WARNING: Removed duplicated region for block: B:55:0x0190  */
////        /* JADX WARNING: Removed duplicated region for block: B:56:0x0195  */
////        /* JADX WARNING: Removed duplicated region for block: B:59:0x019f  */
////        /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
////        /* Code decompiled incorrectly, please refer to instructions dump. */
////        public void setStatistics(boolean r31) {
////        /*
////            r30 = this;
////            r0 = r30
////            java.lang.String r1 = salah.rasoulallah.com.Constants.MY_PREFS_NAME
////            r2 = 4
////            android.content.SharedPreferences r1 = r0.getSharedPreferences(r1, r2)
////            java.lang.String r3 = "AllAyatCount1"
////            r4 = 0
////            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
////            int r6 = r1.getInt(r3, r4)
////            java.lang.String r7 = "lastMonth1"
////            r8 = -1
////            int r9 = r1.getInt(r7, r8)
////            r10 = 1
////            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r10)
////            if (r31 == 0) goto L_0x0027
////            int r6 = r6 + r8
////            if (r6 >= 0) goto L_0x0028
////            r6 = 0
////            goto L_0x0028
////        L_0x0027:
////            int r6 = r6 + r10
////        L_0x0028:
////            android.text.format.Time r12 = new android.text.format.Time
////            java.lang.String r13 = android.text.format.Time.getCurrentTimezone()
////            r12.<init>(r13)
////            r12.setToNow()
////            java.lang.StringBuilder r13 = new java.lang.StringBuilder
////            r13.<init>()
////            int r14 = r12.monthDay
////            r13.append(r14)
////            java.lang.String r14 = ""
////            r13.append(r14)
////            int r15 = r12.month
////            r13.append(r15)
////            r13.append(r14)
////            int r14 = r12.year
////            r13.append(r14)
////            java.lang.String r13 = r13.toString()
////            java.lang.String r14 = "lastDay1"
////            java.lang.String r15 = "none"
////            java.lang.String r2 = r1.getString(r14, r15)
////            int r10 = r12.month
////            java.lang.String r8 = "monthAyatCount1"
////            if (r10 != r9) goto L_0x0068
////            int r9 = r1.getInt(r8, r4)
////            r10 = r5
////            goto L_0x006a
////        L_0x0068:
////            r10 = r11
////            r9 = 0
////        L_0x006a:
////            if (r31 == 0) goto L_0x0074
////            r17 = -1
////            int r9 = r9 + -1
////            if (r9 >= 0) goto L_0x0076
////            r9 = 0
////            goto L_0x0076
////        L_0x0074:
////            r4 = 1
////            int r9 = r9 + r4
////        L_0x0076:
////            int r4 = r12.weekDay
////            r19 = r5
////            r5 = 6
////            r20 = r11
////            java.lang.String r11 = "startWeek1"
////            r21 = r7
////            java.lang.String r7 = "weekAyatCount1"
////            if (r4 == r5) goto L_0x0092
////            r4 = 0
////            int r18 = r1.getInt(r7, r4)
////            r16 = r18
////            r4 = r19
////            r22 = r20
////            r5 = 1
////            goto L_0x00a9
////        L_0x0092:
////            r4 = 0
////            r5 = 1
////            boolean r16 = r1.getBoolean(r11, r5)
////            if (r16 != 0) goto L_0x00a1
////            r22 = r19
////            r4 = r20
////            r16 = 0
////            goto L_0x00a9
////        L_0x00a1:
////            int r16 = r1.getInt(r7, r4)
////            r4 = r19
////            r22 = r4
////        L_0x00a9:
////            if (r31 == 0) goto L_0x00b6
////            r17 = -1
////            int r16 = r16 + -1
////            if (r16 >= 0) goto L_0x00b3
////            r5 = 0
////            goto L_0x00ba
////        L_0x00b3:
////            r5 = r16
////            goto L_0x00ba
////        L_0x00b6:
////            int r23 = r16 + 1
////            r5 = r23
////        L_0x00ba:
////            boolean r23 = r13.equals(r2)
////            r24 = r11
////            java.lang.String r11 = "AyatDayCount1"
////            if (r23 == 0) goto L_0x00ce
////            r23 = r4
////            r4 = 0
////            int r25 = r1.getInt(r11, r4)
////            r4 = r19
////            goto L_0x00d4
////        L_0x00ce:
////            r23 = r4
////            r4 = r20
////            r25 = 0
////        L_0x00d4:
////            if (r31 == 0) goto L_0x00e2
////            r17 = -1
////            int r17 = r25 + -1
////            if (r17 >= 0) goto L_0x00e6
////            r17 = r10
////            r25 = r12
////            r12 = 0
////            goto L_0x00ec
////        L_0x00e2:
////            r16 = 1
////            int r17 = r25 + 1
////        L_0x00e6:
////            r25 = r12
////            r12 = r17
////            r17 = r10
////        L_0x00ec:
////            java.lang.String r10 = salah.rasoulallah.com.Constants.MY_PREFS_NAME
////            r26 = r13
////            r13 = 4
////            android.content.SharedPreferences r10 = r0.getSharedPreferences(r10, r13)
////            android.content.SharedPreferences$Editor r10 = r10.edit()
////            boolean r13 = r4.booleanValue()
////            r27 = r14
////            java.lang.String r14 = "weekSAyatCount1"
////            r28 = r4
////            java.lang.String r4 = "monthSAyatCount1"
////            r29 = r5
////            java.lang.String r5 = "AyatSDayCount1"
////            if (r13 == 0) goto L_0x0143
////            if (r2 == r15) goto L_0x0143
////            r2 = 0
////            int r13 = r1.getInt(r3, r2)
////            r15 = 1
////            int r13 = r13 + r15
////            java.lang.String r15 = "AllSAyatCount1"
////            r10.putInt(r15, r13)
////            int r13 = r1.getInt(r11, r2)
////            r15 = 1
////            int r13 = r13 + r15
////            r10.putInt(r5, r13)
////            int r13 = r1.getInt(r8, r2)
////            int r13 = r13 + r15
////            r10.putInt(r4, r13)
////            int r13 = r1.getInt(r7, r2)
////            int r13 = r13 + r15
////            r10.putInt(r14, r13)
////            r10.commit()
////            java.lang.String r2 = salah.rasoulallah.com.Constants.MY_PREFS_NAME
////            r13 = 4
////            android.content.SharedPreferences r2 = r0.getSharedPreferences(r2, r13)
////            android.content.SharedPreferences$Editor r10 = r2.edit()
////            r19 = r20
////            goto L_0x0144
////        L_0x0143:
////            r13 = 4
////        L_0x0144:
////            r10.putInt(r3, r6)
////            r10.putInt(r11, r12)
////            r10.putInt(r8, r9)
////            r10.commit()
////            java.lang.String r2 = salah.rasoulallah.com.Constants.MY_PREFS_NAME
////            android.content.SharedPreferences r2 = r0.getSharedPreferences(r2, r13)
////            android.content.SharedPreferences$Editor r2 = r2.edit()
////            r3 = r29
////            r2.putInt(r7, r3)
////            boolean r3 = r28.booleanValue()
////            if (r3 == 0) goto L_0x016c
////            r3 = r26
////            r6 = r27
////            r2.putString(r6, r3)
////        L_0x016c:
////            boolean r3 = r17.booleanValue()
////            if (r3 == 0) goto L_0x017b
////            r3 = r25
////            int r3 = r3.month
////            r6 = r21
////            r2.putInt(r6, r3)
////        L_0x017b:
////            boolean r3 = r23.booleanValue()
////            if (r3 == 0) goto L_0x0188
////            r3 = r24
////            r6 = 1
////            r2.putBoolean(r3, r6)
////            goto L_0x018a
////        L_0x0188:
////            r3 = r24
////        L_0x018a:
////            boolean r6 = r22.booleanValue()
////            if (r6 == 0) goto L_0x0195
////            r6 = 0
////            r2.putBoolean(r3, r6)
////            goto L_0x0196
////        L_0x0195:
////            r6 = 0
////        L_0x0196:
////            r2.commit()
////            boolean r2 = r19.booleanValue()
////            if (r2 == 0) goto L_0x01fe
////            r30.showStatistics()
////            boolean r2 = r17.booleanValue()
////            if (r2 == 0) goto L_0x01c0
////            int r2 = r1.getInt(r4, r6)
////            java.lang.StringBuilder r3 = new java.lang.StringBuilder
////            r3.<init>()
////            java.lang.String r4 = "إحصائيات الصلاة على النبي في الشهر : "
////            r3.append(r4)
////            r3.append(r2)
////            java.lang.String r2 = r3.toString()
////            r0.showNotificationStatictics(r2)
////        L_0x01c0:
////            boolean r2 = r23.booleanValue()
////            if (r2 == 0) goto L_0x01df
////            r2 = 0
////            int r3 = r1.getInt(r14, r2)
////            java.lang.StringBuilder r2 = new java.lang.StringBuilder
////            r2.<init>()
////            java.lang.String r4 = "إحصائيات الصلاة على النبي في الإسبوع : "
////            r2.append(r4)
////            r2.append(r3)
////            java.lang.String r2 = r2.toString()
////            r0.showNotificationStatictics(r2)
////        L_0x01df:
////            boolean r2 = r28.booleanValue()
////            if (r2 == 0) goto L_0x01fe
////            r2 = 0
////            int r1 = r1.getInt(r5, r2)
////            java.lang.StringBuilder r2 = new java.lang.StringBuilder
////            r2.<init>()
////            java.lang.String r3 = "إحصائيات الصلاة على النبي اليوم : "
////            r2.append(r3)
////            r2.append(r1)
////            java.lang.String r1 = r2.toString()
////            r0.showNotificationStatictics(r1)
////        L_0x01fe:
////            return
////        */
////            throw new UnsupportedOperationException("Method not decompiled: salah.rasoulallah.com.MEdiaService.setStatistics(boolean):void");
////        }
////
////        private void showNotificationStatictics(String str) {
////            NotificationHelper notificationHelper = new NotificationHelper(getApplicationContext(), 1);
////            Notification build = notificationHelper.getChannelNotification(str, 1, "قم بالضغط للإطلاع على الإحصائيات الخاصة بك").build();
////            build.flags = 48;
////            notificationHelper.getManager().notify(2, build);
////        }
////
////        private void showStatistics() {
////            String str;
////            SharedPreferences sharedPreferences = getSharedPreferences(Constants.MY_PREFS_NAME, 4);
////            int i = sharedPreferences.getInt("AyatSDayCount1", 0);
////            String string = sharedPreferences.getString("days_statisc1", "0");
////            if (string.equals("")) {
////                str = i + "";
////            } else {
////                str = string + "@" + i;
////            }
////            SharedPreferences.Editor edit = getSharedPreferences(Constants.MY_PREFS_NAME, 4).edit();
////            edit.putString("days_statisc1", str);
////            edit.commit();
////        }
//
//        /* access modifiers changed from: private */
//        public void playAudio() {
//            int i;
//            SharedPreferences sharedPreferences = getSharedPreferences(Constants.MY_PREFS_NAME, 4);
//            int i2 = sharedPreferences.getInt("selectedSoundIndex", 0);
//            if (i2 == 5 && (i2 = this.rand.nextInt(12) + 0) == 8) {
//                i2 = 7;
//            }
//            switch (i2) {
//                case 1:
//                    i = C0917R.raw.f149s2;
//                    break;
//                case 2:
//                    i = C0917R.raw.f150s3;
//                    break;
//                case 3:
//                    i = C0917R.raw.f151s4;
//                    break;
//                case 4:
//                    i = C0917R.raw.f152s5;
//                    break;
//                case 6:
//                    i = C0917R.raw.f153s7;
//                    break;
//                case 7:
//                    i = C0917R.raw.f154s8;
//                    break;
//                case 8:
//                    i = C0917R.raw.f155s9;
//                    break;
//                case 9:
//                    i = C0917R.raw.s10;
//                    break;
//                case 10:
//                    i = C0917R.raw.s11;
//                    break;
//                case 11:
//                    i = C0917R.raw.s12;
//                    break;
//                case 12:
//                    i = C0917R.raw.s13;
//                    break;
//                case 13:
//                    i = C0917R.raw.s14;
//                    break;
//                case 14:
//                    i = C0917R.raw.s15;
//                    break;
//                default:
//                    i = C0917R.raw.f148s1;
//                    break;
//            }
//            this.mediaPlayer = MediaPlayer.create(getApplicationContext(), i);
//            float f = ((float) (sharedPreferences.getInt("volumn", 10) * 10)) / 100.0f;
//            this.mediaPlayer.setVolume(f, f);
//            try {
//                getSharedPreferences(Constants.MY_PREFS_NAME, 4);
//                this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    public void onPrepared(MediaPlayer mediaPlayer) {
//                        mediaPlayer.start();
//                        MEdiaService.this.setStatistics(false);
//                    }
//                });
//                this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    /* JADX WARNING: Can't wrap try/catch for region: R(4:2|3|4|5) */
//                    /* JADX WARNING: Failed to process nested try/catch */
//                    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x001a */
//                    /* Code decompiled incorrectly, please refer to instructions dump. */
//                    public void onCompletion(android.media.MediaPlayer r2) {
//                    /*
//                        r1 = this;
//                        salah.rasoulallah.com.service r2 = salah.rasoulallah.com.MEdiaService.this
//                        r0 = 0
//                        r2.isFirst = r0
//                        salah.rasoulallah.com.service r2 = salah.rasoulallah.com.MEdiaService.this
//                        android.media.MediaPlayer r2 = r2.mediaPlayer
//                        if (r2 == 0) goto L_0x0021
//                        salah.rasoulallah.com.service r2 = salah.rasoulallah.com.MEdiaService.this     // Catch:{ Exception -> 0x001a }
//                        android.media.MediaPlayer r2 = r2.mediaPlayer     // Catch:{ Exception -> 0x001a }
//                        r2.reset()     // Catch:{ Exception -> 0x001a }
//                        salah.rasoulallah.com.service r2 = salah.rasoulallah.com.MEdiaService.this     // Catch:{ Exception -> 0x001a }
//                        android.media.MediaPlayer r2 = r2.mediaPlayer     // Catch:{ Exception -> 0x001a }
//                        r2.release()     // Catch:{ Exception -> 0x001a }
//                        goto L_0x0021
//                    L_0x001a:
//                        salah.rasoulallah.com.service r2 = salah.rasoulallah.com.MEdiaService.this     // Catch:{ Error -> 0x0021 }
//                        android.media.MediaPlayer r2 = r2.mediaPlayer     // Catch:{ Error -> 0x0021 }
//                        r2.release()     // Catch:{ Error -> 0x0021 }
//                    L_0x0021:
//                        salah.rasoulallah.com.service r2 = salah.rasoulallah.com.MEdiaService.this
//                        r0 = 0
//                        r2.mediaPlayer = r0
//                        return
//                    */
//                        throw new UnsupportedOperationException("Method not decompiled: salah.rasoulallah.com.MEdiaService.C09284.onCompletion(android.media.MediaPlayer):void");
//                    }
//                });
//            } catch (Exception unused) {
//            }
//        }
//
//        public void onDestroy() {
//            this.isRunning = false;
//            if (this.screenOnOffReceiver != null) {
//                getApplicationContext().unregisterReceiver(this.screenOnOffReceiver);
//            }
//            Intent intent = new Intent(getApplicationContext(), getClass());
//            intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
//            ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).set(3, 5000, PendingIntent.getService(getApplicationContext(), 1, intent, BasicMeasure.EXACTLY));
//            super.onDestroy();
//        }
//
//        public void onTaskRemoved(Intent intent) {
//            this.isRunning = false;
//            super.onTaskRemoved(intent);
//        }
//    }


    private void registerReceiver() {

        final IntentFilter theFilter = new IntentFilter();

        theFilter.addAction(Intent.ACTION_SCREEN_ON);
        theFilter.addAction(Intent.ACTION_SCREEN_OFF);
        theFilter.addAction(Intent.ACTION_USER_PRESENT);

        BroadcastReceiver screenOnOFReceiver1 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String strAction = intent.getAction();

                KeyguardManager kgMgr = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

                if (strAction.equals(Intent.ACTION_SCREEN_OFF)) {

                }
                if (strAction.equals(Intent.ACTION_SCREEN_ON)) {

                }
                if (strAction.equals(Intent.ACTION_SCREEN_OFF)) {

                }
                if (strAction.equals(Intent.ACTION_USER_PRESENT) && !kgMgr.inKeyguardRestrictedInputMode()) {
                    // DEVICE UN-LOCKED

                    Toast.makeText(context, "UNLOCKED!", Toast.LENGTH_SHORT).show();
                    int resID = getResources().getIdentifier("whistle", "raw", getPackageName());

                    MediaPlayer mediaPlayer = MediaPlayer.create(context, resID);
                    mediaPlayer.start();

                } else {
                    // DEVICE LOCKED
                }

            }
        };
        getApplicationContext().registerReceiver(screenOnOFReceiver1, theFilter);
    }

    private void hideBtn() {
        switchMaterial = findViewById(R.id.hide_app_switch);
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                PackageManager p = getPackageManager();
                ComponentName componentName = new ComponentName(HideAppActivity.this,
                        SplashActivity.class);
                if (b) {
                    // HIDE APP
                    p.setComponentEnabledSetting(componentName,
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);

                } else {
                    // SHOW APP
                    p.setComponentEnabledSetting(componentName,
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);

                }

                Toast.makeText(HideAppActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
    }


}