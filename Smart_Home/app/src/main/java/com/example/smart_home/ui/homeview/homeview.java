package com.example.smart_home.ui.homeview;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.smart_home.Album;
import com.example.smart_home.AlbumsAdapter;
import com.example.smart_home.R;
import com.example.smart_home.adapter.TabAdapter;
import com.example.smart_home.app.AppConfig;
import com.example.smart_home.app.AppController;
import com.example.smart_home.ui.home.HomeFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class homeview extends Fragment {
    TextView motion1,alert1;
    private TabAdapter adapter;
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter1;
    private List<Album> albumList;
    public  static Thread thread2;
    CardView card;

    public TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_view, container, false);



        return view;
    }

    /**
     * Adding few albums for testing
     */



}