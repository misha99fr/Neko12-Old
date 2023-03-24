package ru.dimon6018.neko11.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.core.view.WindowCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ru.dimon6018.neko11.R;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.MenuRes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.widget.Toast;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.appcompat.widget.PopupMenu;
import android.view.MenuItem;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.color.DynamicColors;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.WindowCompat;
import com.google.android.material.elevation.SurfaceColors;
import android.view.Window;

import ru.dimon6018.neko11.BuildConfig;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.core.view.WindowCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

import android.app.UiModeManager;

import androidx.core.view.WindowInsetsCompat;
import dev.chrisbanes.insetter.Insetter;

public class NekoSettingsActivity extends AppCompatActivity {

    public static final String STATEPREF = "statepref";
    public static final String SETTINGS = "SettingsPrefs";
	
    public SharedPreferences nekoprefs;
	
    	MaterialButton opensettingsbtn;
        MaterialSwitch whiteswitch;
        MaterialSwitch linearcontrol;
		MaterialSwitch dyncolor;
		MaterialSwitch edge; 
		MaterialSwitch autowhiteswitch;
		MaterialButton accentchoose;
		
		TextView version_check;
		MaterialTextView version_num;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    nekoprefs = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
	int THEME = nekoprefs.getInt("theme", 0);
	switch(THEME) {
		case 0:
            setTheme(R.style.Theme_Neko11_Standart);
            break;
        case 1:
            setTheme(R.style.Theme_Neko11_Pink);
            break;
        case 2:
            setTheme(R.style.Theme_Neko11_Red);
            break;
        case 3:
            setTheme(R.style.Theme_Neko11_Orange);
            break;
		case 4:
            setTheme(R.style.Theme_Neko11_Green);
            break;
		case 5:
            setTheme(R.style.Theme_Neko11_Lime);
            break;
        case 6:
            setTheme(R.style.Theme_Neko11_Aqua);
            break;
        case 7:
            setTheme(R.style.Theme_Neko11_Blue);
            break;
		case 8:
            setTheme(R.style.Theme_Neko11_Dynamic);
			DynamicColors.applyToActivityIfAvailable(this);
            break;
        default:
            setTheme(R.style.Theme_Neko11_Standart);
            break;
       }
        super.onCreate(savedInstanceState);
		setContentView(R.layout.neko_settings_activity);
		
		Toolbar toolbar = findViewById(R.id.toolbarset);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		Insetter.builder()
       .padding(WindowInsetsCompat.Type.statusBars())
       .applyToView(toolbar);
	   
        opensettingsbtn = findViewById(R.id.opensettingsbtn);
        whiteswitch = findViewById(R.id.white_switch);
        linearcontrol = findViewById(R.id.linear_controls_enable);
		dyncolor = findViewById(R.id.dynamic_color);
		autowhiteswitch = findViewById(R.id.white_switch_auto);
		accentchoose = findViewById(R.id.choosetheme);
		
        version_check = findViewById(R.id.androidwarning);
		version_num = findViewById(R.id.ver_num);
				
        accentchoose.setOnClickListener(v -> showMenu(v, R.menu.neko_colors));
		
		int DARK_ENABLE = nekoprefs.getInt("darktheme", 0);
        boolean LINEAR_CONTROL = nekoprefs.getBoolean("linear_control", false);
		
