package rs.orapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class nwcr extends AppCompatActivity {
    int row,col,or[][]=new int[100][100];
    TableLayout quest;
    int column_name=65;
    int row_name=97;
    Button btn;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nwcr);
        quest=(TableLayout)findViewById(R.id.TableLayout);
        btn=(Button)findViewById(R.id.next);
        Bundle passed=getIntent().getExtras();
        row=passed.getInt("d1");
        col=passed.getInt("d2");
        for(int i=0;i<=row+1;i++)
        {
            final TableRow nr=new TableRow(nwcr.this);
            if(i%2==0)
                nr.setBackgroundColor(Color.GRAY);
            else
                nr.setBackgroundColor(Color.WHITE);
            for(int j=0;j<=col+1;j++)
            {
                if(j>0 && i>0 && j<=col+1 && i<=row+1){
                    final EditText nc =new EditText(nwcr.this);
                    nc.setTextColor(Color.BLUE);
                    nc.setGravity(Gravity.RIGHT);
                    nc.setWidth(200);
                    nc.setHeight(100);
                    nc.setInputType(InputType.TYPE_CLASS_NUMBER);
                    nr.addView(nc);
                }
                if(i>0 && i!=row+1 && j==0){
                    final TextView rname=new TextView(nwcr.this);
                    rname.setText(Character.toString((char) row_name));
                    rname.setGravity(Gravity.CENTER);
                    row_name+=1;
                    nr.addView(rname);
                }
                if(i==0 && j>0 && j!=col+1){
                    final TextView cname=new TextView(nwcr.this);
                    cname.setText(Character.toString((char)column_name));
                    cname.setGravity(Gravity.CENTER);
                    column_name+=1;
                    nr.addView(cname);
                }
                if(i==0 && j==col+1)
                {
                    final TextView supply=new TextView(nwcr.this);
                    supply.setText("Supply");
                    nr.addView(supply);
                }
                if(i==row+1 && j==0) {
                    final TextView demand = new TextView(nwcr.this);
                    demand.setText("Demand");
                    nr.addView(demand);
                }
                if(i==0 && j==0)
                {
                    final TextView demand=new TextView(nwcr.this);
                    demand.setText("Dem/Sup");
                    demand.setGravity(Gravity.CENTER);
                    nr.addView(demand);
                }

            }
            quest.addView(nr);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0) {
                    for (int i = 1; i <= row + 1; i++) {
                        final TableRow nr = (TableRow) quest.getChildAt(i);
                        //final TableRow nr_new= new TableRow(nwcr.this);

                        if(i%2==0)
                            nr.setBackgroundColor(Color.GRAY);
                        else
                            nr.setBackgroundColor(Color.WHITE);


                        for (int j = 1; j <= col + 1; j++) {
                            final EditText txt = (EditText) nr.getChildAt(j);
                            int temp = Integer.parseInt(txt.getText().toString());
                            or[i][j] = temp;
                            //final TextView txtv=new TextView(nwcr.this);
                            txt.setEnabled(false);
                            txt.setFocusable(false);
                        }

                    }
                }
            }
        });
    }
}