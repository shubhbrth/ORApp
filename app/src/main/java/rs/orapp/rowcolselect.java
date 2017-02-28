package rs.orapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class rowcolselect extends AppCompatActivity {
    Button Submit;
    EditText row,col;
    int nrow,ncol;
    String d1,d2;
    Bundle data=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rowcolselect);
        row=(EditText)findViewById(R.id.nrow);
        col=(EditText)findViewById(R.id.ncol);
        Submit=(Button)findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nrow=Integer.parseInt(row.getText().toString());
                ncol=Integer.parseInt(col.getText().toString());
                Intent i=new Intent(rowcolselect.this, nwcr.class);
                data.putInt("d1",nrow);
                data.putInt("d2",ncol);
                i.putExtras(data);
                startActivity(i);
            }
        });
    }
}
