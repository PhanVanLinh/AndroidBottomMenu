package com.toong.androidbottommenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.toong.androidbottommenu.bottommenu.BottomMenu;

public class MainActivity extends AppCompatActivity {

    private TextView tvContent;
    private BottomMenu bottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomMenu = (BottomMenu) findViewById(R.id.bottom_menu);
        tvContent = (TextView) findViewById(R.id.content);

        bottomMenu.setOnItemClickListener(new BottomMenu.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position) {
                Log.i("MainActivity", "select " + position);
            }
        });

        bottomMenu.setOnMenuChanged(new BottomMenu.OnMenuChanged() {
            @Override
            public void onMenuChanged() {
                tvContent.setText(bottomMenu.getSelectedMenuItemTitle());
            }
        });

        bottomMenu.select(2); // select page 2 as default

        handleChangPage();

        bottomMenu.showBadge(1,1);
        bottomMenu.showBadge(4,2);
    }

    /**
     * for demo purpose
     */
    private void handleChangPage() {
        findViewById(R.id.btnPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomMenu.getSelectedMenuPosition() == 0) {
                    return;
                }
                bottomMenu.select(bottomMenu.getSelectedMenuPosition() - 1);
            }
        });

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomMenu.getSelectedMenuPosition() == bottomMenu.size() - 1) {
                    return;
                }
                bottomMenu.select(bottomMenu.getSelectedMenuPosition() + 1);
            }
        });
    }
}
