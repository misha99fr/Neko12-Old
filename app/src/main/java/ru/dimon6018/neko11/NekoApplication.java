package ru.dimon6018.neko11;

import android.os.Build;
import android.app.Application;
import android.util.Log;
import android.content.Context;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.elevation.SurfaceColors;

public class NekoApplication extends Application {

	public void applyDynamicColors(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
		DynamicColors.applyToActivitiesIfAvailable(this, R.style.Theme_Neko11_Dynamic);
		} else {
			Log.e("Neko","Android version >= android 12. I cant use dynamic color");
		}
	}
}