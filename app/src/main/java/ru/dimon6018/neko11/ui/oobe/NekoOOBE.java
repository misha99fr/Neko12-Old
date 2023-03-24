package ru.dimon6018.neko11.ui.oobe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ru.dimon6018.neko11.R;
import com.google.android.material.elevation.SurfaceColors;
import androidx.core.view.WindowCompat;
import android.view.Window;
import androidx.core.view.WindowInsetsCompat;
import dev.chrisbanes.insetter.Insetter;

public class NekoOOBE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
				
		setContentView(R.layout.neko_oobe);
		
        Toolbar toolbar = findViewById(R.id.toolbaroobe);
        setSupportActionBar(toolbar);
		
		WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		Insetter.builder()
       .padding(WindowInsetsCompat.Type.statusBars())
       .applyToView(toolbar);
	   
		getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.container_oobe, NekoOOBE1Fragment.class, null)
                .commit();
    } 
}
