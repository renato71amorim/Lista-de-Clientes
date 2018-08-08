package com.app.listadeclientes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.listadeclientes.dao.ClienteSqliteOpenHelper;
import com.app.listadeclientes.model.Cliente;

public class AddClienteActivity extends AppCompatActivity {

    // Declarando propriedades
    private EditText txtNome, txtEmail, txtTelefone;
    private Button btnSalvar, btnCancelar;
    private ClienteSqliteOpenHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        // Criando uma instância da classe ClienteSqliteOpenHelper
        bd = new ClienteSqliteOpenHelper(this);

        // Vinculando propriedades com os componentes da activity
        txtNome = findViewById(R.id.txtNomeId);
        txtEmail = findViewById(R.id.txtEmailId);
        txtTelefone = findViewById(R.id.txtTelefoneId);
        btnSalvar = findViewById(R.id.btnSalvarId);
        btnCancelar = findViewById(R.id.btnCancelarId);

        // Evento do botão salvar
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validando campos não preenchidos
                if(txtNome.getText().length()==0 || txtEmail.getText().length()==0 || txtTelefone.getText().length()==0){
                    Toast.makeText(getApplication(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }else{
                    Cliente c = new Cliente();
                    c.setNome(txtNome.getText().toString());
                    c.setEmail(txtEmail.getText().toString());
                    c.setTelefone(txtTelefone.getText().toString());

                    bd.salvar(c);
                    limparCampos();
                    Toast.makeText(getApplication(), "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Evento do botão cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
            }
        });
    }

    // Método limpar campos
    private void limparCampos(){
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
    }
}
