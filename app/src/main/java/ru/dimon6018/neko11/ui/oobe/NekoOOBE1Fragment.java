package ru.dimon6018.neko11.ui.oobe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;

import ru.dimon6018.neko11.ui.fragments.NekoLand;
import static ru.dimon6018.neko11.ui.activities.NekoSettingsActivity.SETTINGS;
import ru.dimon6018.neko11.ui.oobe.NekoOOBE2Fragment;
import static ru.dimon6018.neko11.ui.activities.NekoSettingsActivity.STATEPREF;
import ru.dimon6018.neko11.R;
import ru.dimon6018.neko11.NekoGeneralActivity;

public class NekoOOBE1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Help", "Start Help Fragment");
        View view = inflater.inflate(R.layout.neko_oobe_frame1, container, false);

        MaterialButton stop = view.findViewById(R.id.stop_oobe);
        MaterialButton next = view.findViewById(R.id.next_oobe);
        stop.setOnClickListener(v -> {
			SharedPreferences nekoprefs = requireActivity().getSharedPreferences(SETTINGS, Context.MODE_PRIVATE);
			SharedPreferences state = requireActivity().getSharedPreferences(STATEPREF, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = state.edit();
			SharedPreferences.Editor editor_N = nekoprefs.edit();
            editor.putInt("state", 1);
			editor_N.putInt("theme", 0);
            editor.apply();
			editor_N.apply();
			startActivity(new Intent(getContext(), NekoGeneralActivity.class)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK));;	
        });
		next.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.container, NekoOOBE2Fragment.class, null)
                    .commit();
        });
        return view;
    }
}