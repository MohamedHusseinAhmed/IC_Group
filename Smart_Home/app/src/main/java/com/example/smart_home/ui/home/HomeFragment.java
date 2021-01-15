package com.example.smart_home.ui.home;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.smart_home.MainActivity;
import com.example.smart_home.R;
import com.example.smart_home.Sets;
import com.example.smart_home.activity.LoginActivity;
import com.example.smart_home.app.AppConfig;
import com.example.smart_home.app.AppController;
import com.example.smart_home.ui.gallery.GalleryFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.smart_home.ui.gallery.GalleryFragment.thread2;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
      Switch s1,s2,s3;

  public static   Thread thread1;
    TextView et;
    @SuppressLint("WrongViewCast")
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
   //     final TextView textView = root.findViewById(R.id.text_home);


        final Button b1 = root.findViewById(R.id.button2);
        final Button on = root.findViewById(R.id.on);
        final Button off = root.findViewById(R.id.off);
         s1 = root.findViewById(R.id.switch1);
        final Button b3 = root.findViewById(R.id.button4);
        final Button b2 = root.findViewById(R.id.button3);
         s2 = root.findViewById(R.id.switch2);
         s3 = root.findViewById(R.id.switch3);
       // check_lamps("lamp1");


        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_lamps("lamp1","ON");
                set_lamps("lamp2","ON");
                set_lamps("lamp3","ON");
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_lamps("lamp1","OFF");
                set_lamps("lamp2","OFF");
                set_lamps("lamp3","OFF");
            }
        });





          thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        sleep(1000);

                        check_lamps("lamp1");
                        check_lamps("lamp2");
                        check_lamps("lamp3");

                        check_lamps("S11");

                        check_lamps("S21");



                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

          if(!thread1.isAlive());
          thread1.start();

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!s1.isChecked()) {

                    s1.setText("OFF");
                }
                else {

                    s1.setText("ON");
                }
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!s2.isChecked()) {

                    s2.setText("OFF");
                }
                else {

                    s2.setText("ON");
                }
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!s3.isChecked()) {

                    s3.setText("OFF");
                }
                else {

                    s3.setText("ON");
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!s1.isChecked()) {
                    s1.setChecked(true);
                    s1.setText("ON");
                    set_lamps("lamp1","ON");

                }
                else {
                    s1.setChecked(false);
                    s1.setText("OFF");
                    set_lamps("lamp1","OFF");

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!s2.isChecked()) {
                    s2.setChecked(true);
                    s2.setText("ON");
                    set_lamps("lamp2","ON");
                }
                else {
                    s2.setChecked(false);
                    s2.setText("OFF");
                    set_lamps("lamp2","OFF");
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!s3.isChecked()) {
                    s3.setChecked(true);
                    s3.setText("ON");
                    set_lamps("lamp3","ON");
                }
                else {
                    s3.setChecked(false);
                    s3.setText("OFF");
                    set_lamps("lamp3","OFF");
                }
            }
        });

        check_lamps("lamp1");

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                s1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!s1.isChecked()) {

                            s1.setText("OFF");
                        }
                        else {

                            s1.setText("ON");
                        }
                    }
                });

                s2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!s2.isChecked()) {

                            s2.setText("OFF");
                        }
                        else {

                            s2.setText("ON");
                        }
                    }
                });
                s3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!s3.isChecked()) {

                            s3.setText("OFF");
                        }
                        else {

                            s3.setText("ON");
                        }
                    }
                });
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!s1.isChecked()) {
                            s1.setChecked(true);
                            s1.setText("ON");
                        }
                        else {
                            s1.setChecked(false);
                            s1.setText("OFF");
                        }
                    }
                });
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!s2.isChecked()) {
                            s2.setChecked(true);
                            s2.setText("ON");
                        }
                        else {
                            s2.setChecked(false);
                            s2.setText("OFF");
                        }
                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!s3.isChecked()) {
                            s3.setChecked(true);
                            s3.setText("ON");
                        }
                        else {
                            s3.setChecked(false);
                            s3.setText("OFF");
                        }
                    }
                });
                //textView.setText(s);
            }
        });
        return root;
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

        if(state.contains("OFF"))
        {

        }
        else if(state.contains("ON"))
        {



        }

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
                        if(name.contains("lamp1")){
                       handle(state,s1);

                        } if(name.contains("lamp2"))
                        {
                            handle(state,s2);
                                                }
                        if(name.contains("lamp3"))
                        {
                            handle(state,s3);
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

    public void set_lamps(final String name,String state) {
        // Tag used to cancel the request
        String tag_string_req = "lamp_state";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_SET_LAMP, new Response.Listener<String>() {

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



                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                          Toast.makeText(getActivity(),  errorMsg, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();

                  //  Toast.makeText(getActivity(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();

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
                params.put("state", state);

                return params;
            }

        };

        // Adding request to request queue

        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);




    }



}