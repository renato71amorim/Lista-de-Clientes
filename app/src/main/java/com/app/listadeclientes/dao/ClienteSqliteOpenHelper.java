package com.app.listadeclientes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.listadeclientes.model.Cliente;

import java.util.ArrayList;

public class ClienteSqliteOpenHelper extends SQLiteOpenHelper {

    // Constantes para conexão com o banco de dados
    private static final int DB_VERSION = 1;
    private static final String DB_CLIENTES = "dbclientes";
    private static final String TB_CLIENTES = "tbclientes";
    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String TELEFONE = "telefone";
    private static final String[] COLUNAS = {ID, NOME, EMAIL, TELEFONE};

    // Construtor com parâmetros da conexão
    public ClienteSqliteOpenHelper(Context context) {
        super(context, DB_CLIENTES, null, DB_VERSION);
    }

    /*
     * Sobrescrevendo os métodos da classe  SQLiteOpenHelper
     * OnCreate para criação do banco de dados
     * OnUpgrade para atualização do banoo de dados
    */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tbclientes ("+
                "id     integer  primary key autoincrement,"+
                "nome text,"+
                "email  text,"+
                "telefone text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tbclientes");
        this.onCreate(db);
    }

    // Método salvar dados
    public void salvar(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, cliente.getNome());
        values.put(EMAIL, cliente.getEmail());
        values.put(TELEFONE, cliente.getTelefone());

        db.insert(TB_CLIENTES, null, values);
        db.close();
    }

    // Fornecendo acesso de leitura no banco de  dados
    private Cliente cursorToCliente(Cursor cursor) {
        Cliente cliente = new Cliente();
        cliente.setId(Integer.parseInt(cursor.getString(0)));
        cliente.setNome(cursor.getString(1));
        cliente.setEmail(cursor.getString(2));
        cliente.setTelefone(cursor.getString(3));
        return cliente;
    }

    // Método pesquisar por id
    public Cliente pesquisar(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =   db.query(TB_CLIENTES,
                COLUNAS,
                " id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Cliente cliente = cursorToCliente(cursor);
            return cliente;
        }
    }

    // Método pesquisar todos
    public ArrayList<Cliente> pesquisarTodos() {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

        String query = "select  * from " + TB_CLIENTES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Cliente cliente = cursorToCliente(cursor);
                listaClientes.add(cliente);
            } while (cursor.moveToNext());
        }
        return listaClientes;
    }

    // Método atualizar dados
    public int atualizar(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, cliente.getNome());
        values.put(EMAIL, cliente.getEmail());
        values.put(TELEFONE, cliente.getTelefone());

        int cli = db.update(TB_CLIENTES,
                values,
                ID+" = ?",
                new String[] { String.valueOf(cliente.getId()) });

        db.close();
        return cli;
    }


    // Método excluir dados
    public int excluir(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        int cli = db.delete(TB_CLIENTES,
                ID + " = ?",
                new String[]{String.valueOf(cliente.getId())});

        db.close();
        return cli;
    }
}
