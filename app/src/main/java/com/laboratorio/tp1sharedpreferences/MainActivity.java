package com.laboratorio.tp1sharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.laboratorio.tp1sharedpreferences.Model.Usuario;
import com.laboratorio.tp1sharedpreferences.Registrar.RegistrarActivity;
import com.laboratorio.tp1sharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                vm.login(mail, password);

            }
        });

        vm.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Toast.makeText(MainActivity.this, "Login Exitoso!! " + usuario.getNombre(), Toast.LENGTH_SHORT).show();
                // Redirigir a la registrar para modificar perfil
                Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                intent.putExtra("dni", usuario.getDni());
                intent.putExtra("nombre", usuario.getNombre());
                intent.putExtra("apellido", usuario.getApellido());
                intent.putExtra("mail", usuario.getMail());
                intent.putExtra("password", usuario.getPassword());

                startActivity(intent);
                finish(); // Cierra la actividad principal si no se desea regresar
            }
        });

        vm.getMError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });



        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(i);
            }
        });


    }
}