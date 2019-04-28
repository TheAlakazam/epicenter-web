package com.example.epicentre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class information extends AppCompatActivity {
    TextView InfoHead,InfoBody;
    ImageView InfoImage;
    int pos;
    ArrayList<Data> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Bundle bundle=getIntent().getExtras();
        pos=bundle.getInt("info");
        InfoHead=findViewById(R.id.InfoHead);
        InfoBody=findViewById(R.id.InfoBody);
        InfoImage=findViewById(R.id.InfoImage);

        datas= new Items(this).Populate();

        InfoHead.setText(datas.get(pos).getHeading());
        InfoBody.setText(datas.get(pos).getBody());
        InfoImage.setImageBitmap(datas.get(pos).getImage());
    }
}
