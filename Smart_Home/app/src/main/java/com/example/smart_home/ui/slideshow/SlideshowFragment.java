package com.example.smart_home.ui.slideshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smart_home.MainActivity;
import com.example.smart_home.PlayActivity;
import com.example.smart_home.R;
import com.example.smart_home.Util;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
       // final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //   textView.setText(s);
                String ip, login, pass, port;
                ip = "192.168.1.110";
                login = "admin";
                pass = "admin";
                port = "";


                String VIFUrl = Util.getVIFString(login, pass, ip, port);
                if (!VIFUrl.isEmpty()) {
                    Intent intent = new Intent(getActivity(), PlayActivity.class);
                    intent.setData(Uri.parse(VIFUrl));
                    getActivity().startActivity(intent);

                }


            }
        });
        return root;
        }


}