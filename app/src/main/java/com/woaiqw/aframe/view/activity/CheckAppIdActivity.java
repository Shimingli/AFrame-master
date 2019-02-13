package com.woaiqw.aframe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.woaiqw.aframe.BuildConfig;
import com.woaiqw.aframe.R;

import org.json.JSONObject;



/**
 * <p>
 *
 * </p>
 *
 * @author shiming
 * @version v1.0
 * @since 2019/2/13 10:31
 */
public class CheckAppIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_app_id);

        String data = URLConnectionTem.getHttp().getRequset("api/switch?appId=" + BuildConfig.AppIdConfig);
        System.out.println("data="+data);
        try {
            JSONObject jsonData = new JSONObject(data);
            String returnCode = jsonData.getString("ReturnCode");
            JSONObject rootData = new JSONObject(returnCode);
            if (!rootData.getString("Code").equals("0")) {
                Toast.makeText(CheckAppIdActivity.this, rootData.getString("Message"), Toast.LENGTH_LONG).show();
            } else {
                String contentData = jsonData.getString("Content");
                JSONObject contentDataChild = new JSONObject(contentData);
                if (contentDataChild.getString("Code").equals("0")) {
                    Intent intent = new Intent(CheckAppIdActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (contentDataChild.getString("Code").equals("1")) {
                    Intent intent = new Intent(CheckAppIdActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else if (contentDataChild.getString("Code").equals("2")) {
                    Intent intent = new Intent(CheckAppIdActivity.this, MWeb.class);
                    intent.putExtra("url", contentDataChild.getString("Url"));
                    startActivity(intent);
                    finish();
                }
            }
        } catch (Exception ex) {

        }

    }
}

