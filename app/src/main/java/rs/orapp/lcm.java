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
import android.widget.Toast;

public class lcm extends AppCompatActivity {
    //lcm temp=new lcm();
    lcm[] arr_cells = new lcm[10];

    //temp object to swap
    lcm temp;
    String sup, dem;
    int row, col, or[][] = new int[100][100];
    TableLayout quest;
    int column_name = 65;
    int row_name = 97;
    Button btn;
    int count = 0;

    //object size
    private int value, index_i, index_j;
    int obj_n = 0;

    public lcm(int a, int b, int c) {
        this.value = a;
        this.index_i = b;
        this.index_j = c;
    }

    public lcm() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //for (int z=0;z<10;z++)
        //  arr_cells[z]=new lcm();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcm);
        quest = (TableLayout) findViewById(R.id.TableLayout);
        btn = (Button) findViewById(R.id.next);
        Bundle passed = getIntent().getExtras();
        row = passed.getInt("d1");
        col = passed.getInt("d2");
        for (int i = 0; i <= row + 1; i++) {
            final TableRow nr = new TableRow(lcm.this);
            if (i % 2 == 0)
                nr.setBackgroundColor(Color.GRAY);
            else
                nr.setBackgroundColor(Color.WHITE);
            for (int j = 0; j <= col + 1; j++) {
                if (j > 0 && i > 0 && j <= col + 1 && i <= row + 1) {
                    final EditText nc = new EditText(lcm.this);
                    nc.setTextColor(Color.BLUE);
                    nc.setGravity(Gravity.RIGHT);
                    nc.setWidth(200);
                    nc.setHeight(100);
                    nc.setInputType(InputType.TYPE_CLASS_NUMBER);
                    nr.addView(nc);
                }
                if (i > 0 && i != row + 1 && j == 0) {
                    final TextView rname = new TextView(lcm.this);
                    rname.setText(Character.toString((char) row_name));
                    rname.setGravity(Gravity.CENTER);
                    row_name += 1;
                    nr.addView(rname);
                }
                if (i == 0 && j > 0 && j != col + 1) {
                    final TextView cname = new TextView(lcm.this);
                    cname.setText(Character.toString((char) column_name));
                    cname.setGravity(Gravity.CENTER);
                    column_name += 1;
                    nr.addView(cname);
                }
                if (i == 0 && j == col + 1) {
                    final TextView supply = new TextView(lcm.this);
                    supply.setText("Supply");
                    nr.addView(supply);
                }
                if (i == row + 1 && j == 0) {
                    final TextView demand = new TextView(lcm.this);
                    demand.setText("Demand");
                    nr.addView(demand);
                }
                if (i == 0 && j == 0) {
                    final TextView demand = new TextView(lcm.this);
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
                try {
                    if (count == 0) {
                        for (int i = 1; i <= row + 1; i++) {
                            final TableRow nr = (TableRow) quest.getChildAt(i);
                            //final TableRow nr_new= new TableRow(lcm.this);

                            if (i % 2 == 0)
                                nr.setBackgroundColor(Color.GRAY);
                            else
                                nr.setBackgroundColor(Color.WHITE);


                            for (int j = 1; j <= col + 1; j++) {
                                final EditText txt = (EditText) nr.getChildAt(j);
                                int temp = Integer.parseInt(txt.getText().toString());
                                or[i][j] = temp;
                                //final TextView txtv=new TextView(lcm.this);
                                txt.setEnabled(false);
                                txt.setFocusable(false);
                            }

                        }
                        count++;
                    } else {

                        //store values in 1d array.

                        for (int i = 1, k = 0; i < row + 1; i++) {
                            for (int j = 1; j < col + 1; j++) {
                                /*arr_cells[k].value=or[i][j];
                                arr_cells[k].index_i=i;
                                arr_cells[k].index_j=j;*/
                                arr_cells[k] = new lcm(or[i][j], i, j);
                                k++;
                                obj_n++;
                            }
                        }

                        //Sorting the above array.

                        for (int i = 1; i < obj_n; i++) {
                            for (int j = i; j > 0; j--) {
                                if (arr_cells[j].value < arr_cells[j - 1].value) {
                                    temp = arr_cells[j];
                                    arr_cells[j] = arr_cells[j - 1];
                                    arr_cells[j - 1] = temp;
                                }
                            }
                        }


                        lcm temp;
                        // lcm working.
                        for (int k = 0; k < obj_n; k++) {
                            //int i = arr_cells[k].index_i;
                            //int j = arr_cells[k].index_j;
                            final TableRow nr = (TableRow) quest.getChildAt(arr_cells[k].index_i);


                            final EditText txt = (EditText) nr.getChildAt(arr_cells[k].index_j);
                            if (or[arr_cells[k].index_i][col + 1] > 0 && or[row + 1][arr_cells[k].index_j] > 0) {
                                //final EditText txt = (EditText) nr.getChildAt(arr_cells[k].index_j);
                                //final EditText txt = (EditText) nr.getChildAt(arr_cells[k].index_j);
                                final TableRow nrlast = (TableRow) quest.getChildAt(row + 1);
                                final EditText txtdemand = (EditText) nrlast.getChildAt(arr_cells[k].index_j);
                                final EditText txtsupply = (EditText) nr.getChildAt(col + 1);
                                if (or[arr_cells[k].index_i][col + 1] < or[row + 1][arr_cells[k].index_j]) {

                                    or[row + 1][arr_cells[k].index_j] -= or[arr_cells[k].index_i][col + 1];
                                    String val = Integer.toString(or[arr_cells[k].index_i][col + 1]);
                                    or[arr_cells[k].index_i][col + arr_cells[k].index_j] = 0;
                                    dem = Integer.toString(or[row + 1][arr_cells[k].index_j]);
                                    sup = Integer.toString(or[arr_cells[k].index_i][col + 1]);
                                    //txt.append(val, txt.length() + 1, val.length() + 1);
                                    txt.setInputType(InputType.TYPE_CLASS_TEXT);
                                    txtdemand.setInputType(InputType.TYPE_CLASS_TEXT);
                                    txtsupply.setInputType(InputType.TYPE_CLASS_TEXT);
                                    txt.append(" (" + val + ")");
                                    txtdemand.append(" /" + dem);
                                    txtsupply.append(" /" + sup);
                                } else {
                                    or[arr_cells[k].index_i][col + 1] -= or[row + 1][arr_cells[k].index_j];
                                    String val = Integer.toString(or[row + 1][arr_cells[k].index_j]);
                                    or[row + 1][arr_cells[k].index_j] = 0;
                                    dem = Integer.toString(or[row + 1][arr_cells[k].index_j]);
                                    sup = Integer.toString(or[arr_cells[k].index_i][col + 1]);
                                    //txt.append(val, txt.length() + 1, val.length() + 1);
                                    txt.setInputType(InputType.TYPE_CLASS_TEXT);
                                    txtdemand.setInputType(InputType.TYPE_CLASS_TEXT);
                                    txtsupply.setInputType(InputType.TYPE_CLASS_TEXT);
                                    txt.append("(" + val + ")");
                                    txtdemand.append(" /" + dem);
                                    txtsupply.append(" /" + sup);
                                }
                            }

                        }
                    }
                } catch (Exception ee) {
                    Toast.makeText(lcm.this, "Please fill the table properly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
