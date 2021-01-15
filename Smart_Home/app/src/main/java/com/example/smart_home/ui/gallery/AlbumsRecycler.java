package com.example.smart_home.ui.gallery;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.smart_home.Album;
import com.example.smart_home.AlbumsAdapter;
import com.example.smart_home.R;
import com.example.smart_home.adapter.TabAdapter;
import com.example.smart_home.adapter.UserAdapter;
import com.example.smart_home.model.User;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AlbumsRecycler extends Fragment {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<User> userList;

    private TabLayout tableLayout;
    private ViewPager viewPager;

    private AlbumsAdapter adapter1;
    private List<Album> albumList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter1 = new AlbumsAdapter(getContext(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new AlbumsRecycler.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter1);

        prepareAlbums();
        return view;
    }



    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,};

        Album a = new Album("Lamp1", 13, covers[1]);
        albumList.add(a);

        a = new Album("Lamp2", 8, covers[1]);
        albumList.add(a);

        a = new Album("Lamp3", 11, covers[2]);
        albumList.add(a);

        a = new Album("Lamp4", 12, covers[3]);
        albumList.add(a);

        a = new Album("Lamp5", 14, covers[4]);
        albumList.add(a);

        a = new Album("Lamp6", 1, covers[5]);
        albumList.add(a);

        a = new Album("Lamp7", 11, covers[6]);
        albumList.add(a);

        a = new Album("Lamp8", 14, covers[7]);
        albumList.add(a);

        a = new Album("Lamp9", 11, covers[8]);
        albumList.add(a);

        a = new Album("Lamp10", 17, covers[9]);
        albumList.add(a);


        a = new Album("Lamp11", 17, covers[9]);
        albumList.add(a);
        a = new Album("Lamp11", 17, covers[9]);
        albumList.add(a);

        a = new Album("Lamp11", 17, covers[9]);
        albumList.add(a);


        adapter1.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



}
