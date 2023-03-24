package ru.dimon6018.neko11;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;

import ru.dimon6018.neko11.ui.fragments.NekoLand;

public class NekoHelpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Help", "Start Help Fragment");
        View view = inflater.inflate(R.layout.fragment_neko_help, container, false);

        MaterialButton returnbtn = view.findViewById(R.id.returnbtn);

        returnbtn.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.container, NekoLand.class, null)
                    .commit();
        });
        return view;
    }
}