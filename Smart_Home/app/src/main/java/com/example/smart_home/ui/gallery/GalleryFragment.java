package com.example.smart_home.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.smart_home.R;
import com.example.smart_home.app.AppConfig;
import com.example.smart_home.app.AppController;
import com.example.smart_home.ui.home.HomeFragment;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {
TextView motion1,alert1;
   public  static Thread thread2;
    private GalleryViewModel galleryViewModel;
    public TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
          textView = root.findViewById(R.id.textView2);
        motion1= root.findViewById(R.id.textView2);
        alert1=  root.findViewById(R.id.textView3);



          thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        sleep(1000);


                        check_lamps("S11");

                        check_lamps("S21");



                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        if(!thread2.isAlive());
       thread2.start();


        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {




            }
        });
        return root;
    }



    public void check_lamps(final String name) {
        // Tag used to cancel the request
        String tag_string_req = "lamp_state";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_GET_LAMP, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        String uid = jObj.getString("uid");

                        JSONObject lamp = jObj.getJSONObject("lamp");
                        String name = lamp.getString("name");
                        String       state = lamp.getString("state");
                        String created_at = lamp  .getString("created_at");


                        // Toast.makeText(getActivity(),state, Toast.LENGTH_LONG).show();

                        if(name.contains("S11"))
                        {//Toast.makeText(getActivity(),"S11", Toast.LENGTH_LONG).show();
                            handle(state,motion1,"Motion");

                        }
                        if(name.contains("S21"))
                        {//Toast.makeText(getActivity(),"S11", Toast.LENGTH_LONG).show();
                            handle(state,alert1,"Alert");
                        }

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        //  Toast.makeText(getActivity(),  errorMsg, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();

                    Toast.makeText(getActivity(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("password", "1");

                return params;
            }

        };

        // Adding request to request queue

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);




    }
    public  void handle(String state , Switch s)
    {

        s.setText(state);
        if(s.getText().toString().contains("OFF"))
        {
            s.setChecked(false);

        }
        else if(s.getText().toString().contains("ON")) {

            s.setChecked(true);
        }

        //  return s;
    }


    public  void handle(String state , TextView s , String m)
    {

        s.setText(m+" sensor is :"+state );

     //   Toast.makeText(getActivity(), state, Toast.LENGTH_SHORT).show();
        if(state.contains("OFF"))
        {
      //      s.setTextColor(16389894);

        }
        else if(state.contains("ON"))
        {

      //      s.setTextColor(this.getResources().getColor(R.color.red));

        }

    }


}