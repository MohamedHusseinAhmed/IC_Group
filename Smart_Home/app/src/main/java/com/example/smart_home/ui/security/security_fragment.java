
package com.example.smart_home.ui.security;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.smart_home.ui.gallery.GalleryViewModel;
import com.example.smart_home.ui.home.HomeFragment;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class security_fragment extends Fragment {
    TextView  alert_txt;
    ImageView alert,emer,motion;
    public static Thread thread2;
    private GalleryViewModel galleryViewModel;
    public TextView textView;
boolean w_found = false,a_found=false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.security_fragment, container, false);
        alert_txt = root.findViewById(R.id.alert_text);
        alert = root.findViewById(R.id.alert_img);
        emer = root.findViewById(R.id.eme_img);
        motion = root.findViewById(R.id.motion_img);



        thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        sleep(1000);


                        check_lamps("S11");

                        check_lamps("S21");


                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        if (!thread2.isAlive()) ;
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
                        String state = lamp.getString("state");
                        String created_at = lamp.getString("created_at");


// Toast.makeText(getActivity(),state, Toast.LENGTH_LONG).show();

                        if (name.contains("S11")) {//Toast.makeText(getActivity(),"S11", Toast.LENGTH_LONG).show();
                            handle(state,  "Motion");

                        }
                        if (name.contains("S21")) {//Toast.makeText(getActivity(),"S11", Toast.LENGTH_LONG).show();
                            handle(state,  "Alert");
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



    public void handle(String state, String m) {


//   Toast.makeText(getActivity(), state, Toast.LENGTH_SHORT).show();
        if (state.contains("OFF")) {
            if(m=="Motion")
            {

                motion.setImageResource(R.drawable.safe);
                motion.setTag(new Boolean(false));
                a_found=false;


            }else if(m=="Alert") {

                emer.setImageResource(R.drawable.safe);
                emer.setTag(new Boolean(false));
                w_found=false;

            }

        } else if (state.contains("ON")) {


            if(m=="Motion")
            {

                motion.setImageResource(R.drawable.warn);
                motion.setTag(new Boolean(true));
                a_found=true;


            }else if(m=="Alert") {

                emer.setImageResource(R.drawable.warn);
                emer.setTag(new Boolean(true));
                w_found=true;


            }


        }


        if(a_found||w_found)
        {
            alert.setImageResource(R.drawable.warn);
            alert_txt.setText("WARNING");
            alert_txt.setTextColor(Color.parseColor("#FD0000"));

        }else
        {

            alert.setImageResource(R.drawable.safe);
            alert_txt.setText("Safe State");
            alert_txt.setTextColor(Color.parseColor("#149A14"));

        }



    }


}
