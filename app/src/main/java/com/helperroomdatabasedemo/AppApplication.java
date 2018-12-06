    package com.helperroomdatabasedemo;

    import android.app.Application;
    import com.facebook.stetho.Stetho;

    public class AppApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();

            Stetho.initializeWithDefaults(this);
        }
    }
