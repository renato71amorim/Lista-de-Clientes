package com.app.listadeclientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.listadeclientes.dao.ClienteSqliteOpenHelper;
import com.app.listadeclientes.model.Cliente;

public class EdtClienteActivity extends AppCompatActivity {

    // Declarando propriedades
    private EditText txtNomeEdt, txtEmailEdt, txtTelefoneEdt;
    private Button btnAtualizar, btnExcluir, btnCancelarEdt;
    private ClienteSqliteOpenHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt_cliente);

        /*
         * Vinculando chamada da activity com a lista de clientes
         * Criando instância da classe ClienteSqliteOpenHelper
         * Criando instância da classe Cliente com propriedade bd e o método pesquisar
        */
        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID", 0);
        bd = new ClienteSqliteOpenHelper(this);
        Cliente cliente = bd.pesquisar(id);

        // Vinculando propriedades com os componentes da activity
        txtNomeEdt = findViewById(R.id.txtNomeEdtId);
        txtEmailEdt = findViewById(R.id.txtEmailEdtId);
        txtTelefoneEdt = findViewById(R.id.txtTelefoneEdtId);
        btnAtualizar = findViewById(R.id.btnAtualizarId);
        btnExcluir = findViewById(R.id.btnExcluirId);
        btnCancelarEdt = findViewById(R.id.btnCancelarEdtId);

        // Evento do botão atualizar
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNomeEdt.getText().length()==0 || txtEmailEdt.getText().length()==0 || txtTelefoneEdt.getText().length()==0){
                    Toast.makeText(getApplication(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }else{
                    Cliente c = new Cliente();
                    c.setId(id);
                    c.setNome(txtNomeEdt.getText().toString());
                    c.setEmail(txtEmailEdt.getText().toString());
                    c.setTelefone(txtTelefoneEdt.getText().toString());
                    bd.atualizar(c);
                    limparCamposEdt();
                    Toast.makeText(getApplication(), "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Evento do botão excluir
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtNomeEdt.getText().length()==0 || txtEmailEdt.getText().length()==0 || txtTelefoneEdt.getText().length()==0){
                    Toast.makeText(getApplication(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }else{
                    Cliente c = new Cliente();
                    c.setId(id);
                    bd.excluir(c);
                    limparCamposEdt();
                    Toast.makeText(getApplication(), "Dados excluídos com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Evento o botão cancelar
        btnCancelarEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCamposEdt();
            }
        });
    }

    // Método para limpar campos
    private void limparCamposEdt(){
        txtNomeEdt.setText("");
        txtEmailEdt.setText("");
        txtTelefoneEdt.setText("");
    }
}
