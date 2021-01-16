package com.example.smart_home;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.smart_home.Splash.SplashScreen;
import com.example.smart_home.activity.LoginActivity;
import com.example.smart_home.app.AppConfig;
import com.example.smart_home.app.AppController;
import com.example.smart_home.helper.SQLiteHandler;
import com.example.smart_home.helper.SessionManager;
import com.example.smart_home.ui.gallery.GalleryFragment;
import com.example.smart_home.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private AppBarConfiguration mAppBarConfiguration;
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    DrawerLayout drawer;
    private SQLiteHandler db;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = findViewById(R.id.nav_view);


        setNavigationViewListener();
        // navigationView.setNavigationItemSelectedListener(this);
        View header= navigationView.getHeaderView(0);

        txtName = header.findViewById(R.id.name);
        txtEmail =  header.findViewById(R.id.email);
        btnLogout =  findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
       txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

          drawer = findViewById(R.id.drawer_layout);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_view, R.id.nav_home, R.id.Lighting, R.id.camera,R.id.security)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void show_Notification(){

        Intent intent=new Intent(getApplicationContext(),HomeFragment.class);
        String CHANNEL_ID="MYCHANNEL";
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"name",NotificationManager.IMPORTANCE_LOW);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),1,intent,0);
        Notification notification=new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentText("Alerting is ON")
                .setContentTitle("Emergency")
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.sym_action_chat,"Title",pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1,notification);


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.

                Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();


        //close navigation drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
                        String created_at = lamp  .getString("created_at");

//                      Toast.makeText(getActivity(), "State is: " + state, Toast.LENGTH_LONG).show();


                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(MainActivity.this,  errorMsg, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();

                    Toast.makeText(MainActivity.this, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,
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



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}