        opensettingsbtn.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", this.getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        });
        whiteswitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = nekoprefs.edit();
            if (isChecked) {
                editor.putInt("darktheme", 1);
				editor.apply();
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
					UiModeManager UImanager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);	
					UImanager.setApplicationNightMode(UiModeManager.MODE_NIGHT_YES);
				} else {
					AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
				}
            } else {
                editor.putInt("darktheme", 0);
				editor.apply();
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
					UiModeManager UImanager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);		
					UImanager.setApplicationNightMode(UiModeManager.MODE_NIGHT_NO);
				} else {
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
				}
            }
        });
		autowhiteswitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = nekoprefs.edit();
            if (isChecked) {
				whiteswitch.setEnabled(false);
				whiteswitch.setChecked(false);
				editor.putInt("darktheme", 2);
				editor.apply();
				AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            } else {
				whiteswitch.setEnabled(true);
				editor.putInt("darktheme", 0);
				editor.apply();
            }
        });
		dyncolor.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = nekoprefs.edit();
            if (isChecked) {
                editor.putInt("theme", 8);
				editor.apply();
            } else {
                editor.putInt("theme", 0);
				editor.apply();
            }
        });
        linearcontrol.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = nekoprefs.edit();
            editor.putBoolean("linear_control", isChecked);
            editor.apply();
            new MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.app_name_neko)
                    .setIcon(R.drawable.ic_success)
                    .setMessage(R.string.success)
                    .setCancelable(false)
                    .setNegativeButton(android.R.string.ok, null)
                    .show();
        });
		nekoprefs = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            version_check.setVisibility(View.INVISIBLE);
        } else {
            version_check.setVisibility(View.VISIBLE);
        }
		
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            dyncolor.setEnabled(true);
        } else {
            dyncolor.setEnabled(false);
        }
		
		version_num.setText(BuildConfig.VERSION_NAME);
        linearcontrol.setChecked(LINEAR_CONTROL);
		
		if(THEME == 8) {
		    dyncolor.setChecked(true);
		} else {
			dyncolor.setChecked(false);
		}
		switch(DARK_ENABLE) {
			case 0:
			 whiteswitch.setChecked(false);
			 autowhiteswitch.setEnabled(true);
			 break;
			case 1:
			 whiteswitch.setChecked(true);
			 break;
			case 2:
			 autowhiteswitch.setChecked(true);
			 whiteswitch.setEnabled(false);
			 break;
			default:
             whiteswitch.setChecked(false);
             break;		
		}
	}
		public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
	}
	  private void showMenu(View v, @MenuRes int menuRes) {
	SharedPreferences nekoprefs = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
	int THEME = nekoprefs.getInt("theme", 0);
    PopupMenu popup = new PopupMenu(this, v);
    popup.getMenuInflater().inflate(menuRes, popup.getMenu());
    popup.setOnMenuItemClickListener(
        menuItem -> {
			SharedPreferences.Editor editor = nekoprefs.edit();
			switch (menuItem.getItemId()) {
				case R.id.pink_theme:
				  editor.putInt("theme", 1);
				  editor.apply();
				  themeChangedDialog();
				  break;
				case R.id.red_theme:
				  editor.putInt("theme", 2);
				  editor.apply(); 
				  themeChangedDialog();
				  break;
				case R.id.orange_theme:
				  editor.putInt("theme", 3);
				  editor.apply();
				  themeChangedDialog();
				  break;
				case R.id.green_theme:
				  editor.putInt("theme", 4);
				  editor.apply(); 
				  themeChangedDialog();
				  break;
				case R.id.lime_theme:
				  editor.putInt("theme", 5);
				  editor.apply(); 
				  themeChangedDialog();
				  break;
				case R.id.aqua_theme:
				  editor.putInt("theme", 6);
				  editor.apply(); 
				  themeChangedDialog();
 				 break; 
				case R.id.blue_theme:
				  editor.putInt("theme", 7);
				  editor.apply();   
				  themeChangedDialog();
				  break;
				case R.id.purple_theme:
				  editor.putInt("theme", 0);
				  editor.apply();
 				  themeChangedDialog();
				  break;
			   default:
            	  editor.putInt("theme", 0);
				  editor.apply(); 	
				  themeChangedDialog();
                  break;
			}					
          return true;
        });
    popup.show();
	  }
	  private void themeChangedDialog() {
		  new MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.success)
                    .setIcon(R.drawable.ic_success)
                    .setMessage(R.string.themechanged)
                    .setCancelable(false)
                    .setNegativeButton(android.R.string.ok, null)
                    .show(); 
	  }
}