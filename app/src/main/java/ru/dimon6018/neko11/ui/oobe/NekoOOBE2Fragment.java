package ru.dimon6018.neko11.ui.oobe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import ru.dimon6018.neko11.R;

import ru.dimon6018.neko11.ui.oobe.NekoOOBE1Fragment;

public class NekoOOBE2Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.neko_oobe_frame2, container, false);

        MaterialButton back = view.findViewById(R.id.oobe_back_2);
        MaterialButton next = view.findViewById(R.id.next_oobe_2);
        back.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.container_oobe, NekoOOBE1Fragment.class, null)
                    .commit();
        });
        return view;
    }
}