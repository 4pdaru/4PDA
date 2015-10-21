package ru4pda.news;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.SilentLogger;

/**
 * Created by asavinova on 18/10/15.
 */
public class App extends Application {

	private static final Logger L = LoggerFactory.getLogger(App.class);

	@Override
	public void onCreate() {
		super.onCreate();
		tuneLogs();
		L.debug("Start application");
	}

	private void tuneLogs() {
		// Не отправляем аналитику, если сборка девелоперская
		boolean isCrashlyticsDisabled = BuildConfig.VERSION_CODE == 1;

		CrashlyticsCore crashlyticsCore = new CrashlyticsCore.Builder()
				.disabled(isCrashlyticsDisabled)
				.build();

		Crashlytics crashlytics = new Crashlytics.Builder()
				.core(crashlyticsCore)
				.build();

		Fabric fabric = new Fabric.Builder(this)
				.kits(crashlytics)
				.logger(new SilentLogger())
				.build();
		Fabric.with(fabric);
	}

}
